package com.hzl.fresh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.fresh.entity.SysUserGroup;
import com.hzl.fresh.mapper.SysUserGroupMapper;
import com.hzl.fresh.service.ISysUserGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 *
 * @since 2021-07-22
 */
@Service
public class SysUserGroupServiceImpl extends ServiceImpl<SysUserGroupMapper, SysUserGroup> implements ISysUserGroupService {
    @Transactional//开启事务 保证更改一致
    @Override
    public Boolean saveUserGroup(Integer id, List<Integer> roleIds) {
        remove(new QueryWrapper<SysUserGroup>().lambda().eq(SysUserGroup::getUserId, id));
        List<SysUserGroup> userRoles = roleIds.stream().map(integer -> new SysUserGroup().setGroupId(integer).setUserId(id)).collect(Collectors.toList());
        return saveBatch(userRoles);
    }
}
