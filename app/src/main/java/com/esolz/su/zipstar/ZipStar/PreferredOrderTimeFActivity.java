package com.esolz.su.zipstar.ZipStar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DateTimePicker.DateTimePickerDialog;
import com.esolz.su.zipstar.Fragment.SelectStore;
import com.esolz.su.zipstar.R;

import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.DateTimeDataType;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.helper.ConnectionDetector;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


public class PreferredOrderTimeFActivity extends FragmentActivity implements DateTimePickerDialog.DateTimeListener {
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    SharedPreferences loginPreferences;
    ConnectionDetector cd;
int i;
    LinkedList<DateTimeDataType> item_detail=new LinkedList<DateTimeDataType>();
    MyriadProRegular select_time;
    MyriadProRegular done;
    String time;
    ImageView back;
    String prefered_time,prefered_date;
    DataBaseCreator db;
    String smonth,sday,shour,smin,syear;
    public static String flag="0";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferred_order_time);




        loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

        System.out.println("!! " + loginPreferences.getString("Remember", ""));

        String currentTimeString = DateFormat.getTimeInstance().format(new Date());

        select_time=(MyriadProRegular)findViewById(R.id.select_time);
        done=(MyriadProRegular)findViewById(R.id.done);
        back=(ImageView)findViewById(R.id.back);
        fragmentManager = getSupportFragmentManager();

//        ((TextView) findViewById(R.id.date)).setText(syear + smonth);
//        ((TextView) findViewById(R.id.hour)).setText(shour);
//        ((TextView) findViewById(R.id.min)).setText(smin);
        select_time.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showDateTimeDialog();
    }
});


//        db = new DataBaseCreator(getApplicationContext());
//        APP_DATA.prefered_time=db.gettime();
//
//        Toast.makeText(getApplicationContext(),db.gettime(), Toast.LENGTH_SHORT).show();


        Calendar c = Calendar.getInstance();
        int month=c.get(Calendar.MONTH)+1;

        String sDate = c.get(Calendar.YEAR) + "-" + month+ "-" + c.get(Calendar.DAY_OF_MONTH) +
                "T" + c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);


         smonth= String.valueOf(month);

         syear= String.valueOf(c.get(Calendar.YEAR));

         sday= String.valueOf(c.get(Calendar.DAY_OF_MONTH));

         shour= String.valueOf(c.get(Calendar.HOUR_OF_DAY));

         smin= String.valueOf(c.get(Calendar.MINUTE));

        prefered_time= shour+"-"+smin;
        prefered_date= syear+"-"+smonth;

        ((TextView) findViewById(R.id.date)).setText(prefered_date);
        ((TextView) findViewById(R.id.hour)).setText(shour);
        ((TextView) findViewById(R.id.min)).setText(smin);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(),
                                LandingActivity
                                        .class);
                        startActivity(intent);
            }
        });





        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flag = "7";
//                db = new DataBaseCreator(getApplicationContext());
//                item_detail=db.addtimetable(APP_DATA.prefered_time);

                APP_DATA.prefered_time=prefered_time;
                APP_DATA.prefered_date=prefered_date;
                Toast.makeText(getApplicationContext(),APP_DATA.prefered_time , Toast.LENGTH_SHORT).show();
                loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

                try {



                    if (loginPreferences.getString("Remember", "").equals(
                            "remember")) {
//                            String   login = "abhishek.singh";
//                            String password = "12345678";
////                   fragmentTransaction = fragmentManager.beginTransaction();
//                        Intent intent = new Intent(getApplicationContext(),
//                                SelectStoreActivity
//                                        .class);
//                        startActivity(intent);


//                        fragmentTransaction = fragmentManager.beginTransaction();
//                        SelectStoreFragment prl_fragment = new SelectStoreFragment();
//                        fragmentTransaction.replace(R.id.pref_coint,
//                                prl_fragment);
//                        fragmentTransaction.commit();


                        Intent intent = new Intent(getApplicationContext(),
                                SelectStore
                                        .class);
                        startActivity(intent);


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

//        DatePickerDialog dpDialog = new DatePickerDialog(this, pDateSetListener, pYear, pMonth, pDay);
//        DatePicker datePicker = dpDialog.getDatePicker();
//
//        Calendar calendar = Calendar.getInstance();//get the current day
//        datePicker.setMaxDate(calendar.getTimeInMillis());//set the current day as the max date or put yore date in miliseconds.
//        datePicker.setMinDate(calendar.getTimeInMillis());//set the current day as the min date or put your date in mili seconds format
////        return dpDialog;
    }

    private void showDateTimeDialog() {
        DateTimePickerDialog pickerDialog = new DateTimePickerDialog(this, false, this);
        pickerDialog.show();
    }





    @Override
    public void onDateTimeSelected(int year, int month, int day, int hour, int min, int am_pm) {
        String text = day + "/" + month + "/" + year + " - " + hour + ":" + min;
        prefered_time=hour + ":" + min;
        prefered_date= year + "/" + month + "/" + day;

        String day1 = String.valueOf(day)+ "-" + String.valueOf(month);
        String month1 = String.valueOf(month);
        String hour1 = String.valueOf(hour);
        String min1 = String.valueOf(min);




        if (am_pm != -1)
            text = text + (am_pm == Calendar.AM ? "AM" : "PM");
//        ((TextView) findViewById(R.id.date)).setText(text);
        ((TextView) findViewById(R.id.date)).setText(day1);
        ((TextView) findViewById(R.id.hour)).setText(hour1);
        ((TextView) findViewById(R.id.min)).setText(min1);
        ((TextView) findViewById(R.id.ampm)).setText((am_pm == Calendar.AM ? "AM" : "PM"));
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
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


}