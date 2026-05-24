package com.qinzhong.service;

import com.qinzhong.entity.Product;

import java.util.List;

/*
 * 商品查询服务
 * */
public interface ProductQueryService {
    /*
    * 根据商品id查询
    * */
    Product getProductById(Long id);

    /*
    * 查询所有商品
    * */
    List<Product> getAllProduct();


    /*
    * 判断商品是否存在
    * */
    boolean exists(Long id);

    /*
    * 获取商品库存
    * */
    Integer getStock(Long id);

}

