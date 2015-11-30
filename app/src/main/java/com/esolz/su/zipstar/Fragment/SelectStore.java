package com.esolz.su.zipstar.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.SelectStoreAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.SelectStoreDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SwipeListView.SwipeMenu;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuCreator;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuItem;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;
import com.esolz.su.zipstar.ZipStar.AddNewStoreFragment;
import com.esolz.su.zipstar.ZipStar.DeliverFragmentActivity;
import com.esolz.su.zipstar.ZipStar.LandingActivity;
import com.esolz.su.zipstar.ZipStar.LoginActivity;
import com.esolz.su.zipstar.ZipStar.PendingFragmentActivity;
import com.esolz.su.zipstar.ZipStar.PreferredOrderTimeFActivity;
import com.esolz.su.zipstar.ZipStar.SettingFragmentActivity;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProRegularEdit;
import com.esolz.su.zipstar.customviews.MyriadProSemibold;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by su on 31/8/15.
 */
public class SelectStore extends FragmentActivity {
    SwipeMenuListView swipelistview;

ProgressBar progressBar;
    LinkedList<SelectStoreDataType> item_detail=new LinkedList<SelectStoreDataType>();
    SelectStoreAdapter adapter;
    private SwipeMenuListView ListView;
    Context context;
    String deletedata;
    Contact contact;
    SelectStoreDataType item;
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParser jsonParser = new JSONParser();
    Button additemto;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    MyriadProRegularEdit address,city,state,pin;
    Button done,cancel,backlist;
    ScrollView delivery_address;
    SharedPreferences loginPreferences;
    String saddress,scity,sstate,spin;
    LinearLayout orderbutton, deliverbutton, pendingbutton,settingbutton,landing_container,relative_store_cont;
    ImageButton imgCal, imgApnt, imgPrg, imgMsg;
    boolean flag=true;
String alreadyexist,storeid;
    FrameLayout addstore_container1;
    MyriadProSemibold next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.frag_select_store);
        fragmentManager = getSupportFragmentManager();


        loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

        addstore_container1=(FrameLayout)findViewById(R.id.addstore_container1);
        ListView = (SwipeMenuListView) findViewById(R.id.selectstore_listView);
         progressBar=(ProgressBar)findViewById(R.id.pbar);
        backlist=(Button)findViewById(R.id.backlist);
        additemto=(Button)findViewById(R.id.additemto);
        delivery_address = (ScrollView)findViewById(R.id.delivery_address);
        next=(MyriadProSemibold)findViewById(R.id.next);
        address=(MyriadProRegularEdit)findViewById(R.id.address);
        city=(MyriadProRegularEdit)findViewById(R.id.city);
        state=(MyriadProRegularEdit)findViewById(R.id.state);
        pin=(MyriadProRegularEdit)findViewById(R.id.pin);
        done=(Button)findViewById(R.id.done);
        cancel=(Button)findViewById(R.id.cancel);
        relative_store_cont= (LinearLayout) findViewById(R.id.relative_store_cont);
        orderbutton = (LinearLayout) findViewById(R.id.one);
        deliverbutton = (LinearLayout) findViewById(R.id.two);
        pendingbutton = (LinearLayout) findViewById(R.id.three);
        settingbutton = (LinearLayout) findViewById(R.id.four);



        imgCal=(ImageButton)findViewById(R.id.oneimg);
        imgApnt =(ImageButton)findViewById(R.id.twoimg);
        imgPrg =(ImageButton)findViewById(R.id.threeimg);
        imgMsg=(ImageButton)findViewById(R.id.fourimg);


        additemto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AddNewStoreFragment.class);
                startActivity(i);

            }
        });

        backlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PreferredOrderTimeFActivity.class);
                startActivity(i);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PreferredOrderTimeFActivity.class);
                startActivity(i);

            }
        });


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplication());

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplication());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));

                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        ListView.setMenuCreator(creator);



        ListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                item = item_detail.get(position);
                storeid = item.getStore_id32();
