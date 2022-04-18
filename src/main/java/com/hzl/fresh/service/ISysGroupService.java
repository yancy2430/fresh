package com.hzl.fresh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.fresh.entity.SysGroup;

/**
 * <p>
 * 用户组 服务类
 * </p>
 *
 *
 * @since 2021-07-22
 */
public interface ISysGroupService extends IService<SysGroup> {
    /**
     * 通过ID获取组详情和权限列表
     * @param id
     * @return
     */
    public SysGroup getGroupPermissionById(Integer id);

    public Boolean updatePermissionsById(SysGroup sysGroup);

    Boolean removeGroup(Integer groupId);
}
