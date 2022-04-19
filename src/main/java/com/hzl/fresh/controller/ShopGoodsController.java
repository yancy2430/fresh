package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopGoods;
import com.hzl.fresh.service.IShopGoodsService;
import org.springframework.web.bind.annotation.RestController;
import com.hzl.fresh.core.ApiController;

/**
 *
 * 商品主表
 *
 * @author hzl
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/fresh/shopGoods")
public class ShopGoodsController extends ApiController {
    @Autowired
    IShopGoodsService shopGoodsService;

    /**
    * 获取商品主表
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopGoods> getById(@RequestParam("id") String id){
        return success(shopGoodsService.getById(id));
    }

    /**
     * 保存或更新商品主表
     * @param shopGoods 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopGoods shopGoods){
        return success(shopGoodsService.saveOrUpdate(shopGoods));
    }
    /**
     * 删除商品主表
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopGoodsService.removeById(id));
    }

    /**
     * 商品主表列表
     * @param page 页码
     * @param size 大小
     * @param shopGoods 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopGoods>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopGoods shopGoods
    ) {
        IPage<ShopGoods> pageData = shopGoodsService.page(new Page<>(page, size), new QueryWrapper<>(shopGoods));
        return success(pageData);
    }

}
