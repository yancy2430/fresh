package com.hzl.fresh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.fresh.entity.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * 
 * @since 2021-09-11
 */
public interface ISysConfigService extends IService<SysConfig> {

    Map<String, List<SysConfig>> configGroupList();

    /**
     * 获取配置项
     * @param code 配置代码
     * @return 配置ID
     */
    String configVal(String code);
    Map<String,String> configValByGroupCode(String code);
}
