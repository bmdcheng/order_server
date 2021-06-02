package net.xdclass.order_server.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chengcheng123
 * @date 2021/6/2 23:30
 */
@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/api/v1/product/findById")
    String findById(@RequestParam("id")int id);

}
