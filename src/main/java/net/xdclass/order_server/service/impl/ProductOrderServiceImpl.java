package net.xdclass.order_server.service.impl;

import com.alibaba.fastjson.JSONObject;
import net.xdclass.order_server.domain.ProductOrder;
import net.xdclass.order_server.service.ProductClient;
import net.xdclass.order_server.service.ProductOrderService;
import net.xdclass.order_server.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author chengcheng123
 * @date 2021/6/2 22:05
 */
@Service
public class ProductOrderServiceImpl  implements ProductOrderService {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @Override
    public ProductOrder save(int userId, int productId) {
        //Object obj=restTemplate.getForObject("http://product-service/api/v1/product/findById?id="+productId,Object.class);
        //System.out.println("Data-From-ribbon"+obj);

        logger.info("save - order ");

        String str = productClient.findById(productId);

        //System.out.println("Data-from-openfeign"+str);
        JSONObject jsonObject = JsonUtil.convStrToJson(str);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId+"--"+jsonObject.getString("name"));
        productOrder.setUserName("tom");

        return productOrder;
    }
}
