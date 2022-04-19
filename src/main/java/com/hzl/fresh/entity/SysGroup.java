package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 用户组
 *
 *
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hzl_sys_group")
public class SysGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限组ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属上级组
     */
    private Integer pid;

    /**
     * 权限组名
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 权限组说明
     */
    @TableField("`desc`")
    private String desc;
    /**
     * 权限组说明
     */
    @TableField("`type`")
    private Integer type;

    @TableField(exist = false)
    private List<String> permissions;

}
