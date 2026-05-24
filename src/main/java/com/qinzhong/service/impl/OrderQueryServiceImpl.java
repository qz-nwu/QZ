package com.qinzhong.service.impl;

import com.qinzhong.entity.BizOrder;
import com.qinzhong.mapper.BizOrderMapper;
import com.qinzhong.redis.OrderListRedisCache;
import com.qinzhong.service.OrderQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    private final BizOrderMapper bizOrderMapper;
    private final OrderListRedisCache orderListRedisCache;

    public OrderQueryServiceImpl(BizOrderMapper bizOrderMapper, OrderListRedisCache orderListRedisCache) {
        this.bizOrderMapper = bizOrderMapper;
        this.orderListRedisCache = orderListRedisCache;
    }

    /*
     * 先查 Redis 缓存，没有再查库并写入缓存
     * */
    @Override
    public List<BizOrder> getOrdersByUserId(Long userId) {
        try {
            return orderListRedisCache.get(userId).orElseGet(() -> loadAndCache(userId));
        } catch (Exception e) {
            return bizOrderMapper.selectByUserIdOrderCreatedDesc(userId);
        }
    }

    private List<BizOrder> loadAndCache(Long userId) {
        List<BizOrder> orders = bizOrderMapper.selectByUserIdOrderCreatedDesc(userId);
        try {
            orderListRedisCache.put(userId, orders);
        } catch (Exception ignored) {
        }
        return orders;
    }
}
