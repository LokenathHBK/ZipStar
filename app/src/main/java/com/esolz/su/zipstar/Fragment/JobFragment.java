package com.esolz.su.zipstar.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.esolz.su.zipstar.Adapter.JobAdapter;
import com.esolz.su.zipstar.Adapter.OrderAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.JobDataType;
import com.esolz.su.zipstar.DataType.OrderDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;
import com.esolz.su.zipstar.customviews.MyriadProRegular;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by su on 31/8/15.
 */
public class JobFragment extends Fragment {

    View view,v1,v2,v3;
    String ordertype;
    LinkedList<JobDataType> item_detail=new LinkedList<JobDataType>();
    JobAdapter adapter;
    private SwipeMenuListView ListView;
    Context context;
    String deletedata;
    JobDataType item;
    LinearLayout tab1,tab2,tab3;
    MyriadProRegular txtaccept,txtwaiting,txtprev;
    ListView listView;
    ProgressBar pgbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_pending_job, container, false);


        

        getActivity().getPackageManager();


        txtaccept=(MyriadProRegular)view.findViewById(R.id.txtaccept);
        txtwaiting=(MyriadProRegular)view.findViewById(R.id.txtwaiting);
        txtprev=(MyriadProRegular)view.findViewById(R.id.txtprev);
        pgbar=(ProgressBar)view.findViewById(R.id.pgbar);
        tab1=(LinearLayout)view.findViewById(R.id.acceptjob);
        tab2=(LinearLayout)view.findViewById(R.id.waitingjob);
        tab3=(LinearLayout)view.findViewById(R.id.prevjob);
        listView=(ListView)view.findViewById(R.id.accepet_list);
        v1=(View)view.findViewById(R.id.v1);
        v2=(View)view.findViewById(R.id.v2);
        v3=(View)view.findViewById(R.id.v3);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtaccept.setTextColor(Color.parseColor("#DA1E00"));
                txtwaiting.setTextColor(Color.parseColor("#000000"));
                txtprev.setTextColor(Color.parseColor("#000000"));
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                ordertype="3";
                new StoreSearch().execute();

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
                ordertype="1";
                new StoreSearch().execute();

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
                ordertype="2";
                new StoreSearch().execute();

            }
        });



        ordertype="3";
        new StoreSearch().execute();


        return view;
    }
    public class StoreSearch extends AsyncTask<Void,Void,Void> {


        InputStream is;
        String json;
        JSONArray json_arr;

        JSONObject all_news_list_object;

        @Override
        protected void onPreExecute() {


            pgbar.setVisibility(View.VISIBLE);
            super.onPreExecute();
//            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {


                DefaultHttpClient httClient = new DefaultHttpClient();


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=zipster_Joblist&loggedinuserid="+ APP_DATA.userid+"&job_type="+ordertype);

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
            pgbar.setVisibility(View.GONE);
            adapter = new JobAdapter(getActivity(), 0, item_detail);
            listView.setAdapter(adapter);


        }


    }



}