package com.hzl.fresh.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static cn.hutool.extra.spring.SpringUtil.getApplicationContext;

@Component
public class WebConfigure implements WebMvcConfigurer {
    Logger logger = Logger.getLogger("WebConfigure");
    MappingJackson2HttpMessageConverter converter;
    public WebConfigure(@Qualifier("jackson2HttpMessageConverter") MappingJackson2HttpMessageConverter converter) {
        this.converter = converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter);
    }

}
