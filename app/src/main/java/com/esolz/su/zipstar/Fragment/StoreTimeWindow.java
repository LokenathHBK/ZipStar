package com.esolz.su.zipstar.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.esolz.su.zipstar.Adapter.TimeWindowAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.TimeWindowDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.ListDataType;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProLight;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.helper.JSONParserPost;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by su on 31/8/15.
 */
public class StoreTimeWindow extends Fragment {
    SwipeMenuListView swipelistview;
    View view,v1,v2,v3;
String ordertype;
    DataBaseCreator db;
    LinkedList<ListDataType> listofdata=new LinkedList<ListDataType>();
    ListDataType listDataType;
    int position;
    String   jsonadat;
    LinkedList<TimeWindowDataType> item_detail=new LinkedList<TimeWindowDataType>();
    TimeWindowAdapter adapter;
    private SwipeMenuListView ListView;
    Context context;
    String deletedata;
    ListView listView;
    Contact contact;
    TimeWindowDataType item;
LinearLayout tab1,tab2,tab3,time;
    MyriadProLight nodata;
    MyriadProRegular txtaccept,txtwaiting,txtprev;
    MyriadProRegular nextstore;
    double quantity,sum,total;



    int success;
    SharedPreferences loginPreferences;
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParserPost jsonParser = new JSONParserPost();

    String no_of_item,total_quantity;
    String qnt;
    ArrayList smartPhones = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragstore_time_window, container, false);


        

        getActivity().getPackageManager();


        txtaccept=(MyriadProRegular)view.findViewById(R.id.txtaccept);
                txtwaiting=(MyriadProRegular)view.findViewById(R.id.txtwaiting);
                txtprev=(MyriadProRegular)view.findViewById(R.id.txtprevt);
nodata=(MyriadProLight)view.findViewById(R.id.nodata);
        nextstore=(MyriadProRegular)view.findViewById(R.id.next);
        tab1=(LinearLayout)view.findViewById(R.id.acceptorder);
        time=(LinearLayout)view.findViewById(R.id.time);
        tab2=(LinearLayout)view.findViewById(R.id.waitingorder);
        tab3=(LinearLayout)view.findViewById(R.id.prevorder);
        v1=(View)view.findViewById(R.id.v1);
        v2=(View)view.findViewById(R.id.v2);
        v3=(View)view.findViewById(R.id.v3);

listView=(ListView)view.findViewById(R.id.order_list);


        db = new DataBaseCreator(getActivity());
        listofdata=db.getAllList();

        listDataType=listofdata.get(position);
        no_of_item= String.valueOf(listofdata.size());
        for (int i=0;i<listofdata.size();i++) {



            qnt=listDataType.getQnt();

            quantity= Double.parseDouble(qnt);

            if (quantity == 1) {
                sum = quantity + (.25 * 1);
            } else {
                sum = quantity + (.25 * (quantity - 1));

            }
            total += sum;
            total_quantity= String.valueOf(total);
        }
//Toast.makeText(getActivity(),total_quantity,Toast.LENGTH_LONG).show();

//        new StoreSearch().execute();



        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtaccept.setTextColor(Color.parseColor("#DA1E00"));
                txtwaiting.setTextColor(Color.parseColor("#000000"));
                txtprev.setTextColor(Color.parseColor("#000000"));
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                new StoreSearch().execute();
                ordertype = "1";

//                new StoreSearch().execute();

            }
        });
        nextstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent i=new Intent(getActivity(),SelectStore.class);
                startActivity(i);

            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtaccept.setTextColor(Color.parseColor("#000000"));
                txtwaiting.setTextColor(Color.parseColor("#DA1E00"));
                txtprev.setTextColor(Color.parseColor("#000000"));
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.GONE);
                ordertype = "2";
                Collections.sort(item_detail, new PriceComparator() {
                    @Override
                    public int compare(Object lhs, Object rhs) {
                        return 0;
                    }

                    @Override
                    public int compare(TimeWindowDataType lhs, TimeWindowDataType rhs) {
                        return ( lhs.cost < rhs.cost ) ? -1: (lhs.cost > rhs.cost) ? 1:0 ;


                    }
                });
                listView.setAdapter(new TimeWindowAdapter(getActivity(),0,item_detail));
//                new StoreSearch().execute();

            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.VISIBLE);

                txtaccept.setTextColor(Color.parseColor("#000000"));
                txtwaiting.setTextColor(Color.parseColor("#000000"));
                txtprev.setTextColor(Color.parseColor("#DA1E00"));
                ordertype="3";
