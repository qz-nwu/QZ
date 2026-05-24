package com.qinzhong.mapper;

import com.qinzhong.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    /*
     * 按 id 查商品
     * */
    @Select("SELECT id, name, price_cent AS priceCent, stock, created_at AS createAt FROM product WHERE id = #{id}")
    Product selectById(Long id);

    /*
     * 查全部商品
     * */
    @Select("SELECT id, name, price_cent AS priceCent, stock, created_at AS createAt FROM product ORDER BY id")
    List<Product> selectAll();

    /*
     * 扣减库存（库存不足时影响行数为 0）
     * */
    @Update("UPDATE product SET stock = stock - #{quantity} WHERE id = #{id} AND stock >= #{quantity}")
    int decreaseStock(@Param("id") Long id, @Param("quantity") Integer quantity);
}
