package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopOrderSnapshot;
import com.hzl.fresh.service.IShopOrderSnapshotService;
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
@RequestMapping("/fresh/shopOrderSnapshot")
public class ShopOrderSnapshotController extends ApiController {
    @Autowired
    IShopOrderSnapshotService shopOrderSnapshotService;

    /**
    * 获取
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopOrderSnapshot> getById(@RequestParam("id") String id){
        return success(shopOrderSnapshotService.getById(id));
    }

    /**
     * 保存或更新
     * @param shopOrderSnapshot 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopOrderSnapshot shopOrderSnapshot){
        return success(shopOrderSnapshotService.saveOrUpdate(shopOrderSnapshot));
    }
    /**
     * 删除
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(shopOrderSnapshotService.removeById(id));
    }

    /**
     * 列表
     * @param page 页码
     * @param size 大小
     * @param shopOrderSnapshot 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopOrderSnapshot>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopOrderSnapshot shopOrderSnapshot
    ) {
        IPage<ShopOrderSnapshot> pageData = shopOrderSnapshotService.page(new Page<>(page, size), new QueryWrapper<>(shopOrderSnapshot));
        return success(pageData);
    }

}
