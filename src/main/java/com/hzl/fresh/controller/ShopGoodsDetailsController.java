package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopGoodsDetails;
import com.hzl.fresh.service.IShopGoodsDetailsService;
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
@RequestMapping("/fresh/shopGoodsDetails")
public class ShopGoodsDetailsController extends ApiController {
    @Autowired
    IShopGoodsDetailsService shopGoodsDetailsService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopGoodsDetails> getById(@RequestParam("id") String id){
        return success(shopGoodsDetailsService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopGoodsDetails 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopGoodsDetails shopGoodsDetails){
        return success(shopGoodsDetailsService.saveOrUpdate(shopGoodsDetails));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopGoodsDetailsService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopGoodsDetails 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopGoodsDetails>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopGoodsDetails shopGoodsDetails
    ) {
        IPage<ShopGoodsDetails> pageData = shopGoodsDetailsService.page(new Page<>(page, size), new QueryWrapper<>(shopGoodsDetails));
        return success(pageData);
    }

}
