package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopOrder;
import com.hzl.fresh.service.IShopOrderService;
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
@RequestMapping("/fresh/shopOrder")
public class ShopOrderController extends ApiController {
    @Autowired
    IShopOrderService shopOrderService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopOrder> getById(@RequestParam("id") String id){
        return success(shopOrderService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopOrder 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopOrder shopOrder){
        return success(shopOrderService.saveOrUpdate(shopOrder));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopOrderService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopOrder 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopOrder>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopOrder shopOrder
    ) {
        IPage<ShopOrder> pageData = shopOrderService.page(new Page<>(page, size), new QueryWrapper<>(shopOrder));
        return success(pageData);
    }

}
