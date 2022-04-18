package com.hzl.fresh.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.fresh.exception.ApiException;
import com.hzl.fresh.config.properties.UploadProperties;
import com.hzl.fresh.entity.SysAttachment;
import com.hzl.fresh.mapper.SysAttachmentMapper;
import com.hzl.fresh.service.ISysAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 * @author yangzhe
 * @since 2022-03-23
 */
@Service
public class SysAttachmentServiceImpl extends ServiceImpl<SysAttachmentMapper, SysAttachment> implements ISysAttachmentService {

    @Autowired
    UploadProperties uploadProperties;
//    @Autowired
//    OSS ossClient;

    @Transactional
    @Override
    public SysAttachment saveFileOss(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiException("上传失败，请选择文件");
        }
        String filePath= "upload/"+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        if (StrUtil.isNotBlank(uploadProperties.aliyunOssFilePath)){
            filePath = uploadProperties.aliyunOssFilePath+LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        }
        //获取原文件名
        String fileName = file.getOriginalFilename();
        //获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称UUID
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid + suffixName;
        SysAttachment SysAttachment = new SysAttachment()
                .setOriginalName(fileName)
                .setName(newFileName)
                .setPath(filePath + newFileName)
                .setUrl("http://"+uploadProperties.getAliyunOssDomain()+"/"+filePath + newFileName)
                .setFileSize(FileUtil.readableFileSize(file.getSize()));
        baseMapper.insert(SysAttachment);

//        try {
//            PutObjectResult res = ossClient.putObject(uploadProperties.aliyunOssBucketName, filePath+newFileName, file.getInputStream());
//        } catch (IOException e) {
//            throw new ApiException("上传失败，异常信息 " + e.getMessage());
//        }
        return SysAttachment;
    }
    @Override
    public List<SysAttachment> saveFileOss(MultipartFile[] files) {
        List<SysAttachment> SysAttachments = new ArrayList<>();
        for (MultipartFile file : files) {
            SysAttachments.add(saveFileOss(file));
        }
        return SysAttachments;
    }

    @Override
    public SysAttachment saveFileOss(String fileName, InputStream file) {
        //获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称UUID
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;
        SysAttachment SysAttachment = new SysAttachment()
                .setOriginalName(fileName)
                .setName(newFileName)
                .setPath(uploadProperties.aliyunOssFilePath + newFileName);
        baseMapper.insert(SysAttachment);
//        PutObjectResult res = ossClient.putObject(uploadProperties.aliyunOssBucketName, newFileName, file);
        return SysAttachment;
    }
}
