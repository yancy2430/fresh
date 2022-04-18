package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * 
 *
 * 
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_sys_config")
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 模块
     */
    @TableField("`module`")
    private String module;

    /**
     * 分组
     */
    @TableField("`group`")
    private String group;

    /**
     * 分组名
     */
    private String groupName;

    /**
     * 配置代码
     */
    private String code;

    /**
     * 配置名
     */
    private String value;

    /**
     * 说明
     */
    @TableField("`explain`")
    private String explain;

    /**
     * 排序
     */
    @TableField("`sort`")
    private String sort;
    /**
     * 类型
     */
    @TableField("`type`")
    private String type;

}
