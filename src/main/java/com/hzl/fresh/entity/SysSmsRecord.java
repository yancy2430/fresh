package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * 短信记录
 *
 * 
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_sys_sms_record")
public class SysSmsRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发送标题
     */
    private String title;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 发送账号
     */
    private String sendSms;

    /**
     * 接收账号
     */
    private String receiveSms;

    /**
     * 接收ID
     */
    private Integer receiveId;

    /**
     * 接收人
     */
    private Integer receiveName;

    /**
     * 发送结果
     */
    private String sendResult;


}
