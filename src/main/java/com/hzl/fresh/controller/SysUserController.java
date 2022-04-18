package com.hzl.fresh.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.annotations.NotLogin;
import com.hzl.fresh.cache.CacheService;
import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.exception.ApiException;
import com.hzl.fresh.config.CookieUtil;
import com.hzl.fresh.entity.*;
import com.hzl.fresh.service.*;
import com.hzl.fresh.utils.Md5Util;
import com.hzl.fresh.utils.NetworkUtils;
import com.hzl.fresh.utils.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理员表
 *
 *
 * @since 2021-07-22
 */
@Slf4j
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController extends ApiController {
    @Autowired
    ISysUserService sysUserService;
    @Autowired
    CacheService<String, Object> cacheService;
    @Autowired
    ISysGroupService sysGroupService;

    /**
     * 获取管理员表
     *
     * @param id 数据ID
     * @return 返回结果
     */
    @RequestMapping("getById")
    public R<SysUser> getById(@RequestParam("id") String id) {
        return success(sysUserService.getById(id));
    }

    /**
     * 添加管理员表
     *
     * @param user 添加数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody SysUser user) {
        return success(sysUserService.saveOrUpdate(user));
    }

    /**
     * 更新管理员表
     *
     * @param sysUser 更新数据
     * @return 返回结果
     */
    @PostMapping("updateById")
    public R<Boolean> updateById(@RequestBody SysUser sysUser) {
        return success(sysUserService.updateById(sysUser));
    }

    /**
     * 删除管理员表
     *
     * @param id 数据ID
     * @return 返回结果
     */
    @PostMapping("removeById")
    public R<Boolean> removeById(@RequestParam String id) {
        return success(sysUserService.removeById(id));
    }

    /**
     * 管理员表列表
     *
     * @param page     页码
     * @param size     大小
     * @param username 用户名
     * @param groupId  角色ID
     * @return 返回结果
     */
    @RequestMapping("page")
    public R<IPage<SysUser>> page(@RequestParam("page") Integer page,
                                  @RequestParam("size") Integer size,
                                  @RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "groupId", required = false) Integer groupId
    ) {

        IPage<SysUser> pageData = sysUserService.page(new Page<>(page, size),new QueryWrapper<SysUser>().lambda()
                .func(q -> {
                    if (StrUtil.isNotBlank(username)) {
                        q.eq(SysUser::getUsername, username);
                    }
                })
                .func(q -> {
                    if (null != groupId) {
                        List<Integer> groupIds = sysGroupService.listObjs(new QueryWrapper<SysGroup>().lambda().select(SysGroup::getId).eq(SysGroup::getPid, groupId), o -> ((Long) o).intValue());
                        groupIds.add(groupId);
                        q.exists(StrUtil.format("select user_id from td_sys_user_group where user_id=td_sys_user.id and group_id in ({})", StrUtil.join(",", groupIds)));
                    }
                })

        );
        return success(pageData);
    }
    @Autowired
    ISysLoginLogService loginLogService;
    @Autowired
    HttpServletResponse response;
    /**
     * 管理员登录
     *
     * @param username       账号
     * @param password       密码
     * @param loginType      登录账号类型 0 email, 1 username, 2 telephone
     * @param validationCode 验证码
     * @return 登录token
     */
    @NotLogin
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public R<String> login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "loginType", required = false, defaultValue = "1") Integer loginType, @RequestParam(value = "validationCode") String validationCode) {
        SysLoginLog loginLog = new SysLoginLog();
        SysUser user = null;
        try {
            Cookie vCodeCookie = CookieUtil.getCookie("vCode");
            if (null == vCodeCookie) {
                throw new ApiException("图片验证码错误");
            }
            Object vCode = cacheService.get("vCode_" + vCodeCookie.getValue());
            if (!validationCode.equals(vCode)) {
                throw new ApiException("图片验证码错误");
            }
            user = sysUserService.getOne(new QueryWrapper<SysUser>().lambda()
                    .func(q -> {
                        if (loginType == 0) { //0 email, 1 username, 2 telephone
                            q.eq(SysUser::getEmail, username);
                        } else if (loginType == 2) {
                            q.eq(SysUser::getPhone, username);
                        } else {
                            q.eq(SysUser::getUsername, username);
                        }
                    })
            );
            if (null == user) {
                throw new ApiException("登录错误，请检查账号或密码是否正确");
            }
//            String pass = MD5.create().digestHex(user.getSalt() + password + MD5.create().digestHex(user.getSalt()));
            String pass = Md5Util.getMD5(user.getSalt() + password + Md5Util.getMD5(user.getSalt()));
            if (pass.equals(user.getPassword())) {
                String token = RandomUtil.randomString(32);
                if (cacheService.set(token, user, 1000 * 60 * 60 * 24)) {
                    user.setLastLoginIp(NetUtil.getLocalMacAddress());
                    user.setLastLoginTime(LocalDateTime.now());
                    sysUserService.updateById(user);
                    Cookie cookie = new Cookie("token", token);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    loginLog.setLoginIp(NetworkUtils.getIpAddress(request));
                    loginLog.setLoginTime(LocalDateTime.now());
                    loginLog.setUserId(user.getId());
                    loginLog.setUsername(user.getUsername());
                    loginLog.setLoginUa(request.getHeader("User-Agent"));
                    return success(token);
                } else {
                    throw new ApiException("系统异常，请联系管理员");
                }
            }else {
                throw new ApiException("登录错误，请检查账号或密码是否正确");
            }

        }catch (Exception exception){
            loginLog.setLoginIp(NetUtil.getLocalMacAddress());
            loginLog.setLoginTime(LocalDateTime.now());
            if (null!=user){
                loginLog.setUserId(user.getId());
                loginLog.setUsername(user.getUsername());
            }else {
                loginLog.setUsername(username);
            }
            loginLog.setLoginUa(request.getHeader("User-Agent"));
        }finally {
            loginLogService.save(loginLog);
        }

        return failed("登录错误，请检查账号或密码是否正确");

    }

    @Autowired
    public HttpServletRequest request;
