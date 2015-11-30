package com.esolz.su.zipstar.DataType;

/**
 * Created by su on 18/6/15.
 */
public class SerachListviewDataType implements Item{
    String upc14;
    String brand_name;
    String qnt;
    public final String name;
   public final String image;
String product_name;

    public SerachListviewDataType(String brand_name,String upc14, String name, String image,String qnt,String product_name) {
        this.brand_name = brand_name;
        this.upc14 = upc14;
        this.name = name;
        this.image = image;
        this.qnt = qnt;
        this.product_name=product_name;
    }


    public String getProduct_name() {
        return product_name;
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


    public String getImage() {
        return image;
    }


    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    @Override
    public boolean isSection() {
        return false;
    }

    public String getQnt() {
        return qnt;
    }

    public void setQnt(String qnt) {
        this.qnt = qnt;
    }
}
