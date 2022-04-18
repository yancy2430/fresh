package com.hzl.fresh.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.hzl.fresh.entity.SysUser;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 *
 * @since 2021-07-21
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    <E extends IPage<SysUser>> E selectUserGroupPage(E page, @Param(Constants.WRAPPER) Wrapper<SysUser> queryWrapper);
}
