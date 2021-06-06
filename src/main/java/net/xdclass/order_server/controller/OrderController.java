package net.xdclass.order_server.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.xdclass.order_server.service.ProductOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author chengcheng123
 * @date 2021/6/2 22:05
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(HttpServletRequest request, @RequestParam("user_id")int userId, @RequestParam("product_id")int productId){
        String token=request.getHeader("token");
        String cookie=request.getHeader("cookie");

        System.out.println("token="+token);

        System.out.println("cookie="+cookie);

        Map<String, Object> data = new HashMap<>();

        data.put("code",0);
        data.put("data",productOrderService.save(userId,productId));
        return data;
    };

    //方法签名要和api一致
    public Object saveOrderFail(HttpServletRequest request,int userId,int productId){
        Map<String, Object> msg = new HashMap<>();

        //监控报警
        String saveOrderKey = "save-order";

        String sendValue = stringRedisTemplate.opsForValue().get(saveOrderKey);
        /**
         * 熔断告警机制：在redis中存储具有时效性的key,然后通过这个key是否存在来进行短信告警
         * 1.并发高的时候这样可能会多发短信，要采用锁
         * 2.也可以在每个服务本地安装一个redis，在发送短信的时附上每个服务的IP，更加便于排查错误
         */
       new Thread(()->{
           if(StringUtils.isBlank(sendValue)){
               System.out.println("紧急短信，用户下单失败，请立刻查找原因");
               //TODO 调用HTTP请求，调用短信服务

               stringRedisTemplate.opsForValue().set(saveOrderKey,"save-order-fail",20, TimeUnit.SECONDS);
           }else{
               System.out.println("已经发送过短信，20秒内不要重复发送");
           }

       }).start();

        msg.put("code",-1);
        msg.put("msg","抢购人数过多，请稍后再试");
        return msg;
    }

}
