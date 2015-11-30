package com.esolz.su.zipstar.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.ZipStar.LandingActivity;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by su on 31/8/15.
 */
public class AccountSettingFragment extends Fragment {
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParser jsonParser = new JSONParser();
    LinearLayout managestoreorder,managestoreshoping,zipstar;
    View  view;
    SharedPreferences loginPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      view = inflater.inflate(R.layout.frag_account_setting, container, false);




        getActivity().getPackageManager();


        managestoreorder=(LinearLayout)view.findViewById(R.id.managestoreorder);
        managestoreshoping=(LinearLayout)view.findViewById(R.id.managestoreshoping);
        zipstar=(LinearLayout)view.findViewById(R.id.zipstar);




        managestoreorder.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

//        SelectStoreFragment fragment2 = new SelectStoreFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.addstore_container, fragment2);
//        fragmentTransaction.commit();
        Intent i=new Intent(getActivity(), SelectStore.class);
        startActivity(i);

    }
});

        managestoreshoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SelectStoreFragment fragment2 = new SelectStoreFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.addstore_container, fragment2);
//                fragmentTransaction.commit();
                Intent i=new Intent(getActivity(), SelectStore.class);
                startActivity(i);
            }
        });
        zipstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



        return view;
    }






    class Logout extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://203.196.159.37/lab4/grocerry/appconnect/login_signup.php?mode=logout&username="+APP_DATA.userid);

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





            Intent i=new Intent(getActivity(), LandingActivity.class);
            startActivity(i);


        }}



}