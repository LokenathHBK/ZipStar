package com.esolz.su.zipstar.ZipStar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.customviews.MyriadProSemibold;
import com.esolz.su.zipstar.helper.JSONParser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by su on 7/10/15.
 */
public class ZipSterCountDown extends Fragment {

View view;
TextView time;
    MyriadProSemibold zipstername;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    SharedPreferences loginPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_count_down, container, false);

        loginPreferences = getActivity().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

        time=(TextView)view.findViewById(R.id.time);
        zipstername=(MyriadProSemibold)view.findViewById(R.id.zipstername);

zipstername.setText(APP_DATA.zipster_name);


        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(""+String.format("0%d %d ",

                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),

                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                                )));
            }

            public void onFinish() {
                time.setText("00 00");
                new Addstore().execute();

            }
        }.start();




        return view;
    }

    class Addstore extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=make_openjob&orderid="+APP_DATA.orderid);

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


            Intent i=new Intent(getActivity(),LandingActivity.class);
            startActivity(i);

//
//            Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show();
//
//            Toast.makeText(getActivity(), APP_DATA.store_id, Toast.LENGTH_LONG).show();

        }}


}
