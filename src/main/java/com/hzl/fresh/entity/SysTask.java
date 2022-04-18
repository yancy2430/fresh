package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * 计划任务
 *
 * 
 * @since 2021-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_sys_task")
public class SysTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * 任务脚本
     */
    private String script;

    /**
     * 任务类型
     */
    private Integer taskType;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 最新执行结果
     */
    private String latestResult;


}
