package com.esolz.su.zipstar.DataType;

public class ManageWindowDataType {

	String time_slot_id;
	String time_slot_start;
	String	 time_slot_end;
	String	 sign_up_for;
	String	 slot_available;
	String	 multiply;


	public  Boolean checkedBox=false;

	public ManageWindowDataType(String time_slot_id, String time_slot_start, String time_slot_end, String sign_up_for, String slot_available, String multiply) {
		this.time_slot_id = time_slot_id;
		this.time_slot_start = time_slot_start;
		this.time_slot_end = time_slot_end;
		this.sign_up_for = sign_up_for;
		this.slot_available = slot_available;
		this.multiply = multiply;
	}

	public String getTime_slot_id() {
		return time_slot_id;
	}

	public void setTime_slot_id(String time_slot_id) {
		this.time_slot_id = time_slot_id;
	}

	public String getTime_slot_start() {
		return time_slot_start;
	}

	public void setTime_slot_start(String time_slot_start) {
		this.time_slot_start = time_slot_start;
	}

	public String getTime_slot_end() {
		return time_slot_end;
	}

	public void setTime_slot_end(String time_slot_end) {
		this.time_slot_end = time_slot_end;
	}

	public String getSign_up_for() {
		return sign_up_for;
	}

	public void setSign_up_for(String sign_up_for) {
		this.sign_up_for = sign_up_for;
	}

	public String getSlot_available() {
		return slot_available;
	}

	public void setSlot_available(String slot_available) {
		this.slot_available = slot_available;
	}

	public String getMultiply() {
		return multiply;
	}

	public void setMultiply(String multiply) {
		this.multiply = multiply;
	}



}