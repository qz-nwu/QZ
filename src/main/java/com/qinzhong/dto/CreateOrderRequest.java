package com.qinzhong.dto;

/*
 * 创建订单请求参数
 * */
public class CreateOrderRequest {
    private Long userId;      // 用户ID
    private Long productId;   // 商品ID
    private Integer qty;      // 购买数量

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Long userId, Long productId, Integer qty) {
        this.userId = userId;
        this.productId = productId;
        this.qty = qty;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", qty=" + qty +
                '}';
    }
}
