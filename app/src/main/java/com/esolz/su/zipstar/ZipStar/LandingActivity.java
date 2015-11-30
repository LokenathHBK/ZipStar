package com.esolz.su.zipstar.ZipStar;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;

import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.FavGroseryAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.Item;
import com.esolz.su.zipstar.Fragment.FavoritesFragment;
import com.esolz.su.zipstar.Fragment.ListGroseryFragment;
import com.esolz.su.zipstar.Fragment.SearchFragment;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.FavouriteDataType;
import com.esolz.su.zipstar.SqlLiteDataBase.ListDataType;

import java.util.LinkedList;


/**
 * Created by su on 31/8/15.
 */
public class LandingActivity extends FragmentActivity {
    FrameLayout fragContainer,search_container,full_container,container1;
    LinearLayout orderbutton, deliverbutton, pendingbutton,settingbutton,landing_container;
    TextView txtMSGCount, txtCal, txtApnt, txtPrg, txtMsg;
    ImageButton imgCal, imgApnt, imgPrg, imgMsg;
    //public static final String calender = "com.esolz.fitness.fragment.CalenderFragment";
    FragmentTransaction fragmentTransaction;
     FragmentManager fragmentManager;
    String getIntentValue = "", getIntentValueMSG = "";
    View v1, v2;
    LinearLayout tab1,tab2,view;
    public static LinearLayout additem;
    public static LinearLayout done;
    Button additemto;
    ListView listView ;
public static String flag="0";
    LinearLayout ok,cancel;
    LinkedList<Item> data = new LinkedList<Item>();
    LinkedList<ListDataType> data1 = new LinkedList<ListDataType>();
    ListDataType aabc;
    String itemValue;
    FavouriteDataType xyz;
    String pname,bname;
    SharedPreferences loginPreferences,loginPreferences1;
    FavGroseryAdapter adapter;
    ListView listing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.landing_activity);
        full_container = (FrameLayout) findViewById(R.id.full_container);
        container1 = (FrameLayout) findViewById(R.id.container1);
        search_container = (FrameLayout) findViewById(R.id.search_container);
        fragContainer = (FrameLayout) findViewById(R.id.container);
        listView = (ListView) findViewById(R.id.list_quant);
        done = (LinearLayout) findViewById(R.id.done);
        additem= (LinearLayout) findViewById(R.id.additem);
        ok = (LinearLayout) findViewById(R.id.ok);
        cancel = (LinearLayout) findViewById(R.id.cancel);
        landing_container= (LinearLayout) findViewById(R.id.landing_container);

        loginPreferences = this.getSharedPreferences("favlist", Context.MODE_PRIVATE);
        loginPreferences1 = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);


//        pname =(loginPreferences.getString("pname",""));
//        bname=(loginPreferences.getString("bname",""));

        final String[] values = new String[] { "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36",
                "37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58",
                "59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","37","76","77","79","80",
                "81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"


        };




        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.quant_row_item, android.R.id.text1, values);


        // Assign adapter to ListView

        listView.setAdapter(adapter);

        // ListView Item Click Listener


        orderbutton = (LinearLayout) findViewById(R.id.one);
        deliverbutton = (LinearLayout) findViewById(R.id.two);
        pendingbutton = (LinearLayout) findViewById(R.id.three);
        settingbutton = (LinearLayout) findViewById(R.id.four);
        view = (LinearLayout)findViewById(R.id.view);

        tab1 = (LinearLayout)findViewById(R.id.tab1);
        tab2 = (LinearLayout)findViewById(R.id.tab2);
        v1 = (View)findViewById(R.id.v1);
        v2 = (View)findViewById(R.id.v2);

        imgCal=(ImageButton)findViewById(R.id.oneimg);
        imgApnt =(ImageButton)findViewById(R.id.twoimg);
        imgPrg =(ImageButton)findViewById(R.id.threeimg);
        imgMsg=(ImageButton)findViewById(R.id.fourimg);


//
        additemto=(Button)findViewById(R.id.additemto);



        fragmentManager = getSupportFragmentManager();
