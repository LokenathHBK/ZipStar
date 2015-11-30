package com.esolz.su.zipstar.Fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.JobAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.JobDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.ListDataType;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;
import com.esolz.su.zipstar.ZipStar.ZipSterCountDown;
import com.esolz.su.zipstar.ZipStar.ZipSterReview;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProLight;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProSemibold;
import com.esolz.su.zipstar.helper.JSONParser;
import com.esolz.su.zipstar.helper.JSONParserPost;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by su on 31/8/15.
 */
public class ReviewZipsterFragment extends Fragment {

    View view;
    String ordertype;
    LinkedList<JobDataType> item_detail=new LinkedList<JobDataType>();
ImageView back;

    LinkedList<ListDataType> listofdata=new LinkedList<ListDataType>();
    JobAdapter adapter;
    private SwipeMenuListView ListView;
    Context context;
    String deletedata;
    JobDataType item;
    LinearLayout placeorder,readreview;
    MyriadProBold zipstarname,fee;
    MyriadProLight timeslot;
    ListView listView;
    MyriadProSemibold date;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    ListDataType listDataType;

    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParserPost jsonParser = new JSONParserPost();
    String   jsonadat;
    DataBaseCreator db;
    int position;
    int success;
    SharedPreferences loginPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.zipstar_review, container, false);


        

        getActivity().getPackageManager();

        back=(ImageView)view.findViewById(R.id.back);
        zipstarname=(MyriadProBold)view.findViewById(R.id.zipstarname);
        fee=(MyriadProBold)view.findViewById(R.id.fee);
        date=(MyriadProSemibold)view.findViewById(R.id.date);
timeslot=(MyriadProLight)view.findViewById(R.id.timeslot);
        placeorder=(LinearLayout)view.findViewById(R.id.placeorder);
        readreview=(LinearLayout)view.findViewById(R.id.readreview);
        listView=(ListView)view.findViewById(R.id.accepet_list);

zipstarname.setText(APP_DATA.zipster_name);
   fee.setText(APP_DATA.cost);
        date.setText(APP_DATA.dateslot);
timeslot.setText(APP_DATA.timeslot);

        db = new DataBaseCreator(getActivity());
        listofdata=db.getAllList();
        loginPreferences = getActivity().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StoreTimeWindow fragment2 = new StoreTimeWindow();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.addstore_container1, fragment2);
                transaction.commit();

            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
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

                                        lst.put("quantity",listDataType.getQnt());
                                        lst.put("img",listDataType.getImage());
                                        lst.put("upc14",listDataType.getUpcid());

                                        lst.put("name",listDataType.getName());
                                        lst.put("manuallyadded","1");
                                        lst.put("brand_name",listDataType.getPhoneNumber());




                                        jsonArray.put(lst);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                jsonadat = jsonArray.toString();
                                new Addintotimewindow().execute();


                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                                alertDialog.setTitle("");

                                alertDialog.setMessage("Your order place successfully.The Zipster will be sent a message to accept your delivery.They will have five minutes for accepet.");

                                alertDialog.setIcon(R.drawable.prefered_icon);

                                alertDialog.setPositiveButton("YES",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Write your code here to execute after dialog
                                                Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();

                                                ZipSterCountDown fragment2 = new ZipSterCountDown();
                                                FragmentManager fm = getFragmentManager();
                                                FragmentTransaction transaction = fm.beginTransaction();
                                                transaction.replace(R.id.addstore_container1, fragment2);
                                                transaction.commit();

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

        readreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ZipSterReview fragment2 = new ZipSterReview();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.addstore_container1, fragment2);
                transaction.commit();
            }
        });


//        JSONArray jsonArray = new JSONArray();
//
//        for (int i = 0; i < listofdata.size(); i++)
//
//
//        {
//            JSONObject lst=new JSONObject();
//
//            try {
//                lst.put("name",listDataType.getName());
//                lst.put("brand_name",listDataType.getPhoneNumber());
//                lst.put("qunt",listDataType.getQnt());
//                lst.put("upc",listDataType.getUpcid());
//
//                jsonArray.put(lst);
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//
//
//
//
//        jsonadat = jsonArray.toString();


        return view;
    }






    class Addintotimewindow extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.makeHttpRequest("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=add_order&grocerydetails="  + URLEncoder.encode(jsonadat, "UTF-8")+","+"&userid="  +URLEncoder.encode(APP_DATA.userid, "UTF-8")+"&storeid="+APP_DATA.store_id+"&zipsterid="+APP_DATA.zipster_id+"&orderdate="+APP_DATA.prefered_date+"&ordertime="+APP_DATA.prefered_time+"&zipsterwindowid="+APP_DATA.Zipsterwindowid+"&total_cost="+APP_DATA.cost+"&location="+APP_DATA.location+"&state="+APP_DATA.state+"&city="+APP_DATA.city+"&pin="+APP_DATA.prefered_date, "POST", params);




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


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=zipster_Joblist&loggedinuserid="+APP_DATA.userid+"&job_type="+ordertype);

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
                        JobDataType obj=new JobDataType(Json_Obj_temp.getString("order_id"),
                                Json_Obj_temp.getString("userid"),
                                Json_Obj_temp.getString("username"),
                                Json_Obj_temp.getString("delivary_date"),
                                Json_Obj_temp.getString("delivary_time"),
                                Json_Obj_temp.getString("delivary_address"),
                                Json_Obj_temp.getString("order_status"),
                                Json_Obj_temp.getString("open_job"),
                                Json_Obj_temp.getString("total_review"),
                                Json_Obj_temp.getString("user_rating"),
                                Json_Obj_temp.getString("total_order_place"),
                                Json_Obj_temp.getString("cost"),
                                Json_Obj_temp.getString("offer_placed"));
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

            adapter = new JobAdapter(getActivity(), 0, item_detail);
            listView.setAdapter(adapter);


        }


    }



}