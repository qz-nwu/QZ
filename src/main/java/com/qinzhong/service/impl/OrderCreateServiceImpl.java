package com.qinzhong.service.impl;

import com.qinzhong.common.BizException;
import com.qinzhong.dto.CreateOrderRequest;
import com.qinzhong.entity.BizOrder;
import com.qinzhong.entity.Product;
import com.qinzhong.mapper.BizOrderMapper;
import com.qinzhong.mapper.ProductMapper;
import com.qinzhong.mq.OrderCreatedProducer;
import com.qinzhong.redis.OrderListRedisCache;
import com.qinzhong.service.OrderCreateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderCreateServiceImpl implements OrderCreateService {

    private final ProductMapper productMapper;
    private final BizOrderMapper bizOrderMapper;
    private final OrderListRedisCache orderListRedisCache;
    private final OrderCreatedProducer orderCreatedProducer;

    public OrderCreateServiceImpl(
            ProductMapper productMapper,
            BizOrderMapper bizOrderMapper,
            OrderListRedisCache orderListRedisCache,
            OrderCreatedProducer orderCreatedProducer) {
        this.productMapper = productMapper;
        this.bizOrderMapper = bizOrderMapper;
        this.orderListRedisCache = orderListRedisCache;
        this.orderCreatedProducer = orderCreatedProducer;
    }

    private String generateOrderNo() {
        String timestamp = LocalDateTime.now().toString()
                .replace("-", "")
                .replace(":", "")
                .replace(".", "")
                .substring(0, 14);
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return "ORD" + timestamp + uuid;
    }

    /*
     * 创建订单：扣库存 + 插单（事务），再清缓存、发 MQ
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizOrder createOrder(CreateOrderRequest request) {
        Product product = productMapper.selectById(request.getProductId());
        if (product == null) {
            throw new BizException(404, "商品不存在");
        }

        int affectedRows = productMapper.decreaseStock(request.getProductId(), request.getQty());
        if (affectedRows == 0) {
            throw new BizException(400, "库存不足");
        }

        int amountCent = product.getPriceCent() * request.getQty();
        String orderNo = generateOrderNo();
        BizOrder order = new BizOrder(orderNo, request.getUserId(), request.getProductId(), request.getQty(), amountCent);
        bizOrderMapper.insert(order);

        orderListRedisCache.evict(request.getUserId());
        orderCreatedProducer.publishOrderCreated(order);
        return order;
    }
}
