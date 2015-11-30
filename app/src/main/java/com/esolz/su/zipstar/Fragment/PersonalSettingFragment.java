package com.esolz.su.zipstar.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

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
public class PersonalSettingFragment extends Fragment {
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParser jsonParser = new JSONParser();
    LinearLayout personalinformation,changepassword,logout;
    View  view;
    SharedPreferences loginPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      view = inflater.inflate(R.layout.frag__personal_setting, container, false);




        getActivity().getPackageManager();


        personalinformation=(LinearLayout)view.findViewById(R.id.personalinformation);
        changepassword=(LinearLayout)view.findViewById(R.id.changepassword);

        logout=(LinearLayout)view.findViewById(R.id.logout);



    personalinformation.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        PersonalInformationFragment fragment2 = new PersonalInformationFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.addstore_container, fragment2);
        fragmentTransaction.commit();

    }
});

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Logout...");

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want to logout?");

                // Setting Icon to Dialog
//                alertDialog.setIcon(R.drawable.iconpen);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                                new Logout().execute();
                                SharedPreferences loginPreferences = getActivity().getSharedPreferences("loginPreferences", Context.MODE_APPEND);


                                SharedPreferences.Editor editor = loginPreferences.edit();
                                editor.clear();
                                editor.commit();


                                Intent i = new Intent(getActivity(), LandingActivity.class);
                                startActivity(i);
                            }
                        });
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,	int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getActivity(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

                // Showing Alert Message
                alertDialog.show();






//                new Logout().execute();
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





                Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show();
            Intent i=new Intent(getActivity(), LandingActivity.class);
            startActivity(i);


        }}



}