package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.entity.SysGroupResources;
import com.hzl.fresh.service.ISysGroupResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 权限角色分配
 *
 *
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/system/sysGroupResources")
public class SysGroupResourcesController extends ApiController {
    @Autowired
    ISysGroupResourcesService sysGroupResourcesService;

    /**
    * 获取权限角色分配
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<SysGroupResources> getById(@RequestParam("id") String id){
        return success(sysGroupResourcesService.getById(id));
    }

    /**
     * 添加权限角色分配
     * @param SysGroupResources 添加数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody SysGroupResources SysGroupResources){
        return success(sysGroupResourcesService.saveOrUpdate(SysGroupResources));
    }

    /**
     * 更新权限角色分配
     * @param SysGroupResources 更新数据
     * @return 返回结果
     */
    @PostMapping("updateById")
    public R<Boolean> updateById(@RequestBody SysGroupResources SysGroupResources){
        return success(sysGroupResourcesService.updateById(SysGroupResources));
    }

    /**
     * 删除权限角色分配
     * @param id 数据ID
     * @return 返回结果
     */
    @PostMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(sysGroupResourcesService.removeById(id));
    }

    /**
     * 权限角色分配列表
     * @param page 页码
     * @param size 大小
     * @param query 查询实体|com.hzl.fresh.entity.SysGroupResources
     * @return 返回结果
     */
    @PostMapping("page")
    public R<IPage<SysGroupResources>> page(@RequestParam("page") Integer page,@RequestParam("size") Integer size,@RequestBody QueryWrapper<SysGroupResources> query) {
        IPage<SysGroupResources> pageData = sysGroupResourcesService.page(new Page<>(page, size), query);
        return success(pageData);
    }

}
