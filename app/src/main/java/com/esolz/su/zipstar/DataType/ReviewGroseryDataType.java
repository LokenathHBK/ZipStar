package com.esolz.su.zipstar.DataType;

/**
 * Created by su on 18/6/15.
 */
public class ReviewGroseryDataType {
String order_item_id ;
    String brand_name;
String upc14;
String name;
String image;
String quantity;
    String manualy_add;

    public ReviewGroseryDataType(String order_item_id, String brand_name, String upc14, String name, String image, String quantity, String manualy_add) {
        this.order_item_id = order_item_id;
        this.brand_name = brand_name;
        this.upc14 = upc14;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.manualy_add = manualy_add;
    }


    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getUpc14() {
        return upc14;
    }

    public void setUpc14(String upc14) {
        this.upc14 = upc14;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getManualy_add() {
        return manualy_add;
    }

    public void setManualy_add(String manualy_add) {
        this.manualy_add = manualy_add;
    }
}