//
//
//                fragmentTransaction = fragmentManager.beginTransaction();
//                 ListGroseryFragment prl_fragment = new ListGroseryFragment();
//                fragmentTransaction.replace(R.id.container,
//                        prl_fragment);
//                fragmentTransaction.commit();


        if(APP_DATA.page_set.equals("0"))
        {
            done.setVisibility(View.VISIBLE);

            flag = "0";
            APP_DATA.page_set="0";
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.GONE);
            additem.setVisibility(View.GONE);
            SelectItem(0);
        }
        else if(APP_DATA.page_set.equals("1"))
        {
            flag = "1";
            v2.setVisibility(View.VISIBLE);
            v1.setVisibility(View.GONE);
            additem.setVisibility(View.VISIBLE);
            done.setVisibility(View.GONE);
            APP_DATA.page_set="1";
            SelectItem(1);
        }
        else
        {
            done.setVisibility(View.VISIBLE);

            flag = "0";
            APP_DATA.page_set="0";
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.GONE);
            additem.setVisibility(View.GONE);
            SelectItem(0);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index


                int itemPosition = position;


                // ListView Clicked item value
                itemValue = (String) listView.getItemAtPosition(position);

                APP_DATA.detailrow = itemValue;


//                DataBaseCreator db = new DataBaseCreator(getApplicationContext());
//
//                db.addquantity(new ListDataType());


                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                        .show();
//                Toast.makeText(getApplicationContext(),APP_DATA.upcid, Toast.LENGTH_LONG)
//                        .show();

//                Bundle bundle = new Bundle();
//                String myMessage = itemValue;
//                bundle.putString("message", myMessage );
//                ListGroseryFragment fragInfo = new ListGroseryFragment();
//                fragInfo.setArguments(bundle);
//                fragmentTransaction.replace(R.id.container, fragInfo);
//                fragmentTransaction.commit();


            }
        });

//        additem.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
////
//
//                showResult(arg0);
//                DataBaseCreator db = new DataBaseCreator(getApplicationContext());
//
////                db.addfromfavList(new ListDataType(APP_DATA.pname, APP_DATA.bname));
////                Toast.makeText(getApplicationContext(), APP_DATA.pname + APP_DATA.bname, Toast.LENGTH_LONG).show();
//
////                db.deleteFavlistiten(new FavouriteDataType(APP_DATA.pname, APP_DATA.bname));
//                SelectItem(0);
//                done.setVisibility(View.VISIBLE);
//
//                flag = "0";
//                v1.setVisibility(View.VISIBLE);
//                v2.setVisibility(View.GONE);
//                additem.setVisibility(View.GONE);
////
//
//            }
//        });

//        done.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
////
//
//
//
//
//
//             Intent i=new Intent(getApplicationContext(),PreferredOrderTimeFActivity.class);
//                startActivity(i);
//
//
//            }
//        });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//



                DataBaseCreator db = new DataBaseCreator(getApplicationContext());

                db.Update_Contact(new ListDataType(APP_DATA.detailrow, APP_DATA.lpname, APP_DATA.lbname));
//                Toast.makeText(getApplicationContext(),APP_DATA.detailrow,Toast.LENGTH_LONG).show();
                SelectItem(0);
                view.setVisibility(View.GONE);


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//
                view.setVisibility(View.GONE);
            }
        });


        additemto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Toast.makeText(getActivity(), "helll", Toast.LENGTH_LONG).show();
//                done.setVisibility(View.VISIBLE);

                additem.setVisibility(View.GONE);
                fragmentTransaction = fragmentManager.beginTransaction();
                SearchFragment bookapnt_fragment = new SearchFragment();
                fragmentTransaction.replace(R.id.search_container,
                        bookapnt_fragment);
                fragmentTransaction.commit();
//
//                    Intent i=new Intent(getApplicationContext(),SearchActivity.class);
//                    startActivity(i);

            }
        });
        tab1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Toast.makeText(getActivity(), "helll", Toast.LENGTH_LONG).show();
                done.setVisibility(View.VISIBLE);

                flag = "0";
                APP_DATA.page_set="0";
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                additem.setVisibility(View.GONE);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                ListGroseryFragment bookapnt_fragment = new ListGroseryFragment();
//
//                fragmentTransaction.replace(R.id.container,
//                        bookapnt_fragment);
//                fragmentTransaction.commit();
                SelectItem(0);

            }
        });

//
        tab2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Toast.makeText(getActivity(), "helllooooo", Toast.LENGTH_LONG).show();

                APP_DATA.page_set="1";
                flag = "1";
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.GONE);
                additem.setVisibility(View.VISIBLE);
                done.setVisibility(View.GONE);


