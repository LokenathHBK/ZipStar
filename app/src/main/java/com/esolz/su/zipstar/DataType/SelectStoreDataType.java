package com.esolz.su.zipstar.DataType;

/**
 * Created by su on 18/6/15.
 */
public class SelectStoreDataType {
String store_id32 ;
String store_namewow;
String store_address1;
String store_statewest;
String pin;
int getdistance;
int already_added;

    public SelectStoreDataType(String store_id32, String store_namewow, String store_address1, String store_statewest, String pin, int getdistance, int already_added) {
        this.store_id32 = store_id32;
        this.store_namewow = store_namewow;
        this.store_address1 = store_address1;
        this.store_statewest = store_statewest;
        this.pin = pin;
        this.getdistance = getdistance;
        this.already_added = already_added;
    }


    public String getStore_id32() {
        return store_id32;
    }

    public void setStore_id32(String store_id32) {
        this.store_id32 = store_id32;
    }

    public String getStore_namewow() {
        return store_namewow;
    }

    public void setStore_namewow(String store_namewow) {
        this.store_namewow = store_namewow;
    }

    public String getStore_address1() {
        return store_address1;
    }

    public void setStore_address1(String store_address1) {
        this.store_address1 = store_address1;
    }

    public String getStore_statewest() {
        return store_statewest;
    }

    public void setStore_statewest(String store_statewest) {
        this.store_statewest = store_statewest;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getGetdistance() {
        return getdistance;
    }

    public void setGetdistance(int getdistance) {
        this.getdistance = getdistance;
    }

    public int getAlready_added() {
        return already_added;
    }

    public void setAlready_added(int already_added) {
        this.already_added = already_added;
    }
}
