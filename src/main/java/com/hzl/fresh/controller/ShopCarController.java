package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopCar;
import com.hzl.fresh.service.IShopCarService;
import org.springframework.web.bind.annotation.RestController;
import com.hzl.fresh.core.ApiController;

/**
 *
 * 购物车表
 *
 * @author hzl
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/fresh/shopCar")
public class ShopCarController extends ApiController {
    @Autowired
    IShopCarService shopCarService;

    /**
    * 获取购物车表
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopCar> getById(@RequestParam("id") String id){
        return success(shopCarService.getById(id));
    }

    /**
     * 保存或更新购物车表
     * @param shopCar 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopCar shopCar){
        return success(shopCarService.saveOrUpdate(shopCar));
    }
    /**
     * 删除购物车表
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopCarService.removeById(id));
    }

    /**
     * 购物车表列表
     * @param page 页码
     * @param size 大小
     * @param shopCar 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopCar>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopCar shopCar
    ) {
        IPage<ShopCar> pageData = shopCarService.page(new Page<>(page, size), new QueryWrapper<>(shopCar));
        return success(pageData);
    }

}
