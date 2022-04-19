package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("hzl_shop_goods_sku_spec")
public class ShopGoodsSkuSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;


}