//                new StoreSearch().execute();
            }
        });


        db = new DataBaseCreator(getActivity());
        listofdata=db.getAllList();
        loginPreferences = getActivity().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("");

                alertDialog.setMessage("Are you sure to place this order ?");

                alertDialog.setIcon(R.drawable.prefered_icon);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();


                                dialog.cancel();


                                JSONArray jsonArray = new JSONArray();

                                listDataType=listofdata.get(position);



                                for (int i = 0; i < listofdata.size(); i++)



                                {

                                    JSONObject lst=new JSONObject();

                                    try {
                                        lst.put("manuallyadded",""+0);
                                        lst.put("name",listDataType.getName());
                                        lst.put("quantity",""+Integer.valueOf(listDataType.getQnt()));
                                        lst.put("img",listDataType.getImage());
                                        lst.put("upc14", ""+Long.valueOf(listDataType.getUpcid()));
                                        lst.put("brand_name",listDataType.getPhoneNumber());


                                        jsonArray.put(lst);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                jsonadat = jsonArray.toString();
                                jsonData();



                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                                alertDialog.setTitle("");

                                alertDialog.setMessage("Your order place successfully.The Zipster will be sent a message to accept your delivery.They will have five minutes for accepet.");

                                alertDialog.setIcon(R.drawable.prefered_icon);

                                alertDialog.setPositiveButton("YES",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Write your code here to execute after dialog
//                                                Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();

//                                                ZipSterCountDown fragment2 = new ZipSterCountDown();
//                                                FragmentManager fm = getFragmentManager();
//                                                FragmentTransaction transaction = fm.beginTransaction();
//                                                transaction.replace(R.id.addstore_container1, fragment2);
//                                                transaction.commit();

                                            }
                                        });

                                // Showing Alert Message
                                alertDialog.show();



                            }
                        });
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                // Showing Alert Message
                alertDialog.show();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item=item_detail.get(position);

//               APP_DATA.cost= item.getCost();
                APP_DATA.zipster_name=  item.getUsername();
                APP_DATA.dateslot=  item.getSelected_date();
                APP_DATA.timeslot=  item.getTimeslot();
                APP_DATA.Zipsterwindowid=item.getZipsterwindowid();
                APP_DATA.zipster_id=item.getUserid();

                ReviewZipsterFragment fragment2 = new ReviewZipsterFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.addstore_container1, fragment2);
                transaction.commit();

            }
        });

        return view;
    }

