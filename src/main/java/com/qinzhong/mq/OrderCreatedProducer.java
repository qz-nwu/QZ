package com.qinzhong.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qinzhong.config.RocketMqTopics;
import com.qinzhong.entity.BizOrder;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
 * RocketMQ 生产者
 * */
@Component
public class OrderCreatedProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderCreatedProducer.class);

    private final RocketMQTemplate rocketMQTemplate;
    private final ObjectMapper objectMapper;

    public OrderCreatedProducer(RocketMQTemplate rocketMQTemplate, ObjectMapper objectMapper) {
        this.rocketMQTemplate = rocketMQTemplate;
        this.objectMapper = objectMapper;
    }

    /*
     * 下单成功后发送消息
     * */
    public void publishOrderCreated(BizOrder order) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("orderNo", order.getOrderNo());
            payload.put("userId", order.getUserId());
            String json = objectMapper.writeValueAsString(payload);
            rocketMQTemplate.syncSend(
                    RocketMqTopics.orderCreatedDestination(),
                    MessageBuilder.withPayload(json).build());
        } catch (Exception ex) {
            log.warn("发送下单消息失败，orderNo={}", order.getOrderNo(), ex);
        }
    }
}
