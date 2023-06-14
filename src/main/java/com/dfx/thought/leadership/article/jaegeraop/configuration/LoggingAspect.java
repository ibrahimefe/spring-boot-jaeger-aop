package com.dfx.thought.leadership.article.jaegeraop.configuration;

import com.dfx.thought.leadership.article.jaegeraop.data.constant.Constant;
import com.dfx.thought.leadership.article.jaegeraop.service.UtilHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final Tracer tracer;

    @Around("execution(* com.dfx.thought.leadership.article.jaegeraop.feign.OrderClient.*(..))")
    public Object logClientCalledMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        Span activeSpan = null;
        try {
            tracer.activateSpan(tracer.buildSpan(joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName()).asChildOf(tracer.activeSpan()).start());
            activeSpan = tracer.activeSpan();
            activeSpan.setTag(Constant.START_TIME, UtilHelper.getDateNow());
            activeSpan.setTag(Constant.CLIENT_REQUEST, getParameters(joinPoint.getArgs()));
            result = joinPoint.proceed();
            activeSpan.setTag(Constant.END_TIME, UtilHelper.getDateNow());
            activeSpan.setTag(Constant.CLIENT_STATUS_CODE, ((Response) result).status());
            activeSpan.setTag(Constant.CLIENT_URL, ((Response) result).request().url());
            activeSpan.setTag(Constant.CLIENT_RESPONSE, getResponseBody(((Response) result)));
            activeSpan.log(getResponseBody(((Response) result)));
        } catch (Exception exception) {
            if(activeSpan != null) activeSpan.setTag(Constant.CLIENT_ERR_DETAIL, exception.getMessage());
            throw exception;
        } finally {
            if(activeSpan != null) activeSpan.finish();
        }
        return result;
    }

    private static String getParameters(Object[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(args));
        return jsonNode.toString();
    }

    private static String getResponseBody(Response feignResponse) {
        String responseBody;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            StreamUtils.copy(feignResponse.body().asInputStream(), byteArrayOutputStream);
            responseBody = byteArrayOutputStream.toString();
        } catch (IOException ioException) {
            throw new GenericException(ioException.getMessage());
        }
        return responseBody;
    }
}
