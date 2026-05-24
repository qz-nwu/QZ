package com.qinzhong.service;

import com.qinzhong.entity.BizOrder;

import java.util.List;

/*
* 订单查询服务接口
* */
public interface OrderQueryService {
    /*
    * 根据用户id查询订单列表
    * */
    List<BizOrder> getOrdersByUserId(Long userId);
}
