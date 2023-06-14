package com.dfx.thought.leadership.article.jaegeraop.configuration.filter;

import com.dfx.thought.leadership.article.jaegeraop.data.constant.Constant;
import com.dfx.thought.leadership.article.jaegeraop.service.UtilHelper;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@RequiredArgsConstructor
public class IncomingFilter extends OncePerRequestFilter {

    private final Tracer tracer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Span span = tracer.activeSpan();
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);
        String requestUri = new ServletServerHttpRequest(request).getURI().toString();
        span.setTag(Constant.URI, requestUri);
        span.setTag(Constant.START_TIME, UtilHelper.getDateNow());
        filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
        String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        span.setTag(Constant.RESULT, responseBody);
        span.log(responseBody);
        contentCachingResponseWrapper.copyBodyToResponse();
        span.setTag(Constant.PARAMETERS, getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding()));
        span.setTag(Constant.END_TIME, UtilHelper.getDateNow());
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException exception) {
            log.error(exception.getMessage());
        }
        return "";
    }
}
