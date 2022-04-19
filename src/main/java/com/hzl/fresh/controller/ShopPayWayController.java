package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopPayWay;
import com.hzl.fresh.service.IShopPayWayService;
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
@RequestMapping("/fresh/shopPayWay")
public class ShopPayWayController extends ApiController {
    @Autowired
    IShopPayWayService shopPayWayService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopPayWay> getById(@RequestParam("id") String id){
        return success(shopPayWayService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopPayWay 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopPayWay shopPayWay){
        return success(shopPayWayService.saveOrUpdate(shopPayWay));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopPayWayService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopPayWay 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopPayWay>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopPayWay shopPayWay
    ) {
        IPage<ShopPayWay> pageData = shopPayWayService.page(new Page<>(page, size), new QueryWrapper<>(shopPayWay));
        return success(pageData);
    }

}
