package com.hzl.fresh.utils;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.entity.SysGroup;
import com.hzl.fresh.entity.SysUser;
import com.hzl.fresh.entity.SysUserGroup;
import com.hzl.fresh.service.ISysGroupService;
import com.hzl.fresh.service.ISysUserGroupService;
import com.hzl.fresh.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户信息同步工具
 */
@Slf4j
//@Component
public class UserSyncUtils {
    /**
     * 同步用户组信息
     */
    @Autowired
    public void syncGroup(ISysGroupService groupService) {
        HttpRequest request = HttpUtil.createGet("https://xiangshangsl.com/gateway/system/sysRole/roleList").header("Access-Token", "567sfh27hsavr07atzb8jul6wqm54q4t");
        HttpResponse response = request.execute();
        JSON jsonObj = JSONUtil.parse(response.body());
        JSONArray list = jsonObj.getByPath("data", JSONArray.class);
        List<SysGroup> groups = new ArrayList<>();
        for (Object o : list) {
            JSON obj = (JSON) o;
            groups.add(new SysGroup()
                    .setName(obj.getByPath("name", String.class))
                    .setDesc(obj.getByPath("desc", String.class))
                    .setId(obj.getByPath("id", Integer.class))
                    .setSort(obj.getByPath("sort", Integer.class))
                    .setType(0)
                    .setPid(0)
            );
            JSONArray children = obj.getByPath("children", JSONArray.class);
            for (Object child : children) {
                JSON childObj = (JSON) child;
                groups.add(new SysGroup()
                        .setName(childObj.getByPath("name", String.class))
                        .setDesc(childObj.getByPath("desc", String.class))
                        .setId(10000 + childObj.getByPath("id", Integer.class))
                        .setSort(childObj.getByPath("sort", Integer.class))
                        .setType(1)
                        .setPid(obj.getByPath("id", Integer.class))
                );
            }

        }
        groupService.saveOrUpdateBatch(groups);
        log.info("用户组同步成功");
    }

    /**
     * 同步用户信息
     */
    @Autowired
    public void syncUser(ISysUserService userService) {
        HttpResponse response = HttpUtil.createPost(StrUtil.format("https://xiangshangsl.com/gateway/system/sysUser/page?page=1&size=500"))
                .header("Access-Token", "567sfh27hsavr07atzb8jul6wqm54q4t")
                .execute();
        IPage<SysUser> p = JSONUtil.parse(JSONUtil.parse(response.body()).getByPath("data", String.class)).toBean(new TypeReference<Page<SysUser>>() {
        });
        userService.saveOrUpdateBatch(p.getRecords());
        log.info("用户信息同步成功");
    }

    /**
     * 同步用户角色关联信息
     */
    @Autowired
    public void syncUserGroup(ISysUserGroupService userGroupService) {
        IPage<JSONObject> p = null;
        List<SysUserGroup> sysUserGroups = new ArrayList<>();
        int i = 1;
        do {
            HttpResponse response = HttpUtil.createPost(StrUtil.format("https://xiangshangsl.com/gateway/system/sysUserRole/page?page={}&size=100", i))
                    .header("Access-Token", "567sfh27hsavr07atzb8jul6wqm54q4t")
                    .execute();
            p = JSONUtil.parse(JSONUtil.parse(response.body()).getByPath("data", String.class)).toBean(new TypeReference<Page<JSONObject>>() {
            });
            i++;
            List<SysUserGroup> list = p.getRecords().stream().map(q -> new SysUserGroup().setUserId(q.getByPath("userId", Integer.class)).setGroupId(q.getByPath("roleId", Integer.class))).collect(Collectors.toList());
            sysUserGroups.addAll(list);
        } while (p.getCurrent() < p.getPages());
        Map<Integer, List<SysUserGroup>> maps = sysUserGroups.stream().collect(Collectors.groupingBy(SysUserGroup::getUserId));
        for (Map.Entry<Integer, List<SysUserGroup>> integerListEntry : maps.entrySet()) {
            userGroupService.saveUserGroup(integerListEntry.getKey(),integerListEntry.getValue().stream().map(q-> q.getGroupId()+10000).collect(Collectors.toList()));
        }
        log.info("用户角色分配同步完成");
    }
}
