package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopGoodsAttr;
import com.hzl.fresh.service.IShopGoodsAttrService;
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
@RequestMapping("/fresh/shopGoodsAttr")
public class ShopGoodsAttrController extends ApiController {
    @Autowired
    IShopGoodsAttrService shopGoodsAttrService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopGoodsAttr> getById(@RequestParam("id") String id){
        return success(shopGoodsAttrService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopGoodsAttr 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopGoodsAttr shopGoodsAttr){
        return success(shopGoodsAttrService.saveOrUpdate(shopGoodsAttr));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopGoodsAttrService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopGoodsAttr 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopGoodsAttr>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopGoodsAttr shopGoodsAttr
    ) {
        IPage<ShopGoodsAttr> pageData = shopGoodsAttrService.page(new Page<>(page, size), new QueryWrapper<>(shopGoodsAttr));
        return success(pageData);
    }

}
