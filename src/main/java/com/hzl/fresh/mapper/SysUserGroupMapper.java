package com.hzl.fresh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzl.fresh.entity.SysGroup;
import com.hzl.fresh.entity.SysUserGroup;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 *
 * @since 2021-07-22
 */
public interface SysUserGroupMapper extends BaseMapper<SysUserGroup> {
    public List<SysGroup> getGroupByUserId(@Param("userId") Serializable userId);
}
