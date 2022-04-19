package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 *
 * 商品主表
 *
 * @author hzl
 * @since 2022-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hzl_shop_goods")
public class ShopGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品分类
     */
    private Long cateId;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格单位 分
     */
    private Integer goodsPrice;

    /**
     * 原价
     */
    private Integer goodsOriginalPrice;

    /**
     * 商品总数
     */
    private Integer goodsTotal;

    /**
     * 销售数量
     */
    private Integer salesTotal;

    /**
     * 销售规则
     */
    private String specs;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 商品状态|state:1.上架,0.下架
     */
    private Boolean status;

    /**
     * 是否包邮
     */
    private Boolean freeMail;

    /**
     * 运费
     */
    private Integer freight;


}
