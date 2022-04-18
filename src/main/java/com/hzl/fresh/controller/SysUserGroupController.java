package com.hzl.fresh.controller;

import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.service.ISysUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 用户角色关联表
 *
 *
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/system/sysUserGroup")
public class SysUserGroupController extends ApiController {
    @Autowired
    ISysUserGroupService sysUserRoleService;

}
