package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 *
 * 
 *
 * @author hzl
 * @since 2022-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hzl_shop_goods_details")
public class ShopGoodsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品头图
     */
    private String images;

    /**
     * 商品详情
     */
    private String goodsContent;

    /**
     * 价格说明
     */
    private String priceDesc;

    /**
     * 发货城市
     */
    private String deliveryCity;

    /**
     * 保障条款
     */
    private String security;


}
