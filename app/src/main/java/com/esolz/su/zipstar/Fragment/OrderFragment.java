package com.esolz.su.zipstar.Fragment;

import android.content.Context;
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
import android.widget.ProgressBar;


import com.esolz.su.zipstar.Adapter.OrderAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.OrderDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;

import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;
import com.esolz.su.zipstar.customviews.MyriadProLight;
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
public class OrderFragment extends Fragment {
    SwipeMenuListView swipelistview;
    View view,v1,v2,v3;
    String ordertype;
    LinkedList<OrderDataType> item_detail=new LinkedList<OrderDataType>();
    OrderAdapter adapter;
    private SwipeMenuListView ListView;
    Context context;
    String deletedata;
    ListView listView;
    Contact contact;
    OrderDataType item;
    LinearLayout tab1,tab2,tab3;
    MyriadProLight nodata;
    MyriadProRegular txtaccept,txtwaiting,txtprev;
    String zipster_name,cost,d_time,d_date,order_id;
    ProgressBar pgbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_pending_order, container, false);


        

        getActivity().getPackageManager();


         txtaccept=(MyriadProRegular)view.findViewById(R.id.txtaccept);
                txtwaiting=(MyriadProRegular)view.findViewById(R.id.txtwaiting);
                txtprev=(MyriadProRegular)view.findViewById(R.id.txtprevt);
        nodata=(MyriadProLight)view.findViewById(R.id.nodata);
        tab1=(LinearLayout)view.findViewById(R.id.acceptorder);
        pgbar=(ProgressBar)view.findViewById(R.id.pgbar);
        tab2=(LinearLayout)view.findViewById(R.id.waitingorder);
        tab3=(LinearLayout)view.findViewById(R.id.prevorder);
        v1=(View)view.findViewById(R.id.v1);
        v2=(View)view.findViewById(R.id.v2);
        v3=(View)view.findViewById(R.id.v3);


        listView=(ListView)view.findViewById(R.id.accepet_list);

        ordertype="3";
        new StoreSearch().execute();

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtaccept.setTextColor(Color.parseColor("#DA1E00"));
                txtwaiting.setTextColor(Color.parseColor("#000000"));
                txtprev.setTextColor(Color.parseColor("#000000"));
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);

                ordertype = "3";
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                         item = item_detail.get(position);
                         zipster_name=item.getUsername();
                         cost=item.getCost();
                         d_time=item.getDelivary_time();
                         d_date=item.getDelivary_date();
                            order_id=item.getOrder_id();

                ReviewOrder fragment2 = new ReviewOrder();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("zipster_name", zipster_name);
                args.putString("cost", cost);
                args.putString("d_time", d_time);
                args.putString("d_date", d_date);
                args.putString("order_id", order_id);
                fragment2.setArguments(args);
                transaction.replace(R.id.tre, fragment2);
                transaction.commit();

            }
        });
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


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=customer_orderlist&userid="+ APP_DATA.userid+"&order_type="+ordertype);

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
                        OrderDataType obj=new OrderDataType(Json_Obj_temp.getString("order_id"),
                                Json_Obj_temp.getString("userid"),
                                Json_Obj_temp.getString("username"),
                                Json_Obj_temp.getString("delivary_date"),
                                Json_Obj_temp.getString("delivary_time"),
                                Json_Obj_temp.getString("delivary_address"),
                                Json_Obj_temp.getString("total_review"),
                                Json_Obj_temp.getString("user_rating"),
                                Json_Obj_temp.getString("cost"),
                                Json_Obj_temp.getString("order_status"));
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
            adapter = new OrderAdapter(getActivity(), 0, item_detail);
            listView.setAdapter(adapter);


        }


    }


}