//    for comparing start from hear



    public static abstract class PriceComparator implements Comparator {


        public int compare(TimeWindowDataType lhs, TimeWindowDataType rhs) {
            return ( lhs.cost < rhs.cost ) ? -1: (lhs.cost > rhs.cost) ? 1:0 ;


        }

    }


    //++++++++++++++++++++Volley reques


    //-----------------------





    class PlaceOrder extends AsyncTask<String, String, String> {

        boolean failure = false;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
        @Override

        protected String doInBackground(String... args) {

            // TODO Auto-generated method stub

            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
               String url="http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=add_order&grocerydetails="  + URLEncoder.encode(jsonadat, "UTF-8")+","+"&userid="  +URLEncoder.encode(APP_DATA.userid, "UTF-8")+"&storeid="+APP_DATA.store_id+"&zipsterid=0&orderdate="+APP_DATA.prefered_date+"&ordertime="+APP_DATA.prefered_time+"&timeslotid=0&total_cost="+APP_DATA.cost+"&location="+APP_DATA.location+"&state="+APP_DATA.state+"&city="+APP_DATA.city+"&pin="+APP_DATA.pin;
                JSONObject json=jsonParser.makeHttpRequest("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=add_order&grocerydetails="  + URLEncoder.encode(jsonadat, "UTF-8")+","+"&userid="  +URLEncoder.encode(APP_DATA.userid, "UTF-8")+"&storeid="+APP_DATA.store_id+"&zipsterid=0&orderdate="+APP_DATA.prefered_date+"&ordertime="+APP_DATA.prefered_time+"&timeslotid=0&total_cost="+APP_DATA.cost+"&location="+APP_DATA.location+"&state="+APP_DATA.state+"&city="+APP_DATA.city+"&pin="+APP_DATA.pin, "POST", params);


                Log.d("request!", url.toString());

                Log.d("request!", "starting");

                success = json.getInt(TAG_SUCCESS);

                if (success == 0) {

                    Log.d("User Login!", json.toString());

                    JSONObject c=json.getJSONObject("response");
                    APP_DATA.orderid=c.getString("orderid");


                    loginPreferences = getActivity().getSharedPreferences("loginPreferences", 0);
                    SharedPreferences.Editor editor = loginPreferences.edit();
                    editor.putString("orderid",APP_DATA.orderid);


                    editor.putString("Remember", "remember");
                    editor.commit();

                    return json.getString(TAG_MESSAGE);

                }else{

                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));

                    return json.getString(TAG_MESSAGE);
                }

            }
            catch (JSONException e) {

                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {

            Toast.makeText(getActivity(),jsonadat,Toast.LENGTH_LONG).show();
Log.e("ssswww",jsonadat.toString());


        }}





    public class StoreSearch extends AsyncTask<Void,Void,Void> {


        InputStream is;
        String json;
        JSONArray json_arr;

        JSONObject all_news_list_object;

        @Override
        protected void onPreExecute() {



            super.onPreExecute();
//            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {


                DefaultHttpClient httClient = new DefaultHttpClient();


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=search_window&prefer_date="+APP_DATA.prefered_date+"&prefer_time="+APP_DATA.prefered_time+"&store_id="+APP_DATA.store_id+"&no_of_item="+no_of_item+"&total_quantity="+total_quantity+"&user_id="+APP_DATA.userid);

                HttpResponse response = httClient.execute(http_get);

                HttpEntity httpEntity = response.getEntity();

                is = httpEntity.getContent();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));

                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");
                }

                is.close();

                json = sb.toString();

            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }

            try {

                all_news_list_object = new JSONObject(json);

            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            try {
                item_detail = new LinkedList<>();
                //String total_data = all_news_list_object.getString("all_user");
                json_arr = all_news_list_object.getJSONArray("response");


                if (json_arr.length() != 0) {

                    for (int i = 0; i < json_arr.length(); i++) {
                        JSONObject Json_Obj_temp;
                        Json_Obj_temp = json_arr.getJSONObject(i);
                        TimeWindowDataType obj=new TimeWindowDataType(Json_Obj_temp.getString("userid"),
                                Json_Obj_temp.getString("username"),
                                Json_Obj_temp.getString("rating"),
                                Json_Obj_temp.getString("total_review"),
                                Json_Obj_temp.getString("total_zip"),
                                Json_Obj_temp.getString("timeslot"),
                                Json_Obj_temp.getString("timeslotid"),
                                Json_Obj_temp.getString("zipsterwindowid"),
                                Json_Obj_temp.getDouble("cost"),
                                Json_Obj_temp.getString("selected_date"));
                        item_detail.add(obj);



                    }
                }




            }catch (Exception e){

                e.printStackTrace();

            }


            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
//            progressBar.setVisibility(View.GONE);

            adapter = new TimeWindowAdapter(getActivity(), 0, item_detail);
            listView.setAdapter(adapter);


        }


    }

protected void jsonData()
{

    final String URL = "http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php";

    StringRequest sr = new StringRequest(Request.Method.POST, URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response2) {

                    //Log.d("RESPONSE Create Album", response2.toString());
                    try {
                        Log.i("grocerydetailsjsonadat ", response2);
                    } catch (Exception ex) {

                    }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getActivity(), "Failed to create album. Try again..!", Toast.LENGTH_LONG).show();

            }
//                ---------------
            // Log.i("AlbumCreate Error", "" + error);


    })

    {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            try {
            params.put("mode", "add_order");
            //params.put("grocerydetails",  URLEncoder.encode(jsonadat, "UTF-16"));
            params.put("grocerydetails",  jsonadat);
                Log.i("grocerydetails jsonadat", jsonadat);
                Log.i("grocerydetails", "===============================================");
                Log.i("grocerydetails jsonadat",jsonadat);

            params.put("userid", URLEncoder.encode(APP_DATA.userid, "UTF-8"));
                params.put("storeid", APP_DATA.store_id);
                params.put("zipsterid",  "" + 0);
                params.put("orderdate", APP_DATA.prefered_date);
                params.put("ordertime", APP_DATA.prefered_time);
                params.put("timeslotid",  "" + 0);
                params.put("total_cost", APP_DATA.cost);
                params.put("location", APP_DATA.location);
                params.put("zipsterwindowid",  "" + 0);
                params.put("state", APP_DATA.state);
                params.put("city", APP_DATA.city);
                params.put("pin", APP_DATA.pin);
            }catch (Exception e) {
                e.printStackTrace();
        }

            return params;
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("Content-Type", "application/x-www-form-urlencoded");
            return params;
        }
    };
    APP_DATA.getInstance().addToRequestQueue(sr);

}
}