package com.hzl.fresh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.fresh.entity.SysEmailRecord;

/**
 * <p>
 * 邮件记录 服务类
 * </p>
 *
 * 
 * @since 2021-09-11
 */
public interface ISysEmailRecordService extends IService<SysEmailRecord> {

    public boolean sendEmail(SysEmailRecord sysEmailRecord);
}
