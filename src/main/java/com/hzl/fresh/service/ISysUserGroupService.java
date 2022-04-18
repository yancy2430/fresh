package com.hzl.fresh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.fresh.entity.SysUserGroup;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * 
 * @since 2021-07-22
 */
public interface ISysUserGroupService extends IService<SysUserGroup> {
    Boolean saveUserGroup(Integer id, List<Integer> roleIds);
}
