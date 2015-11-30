package com.esolz.su.zipstar.ZipStar;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.Fragment.AccountSettingFragment;
import com.esolz.su.zipstar.Fragment.JobFragment;
import com.esolz.su.zipstar.Fragment.ManageWindowFragment;
import com.esolz.su.zipstar.Fragment.MessageFragment;
import com.esolz.su.zipstar.Fragment.OpenJobFragment;
import com.esolz.su.zipstar.Fragment.OrderFragment;
import com.esolz.su.zipstar.Fragment.PersonalSettingFragment;
import com.esolz.su.zipstar.PopUp.SendFeedback;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by su on 31/8/15.
 */
public class SettingFragmentActivity extends FragmentActivity {

   
    LinearLayout personalsetting,accountsetting,paymentinfo,notification,sendfeedback,logout,relative_store_cont;
    LinearLayout orderbutton, deliverbutton, pendingbutton,settingbutton,landing_container;
    ImageButton imgCal, imgApnt, imgPrg, imgMsg;

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
FrameLayout frameLayout,deliver_container;
    View v1,v2,v3;
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParser jsonParser = new JSONParser();
    String storeid;
    SharedPreferences loginPreferences;

    SendFeedback sendFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.frag_setting);



        fragmentManager = getSupportFragmentManager();
        loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        relative_store_cont=(LinearLayout)findViewById(R.id.relative_store_cont);
        deliver_container=(FrameLayout)findViewById(R.id.deliver_container);
        personalsetting=(LinearLayout)findViewById(R.id.personalSetting);
        accountsetting=(LinearLayout)findViewById(R.id.accountSetting);
        paymentinfo=(LinearLayout)findViewById(R.id.paymentinfo);
        notification=(LinearLayout)findViewById(R.id.notification);
        sendfeedback=(LinearLayout)findViewById(R.id.sendfeedback);
        logout=(LinearLayout)findViewById(R.id.logout);

        orderbutton = (LinearLayout) findViewById(R.id.one);
        deliverbutton = (LinearLayout) findViewById(R.id.two);
        pendingbutton = (LinearLayout) findViewById(R.id.three);
        settingbutton = (LinearLayout) findViewById(R.id.four);
        imgCal=(ImageButton)findViewById(R.id.oneimg);
        imgApnt =(ImageButton)findViewById(R.id.twoimg);
        imgPrg =(ImageButton)findViewById(R.id.threeimg);
        imgMsg=(ImageButton)findViewById(R.id.fourimg);

        imgCal.setBackgroundResource(R.drawable.bottombaricon1);
        imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
        imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
        imgMsg.setBackgroundResource(R.drawable.bottombaricon41);

        personalsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relative_store_cont.setVisibility(View.GONE);
                SelectItem(0);

            }
        });

        accountsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectItem(1);
            }
        });
        paymentinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                relative_store_cont.setVisibility(View.GONE);


            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                relative_store_cont.setVisibility(View.GONE);

            }
        });
        sendFeedback = new SendFeedback(getApplicationContext());


        sendfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendFeedback.getmoreLayouts();
                sendFeedback.showAtLocation(v, Gravity.CENTER_HORIZONTAL, 0,
                        0);

//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingFragmentActivity.this);
//
//                alertDialog.setTitle("Feedback sent successfully");
//
//                alertDialog.setMessage("");
//
//                alertDialog.setIcon(R.drawable.prefered_icon);
//
//                alertDialog.setPositiveButton("OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                dialog.cancel();
//                            }
//                        });
//
//                alertDialog.show();


            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingFragmentActivity.this);

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Logout...");

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want to logout?");

                // Setting Icon to Dialog
