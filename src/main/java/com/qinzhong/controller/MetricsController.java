package com.qinzhong.controller;

import com.qinzhong.common.Result;
import com.qinzhong.config.AppRedisKeys;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * MQ 联调：查看消费者累计的下单次数
 * */
@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    private final StringRedisTemplate stringRedisTemplate;

    public MetricsController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /*
     * 读取 Redis 中的下单累计次数
     * */
    @GetMapping("/orders-created")
    public Result<Long> ordersCreated() {
        String v = stringRedisTemplate.opsForValue().get(AppRedisKeys.METRIC_ORDERS_CREATED);
        long n = 0L;
        if (v != null && !v.isEmpty()) {
            try {
                n = Long.parseLong(v);
            } catch (NumberFormatException ignored) {
                n = 0L;
            }
        }
        return Result.success(n);
    }
}
