package com.hzl.fresh.controller;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzl.fresh.core.ApiController;
import com.hzl.fresh.core.R;
import com.hzl.fresh.entity.SysResources;
import com.hzl.fresh.service.ISysResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 资源管理
 *
 * 
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/system/sysResources")
public class SysResourcesController extends ApiController {
    @Autowired
    ISysResourcesService sysResourcesService;
    @Autowired
    WebApplicationContext applicationContext;

    /**
    * 获取资源管理
    * @param id 数据ID
    * @return 返回结果
    */
    @RequestMapping("getById")
    public R<SysResources> getById(@RequestParam("id") String id){
        return success(sysResourcesService.getById(id));
    }

    /**
     * 添加资源管理
     * @param sysResources 添加数据
     * @return 返回结果
     */
    @PostMapping("save")
    public R<Boolean> save(@RequestBody SysResources sysResources){
        sysResources.setCode(MD5.create().digestHex16(sysResources.getComponent()+sysResources.getName()+sysResources.getPid()));
        sysResources.setPath(sysResources.getComponent().replace("./","/").replace(".vue","").toLowerCase(Locale.ROOT));
        return success(sysResourcesService.saveOrUpdate(sysResources));
    }

    /**
     * 更新资源管理
     * @param sysResources 更新数据
     * @return 返回结果
     */
    @PostMapping("updateById")
    public R<Boolean> updateById(@RequestBody SysResources sysResources){
        return success(sysResourcesService.updateById(sysResources));
    }

    /**
     * 删除资源管理
     * @param ids 数据ID
     * @return 返回结果
     */
    @PostMapping("removeById")
    public R<Boolean> removeById(@RequestBody List<String> ids){

        return success(sysResourcesService.removeByIds(ids));
    }

    /**
     * 资源管理列表
     * @param page 页码
     * @param size 大小
     * @param query 查询实体|com.hzl.fresh.entity.SysResources
     * @return 返回结果
     */
    @PostMapping("page")
    public R<IPage<SysResources>> page(@RequestParam("page") Integer page,@RequestParam("size") Integer size,@RequestBody QueryWrapper<SysResources> query) {
        IPage<SysResources> pageData = sysResourcesService.page(new Page<>(page, size), query);
        return success(pageData);
    }

    /**
     * 资源列表
     * @return
     */
    @PostMapping("list")
    public R<List<SysResources>> list() {
        List<SysResources> pageData = sysResourcesService.list();
        return success(pageData);
    }
    /**
     * 获取所有的方法接口
     * @return
     */
    @RequestMapping("methodAll")
    public R<List<Map<String, String>>> methods() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, String> map1 = new HashMap<String, String>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                map1.put("url", url);
            }
            map1.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            map1.put("method", method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                map1.put("type", requestMethod.toString());
            }
            list.add(map1);
        }
        return success(list.stream().distinct().collect(Collectors.toList()));
    }
}
