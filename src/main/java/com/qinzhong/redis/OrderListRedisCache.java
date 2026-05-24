package com.qinzhong.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qinzhong.config.AppRedisKeys;
import com.qinzhong.entity.BizOrder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/*
 * 订单列表缓存：Redis String 存 JSON
 * */
@Component
public class OrderListRedisCache {

    private static final Duration TTL = Duration.ofSeconds(60);

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    public OrderListRedisCache(StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    public Optional<List<BizOrder>> get(Long userId) throws Exception {
        String json = stringRedisTemplate.opsForValue().get(AppRedisKeys.orderList(userId));
        if (json == null) {
            return Optional.empty();
        }
        return Optional.of(objectMapper.readValue(json, new TypeReference<>() {}));
    }

    public void put(Long userId, List<BizOrder> orders) throws Exception {
        String json = objectMapper.writeValueAsString(orders != null ? orders : Collections.emptyList());
        stringRedisTemplate.opsForValue().set(AppRedisKeys.orderList(userId), json, TTL);
    }

    /*
     * 下单/支付后删除缓存
     * */
    public void evict(Long userId) {
        stringRedisTemplate.delete(AppRedisKeys.orderList(userId));
    }
}
