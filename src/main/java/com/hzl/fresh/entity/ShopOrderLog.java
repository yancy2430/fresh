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
@TableName("hzl_shop_order_log")
public class ShopOrderLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作时间
     */
    private LocalDateTime operatTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作内容
     */
    private String msg;

    /**
     * 操作人ID
     */
    private Long operatorId;

    /**
     * 订单ID
     */
    private Long orderId;


}
