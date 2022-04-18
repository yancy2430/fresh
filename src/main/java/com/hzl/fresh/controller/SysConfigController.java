package com.hzl.fresh.controller;

import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.entity.SysConfig;
import com.hzl.fresh.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *
 * 系统配置
 * @since 2021-09-11
 */
@RestController
@RequestMapping("/system/sysConfig")
public class SysConfigController extends ApiController {
    @Autowired
    ISysConfigService sysConfigService;

    /**
     * 获取配置分组数据
     * @return
     */
    @RequestMapping("/configGroupList")
    public R<Map<String, List<SysConfig>>> configGroupList(){
        return success(sysConfigService.configGroupList());
    }

    /**
     * 保存配置数据
     * @param configList 配置数据
     * @return 执行结果
     */
    @RequestMapping("saveConfig")
    public R<Boolean> saveConfig(@RequestBody List<SysConfig> configList){
        sysConfigService.updateBatchById(configList);
        return success(true);
    }

}
