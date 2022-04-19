package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopGoodsCollect;
import com.hzl.fresh.service.IShopGoodsCollectService;
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
@RequestMapping("/fresh/shopGoodsCollect")
public class ShopGoodsCollectController extends ApiController {
    @Autowired
    IShopGoodsCollectService shopGoodsCollectService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopGoodsCollect> getById(@RequestParam("id") String id){
        return success(shopGoodsCollectService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopGoodsCollect 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopGoodsCollect shopGoodsCollect){
        return success(shopGoodsCollectService.saveOrUpdate(shopGoodsCollect));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopGoodsCollectService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopGoodsCollect 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopGoodsCollect>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopGoodsCollect shopGoodsCollect
    ) {
        IPage<ShopGoodsCollect> pageData = shopGoodsCollectService.page(new Page<>(page, size), new QueryWrapper<>(shopGoodsCollect));
        return success(pageData);
    }

}
