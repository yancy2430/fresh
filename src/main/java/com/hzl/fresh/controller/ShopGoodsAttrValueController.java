package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopGoodsAttrValue;
import com.hzl.fresh.service.IShopGoodsAttrValueService;
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
@RequestMapping("/fresh/shopGoodsAttrValue")
public class ShopGoodsAttrValueController extends ApiController {
    @Autowired
    IShopGoodsAttrValueService shopGoodsAttrValueService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopGoodsAttrValue> getById(@RequestParam("id") String id){
        return success(shopGoodsAttrValueService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopGoodsAttrValue 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopGoodsAttrValue shopGoodsAttrValue){
        return success(shopGoodsAttrValueService.saveOrUpdate(shopGoodsAttrValue));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopGoodsAttrValueService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopGoodsAttrValue 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopGoodsAttrValue>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopGoodsAttrValue shopGoodsAttrValue
    ) {
        IPage<ShopGoodsAttrValue> pageData = shopGoodsAttrValueService.page(new Page<>(page, size), new QueryWrapper<>(shopGoodsAttrValue));
        return success(pageData);
    }

}
