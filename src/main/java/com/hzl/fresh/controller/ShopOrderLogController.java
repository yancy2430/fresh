package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopOrderLog;
import com.hzl.fresh.service.IShopOrderLogService;
import org.springframework.web.bind.annotation.RestController;
import com.hzl.fresh.core.ApiController;

/**
 *
 * 
 *
 * @author hzl
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/fresh/shopOrderLog")
public class ShopOrderLogController extends ApiController {
    @Autowired
    IShopOrderLogService shopOrderLogService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopOrderLog> getById(@RequestParam("id") String id){
        return success(shopOrderLogService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopOrderLog 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopOrderLog shopOrderLog){
        return success(shopOrderLogService.saveOrUpdate(shopOrderLog));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopOrderLogService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopOrderLog 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopOrderLog>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopOrderLog shopOrderLog
    ) {
        IPage<ShopOrderLog> pageData = shopOrderLogService.page(new Page<>(page, size), new QueryWrapper<>(shopOrderLog));
        return success(pageData);
    }

}
