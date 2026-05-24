package com.qinzhong.controller;

import com.qinzhong.common.Result;
import com.qinzhong.dto.CreateOrderRequest;
import com.qinzhong.entity.BizOrder;
import com.qinzhong.service.OrderCreateService;
import com.qinzhong.service.OrderPayService;
import com.qinzhong.service.OrderQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 订单接口（需请求头 X-Demo-User-Id，见拦截器）
 * */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderCreateService orderCreateService;
    private final OrderQueryService orderQueryService;
    private final OrderPayService orderPayService;

    public OrderController(
            OrderCreateService orderCreateService,
            OrderQueryService orderQueryService,
            OrderPayService orderPayService) {
        this.orderCreateService = orderCreateService;
        this.orderQueryService = orderQueryService;
        this.orderPayService = orderPayService;
    }

    /*
     * 创建订单
     * */
    @PostMapping
    public Result<BizOrder> createOrder(@RequestBody CreateOrderRequest request) {
        BizOrder order = orderCreateService.createOrder(request);
        return Result.success("下单成功", order);
    }

    /*
     * 查询用户订单列表
     * */
    @GetMapping
    public Result<List<BizOrder>> getOrdersByUserId(@RequestParam Long userId) {
        return Result.success(orderQueryService.getOrdersByUserId(userId));
    }

    /*
     * 支付订单
     * */
    @PostMapping("/{orderNo}/pay")
    public Result<String> payOrder(@PathVariable String orderNo, @RequestParam Long userId) {
        boolean success = orderPayService.payOrder(orderNo, userId);
        if (success) {
            return Result.success("支付成功", null);
        }
        return Result.error(400, "订单已支付");
    }
}
