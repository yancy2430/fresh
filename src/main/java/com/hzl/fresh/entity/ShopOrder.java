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
 * 
 *
 * @author hzl
 * @since 2022-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hzl_shop_order")
public class ShopOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单状态
     */
    private Boolean status;

    /**
     * 商品总价
     */
    private Integer goodsTotalPrice;

    /**
     * 订单总价
     */
    private Integer orderTotalPrice;

    /**
     * 订单减免
     */
    private Integer orderBreaks;

    /**
     * 支付价格
     */
    private Integer payTotalPrice;

    /**
     * 是否邮寄
     */
    private Boolean mail;

    /**
     * 运费
     */
    private Integer freight;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 支付订单号
     */
    private String payOrderId;

    /**
     * 支付方式
     */
    private Long payType;

    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 收货人电话
     */
    private String tel;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 操作人
     */
    private Long operator;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 备注
     */
    private String remark;

    /**
     * 店铺ID
     */
    private Long storeId;


}
