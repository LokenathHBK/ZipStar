package com.esolz.su.zipstar.SqlLiteDataBase;

import com.esolz.su.zipstar.DataType.SerachListviewDataType;
import com.esolz.su.zipstar.DataType.TimeWindowDataType;

public class ListDataType {

	//private variables

	int _id;
	String qnt="1";
	String _name;
	String _phone_number;
	String upcid;
	String image;
 	SerachListviewDataType abc;
	FavouriteDataType favouriteDataType;

	public ListDataType(SerachListviewDataType abc) {
		this.abc = abc;
	}

	// Empty constructor
	public ListDataType(){


	}
	// constructor
	public ListDataType(int id, String name, String _phone_number){
		this._id = id;
		this._name = name;
		this._phone_number = _phone_number;
	}

	// constructor
	public ListDataType(String name, String _phone_number){
		this._name = name;
		this._phone_number = _phone_number;
	}

	public ListDataType(String qnt) {
		this.qnt = qnt;
	}

	public ListDataType(String detailrow, String lpname, String lbname) {
		this.qnt = detailrow;
		this._name = lpname;
		this._phone_number=lbname;
	}

	public ListDataType(FavouriteDataType item) {
		this.favouriteDataType=item;
	}


	// getting ID
	public int getID(){
		return this._id;
	}

	// setting id
	public void setID(int id){
		this._id = id;
	}

	// getting name
	public String getName(){
		return this._name;
	}

	// setting name
	public void setName(String name){
		this._name = name;
	}

	// getting phone number
	public String getPhoneNumber(){
		return this._phone_number;
	}

	// setting phone number
	public void setPhoneNumber(String phone_number){
		this._phone_number = phone_number;
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
}
