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
 * 邮件记录
 *
 *
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hzl_sys_email_record")
public class SysEmailRecord implements Serializable {

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
    private String sendEmail;

    /**
     * 接收账号
     */
    private String receiveEmail;

    /**
     * 接收ID
     */
    private Integer receiveId;

    /**
     * 发送结果
     */
    private String sendResult;

    /**
     * 接收人
     */
    private String receiveName;


}
