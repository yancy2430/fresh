package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.entity.SysTask;
import com.hzl.fresh.service.ISysTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 计划任务
 *
 *
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/system/sysTask")
public class SysTaskController extends ApiController {
    @Autowired
    ISysTaskService sysTaskService;

    /**
    * 获取计划任务
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<SysTask> getById(@RequestParam("id") String id){
        return success(sysTaskService.getById(id));
    }

    /**
     * 添加计划任务
     * @param sysTask 添加数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody SysTask sysTask){
        return success(sysTaskService.saveOrUpdate(sysTask));
    }

    /**
     * 更新计划任务
     * @param sysTask 更新数据
     * @return 返回结果
     */
    @PostMapping("updateById")
    public R<Boolean> updateById(@RequestBody SysTask sysTask){
        return success(sysTaskService.updateById(sysTask));
    }

    /**
     * 删除计划任务
     * @param id 数据ID
     * @return 返回结果
     */
    @PostMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(sysTaskService.removeById(id));
    }

    /**
     * 计划任务列表
     * @param page 页码
     * @param size 大小
     * @param query 查询实体|com.hzl.fresh.entity.SysTask
     * @return 返回结果
     */
    @PostMapping("page")
    public R<IPage<SysTask>> page(@RequestParam("page") Integer page,@RequestParam("size") Integer size,@RequestBody QueryWrapper<SysTask> query) {
        IPage<SysTask> pageData = sysTaskService.page(new Page<>(page, size), query);
        return success(pageData);
    }

}
