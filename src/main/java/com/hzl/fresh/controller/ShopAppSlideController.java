package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopAppSlide;
import com.hzl.fresh.service.IShopAppSlideService;
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
@RequestMapping("/fresh/shopAppSlide")
public class ShopAppSlideController extends ApiController {
    @Autowired
    IShopAppSlideService shopAppSlideService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopAppSlide> getById(@RequestParam("id") String id){
        return success(shopAppSlideService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopAppSlide 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopAppSlide shopAppSlide){
        return success(shopAppSlideService.saveOrUpdate(shopAppSlide));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopAppSlideService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopAppSlide 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopAppSlide>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopAppSlide shopAppSlide
    ) {
        IPage<ShopAppSlide> pageData = shopAppSlideService.page(new Page<>(page, size), new QueryWrapper<>(shopAppSlide));
        return success(pageData);
    }

}
