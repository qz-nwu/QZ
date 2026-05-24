package com.qinzhong.service;

/*
 * 支付订单服务
 * */
public interface OrderPayService {
    /*
    * 支付订单
    * */
    boolean payOrder(String orderNo, Long userId);
}
