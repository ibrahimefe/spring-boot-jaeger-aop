package com.dfx.thought.leadership.article.jaegeraop.feign;

import com.dfx.thought.leadership.article.jaegeraop.data.model.Order;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${order.feign.name}", url = "${order.feign.url}", path = "/order")
public interface OrderClient {

    @PostMapping(path = "/saveOrder")
    Response saveOrder(@RequestBody Order order);
}
