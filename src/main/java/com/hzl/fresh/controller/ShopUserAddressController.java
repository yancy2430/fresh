package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopUserAddress;
import com.hzl.fresh.service.IShopUserAddressService;
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
@RequestMapping("/fresh/shopUserAddress")
public class ShopUserAddressController extends ApiController {
    @Autowired
    IShopUserAddressService shopUserAddressService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopUserAddress> getById(@RequestParam("id") String id){
        return success(shopUserAddressService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopUserAddress 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopUserAddress shopUserAddress){
        return success(shopUserAddressService.saveOrUpdate(shopUserAddress));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopUserAddressService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopUserAddress 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopUserAddress>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopUserAddress shopUserAddress
    ) {
        IPage<ShopUserAddress> pageData = shopUserAddressService.page(new Page<>(page, size), new QueryWrapper<>(shopUserAddress));
        return success(pageData);
    }

}