//                fragmentTransaction = fragmentManager.beginTransaction();
//                FavoritesFragment bookapnt = new FavoritesFragment();
//
//                fragmentTransaction.replace(R.id.container,
//                        bookapnt);
//                fragmentTransaction.commit();
                SelectItem(1);
            }
        });


        imgCal.setBackgroundResource(R.drawable.bottombaricon11);
        imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
        imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
        imgMsg.setBackgroundResource(R.drawable.bottombaricon4);


        imgCal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Toast.makeText(getApplicationContext(), "order", Toast.LENGTH_LONG).show();
                flag = "4";
                imgCal.setBackgroundResource(R.drawable.bottombaricon11);
                imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
                imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
                imgMsg.setBackgroundResource(R.drawable.bottombaricon4);
                Intent i = new Intent(getApplicationContext(), LandingActivity.class);
                startActivity(i);

            }
        });
        imgApnt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Toast.makeText(getApplicationContext(),"deliver",Toast.LENGTH_LONG).show();
                flag = "3";
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
//                Toast.makeText(getApplicationContext(),"pending",Toast.LENGTH_LONG).show();
                flag = "5";
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
//                Toast.makeText(getApplicationContext(), "setting", Toast.LENGTH_LONG).show();
                flag = "6";
                imgCal.setBackgroundResource(R.drawable.bottombaricon1);
                imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
                imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
                imgMsg.setBackgroundResource(R.drawable.bottombaricon41);
                additem.setVisibility(View.GONE);


                loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

                try {


                    if (loginPreferences.getString("Remember", "").equals(
                            "remember")) {


                        Intent i = new Intent(getApplicationContext(), SettingFragmentActivity.class);
                        startActivity(i);


                    } else {
                        Intent intent = new Intent(LandingActivity.this,
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

//        Toast.makeText(getApplicationContext(), APP_DATA.userid, Toast.LENGTH_LONG).show();

        if(APP_DATA.page_set.equals("0"))
        {
            done.setVisibility(View.VISIBLE);

            flag = "0";
            APP_DATA.page_set="0";
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.GONE);
            additem.setVisibility(View.GONE);
            SelectItem(0);
        }
        else if(APP_DATA.page_set.equals("1"))
        {
            flag = "1";
            v2.setVisibility(View.VISIBLE);
            v1.setVisibility(View.GONE);
            additem.setVisibility(View.VISIBLE);
            done.setVisibility(View.GONE);
            APP_DATA.page_set="1";
            SelectItem(1);
        }
        else
        {
            done.setVisibility(View.VISIBLE);

            flag = "0";
            APP_DATA.page_set="0";
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.GONE);
            additem.setVisibility(View.GONE);
            SelectItem(0);
        }

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

    public void viesvisible()
    {

//        Toast.makeText(getApplicationContext(), "is typingg......", Toast.LENGTH_LONG).show();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

//stuff that updates ui


                view.setVisibility(View.VISIBLE);
            }
        });

    }

    public void Favitem()
    {

//        Toast.makeText(getApplicationContext(), "is typingg......", Toast.LENGTH_LONG).show();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

//stuff that updates ui



//                Toast.makeText(getActivity(), "helllooooo", Toast.LENGTH_LONG).show();

//                fragmentTransaction = fragmentManager.beginTransaction();
//                FavoritesFragment bookapnt = new FavoritesFragment();
//
//                fragmentTransaction.replace(R.id.container,
//                        bookapnt);
//                fragmentTransaction.commit();
                        SelectItem(1);

            }
        });

    }



    public  void SelectItem(int possition) {





        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                fragment = new ListGroseryFragment();

                ///mDrawerLayout.closeDrawer(mDrawerPane);
                ///layout2.setVisibility(View.VISIBLE);
                break;
            case 1:
                //mDrawerLayout.closeDrawer(mDrawerPane);
                fragment = new FavoritesFragment();

                break;

            default:
                break;
        }

        fragmentManager.beginTransaction().replace(R.id.container, fragment)
                .commit();











//        fragmentTransaction = fragmentManager.beginTransaction();
//        FavoritesFragment bookapnt = new FavoritesFragment();
//
//        fragmentTransaction.replace(R.id.container,
//                bookapnt);
//        fragmentTransaction.commit();

    }
//    public void showResult(View v) {
//        String result = "Selected Product are :";
//        String totalAmount="0";
//        for (FavouriteDataType p : adapter.getBox()) {
//            if (p.checkedBox){
//                result += "\n" + p.get_name();
//                totalAmount+=p.get_phone_number();
//            }
//        }
//        Toast.makeText(this, result+"\n"+"Total Amount:="+totalAmount, Toast.LENGTH_LONG).show();
//    }


}
