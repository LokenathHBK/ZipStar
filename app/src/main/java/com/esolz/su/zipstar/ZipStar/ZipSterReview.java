package com.esolz.su.zipstar.ZipStar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import com.esolz.su.zipstar.Adapter.EntryAdapter;
import com.esolz.su.zipstar.Adapter.TimeWindowAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.TimeWindowDataType;
import com.esolz.su.zipstar.Fragment.ReviewZipsterFragment;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.customviews.MyriadProBold;

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
 * Created by su on 7/10/15.
 */
public class ZipSterReview extends Fragment {

View view;
ImageView back;
    MyriadProBold zipster_name;
    LinearLayout rating,ziplayout,nodatafound;
    ListView listforreview;
    LinkedList<TimeWindowDataType> item_detail=new LinkedList<TimeWindowDataType>();
    TimeWindowAdapter adapter;
    String name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.zipster_reviews, container, false);

                    rating=(LinearLayout)view.findViewById(R.id.rating);
                  ziplayout=(LinearLayout)view.findViewById(R.id.ziplayout);
                  nodatafound=(LinearLayout)view.findViewById(R.id.nodatafound);
                  listforreview=(ListView)view.findViewById(R.id.listforreview);
                    back=(ImageView)view.findViewById(R.id.back);
        zipster_name=(MyriadProBold)view.findViewById(R.id.zipster_name);

        zipster_name.setText(APP_DATA.zipster_name);


        nodatafound.setVisibility(View.VISIBLE);
        rating.setVisibility(View.INVISIBLE);
        ziplayout.setVisibility(View.INVISIBLE);
        listforreview.setVisibility(View.GONE);



//        new CountDownTimer(30000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                zipster_name.setText("seconds remaining: " + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//                zipster_name.setText("done!");
//            }
//        }.start();

        zipster_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZipSterCountDown fragment2 = new ZipSterCountDown();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.addstore_container1, fragment2);
                transaction.commit();
            }
        });


        new StoreSearch().execute();




        return view;
    }

    public class StoreSearch extends AsyncTask<String, String, String> {


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
        protected String doInBackground(String... args) {

            try {


                DefaultHttpClient httClient = new DefaultHttpClient();


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=view_review&user_id="+APP_DATA.userid+"&review_for=1");

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
        protected void onPostExecute(String file_url) {


            adapter = new TimeWindowAdapter(getActivity(), 0, item_detail);
            listforreview.setAdapter(adapter);

            if (json_arr.length()>0) {
                Toast.makeText(getActivity(), file_url , Toast.LENGTH_LONG).show();

                adapter = new TimeWindowAdapter(getActivity(), 0, item_detail);
                listforreview.setAdapter(adapter);
            }else {
                nodatafound.setVisibility(View.VISIBLE);
                rating.setVisibility(View.GONE);
                ziplayout.setVisibility(View.GONE);
                listforreview.setVisibility(View.GONE);

            }
        }


    }


}
