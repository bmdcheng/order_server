package net.xdclass.order_server.service;

import net.xdclass.order_server.domain.ProductOrder;

/**
 * @author chengcheng123
 * @date 2021/6/2 22:04
 */
public interface ProductOrderService {

    /**
     * 下单接口
     * @param userId
     * @param productId
     * @return
     */
    ProductOrder save(int userId,int productId);
}
