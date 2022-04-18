package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.entity.SysGroup;
import com.hzl.fresh.service.ISysGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户组
 *
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/system/sysGroup")
public class SysGroupController extends ApiController {
    @Autowired
    ISysGroupService sysGroupService;

    /**
     * 删除权限组
     *
     * @param groupId 权限组ID
     * @return
     */
    @RequestMapping("removeGroupById")
    public R<Boolean> removeGroupById(@RequestParam Integer groupId) {
        if (sysGroupService.count(new QueryWrapper<SysGroup>().lambda().eq(SysGroup::getPid, groupId)) > 0) {
            return failed("当前项下级存在子项，请先移出或删除后再操作");
        }
        return success(sysGroupService.removeGroup(groupId));
    }

    /**
     * 更新数据
     *
     * @return 更新结果
     */
    @RequestMapping("submitGroup")
    public R<Boolean> submitGroup(@RequestBody SysGroup sysGroup) {
        return success(sysGroupService.updatePermissionsById(sysGroup));
    }

    /**
     * 获取用户组详情
     *
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("getById")
    public R<SysGroup> getById(@RequestParam("id") Integer id) {

        return success(sysGroupService.getGroupPermissionById(id));
    }

    /**
     * 分组
     *
     * @return
     */
    @RequestMapping("list")
    public R<List<SysGroup>> list() {
        return success(sysGroupService.list());
    }
}
