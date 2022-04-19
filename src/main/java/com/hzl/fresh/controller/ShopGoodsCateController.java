package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopGoodsCate;
import com.hzl.fresh.service.IShopGoodsCateService;
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
@RequestMapping("/fresh/shopGoodsCate")
public class ShopGoodsCateController extends ApiController {
    @Autowired
    IShopGoodsCateService shopGoodsCateService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopGoodsCate> getById(@RequestParam("id") String id){
        return success(shopGoodsCateService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopGoodsCate 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopGoodsCate shopGoodsCate){
        return success(shopGoodsCateService.saveOrUpdate(shopGoodsCate));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopGoodsCateService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopGoodsCate 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopGoodsCate>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopGoodsCate shopGoodsCate
    ) {
        IPage<ShopGoodsCate> pageData = shopGoodsCateService.page(new Page<>(page, size), new QueryWrapper<>(shopGoodsCate));
        return success(pageData);
    }

}
