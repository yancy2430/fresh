package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.entity.SysUserAttached;
import com.hzl.fresh.service.ISysUserAttachedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 用户表附表
 *
 *
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/system/sysUserAttached")
public class SysUserAttachedController extends ApiController {
    @Autowired
    ISysUserAttachedService sysUserAttachedService;

    /**
    * 获取用户表附表
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<SysUserAttached> getById(@RequestParam("id") String id){
        return success(sysUserAttachedService.getById(id));
    }

    /**
     * 添加用户表附表
     * @param sysUserAttached 添加数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody SysUserAttached sysUserAttached){
        return success(sysUserAttachedService.saveOrUpdate(sysUserAttached));
    }

    /**
     * 更新用户表附表
     * @param sysUserAttached 更新数据
     * @return 返回结果
     */
    @PostMapping("updateById")
    public R<Boolean> updateById(@RequestBody SysUserAttached sysUserAttached){
        return success(sysUserAttachedService.updateById(sysUserAttached));
    }

    /**
     * 删除用户表附表
     * @param id 数据ID
     * @return 返回结果
     */
    @PostMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(sysUserAttachedService.removeById(id));
    }

    /**
     * 用户表附表列表
     * @param page 页码
     * @param size 大小
     * @param query 查询实体|com.hzl.fresh.entity.SysUserAttached
     * @return 返回结果
     */
    @PostMapping("page")
    public R<IPage<SysUserAttached>> page(@RequestParam("page") Integer page,@RequestParam("size") Integer size,@RequestBody QueryWrapper<SysUserAttached> query) {
        IPage<SysUserAttached> pageData = sysUserAttachedService.page(new Page<>(page, size), query);
        return success(pageData);
    }

}
