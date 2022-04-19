package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopGoodsSku;
import com.hzl.fresh.service.IShopGoodsSkuService;
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
@RequestMapping("/fresh/shopGoodsSku")
public class ShopGoodsSkuController extends ApiController {
    @Autowired
    IShopGoodsSkuService shopGoodsSkuService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopGoodsSku> getById(@RequestParam("id") String id){
        return success(shopGoodsSkuService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopGoodsSku 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopGoodsSku shopGoodsSku){
        return success(shopGoodsSkuService.saveOrUpdate(shopGoodsSku));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopGoodsSkuService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopGoodsSku 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopGoodsSku>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopGoodsSku shopGoodsSku
    ) {
        IPage<ShopGoodsSku> pageData = shopGoodsSkuService.page(new Page<>(page, size), new QueryWrapper<>(shopGoodsSku));
        return success(pageData);
    }

}
