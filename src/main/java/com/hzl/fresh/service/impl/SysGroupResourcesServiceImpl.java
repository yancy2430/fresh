package com.hzl.fresh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.fresh.entity.SysGroupResources;
import com.hzl.fresh.mapper.SysGroupResourcesMapper;
import com.hzl.fresh.service.ISysGroupResourcesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限角色分配 服务实现类
 * </p>
 *
 *
 * @since 2021-07-22
 */
@Service
public class SysGroupResourcesServiceImpl extends ServiceImpl<SysGroupResourcesMapper, SysGroupResources> implements ISysGroupResourcesService {
    @Transactional
    @Override
    public boolean saveRole(Integer roleId, Set<String> codes) {

        List<SysGroupResources> arr = new ArrayList<>();
        for (String code : codes) {
            arr.add(new SysGroupResources().setCode(code).setGroupId(roleId));
        }
        baseMapper.delete(new QueryWrapper<SysGroupResources>().lambda().eq(SysGroupResources::getGroupId,roleId));
        return saveBatch(arr);
    }
}