//    @Resource
//    public HttpServletResponse response;
    /**
     * 验证码
     */
    @NotLogin
    @RequestMapping("validationCode")
    public R<String> validationCode() {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 80, 4, 1);
        String vCodeToKen = RandomUtil.randomString(32);
        response.addCookie(new Cookie("vCode", vCodeToKen));
        cacheService.set("vCode_" + vCodeToKen, captcha.getCode());
        return R.ok(captcha.getImageBase64Data());
    }

    /**
     * 验证验证码
     *
     * @param code
     * @return
     */
    @NotLogin
    @RequestMapping("validation")
    public R<Boolean> validation(String code) {
        Cookie vCodeCookie = CookieUtil.getCookie("vCode");
        if (null == vCodeCookie) {
            return R.failed("图片验证码错误");
        }
        Object vCode = cacheService.get("vCode_" + vCodeCookie.getValue());
        if (!code.equals(vCode)) {
            return R.failed("图片验证码错误");
        }
        return R.ok(true);
    }

    /**
     * 退出登录
     *
     * @return 退出成功
     */
    @RequestMapping("logout")
    public R<Boolean> logout() {

        cacheService.remove(UserRequest.getRequest().changeSessionId());
        return success(true);
    }


    /**
     * 通过邮箱重置密码
     *
     * @param username 用户名
     * @param email    邮箱
     * @return 是否发送邮件成功
     */
    @RequestMapping(value = "resetPassByEmail", method = RequestMethod.POST)
    public R<Boolean> resetPassByEmail(@RequestParam("username") String username, @RequestParam("email") String email) {
        SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>().lambda()
                .eq(SysUser::getUsername, username)
                .eq(SysUser::getEmail, email)
        );
        if (null != user) {
            try {
//                emailService.sendEmail(user.getEmail(), "密码重置验证", "您最近提交了一个重置密码的请求,若您没有,请忽略,该邮件将在2h后失效<br /> 您的验证链接是:\n" +
//                        "<a href='https://hentaissr.me/password/reset/use/key/3c57e3c5fbfde9b381859d28d65fc3f792c85aa192e8f13a65c86b93e971b59b'>https://hentaissr.me/password/reset/use/key/3c57e3c5fbfde9b381859d28d65fc3f792c85aa192e8f13a65c86b93e971b59b</a>" +
//                        "\n<br />" +
//                        "请点击链接以重置密码.");
            } catch (Exception e) {
                e.printStackTrace();
                return failed("邮箱发送失败");
            }

        } else {

        }
        return failed("账号不存在或者绑定邮箱不符");
    }


    /**
     * 重置密码
     *
     * @param key
     * @param password
     * @return
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public R<Boolean> resetPassword(@RequestParam("key") String key, @RequestParam("password") String password) {
        SysUser sysUser = (SysUser) cacheService.get(key);
        String salt = RandomUtil.randomString(6);
        String pass = MD5.create().digestHex(sysUser.getSalt() + password + MD5.create().digestHex(sysUser.getSalt()));
        sysUser.setSalt(salt);
        sysUser.setPassword(pass);
        sysUserService.updateById(sysUser);
        return success(true);
    }

    @Autowired
    ISysGroupResourcesService sysGroupResourcesService;
    @Autowired
    ISysUserGroupService sysUserRoleService;
    @Autowired
    ISysResourcesService sysResourcesService;

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @RequestMapping(value = "userInfo")
    public R<SysUser> userInfo() {
        SysUser sysUser = UserRequest.getSysUser();
        SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>().lambda()
                .select(SysUser::getUsername, SysUser::getName, SysUser::getId, SysUser::getAvatar, SysUser::getPhone, SysUser::getStatus, SysUser::getEmail, SysUser::getLastLoginTime, SysUser::getLastLoginIp)
                .eq(SysUser::getId, sysUser.getId()));
        List<Object> roleIds = sysUserRoleService.listObjs(new QueryWrapper<SysUserGroup>().lambda().select(SysUserGroup::getGroupId).eq(SysUserGroup::getUserId, user.getId()));
        return success(user);
    }


    /**
     * 获取用户可用菜单
     *
     * @return
     */
    @RequestMapping(value = "menu")
    public R<List<SysResources>> menu() {
        SysUser sysUser = UserRequest.getSysUser();
        List<Object> roleIds = sysUserRoleService.listObjs(new QueryWrapper<SysUserGroup>().lambda().select(SysUserGroup::getGroupId).eq(SysUserGroup::getUserId, sysUser.getId()));
        List<String> codes = (List) sysGroupResourcesService.listObjs(new QueryWrapper<SysGroupResources>().lambda().select(SysGroupResources::getCode).in(SysGroupResources::getGroupId, roleIds));

        List<SysResources> list = sysResourcesService.list(new QueryWrapper<SysResources>().lambda()
                .in(SysResources::getCode, codes)
                .orderByAsc(SysResources::getSort).orderByAsc(SysResources::getId)
        );
//        for (SysResources sysResources : list) {
//            List<Map<String,String>> mapList = new ArrayList<>();
//            if (null != sysResources.getBtns()) {
//                for (Map<String,String> btn : sysResources.getBtns()) {
//                    if (codes.contains(btn.get("code"))) {
//                        mapList.add(btn);
//                    }
//                }
//            }
//            sysResources.setBtns(mapList);
//        }
        return success(list);
    }


    /**
     * 锁定后台用户
     *
     * @param id 用户ID
     * @return 锁定结果
     */
    @RequestMapping("lockSys")
    public R<Boolean> lockSys(@RequestParam("id") Integer id) {
        boolean res = sysUserService.update(new UpdateWrapper<SysUser>().lambda().set(SysUser::getStatus, 1).eq(SysUser::getId, id));
        return success(res);
    }

    /**
     * 解锁后台用户
     *
     * @param id 用户ID
     * @return
     */
    @RequestMapping("unlockSys")
    public R<Boolean> unlockSys(@RequestParam("id") Integer id) {
        boolean res = sysUserService.update(new UpdateWrapper<SysUser>().lambda().set(SysUser::getStatus, 0).eq(SysUser::getId, id));
        return success(res);
    }

    /**
     * 通过ID获取权限组ID
     *
     * @param id 用户ID
     * @return 权限组ID
     */
    @RequestMapping("getRoleByUid")
    public R<List<Integer>> getRoleByUid(@RequestParam("id") Integer id) {
        List<SysUserGroup> roles = sysUserRoleService.list(new QueryWrapper<SysUserGroup>().lambda().select(SysUserGroup::getGroupId).eq(SysUserGroup::getUserId, id));
        return success(roles.stream().map(SysUserGroup::getGroupId).collect(Collectors.toList()));
    }

    /**
     * 保存分配的角色
     *
     * @param id      用户ID
     * @param roleIds 角色列表
     * @return 保存结果
     */
    @RequestMapping("saveRole")
    public R<Boolean> saveRole(@RequestParam("id") Integer id, @RequestBody List<Integer> roleIds) {
        return success(sysUserRoleService.saveUserGroup(id, roleIds));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping("loginUser")
    public R<SysUser> loginUser() {
        return success(sysUserService.getById(UserRequest.getSysUser().getId()));
    }

    /**
     * 更新客服
     *
     * @param user
     * @return
     */
    @RequestMapping("saveCustomer")
    public R<Boolean> saveCustomer(@RequestBody SysUser user) {
        user.setId(UserRequest.getSysUser().getId());
        return success(sysUserService.saveOrUpdate(user));
    }
}
