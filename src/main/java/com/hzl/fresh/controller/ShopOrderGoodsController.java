package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopOrderGoods;
import com.hzl.fresh.service.IShopOrderGoodsService;
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
@RequestMapping("/fresh/shopOrderGoods")
public class ShopOrderGoodsController extends ApiController {
    @Autowired
    IShopOrderGoodsService shopOrderGoodsService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopOrderGoods> getById(@RequestParam("id") String id){
        return success(shopOrderGoodsService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopOrderGoods 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopOrderGoods shopOrderGoods){
        return success(shopOrderGoodsService.saveOrUpdate(shopOrderGoods));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopOrderGoodsService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopOrderGoods 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopOrderGoods>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopOrderGoods shopOrderGoods
    ) {
        IPage<ShopOrderGoods> pageData = shopOrderGoodsService.page(new Page<>(page, size), new QueryWrapper<>(shopOrderGoods));
        return success(pageData);
    }

}
