package com.deloitte.micro.orderservice;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Orders {

    private Long orderId;

    private String orderItem;

    private String customerName;

    private Date orderDate;




    public Orders(String orderItem, String customerName) {
        this.orderItem = orderItem;
        this.customerName = customerName;
    }


    public Orders() {
    }


    @Id
    @GeneratedValue
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    @Column
    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    @Column
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column
    @JsonFormat(pattern = "dd-MM-YYYY")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = new Date();
    }


}
