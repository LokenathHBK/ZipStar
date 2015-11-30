package com.esolz.su.zipstar.DataType;

import java.util.LinkedList;

/**
 * Created by su on 1/9/15.
 */
public class BrandwiseDeatailsDatatype implements Item {

String brand_name;

    Integer total_item_of_this_brand;
    public LinkedList<SerachListviewDataType> item_details=new LinkedList<SerachListviewDataType>();

    public BrandwiseDeatailsDatatype(String brand_name, Integer total_item_of_this_brand, LinkedList<SerachListviewDataType> item_details) {
        this.brand_name = brand_name;
        this.total_item_of_this_brand = total_item_of_this_brand;
        this.item_details = item_details;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Integer getTotal_item_of_this_brand() {
        return total_item_of_this_brand;
    }

    public void setTotal_item_of_this_brand(Integer total_item_of_this_brand) {
        this.total_item_of_this_brand = total_item_of_this_brand;
    }

    public LinkedList<SerachListviewDataType> getItem_details() {
        return item_details;
    }

    public void setItem_details(LinkedList<SerachListviewDataType> item_details) {
        this.item_details = item_details;
    }

    @Override
    public boolean isSection() {
        return false;
    }
}
