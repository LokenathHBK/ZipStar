package com.esolz.su.zipstar.Fragment;

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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.esolz.su.zipstar.Adapter.OpenJobAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.OpenJobDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.ZipStar.DeliverFragmentActivity;

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
public class OpenJobFragment extends Fragment {

   View view;
    LinearLayout openjob;
    ListView listview;
FrameLayout job_list_cont;
    LinkedList<OpenJobDataType> item_detail=new LinkedList<OpenJobDataType>();
    OpenJobAdapter adapter;
    OpenJobDataType data;
    String zipster_name,cost,d_time,d_date,order_id,delivary_address;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_open_job, container, false);

        getActivity().getPackageManager();

        listview=(ListView)view.findViewById(R.id.listview);









        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DeliverFragmentActivity.delivery_fragment_cont.setVisibility(View.VISIBLE);
                DeliverFragmentActivity.delivery_container.setVisibility(View.GONE);
                data=item_detail.get(position);

                data = item_detail.get(position);
                zipster_name=data.getUsername();
                cost=data.getCost();
                d_time=data.getDelivary_time();
                d_date=data.getDelivary_date();
                order_id=data.getOrder_id();
                delivary_address=data.getDelivary_address();
                AcceptOrder fragment2 = new AcceptOrder();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();




                Bundle args = new Bundle();
                args.putString("zipster_name", zipster_name);
                args.putString("cost", cost);
                args.putString("d_time", d_time);
                args.putString("d_date", d_date);
                args.putString("order_id", order_id);
                args.putString("delivary_address", delivary_address);
                fragment2.setArguments(args);
                transaction.replace(R.id.delivery_fragment_cont, fragment2);
                transaction.commit();


    }
    });




      new  OpenJob().execute();



        return view;
    }

    public class OpenJob extends AsyncTask<Void,Void,Void> {


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


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=openjoblist&loggedinuserid="+ APP_DATA.userid);

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
                        OpenJobDataType obj=new OpenJobDataType(Json_Obj_temp.getString("order_id"),
                                Json_Obj_temp.getString("order_status"),
                                Json_Obj_temp.getString("userid"),
                                Json_Obj_temp.getString("username"),
                                Json_Obj_temp.getString("delivary_date"),
                                Json_Obj_temp.getString("delivary_time"),
                                Json_Obj_temp.getString("open_job"),
                                Json_Obj_temp.getString("delivary_address"),
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

            adapter = new OpenJobAdapter(getActivity(), 0, item_detail);
            listview.setAdapter(adapter);


        }


    }

}