package com.qinzhong.config;

/*
 * RocketMQ Topic / Tag 常量
 * */
public final class RocketMqTopics {

    private RocketMqTopics() {
    }

    public static final String ORDER_TOPIC = "ORDER_TOPIC";

    public static final String ORDER_CREATED_TAG = "ORDER_CREATED";

    public static String orderCreatedDestination() {
        return ORDER_TOPIC + ":" + ORDER_CREATED_TAG;
    }
}
