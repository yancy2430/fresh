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
@TableName("hzl_shop_goods_sku")
public class ShopGoodsSku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 价格单位分
     */
    private Integer price;

    /**
     * 编码
     */
    private String code;

    /**
     * 库存
     */
    private Integer stockNum;


}
