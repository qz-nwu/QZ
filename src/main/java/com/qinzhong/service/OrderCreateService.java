package com.qinzhong.service;

import com.qinzhong.dto.CreateOrderRequest;
import com.qinzhong.entity.BizOrder;

/*
 * 创建订单服务
 * */
public interface OrderCreateService {
    /*
    * 创建订单
    * */
    BizOrder createOrder(CreateOrderRequest request);
}
