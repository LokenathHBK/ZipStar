package com.esolz.su.zipstar.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.ManageWindowsAdapter;
import com.esolz.su.zipstar.Adapter.OpenJobAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.ManageWindowDataType;
import com.esolz.su.zipstar.DataType.OpenJobDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.ListDataType;
import com.esolz.su.zipstar.SwipeListView.SwipeMenu;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuCreator;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuItem;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;
import com.esolz.su.zipstar.ZipStar.LandingActivity;
import com.esolz.su.zipstar.helper.JSONParser;

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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by su on 31/8/15.
 */
public class ManageWindowsFragment extends Fragment {
    SwipeMenuListView swipelistview;
    View view;

    LinkedList<ManageWindowDataType> item_detail=new LinkedList<ManageWindowDataType>();
    ManageWindowsAdapter manageWindowsAdapter;
ListView listView,list_multipel;
    Context context;
    ManageWindowDataType item;
    LinearLayout done,cancel,ok;
    public static LinearLayout multipel_layout;
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParser jsonParser = new JSONParser();
ImageView back;

   String userid,selected_date,slot_available="",multiply="";

    String selecetd_date;
    int success;


    String itemValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        selecetd_date = getArguments().getString("CID");

        view = inflater.inflate(R.layout.manage_window, container, false);


        

        getActivity().getPackageManager();
        ok = (LinearLayout) view.findViewById(R.id.ok);
        cancel = (LinearLayout) view.findViewById(R.id.cancel);
        done = (LinearLayout) view.findViewById(R.id.done);
        listView = (ListView) view.findViewById(R.id.timemanagelistview);
        multipel_layout= (LinearLayout) view.findViewById(R.id.multipel_layout);
        back=(ImageView)view.findViewById(R.id.back);
        list_multipel= (ListView) view.findViewById(R.id.list_multipel);
        new  TimeSlotList().execute();



        final String[] values = new String[] { "0","0.05","0.10","0.15","0.20","0.25","0.30","0.35",
                "0.40","0.45","0.50","0.55","0.60","0.65","0.70","0.75","0.80","0.85","0.90","1","1.10",
                "1.20","1.30","1.40","1.50","1.60","1.70","1.80","1.90","2.0","2.10","2.20"


        };




        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.quant_row_item, android.R.id.text1, values);


        // Assign adapter to ListView
        list_multipel.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageWindowFragment fragment2 = new ManageWindowFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();

                transaction.replace(R.id.deliver_container, fragment2);
                transaction.commit();

            }
        });

        list_multipel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index


                int itemPosition = position;


                // ListView Clicked item value
                itemValue = (String) list_multipel.getItemAtPosition(position);

                APP_DATA.multipel=itemValue;
//                Toast.makeText(getActivity(),
//                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                        .show();



            }
        });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//

                ManageWindowsAdapter.manageWindowDataType.setMultiply(APP_DATA.multipel);
//                Toast.makeText(getActivity(), itemValue, Toast.LENGTH_LONG).show();
                multipel_layout.setVisibility(View.GONE);



//                ManageWindowsAdapter.multipel.setText(APP_DATA.multipel);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                multipel_layout.setVisibility(View.GONE);


            }
        });

        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View arg0) {
//
                selecetd_date = getArguments().getString("CID");
                showResult(arg0);


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//
                alertDialog.setTitle("");

                alertDialog.setMessage("Slote booked");

                alertDialog.setIcon(R.drawable.prefered_icon);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();
//                                dialog.cancel();

                            }
                        });

                // Showing Alert Message
                alertDialog.show();

//

            }
        });




        return view;
    }

    public void showResult(View v) {

        for (ManageWindowDataType p : manageWindowsAdapter.getBox()) {

            if (p.checkedBox){

                selected_date += "\n" + p.getTime_slot_id();
                multiply +=p.getMultiply()+",";
                slot_available +="1,";
            }
            else{
                multiply +=p.getMultiply()+",";
                slot_available+="0,";
//                Toast.makeText(getActivity(), slot_available, Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(), multiply, Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(), selecetd_date, Toast.LENGTH_LONG).show();
            }

        }
        multiply=multiply.length() > 0 ? multiply.substring(0, multiply.length() - 1): "";
        slot_available=slot_available.length() > 0 ? slot_available.substring(0, slot_available.length() - 1): "";


        new Addintotimewindow().execute();


    }


    public class TimeSlotList extends AsyncTask<Void,Void,Void> {


        InputStream is;
        String json;
        JSONArray json_arr;

        JSONObject all_news_list_object;

        @Override
        protected void onPreExecute() {



            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {


                DefaultHttpClient httClient = new DefaultHttpClient();


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=get_time_slot_details&userid="+APP_DATA.userid+"selected_date="+selecetd_date);

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
                json_arr = all_news_list_object.getJSONArray("response");


                if (json_arr.length() != 0) {

                    for (int i = 0; i < json_arr.length(); i++) {
                        JSONObject Json_Obj_temp;
                        Json_Obj_temp = json_arr.getJSONObject(i);
                        ManageWindowDataType obj=new ManageWindowDataType(Json_Obj_temp.getString("time_slot_id"),
                                Json_Obj_temp.getString("time_slot_start"),
                                Json_Obj_temp.getString("time_slot_end"),
                                Json_Obj_temp.getString("sign_up_for"),
                                Json_Obj_temp.getString("slot_available"),
                                Json_Obj_temp.getString("multiply"));
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

            manageWindowsAdapter = new ManageWindowsAdapter(getActivity(), 0, item_detail);
            listView.setAdapter(manageWindowsAdapter);


        }


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
String url="http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=add_time_slot_details&userid="+APP_DATA.userid+"&selected_date="+selecetd_date+"&slot_available="+slot_available+"&multiply="+multiply;
                JSONObject json=jsonParser.getJSONFromUrl(url);

                    Log.d("request!", url.toString());

                Log.d("request!", "starting");

                success = json.getInt(TAG_SUCCESS);

                if (success == 0) {

                    Log.d("User Login!", json.toString());

                    JSONObject c=json.getJSONObject("response");




                    return json.getString(TAG_MESSAGE);

                }else{

                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));

                    return json.getString(TAG_MESSAGE);
                }

            }
            catch (JSONException e) {

                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {




        }}

}