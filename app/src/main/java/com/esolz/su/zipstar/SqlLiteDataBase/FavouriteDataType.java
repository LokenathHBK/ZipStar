package com.esolz.su.zipstar.SqlLiteDataBase;

import com.esolz.su.zipstar.DataType.SerachListviewDataType;

public class FavouriteDataType {

	//private variables
	int _id;
	String _name;
	String _phone_number;
	SerachListviewDataType abc;
	String qnt;
	String upcid;
	String image;
	String product_type;
	public  Boolean checkedBox=false;


	// Empty constructor
	public FavouriteDataType(){


	}
	public FavouriteDataType(SerachListviewDataType abc){
this.abc=abc;
		checkedBox=checkedBox;
	}

	public FavouriteDataType(int _id, String _name, String _phone_number, SerachListviewDataType abc) {
		this._id = _id;
		this._name = _name;
		this._phone_number = _phone_number;
		this.abc = abc;
	}

	public FavouriteDataType(String pname, String bname) {

		this._name = pname;
		this._phone_number = bname;


	}

	public FavouriteDataType(String a, String sitem, String sbrand) {
		this.qnt= a;
		this._name = sitem;
		this._phone_number = sbrand;


	}

	public FavouriteDataType(String product_type) {
		this.product_type = product_type;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_phone_number() {
		return _phone_number;
	}

	public void set_phone_number(String _phone_number) {
		this._phone_number = _phone_number;
	}

	public SerachListviewDataType getAbc() {
		return abc;
	}

	public void setAbc(SerachListviewDataType abc) {
		this.abc = abc;
	}

	public String getQnt() {
		return qnt;
	}

	public void setQnt(String qnt) {
		this.qnt = qnt;
	}

	public String getUpcid() {
		return upcid;
	}

	public void setUpcid(String upcid) {
		this.upcid = upcid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
}
