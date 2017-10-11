package com.deloitte.micro.orderclient;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


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


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }



    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    @JsonFormat(pattern = "dd-MM-YYYY")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = new Date();
    }




}
