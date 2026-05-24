package com.qinzhong.entity;

import java.time.LocalDateTime;

/*
 * 订单实体
 * */
public class BizOrder {

    private Long id;
    private String orderNo;      // 业务单号，全局唯一
    private Long userId;         // 用户ID
    private Long productId;      // 商品ID
    private Integer qty;         // 购买数量
    private Integer amountCent;  // 订单应付金额（分）
    private Integer status;      // 0待支付 1已支付 2已取消
    private LocalDateTime createdAt;

    public BizOrder() {
    }

    public BizOrder(String orderNo, Long userId, Long productId, Integer qty, Integer amountCent) {
        this.orderNo = orderNo;
        this.userId = userId;
        this.productId = productId;
        this.qty = qty;
        this.amountCent = amountCent;
        this.status = 0;  // 默认待支付
    }

    // getter和setter方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Integer getAmountCent() {
        return amountCent;
    }

    public void setAmountCent(Integer amountCent) {
        this.amountCent = amountCent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BizOrder{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", productId=" + productId +
                ", qty=" + qty +
                ", amountCent=" + amountCent +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}