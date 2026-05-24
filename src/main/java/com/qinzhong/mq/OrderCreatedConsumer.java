package com.qinzhong.mq;

import com.qinzhong.config.AppRedisKeys;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/*
 * RocketMQ 消费者：收到下单消息后 Redis 计数 +1
 * */
@Component
@RocketMQMessageListener(
        topic = "ORDER_TOPIC",
        consumerGroup = "${rocketmq.consumer.order-created-group}",
        selectorExpression = "ORDER_CREATED")
public class OrderCreatedConsumer implements RocketMQListener<String> {

    private static final Logger log = LoggerFactory.getLogger(OrderCreatedConsumer.class);

    private final StringRedisTemplate stringRedisTemplate;

    public OrderCreatedConsumer(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void onMessage(String message) {
        log.info("[MQ] 收到下单消息: {}", message);
        stringRedisTemplate.opsForValue().increment(AppRedisKeys.METRIC_ORDERS_CREATED);
    }
}
