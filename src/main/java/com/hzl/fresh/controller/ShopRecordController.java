package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.fresh.entity.ShopRecord;
import com.hzl.fresh.service.IShopRecordService;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 直连记录表
 *
 * @author hzl
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/fresh/shopRecord")
public class ShopRecordController {
    @Autowired
    IShopRecordService shopRecordService;

    /**
    * 获取直连记录表
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<ShopRecord> getById(@RequestParam("id") String id){
        return R.ok(shopRecordService.getById(id));
    }

    /**
     * 保存或更新直连记录表
     * @param shopRecord 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody ShopRecord shopRecord){
        return R.ok(shopRecordService.saveOrUpdate(shopRecord));
    }
    /**
     * 删除直连记录表
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return R.ok(shopRecordService.removeById(id));
    }

    /**
     * 直连记录表列表
     * @param page 页码
     * @param size 大小
     * @param shopRecord 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<ShopRecord>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            ShopRecord shopRecord
    ) {
        IPage<ShopRecord> pageData = shopRecordService.page(new Page<>(page, size), new QueryWrapper<>(shopRecord));
        return R.ok(pageData);
    }

}
