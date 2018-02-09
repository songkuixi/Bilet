package com.krayc.vo;

import com.krayc.model.EventEntity;
import com.krayc.model.OrderEntity;
import com.krayc.util.DateFormatter;

import java.sql.Date;

public class OrderVO {

    private int oid;
    private String orderTime;
    private String status;
    private EventEntity eventByEid;
    private int seatNumber;
    private String memberEmail;
    private String type;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EventEntity getEventByEid() {
        return eventByEid;
    }

    public void setEventByEid(EventEntity eventByEid) {
        this.eventByEid = eventByEid;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public OrderVO(OrderEntity orderEntity) {
        Date date = new Date(orderEntity.getOrderTime().getTime());
        String status = "";
        switch (orderEntity.getStatus()) {
            case 0:
                status = "等待付款";
                break;
            case 1:
                status = "等待检票";
                break;
            case 2:
                status = "已取消";
                break;
            case 3:
                status = "已退款";
                break;
            case 4:
                status = "已检票";
                break;
            default:
                break;
        }
        this.oid = orderEntity.getOid();
        this.status = status;
        this.orderTime = DateFormatter.getDateFormatter().stringFromDate(date);
        this.eventByEid = orderEntity.getEventByEid();
        this.seatNumber = orderEntity.getOrderEventSeats().size();
        this.memberEmail = orderEntity.getMemberByMid().getEmail();
        switch (orderEntity.getType()) {
            case 0:
                this.type = "选座购买";
                break;
            case 1:
                this.type = "现场购买";
                break;
            case 2:
                this.type = "不选座购买";
                break;
        }
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
