package net.xdclass.order_server.fallback;

import net.xdclass.order_server.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * @author chengcheng123
 * @date 2021/6/3 23:33
 */
@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public String findById(int id) {
        System.out.println("feign 调用product-service findById 异常");
        return null;
    }
}