//                APP_DATA.store_id = storeid;

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SelectStore.this);

                alertDialog.setTitle("Confirm Delete...");

                alertDialog.setMessage("Are you sure you want delete this?");

                alertDialog.setIcon(R.drawable.prefered_icon);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(SelectStore.this, "You clicked on YES", Toast.LENGTH_SHORT).show();

                                delete(item);
                            }
                        });
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                // Showing Alert Message
                alertDialog.show();
                return false;
            }
        });


        ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {


                item=item_detail.get(position);
                APP_DATA.store_id=item.getStore_id32();
                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SelectStore.this);

                alertDialog.setTitle("Is this your Delivery Address?");

                alertDialog.setMessage(APP_DATA.location);


                alertDialog.setPositiveButton("Change",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                delivery_address.setVisibility(View.VISIBLE);

                            }
                        });
                alertDialog.setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                relative_store_cont.setVisibility(View.GONE);

                                fragmentTransaction = fragmentManager.beginTransaction();
                                StoreTimeWindow bookapnt_fragment = new StoreTimeWindow();

                                fragmentTransaction.replace(R.id.addstore_container1,
                                        bookapnt_fragment);
                                fragmentTransaction.commit();

                            }
                        });

                alertDialog.show();


                return false;


            }
        });
        ListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
            }

            @Override
            public void onSwipeEnd(int position) {
            }
        });



        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPreferences = getSharedPreferences("loginPreferences", 0);

                // TODO Auto-generated method stub
                saddress = address.getText().toString();
                if (saddress == null) {
                    address.setError("Address Can't be Blank");

                    flag = false;
                } else {
                    scity = city.getText().toString();

                    if (scity == null) {
                        city.setError("city Can't Be Blank");
                    } else {
                        flag = true;

                        spin = pin.getText().toString();

                        sstate = state.getText().toString();
                        new Updateprofile().execute();


                        delivery_address.setVisibility(View.GONE);

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SelectStore.this);

                        alertDialog.setTitle("Profile details updated");

                        alertDialog.setMessage("Successfully");

                        alertDialog.setIcon(R.drawable.prefered_icon);

                        alertDialog.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Write your code here to execute after dialog
                                        Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();

                                        dialog.cancel();
                                    }
                                });

                        // Showing Alert Message
                        alertDialog.show();
                    }
                }


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delivery_address.setVisibility(View.GONE);


            }
        });



        imgCal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "order", Toast.LENGTH_LONG).show();

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
                Toast.makeText(getApplicationContext(), "deliver", Toast.LENGTH_LONG).show();

                imgCal.setBackgroundResource(R.drawable.bottombaricon1);
                imgApnt.setBackgroundResource(R.drawable.bottombaricon21);
                imgPrg.setBackgroundResource(R.drawable.bottombaricon3);
                imgMsg.setBackgroundResource(R.drawable.bottombaricon4);


                loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

                try {


                    if (loginPreferences.getString("Remember", "").equals(
                            "remember")) {
                        Intent i = new Intent(getApplicationContext(), DeliverFragmentActivity.class);
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



//        loginPreferences = getApplication().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);


        new Storelist().execute();

    }


    class Updateprofile extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/login_signup.php?mode=change_profile_details&user_id="+APP_DATA.userid+
                        "&name="+APP_DATA.name+
                        "&city="+scity+
                        "&state="+sstate+
                        "&pin="+spin+
                        "&last_name="+APP_DATA.lname+
                        "&email="+APP_DATA.email+
                        "&phone="+APP_DATA.phone+
                        "&location="+saddress);



                Log.d("request!", "starting");

                success = json.getInt(TAG_SUCCESS);

                if (success == 0) {

                    Log.d("User Login!", json.toString());

                    JSONObject c=json.getJSONObject("response");

                    APP_DATA.userid=c.getString("userid");
                    APP_DATA.name=c.getString("name");
                    APP_DATA.location=c.getString("location");

                    APP_DATA.lname=c.getString("last_name");
                    APP_DATA.phone=c.getString("phone");
                    APP_DATA.email=c.getString("email");
                    APP_DATA.city=c.getString("location_city");
                    APP_DATA.state=c.getString("location_state");
                    APP_DATA.pin=c.getString("location_pin");





                    loginPreferences = getApplicationContext().getSharedPreferences("loginPreferences", 0);
                    SharedPreferences.Editor editor = loginPreferences.edit();
                    editor.putString("userid",APP_DATA.userid);
                    editor.putString("name",APP_DATA.name);

                    editor.putString("location", APP_DATA.location);


                    editor.putString("last_name",APP_DATA.lname);
                    editor.putString("phone",APP_DATA.phone);
                    editor.putString("email",APP_DATA.email);
                    editor.putString("location_city", APP_DATA.city);
                    editor.putString("location_pin", APP_DATA.pin);
                    editor.putString("location_state", APP_DATA.state);

                    editor.putString("Remember", "remember");
                    editor.commit();


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





            Toast.makeText(getApplicationContext(), file_url, Toast.LENGTH_LONG).show();



        }}


    public class Storelist extends AsyncTask<Void,Void,Void> {


        InputStream is;
        String json;
        JSONArray json_arr;

        JSONObject all_news_list_object;

        @Override
        protected void onPreExecute() {



            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {


                DefaultHttpClient httClient = new DefaultHttpClient();


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=my_store_list&added_by="+APP_DATA.userid+"&added_for=1");

                HttpResponse response = httClient.execute(http_get);

                HttpEntity httpEntity = response.getEntity();

                is = httpEntity.getContent();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));

                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");
                }

                is.close();

                json = sb.toString();

            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }

            try {

                all_news_list_object = new JSONObject(json);

            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            try {
                item_detail = new LinkedList<>();
                //String total_data = all_news_list_object.getString("all_user");
                json_arr = all_news_list_object.getJSONArray("response");


                if (json_arr.length() != 0) {

                    for (int i = 0; i < json_arr.length(); i++) {
                        JSONObject Json_Obj_temp;
                        Json_Obj_temp = json_arr.getJSONObject(i);
                        SelectStoreDataType obj=new SelectStoreDataType(Json_Obj_temp.getString("store_id"),
                                Json_Obj_temp.getString("store_name"),
                                Json_Obj_temp.getString("store_address"),
                                Json_Obj_temp.getString("store_state"),
                                Json_Obj_temp.getString("pin"),
                                Json_Obj_temp.getInt("getdistance"),
                                Json_Obj_temp.getInt("already_added"));
                        item_detail.add(obj);


                    }
                }
            }catch (Exception e){

                e.printStackTrace();

            }


            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), APP_DATA.userid, Toast.LENGTH_LONG).show();
            adapter = new SelectStoreAdapter(getApplicationContext(), 0, item_detail);
            ListView.setAdapter(adapter);


        }


    }



    private void delete(SelectStoreDataType item) {


        // delete app
        try {
            new deletestore().execute();
            item_detail.remove(item);
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    class deletestore extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=remove_store_list&store_id="+storeid+"&added_for=1");

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





            Toast.makeText(getApplicationContext(), file_url, Toast.LENGTH_LONG).show();
//            Intent i=new Intent(getActivity(), SelectStoreActivity.class);
//            startActivity(i);


        }}

}