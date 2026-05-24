package com.qinzhong.entity;

import java.time.LocalDateTime;

/*
 * 商品实体
 * */
public class Product {
    private Long id;
    private String name;
    private Integer priceCent;
    private Integer stock;
    private LocalDateTime createAt;

    public Product() {
    }

    public Product(Long id, String name, Integer priceCent, Integer stock, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.priceCent = priceCent;
        this.stock = stock;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriceCent() {
        return priceCent;
    }

    public void setPriceCent(Integer priceCent) {
        this.priceCent = priceCent;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priceCent=" + priceCent +
                ", stock=" + stock +
                ", createAt=" + createAt +
                '}';
    }
}
