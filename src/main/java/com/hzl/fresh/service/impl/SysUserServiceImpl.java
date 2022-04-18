package com.hzl.fresh.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.fresh.entity.SysGroup;
import com.hzl.fresh.entity.SysUser;
import com.hzl.fresh.entity.SysUserGroup;
import com.hzl.fresh.mapper.SysGroupMapper;
import com.hzl.fresh.mapper.SysUserGroupMapper;
import com.hzl.fresh.mapper.SysUserMapper;
import com.hzl.fresh.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * 
 * @since 2021-07-21
 */
@Primary
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    SysUserGroupMapper sysUserGroupMapper;
    @Autowired
    SysGroupMapper sysGroupMapper;

    @Override
    public <E extends IPage<SysUser>> E page(E page, Wrapper<SysUser> queryWrapper) {
        return baseMapper.selectUserGroupPage(page,queryWrapper);
    }

    @Override
    public <E extends IPage<SysUser>> E page(E page) {
        return this.page(page, new QueryWrapper<>());
    }

    @Override
    public SysUser getById(Serializable id) {
        SysUser sysUser = super.getById(id);
        sysUser.setGroups(sysUserGroupMapper.getGroupByUserId(id));
        return sysUser;
    }

    @Transactional
    @Override
    public boolean save(SysUser entity) {
        String salt = RandomUtil.randomString(6);
        String pass = MD5.create().digestHex(salt + entity.getPassword() + MD5.create().digestHex(salt));
        entity.setSalt(salt);
        entity.setPassword(pass);
        super.save(entity);
        sysUserGroupMapper.delete(new QueryWrapper<SysUserGroup>().lambda().eq(SysUserGroup::getUserId, entity.getId()));
        for (SysGroup group : entity.getGroups()) {
            sysUserGroupMapper.insert(new SysUserGroup().setUserId(entity.getId()).setGroupId(group.getId()));
        }
        return true;
    }

    @Transactional
    @Override
    public boolean updateById(SysUser entity) {
        if (null!=entity.getGroups()){
            sysUserGroupMapper.delete(new QueryWrapper<SysUserGroup>().lambda().eq(SysUserGroup::getUserId, entity.getId()));
            for (SysGroup group : entity.getGroups()) {
                sysUserGroupMapper.insert(new SysUserGroup().setUserId(entity.getId()).setGroupId(group.getId()));
            }
        }
        SysUser oldUser = super.getById(entity.getId());
        if (!oldUser.getPassword().equals(entity.getPassword())) {
            String salt = RandomUtil.randomString(6);
            String pass = MD5.create().digestHex(salt + entity.getPassword() + MD5.create().digestHex(salt));
            entity.setSalt(salt);
            entity.setPassword(pass);
        }
        return super.updateById(entity);
    }

}
