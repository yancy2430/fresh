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
@TableName("hzl_shop_goods_cate")
public class ShopGoodsCate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID|auto
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类上级|hide
     */
    private Long pid;

    /**
     * 分类名称|data:input
     */
    private String cateName;

    /**
     * 排序|data:num
     */
    private Integer sort;

    /**
     * 操作人|hide
     */
    private String operator;

    /**
     * 创建时间|hide
     */
    private LocalDateTime createTime;

    /**
     * 是否删除|hide
     */
    private Boolean deleted;


}
