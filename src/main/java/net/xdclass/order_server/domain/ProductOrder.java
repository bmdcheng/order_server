package net.xdclass.order_server.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chengcheng123
 * @date 2021/6/2 21:53
 */
public class ProductOrder implements Serializable {


    public ProductOrder() {
    }

    public ProductOrder(int id, String productName, String tradeNo, int price, Date createTime, String userName, String userId) {
        this.id = id;
        this.productName = productName;
        this.tradeNo = tradeNo;
        this.price = price;
        this.createTime = createTime;
        this.userName = userName;
        this.userId = userId;
    }

    private int id;

    /**
     * 商品名称
     */
    private String productName;
    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 价格
     */
    private int price;

    /**
     *订单时间
     */
    private Date createTime;

    private  String userName;

    private  String userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
