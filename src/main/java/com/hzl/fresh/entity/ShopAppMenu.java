package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hzl_shop_app_menu")
public class ShopAppMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接
     */
    private String link;

    /**
     * 排序
     */
    private Integer sort;


}
