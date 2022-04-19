package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopGoodsSkuSpec;
import com.hzl.fresh.service.IShopGoodsSkuSpecService;
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
@RequestMapping("/fresh/shopGoodsSkuSpec")
public class ShopGoodsSkuSpecController extends ApiController {
    @Autowired
    IShopGoodsSkuSpecService shopGoodsSkuSpecService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopGoodsSkuSpec> getById(@RequestParam("id") String id){
        return success(shopGoodsSkuSpecService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopGoodsSkuSpec 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopGoodsSkuSpec shopGoodsSkuSpec){
        return success(shopGoodsSkuSpecService.saveOrUpdate(shopGoodsSkuSpec));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopGoodsSkuSpecService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopGoodsSkuSpec 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopGoodsSkuSpec>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopGoodsSkuSpec shopGoodsSkuSpec
    ) {
        IPage<ShopGoodsSkuSpec> pageData = shopGoodsSkuSpecService.page(new Page<>(page, size), new QueryWrapper<>(shopGoodsSkuSpec));
        return success(pageData);
    }

}
