CREATE DATABASE IF NOT EXISTS qzdemo DEFAULT CHARSET utf8mb4;
USE qzdemo;

DROP TABLE IF EXISTS biz_order;
DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(128) NOT NULL,
    price_cent  INT          NOT NULL COMMENT '单价（分）',
    stock       INT          NOT NULL DEFAULT 0,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE biz_order (
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    order_no     VARCHAR(64)  NOT NULL,
    user_id      BIGINT       NOT NULL,
    product_id   BIGINT       NOT NULL,
    qty          INT          NOT NULL DEFAULT 1,
    amount_cent  INT          NOT NULL,
    status       TINYINT      NOT NULL DEFAULT 0 COMMENT '0待支付 1已支付',
    created_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX idx_order_no (order_no) COMMENT '按订单号查询（索引演示）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO product (id, name, price_cent, stock) VALUES
    (1, '纸巾', 9900, 100),
    (2, '牙线', 12900, 50),
    (3, '唇膏', 8888, 50);
