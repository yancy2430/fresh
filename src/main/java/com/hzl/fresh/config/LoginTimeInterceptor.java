package com.hzl.fresh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzl.fresh.core.annotations.NotLogin;
import com.hzl.fresh.core.cache.CacheService;
import com.hzl.fresh.entity.SysUser;
import com.hzl.fresh.core.exception.ApiException;
import com.hzl.fresh.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class LoginTimeInterceptor implements HandlerInterceptor {
    @Autowired
    public CacheService<String,Object> cacheService;
    @Autowired
    public ISysUserService userService;
    @Autowired
    ObjectMapper objectMapper;
    //在控制器执行前调用
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        if (handler.getClass()== ResourceHttpRequestHandler.class){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        NotLogin notLogin = method.getAnnotation(NotLogin.class);
        if (null!=notLogin){//不需要登录
            return true;
        }
        String token = "";
        if (null != request.getCookies()) {
            Cookie c = CookieUtil.getCookie("token");
            if (null!=c){
                token =c.getValue();
            }
        }
        if (null!=token){
            SysUser user = (SysUser) cacheService.get(token);
            if (null!=user){
                request.setAttribute("ADMIN_USER",user);
                return true;
            }
            throw  new ApiException("没有权限");
        }
        throw  new ApiException("没有权限");
    }
}
