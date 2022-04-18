package com.hzl.fresh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzl.fresh.entity.SysGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户组 Mapper 接口
 * </p>
 *
 *
 * @since 2021-07-22
 */
public interface SysGroupMapper extends BaseMapper<SysGroup> {
    List<SysGroup> selectGroupListByUserId(@Param("userId") Integer userId);
}
