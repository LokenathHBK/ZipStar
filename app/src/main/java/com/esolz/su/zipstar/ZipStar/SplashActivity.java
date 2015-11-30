package com.esolz.su.zipstar.ZipStar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.helper.ConnectionDetector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by su on 31/8/15.
 */
public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";
    private final int SPLASH_TIMEOUT = 2000;
    private Intent intent = null;
    private Activity activity = null;
    SharedPreferences loginPreferences;
    ConnectionDetector cd;
    TextView time;


    int count=0;
    Timer _t;
    LinearLayout lnMain;
    ImageView image;
    ImageView s1,s2,s3,s4,s5;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         remove title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);
        cd = new ConnectionDetector(SplashActivity.this);

        loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

        System.out.println("!! " + loginPreferences.getString("Remember", ""));

        time=(TextView)findViewById(R.id.time);
        s1=(ImageView)findViewById(R.id.s1);
        s2=(ImageView)findViewById(R.id.s2);
        s3=(ImageView)findViewById(R.id.s3);
        s4=(ImageView)findViewById(R.id.s4);
        s5=(ImageView)findViewById(R.id.s5);

        _t = new Timer();
        _t.scheduleAtFixedRate(new TimerTask() {




            @Override
            public void run() {

                runOnUiThread(new Runnable() // run on ui thread
                {
                    public void run() {


                        try {


                            if (count == 0) {

                                s1.setBackgroundResource(R.drawable.dot1);
                                s2.setBackgroundResource(R.drawable.dot2);
                                s3.setBackgroundResource(R.drawable.dot2);
                                s4.setBackgroundResource(R.drawable.dot2);
                                s5.setBackgroundResource(R.drawable.dot2);
                                count = (count + 1);

                            } else if (count == 1) {

                                s1.setBackgroundResource(R.drawable.dot2);
                                s2.setBackgroundResource(R.drawable.dot1);
                                s3.setBackgroundResource(R.drawable.dot2);
                                s4.setBackgroundResource(R.drawable.dot2);
                                s5.setBackgroundResource(R.drawable.dot2);
                                count = (count + 1);

                            } else if (count == 2) {

                                s1.setBackgroundResource(R.drawable.dot2);
                                s2.setBackgroundResource(R.drawable.dot2);
                                s3.setBackgroundResource(R.drawable.dot1);
                                s4.setBackgroundResource(R.drawable.dot2);
                                s5.setBackgroundResource(R.drawable.dot2);
                                count = (count + 1);

                            } else if (count == 3) {

                                s1.setBackgroundResource(R.drawable.dot2);
                                s2.setBackgroundResource(R.drawable.dot2);
                                s3.setBackgroundResource(R.drawable.dot2);
                                s4.setBackgroundResource(R.drawable.dot1);
                                s5.setBackgroundResource(R.drawable.dot2);
                                count = (count + 1);

                            } else if (count == 4) {

                                s1.setBackgroundResource(R.drawable.dot2);
                                s2.setBackgroundResource(R.drawable.dot2);
                                s3.setBackgroundResource(R.drawable.dot2);
                                s4.setBackgroundResource(R.drawable.dot2);

                                s5.setBackgroundResource(R.drawable.dot1);
                                count = (count + 1);


                    if (loginPreferences.getString("Remember", "").equals("remember")) {

                        APP_DATA.userid=loginPreferences.getString("userid", "");
                        Intent intent = new Intent(SplashActivity.this,
                                LandingActivity.class);
                        intent.putExtra("user_id", APP_DATA.userid);
                        startActivity(intent);


                    } else {
                        Intent intent = new Intent(SplashActivity.this,
                                LandingActivity
                                        .class);
                        startActivity(intent);
                        finish();
                    }


                            } else {

//                                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent(SplashActivity.this,
//                                        LandingActivity
//                                                .class);
//                                startActivity(intent);
//                                finish();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

//                stopThread(this);
            }
        }, 3000, 1000);





//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//
//                try {
//
//
//                    if (loginPreferences.getString("Remember", "").equals(
//                            "remember")) {
////                            String   login = "abhishek.singh";
////                            String password = "12345678";
//////                            createSession(login, password);
//                        Intent intent = new Intent(SplashActivity.this,
//                                LandingActivity.class);
//                        startActivity(intent);
//
//
//                    } else {
//                        Intent intent = new Intent(SplashActivity.this,
//                                LandingActivity
//                                        .class);
//                        startActivity(intent);
//                        finish();
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, 2000);







    }
//    private synchronized void stopThread(TimerTask theThread)
//    {
//        if (theThread != null)
//        {
//            theThread = null;
//        }
//    }
}