//                alertDialog.setIcon(R.drawable.iconpen);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                                new Logout().execute();
                                SharedPreferences loginPreferences = getApplicationContext().getSharedPreferences("loginPreferences", Context.MODE_APPEND);


                                SharedPreferences.Editor editor = loginPreferences.edit();
                                editor.clear();
                                editor.commit();


                                Intent i = new Intent(SettingFragmentActivity.this, LandingActivity.class);
                                startActivity(i);
                            }
                        });
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

                // Showing Alert Message
                alertDialog.show();







            }
        });
        imgCal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),"order",Toast.LENGTH_LONG).show();

                imgCal.setBackgroundResource(R.drawable.bottombaricon11);
                imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
                imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
                imgMsg.setBackgroundResource(R.drawable.bottombaricon4);


                Intent i=new Intent(getApplicationContext(),LandingActivity.class);
                startActivity(i);

            }
        });
        imgApnt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),"deliver",Toast.LENGTH_LONG).show();

                imgCal.setBackgroundResource(R.drawable.bottombaricon1);
                imgApnt.setBackgroundResource(R.drawable.bottombaricon21);
                imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
                imgMsg.setBackgroundResource(R.drawable.bottombaricon4);


                loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

                try {



                    if (loginPreferences.getString("Remember", "").equals(
                            "remember")) {
                        Intent i=new Intent(getApplicationContext(),DeliverFragmentActivity.class);
                        startActivity(i);


                    } else {
                        Intent intent = new Intent(getApplicationContext(),
                                LoginActivity
                                        .class);
                        startActivity(intent);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        imgPrg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),"pending",Toast.LENGTH_LONG).show();

                imgCal.setBackgroundResource(R.drawable.bottombaricon1);
                imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
                imgPrg.setBackgroundResource(R.drawable.bottombaricon31);
                imgMsg.setBackgroundResource(R.drawable.bottombaricon4);

                loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

                try {



                    if (loginPreferences.getString("Remember", "").equals(
                            "remember")) {
                        Intent i=new Intent(getApplicationContext(),PendingFragmentActivity.class);
                        startActivity(i);


                    } else {
                        Intent intent = new Intent(getApplicationContext(),
                                LoginActivity
                                        .class);
                        startActivity(intent);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        imgMsg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),"setting",Toast.LENGTH_LONG).show();

                imgCal.setBackgroundResource(R.drawable.bottombaricon1);
                imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
                imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
                imgMsg.setBackgroundResource(R.drawable.bottombaricon41);


                loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

                try {



                    if (loginPreferences.getString("Remember", "").equals(
                            "remember")) {


                        Intent i=new Intent(getApplicationContext(),SettingFragmentActivity.class);
                        startActivity(i);


                    } else {
                        Intent intent = new Intent(getApplicationContext(),
                                LoginActivity
                                        .class);
                        startActivity(intent);
                        finish();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
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
                JSONObject json=jsonParser.getJSONFromUrl("http://203.196.159.37/lab4/grocerry/appconnect/login_signup.php?mode=logout&username="+ APP_DATA.userid);

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
//            Toast.makeText(getApplicationContext(), file_url, Toast.LENGTH_LONG).show();
            Intent i=new Intent(getApplicationContext(), LandingActivity.class);
            startActivity(i);
        }}



    public void SelectItem(int possition) {





        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                fragment = new PersonalSettingFragment();

                ///mDrawerLayout.closeDrawer(mDrawerPane);
                ///layout2.setVisibility(View.VISIBLE);
                break;
            case 1:
                //mDrawerLayout.closeDrawer(mDrawerPane);
                fragment = new AccountSettingFragment();

                break;
            case 2:
                //mDrawerLayout.closeDrawer(mDrawerPane);
//                fragment = new PaymentInfoFragment();

                break;
            case 3:
                //mDrawerLayout.closeDrawer(mDrawerPane);
//                fragment = new NotificationFragment();

                break;
            case 4:
                //mDrawerLayout.closeDrawer(mDrawerPane);
//                fragment = new SendFeedbackFragment();

                break;

            default:
                break;
        }

        fragmentManager.beginTransaction().replace(R.id.addstore_container, fragment)
                .commit();











//        fragmentTransaction = fragmentManager.beginTransaction();
//        FavoritesFragment bookapnt = new FavoritesFragment();
//
//        fragmentTransaction.replace(R.id.container,
//                bookapnt);
//        fragmentTransaction.commit();

    }




}