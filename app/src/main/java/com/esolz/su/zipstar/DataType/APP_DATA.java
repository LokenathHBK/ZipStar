
package com.esolz.su.zipstar.DataType;


import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class APP_DATA extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	private RequestQueue mRequestQueue;
	private  String TAG = "APP_DATA";
	private  static  APP_DATA mInstance;

	public static String page_set="";
	public static String userid="";
	public static String email="";
	public static String phone="";
	public static String city="";
	public static String pin="";
	public static String state="";

	public static String fname="";
	public static String lname="'";

	public static String name="";
	public static String image="";
	public static String thumb="";
	public static String location="";
	public static String number=null;
	public static String detailrow="";
	public static int chkbox;

	public static String pname="";
	public static String bname="";
	public static String lpname="";
	public static String lbname="";
	public static int upcid;

	public static String addstore="";

	public static String zipster_name="";
	public static String cost="100";
	public static String dateslot="";
	public static String timeslo="";
	public static String zipster_id="";

	public static String store_id="";
	public static String timeslot="";

	public static String orderid="";
	public static String item_orderid="";
	public static String item_orderid_for_review="";

	public static String multipel="";

	public static String prefered_time="";
	public static String prefered_date="";
	public static String Zipsterwindowid="";


	public static synchronized APP_DATA getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}


	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

}
