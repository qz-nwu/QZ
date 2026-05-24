package com.qinzhong.mapper;

import com.qinzhong.entity.BizOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BizOrderMapper {

    /*
     * 新增订单
     * */
    @Insert("INSERT INTO biz_order(order_no, user_id, product_id, qty, amount_cent, status) "
            + "VALUES(#{orderNo}, #{userId}, #{productId}, #{qty}, #{amountCent}, #{status})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(BizOrder order);

    /*
     * 按用户查订单列表
     * */
    @Select("SELECT id, order_no AS orderNo, user_id AS userId, product_id AS productId, "
            + "qty, amount_cent AS amountCent, status, created_at AS createdAt "
            + "FROM biz_order WHERE user_id = #{userId} ORDER BY created_at DESC")
    List<BizOrder> selectByUserIdOrderCreatedDesc(Long userId);

    /*
     * 按订单号 + 用户查单条
     * */
    @Select("SELECT id, order_no AS orderNo, user_id AS userId, product_id AS productId, "
            + "qty, amount_cent AS amountCent, status, created_at AS createdAt "
            + "FROM biz_order WHERE order_no = #{orderNo} AND user_id = #{userId}")
    BizOrder selectByOrderNoAndUserId(@Param("orderNo") String orderNo, @Param("userId") Long userId);

    /*
     * 待支付订单改为已支付
     * */
    @Update("UPDATE biz_order SET status = 1 "
            + "WHERE order_no = #{orderNo} AND user_id = #{userId} AND status = 0")
    int updatePaidIfPending(@Param("orderNo") String orderNo, @Param("userId") Long userId);
}
