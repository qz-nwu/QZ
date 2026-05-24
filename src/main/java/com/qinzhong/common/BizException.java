package com.qinzhong.common;

/*
 * 业务异常
 * */
public class BizException extends RuntimeException {
    private Integer code;
    public BizException(String message) {
        super(message);
        this.code = 500;
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
