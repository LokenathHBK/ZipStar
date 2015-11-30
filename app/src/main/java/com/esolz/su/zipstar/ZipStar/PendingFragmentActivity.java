package com.esolz.su.zipstar.ZipStar;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.esolz.su.zipstar.Fragment.JobFragment;
import com.esolz.su.zipstar.Fragment.MessageFragment;
import com.esolz.su.zipstar.Fragment.OrderFragment;
import com.esolz.su.zipstar.R;

import com.esolz.su.zipstar.helper.JSONParser;




/**
 * Created by su on 31/8/15.
 */
public class PendingFragmentActivity extends FragmentActivity {

    LinearLayout orderbutton, deliverbutton, pendingbutton,settingbutton,landing_container;
    ImageButton imgCal, imgApnt, imgPrg, imgMsg;
    LinearLayout messagetab, jobtab, ordertab;



    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
FrameLayout frameLayout;
    RelativeLayout relative_store_cont;
    View v1,v2,v3;
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParser jsonParser = new JSONParser();
    String storeid;
    SharedPreferences loginPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.frag_pending);



        fragmentManager = getSupportFragmentManager();
        loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        relative_store_cont= (RelativeLayout) findViewById(R.id.relative_store_cont);
        messagetab = (LinearLayout) findViewById(R.id.message);
        jobtab = (LinearLayout) findViewById(R.id.job);
        ordertab = (LinearLayout) findViewById(R.id.order);
        orderbutton = (LinearLayout) findViewById(R.id.one);
        deliverbutton = (LinearLayout) findViewById(R.id.two);
        pendingbutton = (LinearLayout) findViewById(R.id.three);
        settingbutton = (LinearLayout) findViewById(R.id.four);
        imgCal=(ImageButton)findViewById(R.id.oneimg);
        imgApnt =(ImageButton)findViewById(R.id.twoimg);
        imgPrg =(ImageButton)findViewById(R.id.threeimg);
        imgMsg=(ImageButton)findViewById(R.id.fourimg);
        v1=(View)findViewById(R.id.v1);
        v2=(View)findViewById(R.id.v2);
        v3=(View)findViewById(R.id.v3);






        imgCal.setBackgroundResource(R.drawable.bottombaricon1);
        imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
        imgPrg.setBackgroundResource(R.drawable.bottombaricon31);
        imgMsg.setBackgroundResource(R.drawable.bottombaricon4);


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




        messagetab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "order", Toast.LENGTH_LONG).show();

                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                fragmentTransaction = fragmentManager.beginTransaction();
                MessageFragment bookapnt_fragment = new MessageFragment();

                fragmentTransaction.replace(R.id.pending_container,
                        bookapnt_fragment);
                fragmentTransaction.commit();

            }
        });

        ordertab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "order", Toast.LENGTH_LONG).show();
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.GONE);
                fragmentTransaction = fragmentManager.beginTransaction();
                OrderFragment bookapnt_fragment = new OrderFragment();

                fragmentTransaction.replace(R.id.pending_container,
                        bookapnt_fragment);
                fragmentTransaction.commit();


            }
        });

        jobtab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "order", Toast.LENGTH_LONG).show();

                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.VISIBLE);

                fragmentTransaction = fragmentManager.beginTransaction();
                JobFragment bookapnt_fragment = new JobFragment();

                fragmentTransaction.replace(R.id.pending_container,
                        bookapnt_fragment);
                fragmentTransaction.commit();

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












}