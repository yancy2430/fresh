package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopAppMenu;
import com.hzl.fresh.service.IShopAppMenuService;
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
@RequestMapping("/fresh/shopAppMenu")
public class ShopAppMenuController extends ApiController {
    @Autowired
    IShopAppMenuService shopAppMenuService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopAppMenu> getById(@RequestParam("id") String id){
        return success(shopAppMenuService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopAppMenu 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopAppMenu shopAppMenu){
        return success(shopAppMenuService.saveOrUpdate(shopAppMenu));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopAppMenuService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopAppMenu 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopAppMenu>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopAppMenu shopAppMenu
    ) {
        IPage<ShopAppMenu> pageData = shopAppMenuService.page(new Page<>(page, size), new QueryWrapper<>(shopAppMenu));
        return success(pageData);
    }

}
