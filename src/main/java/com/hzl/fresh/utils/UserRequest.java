package com.hzl.fresh.utils;

import com.hzl.fresh.entity.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserRequest {
   public static SysUser getSysUser(){
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      return (SysUser)request.getAttribute("ADMIN_USER");
   }

   public static HttpServletRequest getRequest(){
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      return request;
   }
}
