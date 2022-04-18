package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.entity.SysLoginLog;
import com.hzl.fresh.service.ISysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录日志
 * @author yangzhe
 * @since 2022-03-14
 */
@RestController
@RequestMapping("/system/sysLoginLog")
public class SysLoginLogController extends ApiController {
    @Autowired
    ISysLoginLogService sysLoginLogService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<SysLoginLog> getById(@RequestParam("id") String id){
        return success(sysLoginLogService.getById(id));
    }

    /**
     * 保存或更新
     * @param sysLoginLog 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody SysLoginLog sysLoginLog){
        return success(sysLoginLogService.saveOrUpdate(sysLoginLog));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(sysLoginLogService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param sysLoginLog 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<SysLoginLog>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            SysLoginLog sysLoginLog
    ) {
        IPage<SysLoginLog> pageData = sysLoginLogService.page(new Page<>(page, size), new QueryWrapper<>(sysLoginLog));
        return success(pageData);
    }

}
