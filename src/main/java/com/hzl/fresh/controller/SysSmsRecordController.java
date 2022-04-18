package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.entity.SysSmsRecord;
import com.hzl.fresh.service.ISysSmsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 短信记录
 *
 * 
 * @since 2021-09-11
 */
@RestController
@RequestMapping("/system/sysSmsRecord")
public class SysSmsRecordController extends ApiController {
    @Autowired
    ISysSmsRecordService sysSmsRecordService;

    /**
    * 获取短信记录
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<SysSmsRecord> getById(@RequestParam("id") String id){
        return success(sysSmsRecordService.getById(id));
    }

    /**
     * 添加短信记录
     * @param sysSmsRecord 添加数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody SysSmsRecord sysSmsRecord){
        return success(sysSmsRecordService.saveOrUpdate(sysSmsRecord));
    }

    /**
     * 更新短信记录
     * @param sysSmsRecord 更新数据
     * @return 返回结果
     */
    @PostMapping("updateById")
    public R<Boolean> updateById(@RequestBody SysSmsRecord sysSmsRecord){
        return success(sysSmsRecordService.updateById(sysSmsRecord));
    }

    /**
     * 删除短信记录
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(sysSmsRecordService.removeById(id));
    }

    /**
     * 短信记录列表
     * @param page 页码
     * @param size 大小
     * @param sysSmsRecord 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<SysSmsRecord>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            SysSmsRecord sysSmsRecord
    ) {
        IPage<SysSmsRecord> pageData = sysSmsRecordService.page(new Page<>(page, size), new QueryWrapper<>(sysSmsRecord));
        return success(pageData);
    }

}
