package com.hzl.fresh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * 
 *
 * 
 * @since 2021-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hzl_shop_notice_take")
public class ShopNoticeTake implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer noticeId;

    private Integer userId;

    private LocalDateTime readTime;


}
