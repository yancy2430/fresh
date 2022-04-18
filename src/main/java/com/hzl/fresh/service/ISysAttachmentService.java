package com.hzl.fresh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.fresh.entity.SysAttachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 附件表 服务类
 * </p>
 *
 * @author yangzhe
 * @since 2022-03-23
 */
public interface ISysAttachmentService extends IService<SysAttachment> {
    public SysAttachment saveFileOss(MultipartFile file);
    public List<SysAttachment> saveFileOss(MultipartFile[] files);
    SysAttachment saveFileOss(String fileName, InputStream file);
}
