package com.qinzhong.service.impl;

import com.qinzhong.common.BizException;
import com.qinzhong.config.AppRedisKeys;
import com.qinzhong.entity.BizOrder;
import com.qinzhong.mapper.BizOrderMapper;
import com.qinzhong.redis.DistributedRedisLock;
import com.qinzhong.redis.OrderListRedisCache;
import com.qinzhong.service.OrderPayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
public class OrderPayServiceImpl implements OrderPayService {

    private final BizOrderMapper bizOrderMapper;
    private final DistributedRedisLock distributedRedisLock;
    private final OrderListRedisCache orderListRedisCache;

    public OrderPayServiceImpl(
            BizOrderMapper bizOrderMapper,
            DistributedRedisLock distributedRedisLock,
            OrderListRedisCache orderListRedisCache) {
        this.bizOrderMapper = bizOrderMapper;
        this.distributedRedisLock = distributedRedisLock;
        this.orderListRedisCache = orderListRedisCache;
    }

    /*
     * 支付订单：Redis 分布式锁 + 改状态（事务）
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean payOrder(String orderNo, Long userId) {
        String lockKey = AppRedisKeys.payLock(orderNo);
        if (!distributedRedisLock.tryLock(lockKey, Duration.ofSeconds(10))) {
            throw new BizException(429, "支付处理中，请稍后再试");
        }

        try {
            BizOrder order = bizOrderMapper.selectByOrderNoAndUserId(orderNo, userId);
            if (order == null) {
                throw new BizException(404, "订单不存在");
            }
            if (order.getStatus() == 1) {
                return false;
            }

            int affectedRows = bizOrderMapper.updatePaidIfPending(orderNo, userId);
            if (affectedRows > 0) {
                orderListRedisCache.evict(userId);
                return true;
            }
            return false;
        } finally {
            distributedRedisLock.unlock(lockKey);
        }
    }
}
