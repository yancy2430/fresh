package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopPayLog;
import com.hzl.fresh.service.IShopPayLogService;
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
@RequestMapping("/fresh/shopPayLog")
public class ShopPayLogController extends ApiController {
    @Autowired
    IShopPayLogService shopPayLogService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopPayLog> getById(@RequestParam("id") String id){
        return success(shopPayLogService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopPayLog 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopPayLog shopPayLog){
        return success(shopPayLogService.saveOrUpdate(shopPayLog));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopPayLogService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopPayLog 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopPayLog>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopPayLog shopPayLog
    ) {
        IPage<ShopPayLog> pageData = shopPayLogService.page(new Page<>(page, size), new QueryWrapper<>(shopPayLog));
        return success(pageData);
    }

}
