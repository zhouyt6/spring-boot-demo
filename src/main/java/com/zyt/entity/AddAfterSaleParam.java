package com.zyt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: zhouyt
 * @Date: 2019/09/29
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAfterSaleParam implements Serializable {

    private Long id;

    /**
     * 子订单ID
     */
    @NotNull(message = "子订单编号不能为空")
    private Long subOrderId;

    @NotNull(message = "销售订单编号不能为空")
    private Long saleOrderId;

    @NotNull(message = "采购订单编号不能为空")
    private Long purchaseOrderId;

    @NotNull(message = "售后类型不能为空")
    private Byte afterSaleType;

    @NotBlank(message = "货物状态不能为空")
    private String goodState;

    @NotBlank(message = "申请原因不能为空")
    private String afterSaleReason;

    @NotNull(message = "申请数量不能为空")
    private Integer afterSaleCount;

    /**
     * 补运费
     */
    private Double expressFee;

    /**
     * 申请金额
     */
    private Double afterSaleAmount;



}
