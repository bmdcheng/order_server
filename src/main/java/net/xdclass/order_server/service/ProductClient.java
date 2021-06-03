package net.xdclass.order_server.service;

import net.xdclass.order_server.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chengcheng123
 * @date 2021/6/2 23:30
 */
@FeignClient(value = "product-service",fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/api/v1/product/findById")
    String findById(@RequestParam("id")int id);

}
