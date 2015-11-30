package com.esolz.su.zipstar.SqlLiteDataBase;

import com.esolz.su.zipstar.DataType.BrandwiseDeatailsDatatype;
import com.esolz.su.zipstar.DataType.SerachListviewDataType;

public class Contact {
	
	//private variables
	int _id;
	String _name;
	String _phone_number;
	SerachListviewDataType abc;
	BrandwiseDeatailsDatatype bcd;
	String quantity;
	String manually_addes;
	String product_type;

	public Contact(String product_type, String manually_addes, String quantity) {
		this.product_type = product_type;
		this.manually_addes = manually_addes;
		this.quantity = quantity;
	}

	public Contact(SerachListviewDataType abc) {
		this.abc = abc;
	}
	public Contact(BrandwiseDeatailsDatatype bcd) {
		this.bcd = bcd;
	}

	// Empty constructor
	public Contact(){

		
	}
	// constructor
	public Contact(int id, String name, String _phone_number){
		this._id = id;
		this._name = name;
		this._phone_number = _phone_number;
	}
	
	// constructor
	public Contact(String name, String _phone_number){
		this._name = name;
		this._phone_number = _phone_number;
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

	public String getQuantity() {
		return quantity;
	}

	public String getManually_addes() {
		return manually_addes;
	}

	public String getProduct_type() {
		return product_type;
	}
}
