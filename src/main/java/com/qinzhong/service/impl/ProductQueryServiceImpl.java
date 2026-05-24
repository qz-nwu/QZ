package com.qinzhong.service.impl;

import com.qinzhong.entity.Product;
import com.qinzhong.mapper.ProductMapper;
import com.qinzhong.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productMapper.selectAll();
    }

    @Override
    public boolean exists(Long id) {
        return productMapper.selectById(id) != null;
    }

    @Override
    public Integer getStock(Long id) {
        Product product = productMapper.selectById(id);
        return product != null ? product.getStock() : -1;
    }
}
