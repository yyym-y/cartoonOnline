package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Order {
    @TableId("order_id")
    private String orderId;
    private String uid;
    private String buyerId;
    private Integer buyerPayAmount;
    private String tradeNo;
    private Date gmtPayment;
    private Date orderCreate;

    public Order(String orderId, String uid) {
        this.orderId = orderId; this.uid = uid;
        this.orderCreate = new Date();
    }
}
