package com.esolz.su.zipstar.DataType;

/**
 * Created by su on 18/6/15.
 */
public class TimeWindowDataType {

    String userid;
String username;
String rating;
String total_review;
String total_zip;
String timeslot;
String timeslotid;
String zipsterwindowid;
public double cost;
String selected_date;

    public TimeWindowDataType(String userid, String username, String rating, String total_review, String total_zip, String timeslot, String timeslotid, String zipsterwindowid, Double cost, String selected_date) {
        this.userid = userid;
        this.username = username;
        this.rating = rating;
        this.total_review = total_review;
        this.total_zip = total_zip;
        this.timeslot = timeslot;
        this.timeslotid = timeslotid;
        this.zipsterwindowid = zipsterwindowid;
        this.cost = cost;
        this.selected_date = selected_date;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTotal_review() {
        return total_review;
    }

    public void setTotal_review(String total_review) {
        this.total_review = total_review;
    }

    public String getTotal_zip() {
        return total_zip;
    }

    public void setTotal_zip(String total_zip) {
        this.total_zip = total_zip;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getTimeslotid() {
        return timeslotid;
    }

    public void setTimeslotid(String timeslotid) {
        this.timeslotid = timeslotid;
    }

    public String getZipsterwindowid() {
        return zipsterwindowid;
    }

    public void setZipsterwindowid(String zipsterwindowid) {
        this.zipsterwindowid = zipsterwindowid;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSelected_date() {
        return selected_date;
    }

    public void setSelected_date(String selected_date) {
        this.selected_date = selected_date;
    }
}
