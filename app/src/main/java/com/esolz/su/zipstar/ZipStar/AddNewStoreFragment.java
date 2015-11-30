package com.esolz.su.zipstar.ZipStar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.AddStoreAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.AddStoreDataType;
import com.esolz.su.zipstar.Fragment.SelectStore;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SwipeListView.SwipeMenu;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuCreator;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuItem;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;
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
public class AddNewStoreFragment extends FragmentActivity {
    SwipeMenuListView swipelistview;
    View view;
ProgressBar progressBar;
    LinearLayout actionBa1r;
    LinkedList<AddStoreDataType> item_detail=new LinkedList<AddStoreDataType>();
    AddStoreAdapter adapter;
    private SwipeMenuListView ListView;
    Context context;
    String deletedata;
    Contact contact;
    AddStoreDataType item;
    Button searchbutton;
    ImageView addstore;
int alreadyexist;
    String storeid;
    MyriadProRegularEdit serchbox;

    boolean flag=true;
    SharedPreferences loginPreferences;
String editsearch;
    MyriadProSemibold next;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    LinearLayout orderbutton, deliverbutton, pendingbutton,settingbutton,landing_container;
    ImageButton imgCal, imgApnt, imgPrg, imgMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.addnew_srore);
        fragmentManager = getSupportFragmentManager();

        loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

        ListView = (SwipeMenuListView) findViewById(R.id.add_newstorelistview);
         progressBar=(ProgressBar)findViewById(R.id.prgbar);
        actionBa1r=(LinearLayout)findViewById(R.id.actionBa1r);
        addstore=(ImageView)findViewById(R.id.addstore);
        searchbutton=(Button)findViewById(R.id.searchbutton);
        serchbox=(MyriadProRegularEdit)findViewById(R.id.serchbox);
        orderbutton = (LinearLayout) findViewById(R.id.one);
        deliverbutton = (LinearLayout) findViewById(R.id.two);
        pendingbutton = (LinearLayout) findViewById(R.id.three);
        settingbutton = (LinearLayout) findViewById(R.id.four);
next= (MyriadProSemibold) findViewById(R.id.next);


        imgCal=(ImageButton)findViewById(R.id.oneimg);
        imgApnt =(ImageButton)findViewById(R.id.twoimg);
        imgPrg =(ImageButton)findViewById(R.id.threeimg);
        imgMsg=(ImageButton)findViewById(R.id.fourimg);


        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBa1r.setVisibility(View.GONE);
                ListView.setVisibility(View.VISIBLE);
                editsearch=serchbox.getText().toString();
                Toast.makeText(getApplicationContext(),"add item",Toast.LENGTH_LONG).show();
                new StoreSearch().execute();
            }
        });


        addstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

                actionBa1r.setVisibility(View.GONE);
                ListView.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(),"add item",Toast.LENGTH_LONG).show();


                new Addstore().execute();
                    Intent i=new Intent(getApplicationContext(),SelectStore.class);
                    startActivity(i);



         }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

                actionBa1r.setVisibility(View.GONE);
                ListView.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(),"add item",Toast.LENGTH_LONG).show();

                new Addstore().execute();
                Intent i=new Intent(getApplicationContext(),SelectStore.class);
                startActivity(i);

            }
        });




        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
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
                item=item_detail.get(position);
                alreadyexist= item.getAlready_added();
                storeid=item.getStore_id32();
                APP_DATA.store_id=storeid;

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddNewStoreFragment.this);

                alertDialog.setTitle("Confirm Delete...");

                alertDialog.setMessage("Are you sure you want delete this?");

                alertDialog.setIcon(R.drawable.prefered_icon);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();

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
        // set SwipeListener
        ListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });


        ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
                item=item_detail.get(position);
                alreadyexist= item.getAlready_added();
                storeid=item.getStore_id32();
                APP_DATA.store_id=storeid;
                return false;
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

    }


    public class StoreSearch extends AsyncTask<Void,Void,Void> {


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


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=search_store&zip="+editsearch+"&userid="+APP_DATA.userid+"&added_for=1");

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
                        AddStoreDataType obj=new AddStoreDataType(Json_Obj_temp.getString("store_id"),
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

            adapter = new AddStoreAdapter(getApplicationContext(), 0, item_detail);
            ListView.setAdapter(adapter);


        }


    }



    private void delete(AddStoreDataType item) {

//        new deletestore().execute();
//        // delete app
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





    class Addstore extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=add_store_from_searchlist&added_by="+APP_DATA.userid+"&storeid="+APP_DATA.store_id+"&added_for=1");

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

            Toast.makeText(getApplicationContext(), APP_DATA.store_id, Toast.LENGTH_LONG).show();

        }}

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