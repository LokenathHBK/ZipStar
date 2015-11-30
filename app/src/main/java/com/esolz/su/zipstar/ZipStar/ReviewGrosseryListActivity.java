package com.esolz.su.zipstar.ZipStar;


import android.content.Context;
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
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.FavGroseryAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.Item;
import com.esolz.su.zipstar.Fragment.FavoritesFragment;
import com.esolz.su.zipstar.Fragment.ReviewListGroseryFragment;
import com.esolz.su.zipstar.Fragment.ReviewSearchFragment;
import com.esolz.su.zipstar.Fragment.SearchFragment;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.FavouriteDataType;
import com.esolz.su.zipstar.SqlLiteDataBase.ListDataType;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by su on 31/8/15.
 */
public class ReviewGrosseryListActivity extends FragmentActivity {
    FrameLayout fragContainer,search_container,full_container,container1;
    LinearLayout orderbutton, deliverbutton, pendingbutton,settingbutton,landing_container;
    ImageButton imgCal, imgApnt, imgPrg, imgMsg;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
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
    String itemValue;
    SharedPreferences loginPreferences,loginPreferences1;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    String item_orderid;
    String value,order_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.reviewg_list_activity);
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



        final String[] values = new String[] { "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32"


        };




        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.quant_row_item, android.R.id.text1, values);


        listView.setAdapter(adapter);



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





        order_id=APP_DATA.orderid;
        fragmentTransaction = fragmentManager.beginTransaction();
        ReviewListGroseryFragment prl_fragment = new ReviewListGroseryFragment();
        fragmentTransaction.replace(R.id.container,
                        prl_fragment);
                fragmentTransaction.commit();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int itemPosition = position;
                itemValue = (String) listView.getItemAtPosition(position);
                APP_DATA.detailrow = itemValue;
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();



            }
        });



        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                item_orderid=APP_DATA.item_orderid;
                APP_DATA.detailrow = itemValue;
                new UpdateQuantity().execute();
                SelectItem(0);
                view.setVisibility(View.GONE);


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//

            }
        });


        additemto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

//                Bundle extras = getIntent().getExtras();
//                if (extras != null) {
//                   value = extras.getString("order_ids");
//                }
                additem.setVisibility(View.GONE);

                fragmentTransaction = fragmentManager.beginTransaction();
                ReviewSearchFragment bookapnt_fragment = new ReviewSearchFragment();

                fragmentTransaction.replace(R.id.search_container,
                        bookapnt_fragment);
                fragmentTransaction.commit();

//                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
            }
        });
        tab1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                done.setVisibility(View.VISIBLE);

                flag = "0";
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                additem.setVisibility(View.GONE);
//
//                Bundle args = new Bundle();
//                args.putString("orderid", value);
                SelectItem(0);
//                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();

            }
        });

//
        tab2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                flag = "1";
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.GONE);
                additem.setVisibility(View.VISIBLE);
                done.setVisibility(View.GONE);

                SelectItem(1);
            }
        });


        imgCal.setBackgroundResource(R.drawable.bottombaricon11);
        imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
        imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
        imgMsg.setBackgroundResource(R.drawable.bottombaricon4);


        orderbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "order", Toast.LENGTH_LONG).show();

                imgCal.setBackgroundResource(R.drawable.bottombaricon11);
                imgApnt.setBackgroundResource(R.drawable.bottombaricon2);
                imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
                imgMsg.setBackgroundResource(R.drawable.bottombaricon4);


                Intent i = new Intent(getApplicationContext(), ReviewGrosseryListActivity.class);
                startActivity(i);

            }
        });
        deliverbutton.setOnClickListener(new View.OnClickListener() {

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
        pendingbutton.setOnClickListener(new View.OnClickListener() {

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
        settingbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "setting", Toast.LENGTH_LONG).show();

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
                        Intent intent = new Intent(ReviewGrosseryListActivity.this,
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

    public void viesvisible()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);}
        });

    }

    public void Favitem()
    {runOnUiThread(new Runnable() {
            @Override
            public void run() {SelectItem(1);}
        });}


    public void SelectItem(int possition) {
        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                fragment = new ReviewListGroseryFragment();


                break;
            case 1:
                fragment = new FavoritesFragment();

                break;

            default:
                break;
        }

        fragmentManager.beginTransaction().replace(R.id.container, fragment)
                .commit();

    }

    class UpdateQuantity extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=update_item_quantity&item_id="+item_orderid+"&item_quantity="+itemValue);

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
