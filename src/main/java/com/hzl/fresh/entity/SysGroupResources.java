package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * 权限角色分配
 *
 *
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_sys_group_resources")
public class SysGroupResources implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer groupId;

    /**
     * 权限键
     */
    private String code;


}
