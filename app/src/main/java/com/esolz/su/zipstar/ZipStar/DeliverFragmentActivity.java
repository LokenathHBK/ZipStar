package com.esolz.su.zipstar.ZipStar;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esolz.su.zipstar.Fragment.ManageWindowFragment;
import com.esolz.su.zipstar.Fragment.OpenJobFragment;
import com.esolz.su.zipstar.R;


/**
 * Created by su on 31/8/15.
 */
public class DeliverFragmentActivity extends FragmentActivity {
LinearLayout openjob,managewindow,orderbutton, deliverbutton, pendingbutton,settingbutton;
    ImageButton imgCal, imgApnt, imgPrg, imgMsg;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    View v1,v2,v3;
    SharedPreferences loginPreferences;
    public static FrameLayout delivery_fragment_cont;
    public static FrameLayout delivery_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.fragment_delivery);
        loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

        fragmentManager = getSupportFragmentManager();

        delivery_fragment_cont= (FrameLayout) findViewById(R.id.delivery_fragment_cont);
        delivery_container= (FrameLayout) findViewById(R.id.delivery_container);


        openjob = (LinearLayout) findViewById(R.id.openjob);
        managewindow = (LinearLayout) findViewById(R.id.managewindow);
        orderbutton = (LinearLayout) findViewById(R.id.one);
        deliverbutton = (LinearLayout) findViewById(R.id.two);
        pendingbutton = (LinearLayout) findViewById(R.id.three);
        settingbutton = (LinearLayout) findViewById(R.id.four);
        imgCal=(ImageButton)findViewById(R.id.oneimg);
        imgApnt =(ImageButton)findViewById(R.id.twoimg);
        imgPrg =(ImageButton)findViewById(R.id.threeimg);
        imgMsg=(ImageButton)findViewById(R.id.fourimg);
        v1=(View)findViewById(R.id.v2);
        v2=(View)findViewById(R.id.v3);
        SelectItem(0);


        imgCal.setBackgroundResource(R.drawable.bottombaricon1);
        imgApnt.setBackgroundResource(R.drawable.bottombaricon21);
        imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
        imgMsg.setBackgroundResource(R.drawable.bottombaricon4);


        openjob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "order", Toast.LENGTH_LONG).show();
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
SelectItem(0);



            }
        });
        managewindow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "manage window", Toast.LENGTH_LONG).show();
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.VISIBLE);
                SelectItem(1);

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



    public void SelectItem(int possition) {





        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                fragment = new OpenJobFragment();

                ///mDrawerLayout.closeDrawer(mDrawerPane);
                ///layout2.setVisibility(View.VISIBLE);
                break;
            case 1:
                //mDrawerLayout.closeDrawer(mDrawerPane);
                fragment = new ManageWindowFragment();

                break;

            default:
                break;
        }

        fragmentManager.beginTransaction().replace(R.id.deliver_container, fragment)
                .commit();













    }









}