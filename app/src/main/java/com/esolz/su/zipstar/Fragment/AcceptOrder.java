package com.esolz.su.zipstar.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.ZipStar.DeliverFragmentActivity;
import com.esolz.su.zipstar.ZipStar.PendingFragmentActivity;
import com.esolz.su.zipstar.customviews.MyriadProLight;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProSemibold;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by su on 7/10/15.
 */
public class AcceptOrder extends Fragment {

View view;
    MyriadProSemibold zipstername;
    LinearLayout accept_order;
MyriadProRegular date;
    String zipster_name,cost,d_time,d_date,order_id,delivary_address;
ImageView back;
    MyriadProLight address;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    SharedPreferences loginPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        zipster_name = getArguments().getString("zipster_name");
        cost = getArguments().getString("cost");
        d_time = getArguments().getString("d_time");
        d_date = getArguments().getString("d_date");
        order_id = getArguments().getString("order_id");
        delivary_address = getArguments().getString("delivary_address");

        view = inflater.inflate(R.layout.accept_order, container, false);

        loginPreferences = getActivity().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

        zipstername=(MyriadProSemibold)view.findViewById(R.id.zipster_name);
        accept_order=(LinearLayout)view.findViewById(R.id.accept_order);
        date=(MyriadProRegular)view.findViewById(R.id.date);
        address=(MyriadProLight)view.findViewById(R.id.location);
        zipstername.setText(zipster_name);
        date.setText(d_date+" at "+d_time);
        address.setText(delivary_address);

        back=(ImageView)view.findViewById(R.id.back);


        accept_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_id = getArguments().getString("order_id");
                new acceptOrder().execute();
                Intent i=new Intent(getActivity(),PendingFragmentActivity.class);
                startActivity(i);

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),DeliverFragmentActivity.class);
                startActivity(i);
            }
        });
        return view;


    }




    class acceptOrder extends AsyncTask<String, String, String> {

        boolean failure = false;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
        @Override

        protected String doInBackground(String... args) {

            // TODO Auto-generated method stub

            int success;
            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=accept_order&order_id="+order_id);

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
