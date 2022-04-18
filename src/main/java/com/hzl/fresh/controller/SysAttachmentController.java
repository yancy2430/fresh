package com.hzl.fresh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.entity.SysAttachment;
import com.hzl.fresh.service.ISysAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * 附件表
 *
 * @author yangzhe
 * @since 2022-03-23
 */
@RestController
@RequestMapping("/system/sysAttachment")
public class SysAttachmentController extends ApiController {
    @Autowired
    ISysAttachmentService sysAttachmentService;

    /**
    * 获取附件表
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<SysAttachment> getById(@RequestParam("id") String id){
        return success(sysAttachmentService.getById(id));
    }

    /**
     * 保存或更新附件表
     * @param sysAttachment 保存数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody SysAttachment sysAttachment){
        return success(sysAttachmentService.saveOrUpdate(sysAttachment));
    }
    /**
     * 删除附件表
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id){
        return success(sysAttachmentService.removeById(id));
    }

    /**
     * 附件表列表
     * @param page 页码
     * @param size 大小
     * @param sysAttachment 参数实体
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<SysAttachment>> page(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            SysAttachment sysAttachment
    ) {
        IPage<SysAttachment> pageData = sysAttachmentService.page(new Page<>(page, size), new QueryWrapper<>(sysAttachment));
        return success(pageData);
    }

    /**
     * 文件上传
     * @param file 附件
     * @return 返回结果
     */
    @PostMapping("upload")
    public R<SysAttachment> upload(@RequestParam("file") MultipartFile file){

        return success(sysAttachmentService.saveFileOss(file));
    }

    /**
     * 文件上传
     * @param files 附件
     * @return 返回结果
     */
    @PostMapping("uploads")
    public R<List<SysAttachment>> uploads(MultipartFile[] files){

        return success(sysAttachmentService.saveFileOss(files));
    }
}
