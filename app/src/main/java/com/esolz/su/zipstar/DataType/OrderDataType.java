package com.esolz.su.zipstar.DataType;

/**
 * Created by su on 18/6/15.
 */
public class OrderDataType {

    String order_id;
String userid;
String username;
String delivary_date;
String delivary_time;
String delivary_address;
String total_review;
String user_rating;
String cost;
String order_status;





    public OrderDataType(String order_id, String userid, String username, String delivary_date, String delivary_time, String delivary_address, String total_review, String user_rating, String cost, String order_status) {
        this.order_id = order_id;
        this.userid = userid;
        this.username = username;
        this.delivary_date = delivary_date;
        this.delivary_time = delivary_time;
        this.delivary_address = delivary_address;
        this.total_review = total_review;
        this.user_rating = user_rating;
        this.cost = cost;
        this.order_status = order_status;
    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDelivary_date() {
        return delivary_date;
    }

    public void setDelivary_date(String delivary_date) {
        this.delivary_date = delivary_date;
    }

    public String getDelivary_time() {
        return delivary_time;
    }

    public void setDelivary_time(String delivary_time) {
        this.delivary_time = delivary_time;
    }

    public String getDelivary_address() {
        return delivary_address;
    }

    public void setDelivary_address(String delivary_address) {
        this.delivary_address = delivary_address;
    }

    public String getTotal_review() {
        return total_review;
    }

    public void setTotal_review(String total_review) {
        this.total_review = total_review;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
