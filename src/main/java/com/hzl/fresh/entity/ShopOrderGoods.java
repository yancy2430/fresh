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
@TableName("hzl_shop_order_goods")
public class ShopOrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属订单
     */
    private Long orderId;

    /**
     * 产品名称
     */
    private String goodsName;

    /**
     * 产品单价
     */
    private Integer goodsPrice;

    /**
     * 产品数量
     */
    private Integer goodsCount;

    /**
     * 规格ID
     */
    private Long goodsSku;

    /**
     * 规格信息
     */
    private String skuInfo;


}
