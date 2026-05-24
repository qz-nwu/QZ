package com.qinzhong.config;

/*
 * Redis Key 常量
 * */
public final class AppRedisKeys {

    private AppRedisKeys() {
    }

    /*
    * MQ 消费者累计下单次数
    * */
    public static final String METRIC_ORDERS_CREATED = "qz:metric:orders:created";

    public static String orderList(Long userId) {
        return "qz:orders:list:" + userId;
    }

    public static String payLock(String orderNo) {
        return "qz:lock:pay:" + orderNo;
    }
}
