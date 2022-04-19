package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 资源管理
 *
 *
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "hzl_sys_resources",autoResultMap = true)
public class SysResources implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 上级ID
     */
    private Integer pid;

    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 权限唯一键
     */
    private String code;

    /**
     * 路径
     */
    private String path;

    /**
     * 类型
     */
    private String component;
    /**
     * 接口
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> apis;
    /**
     * 打开方式
     */
    private Integer open;
    /**
     * 图标
     */
    private String icon;
    /**
     * 页内权限列表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Permission> permissions;
    /**
     * 排序
     */
    private Integer sort;
    @Data
    @Accessors(chain = true)
    public static class Permission{
        private String name;
        private String code;
        private List<String> apis;
    }

}
