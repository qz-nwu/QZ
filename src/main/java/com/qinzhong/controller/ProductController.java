package com.qinzhong.controller;

import com.qinzhong.common.Result;
import com.qinzhong.entity.Product;
import com.qinzhong.service.ProductQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * 商品接口
 * */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductQueryService productQueryService;

    public ProductController(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    /*
    * 查询所有商品
    * */
    @GetMapping
    public Result<List<Product>> getAllProduct() {
        List<Product> products = productQueryService.getAllProduct();
        return Result.success(products);
    }

    /*
     * 查询商品库存
     * */
    @GetMapping("/{id}/stock")
    public Result<Integer> getStock(@PathVariable Long id) {
        Integer stock = productQueryService.getStock(id);
        if (stock == -1) {
            return Result.error(404, "商品不存在");
        }
        return Result.success(stock);
    }

    /*
    * 查询商品是否存在
    * */
    @GetMapping("/{id}/exists")
    public Result<Boolean> exists(@PathVariable Long id) {
        boolean exists = productQueryService.exists(id);
        return Result.success(exists);
    }

    /*
    * 根据id查询商品
    * */
    @GetMapping("/{id}")
    public Result<Product> getProduct(@PathVariable Long id) {
        Product product = productQueryService.getProductById(id);
        if (product == null) {
            return Result.error(404, "商品不存在");
        }
        return Result.success(product);
    }
}
