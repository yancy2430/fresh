package com.hzl.fresh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.fresh.entity.SysGroup;
import com.hzl.fresh.entity.SysGroupResources;
import com.hzl.fresh.mapper.SysGroupMapper;
import com.hzl.fresh.mapper.SysGroupResourcesMapper;
import com.hzl.fresh.service.ISysGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户组 服务实现类
 * </p>
 *
 *
 * @since 2021-07-22
 */
@Primary
@Service
public class SysGroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements ISysGroupService {
    @Autowired
    SysGroupResourcesMapper sysGroupResourcesMapper;
    @Override
    public SysGroup getGroupPermissionById(Integer id) {
        SysGroup group = baseMapper.selectById(id);
        List<String> objectList = sysGroupResourcesMapper.selectList(new QueryWrapper<SysGroupResources>().lambda()
                .select(SysGroupResources::getCode)
                .eq(SysGroupResources::getGroupId, id)
        ).stream().map(SysGroupResources::getCode).collect(Collectors.toList());
        group.setPermissions(objectList);
        return group;
    }

    /**
     * 保存权限组数据
     * @param sysGroup
     * @return
     */
    @Transactional
    @Override
    public Boolean updatePermissionsById(SysGroup sysGroup) {
        if (null==sysGroup.getId()){
            baseMapper.insert(sysGroup);
        }else {
            baseMapper.updateById(sysGroup);
        }
        sysGroupResourcesMapper.delete(new QueryWrapper<SysGroupResources>().lambda().eq(SysGroupResources::getGroupId,sysGroup.getId()));
        for (String code : sysGroup.getPermissions()) {
            sysGroupResourcesMapper.insert(new SysGroupResources().setGroupId(sysGroup.getId()).setCode(code));
        }
        return true;
    }

    /**
     * 删除权限组
     * @param groupId 权限组ID
     * @return
     */
    @Transactional
    @Override
    public Boolean removeGroup(Integer groupId) {
        baseMapper.deleteById(groupId);
        sysGroupResourcesMapper.delete(new QueryWrapper<SysGroupResources>().lambda().eq(SysGroupResources::getGroupId,groupId));
        return true;
    }
}
