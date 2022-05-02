package com.hzl.fresh.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 *
 * 直连记录表
 *
 * @author hzl
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hzl_shop_record")
public class ShopRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 生单时间
     */
    private LocalDateTime bTime;

    /**
     * 订单类别
     */
    private Integer platform;

    /**
     * 起飞
     */
    private String dptAirport;

    /**
     * 到达
     */
    private String arrAirport;

    /**
     * 日期
     */
    private LocalDate date;

    /**
     * 航司
     */
    private String carrier;

    /**
     * 航班号
     */
    private String code;

    /**
     * 起飞时间
     */
    private String dptTime;

    /**
     * 到达时间
     */
    private String arrTime;

    /**
     * 机型
     */
    private String planeType;

    /**
     * 余位
     */
    private Integer remain;

    /**
     * Y仓价格
     */
    private BigDecimal yPrice;

    /**
     * 舱位
     */
    private String cabin;

    /**
     * cpc销售价
     */
    private BigDecimal cpcCabinPrice;

    /**
     * 成人销售价
     */
    private BigDecimal adultPrice;

    /**
     * 票面价
     */
    private BigDecimal printPrice;

    /**
     * 儿童票面价
     */
    private BigDecimal printPriceChild;

    /**
     * 乘机人
     */
    private String manList;

    /**
     * 证件
     */
    private String cardList;

    /**
     * 生日
     */
    private String bdayList;

    /**
     * pnr
     */
    private String pnr;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 支付状态(0:未支付,1:已支付,2:取消支付)
     */
    private Integer state;

    /**
     * 成人人数
     */
    private Integer adultCount;

    /**
     * 儿童人数
     */
    private Integer childCount;

    /**
     * 婴儿人数
     */
    private Integer infantCount;

    /**
     * 儿童销售价格
     */
    private BigDecimal childPrice;

    /**
     * 婴儿价格
     */
    private BigDecimal infantPrice;

    /**
     * 是否拦截 1不拦截 2拦截
     */
    private Integer isIntercept;

    /**
     * 拦截原因
     */
    private String interceptMsg;

    /**
     * 原始乘机人数据
     */
    private String passengerList;

    /**
     * 政策ID
     */
    private Long policyId;

    /**
     * 政策代码
     */
    private String policyCode;

    /**
     * 政策备注
     */
    private String policyRemarks;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 返程出发机场
     */
    private String dptAirportBack;

    /**
     * 返程出发机场
     */
    private String arrAirportBack;

    /**
     * 返程日期
     */
    private LocalDate dateBack;

    /**
     * 返程航司
     */
    private String carrierBack;

    /**
     * 返程航班号
     */
    private String codeBack;

    /**
     * 返程出发时刻
     */
    private String dptTimeBack;

    /**
     * 返程到达时刻
     */
    private String arrTimeBack;

    /**
     * 返程仓位
     */
    private String cabinBack;

    /**
     * 返程政策ID
     */
    private Long policyIdBack;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 退票规则
     */
    private String refundRule;

    /**
     * 改期规则
     */
    private String changeRule;

    /**
     * 转签规则
     */
    private String signRule;

    /**
     * 燃油费
     */
    private BigDecimal fuel;

    /**
     * 儿童燃油费
     */
    private BigDecimal fuelChild;

    /**
     * 成人机建费
     */
    private BigDecimal airTax;

    /**
     * 儿童机建费
     */
    private BigDecimal airTaxChild;

    /**
     * 返程燃油费
     */
    private BigDecimal fuelBack;

    /**
     * 返程儿童燃油费
     */
    private BigDecimal fuelChildBack;

    /**
     * 优质订单
     */
    private Integer hasInvoice;

    /**
     * 退差订单
     */
    private Integer hasInvoiceDifference;

    /**
     * 风险订单
     */
    private Integer hasRiskUser;

    /**
     * 行程单
     */
    private Integer hasInvoiceNow;

    /**
     * 行李额
     */
    private String luggage;

    /**
     * session标识
     */
    private String sessionId;

    /**
     * 拦截时间
     */
    private LocalDateTime orderTime;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 价格校验时间
     */
    private LocalDateTime priceTime;

    /**
     * 销售方式
     */
    private String saleMode;

    /**
     * 是否代理
     */
    private Integer acting;


}
