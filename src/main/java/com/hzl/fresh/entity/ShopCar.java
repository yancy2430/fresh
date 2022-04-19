package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 *
 * 购物车表
 *
 * @author hzl
 * @since 2022-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hzl_shop_car")
public class ShopCar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属用户
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 规格ID
     */
    private Long skuId;

    /**
     * 店铺名
     */
    private String storeName;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 封面
     */
    private String cover;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 规格字符串
     */
    private String skuStr;


}
