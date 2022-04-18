package com.hzl.fresh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.fresh.entity.SysGroupResources;

import java.util.Set;

/**
 * <p>
 * 权限角色分配 服务类
 * </p>
 *
 * 
 * @since 2021-07-22
 */
public interface ISysGroupResourcesService extends IService<SysGroupResources> {
    public boolean saveRole(Integer userId, Set<String> codes);
}
