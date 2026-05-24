package com.qinzhong.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 * 拦截器：订单接口校验 X-Demo-User-Id 请求头
 * */
@Component
public class DemoAuthInterceptor implements HandlerInterceptor {

    @Value("${app.demo.header-user-id:X-Demo-User-Id}")
    private String headerName;

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) throws Exception {
        String raw = request.getHeader(headerName);
        if (raw == null || raw.isBlank()) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"缺少请求头 " + headerName + "\"}");
            return false;
        }
        return true;
    }
}
