package com.hzl.fresh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.fresh.entity.SysConfig;
import com.hzl.fresh.mapper.SysConfigMapper;
import com.hzl.fresh.service.ISysConfigService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 *
 * @since 2021-09-11
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

    @Override
    public Map<String, List<SysConfig>> configGroupList() {
        return new LinkedHashMap<>(list(new QueryWrapper<SysConfig>().lambda()).stream().collect(Collectors.groupingBy(SysConfig::getGroupName)));
    }

    @Override
    public String configVal(String code) {
        String[] codes = code.split("\\.");
        return baseMapper.selectOne(new QueryWrapper<SysConfig>().lambda()
                .eq(SysConfig::getModule,codes[0])
                .eq(SysConfig::getGroup,codes[1])
                .eq(SysConfig::getCode,codes[2])
        ).getValue();
    }

    @Override
    public Map<String, String> configValByGroupCode(String code) {
        String[] codes = code.split("\\.");
        List<SysConfig> configs = baseMapper.selectList(new QueryWrapper<SysConfig>().lambda()
                .eq(SysConfig::getModule, codes[0])
                .eq(SysConfig::getGroup, codes[1])
        );
        Map<String, String> map = configs.stream().collect(Collectors.toMap(SysConfig::getCode, SysConfig::getValue));
        return map;
    }
}
