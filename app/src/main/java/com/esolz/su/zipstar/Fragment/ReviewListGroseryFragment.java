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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.ReviewListGroseryAdapter;
import com.esolz.su.zipstar.Adapter.SelectStoreAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.ReviewGroseryDataType;
import com.esolz.su.zipstar.DataType.SelectStoreDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SwipeListView.SwipeMenu;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuCreator;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuItem;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;
import com.esolz.su.zipstar.ZipStar.PendingFragmentActivity;
import com.esolz.su.zipstar.ZipStar.PreferredOrderTimeFActivity;
import com.esolz.su.zipstar.ZipStar.ReviewGrosseryListActivity;
import com.esolz.su.zipstar.ZipStar.ZipSterCountDown;
import com.esolz.su.zipstar.helper.JSONParser;
import com.esolz.su.zipstar.helper.JSONParserPost;

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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by su on 31/8/15.
 */
public class ReviewListGroseryFragment extends Fragment {
    View view;
    LinkedList<ReviewGroseryDataType> item_detail=new LinkedList<ReviewGroseryDataType>();
    ReviewListGroseryAdapter adapter;
    private SwipeMenuListView listView;
    Context context;
Contact contact;
    int position;
    ReviewGroseryDataType item;
    String a,b,c,jsonadat;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    SharedPreferences loginPreferences;
    JSONParserPost jsonParserPost = new JSONParserPost();

    int success;
String strtext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.frag_grocery_list, container, false);


        

        getActivity().getPackageManager();


        listView = (SwipeMenuListView) view.findViewById(R.id.listView);



        Toast.makeText(getActivity(),strtext,Toast.LENGTH_LONG).show();

        new OrderListItem().execute();
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity());

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));

                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);

        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                item = item_detail.get(position);
                a=item.getName();
                b=item.getBrand_name();



//                delete(item);


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("Confirm Delete...");

                alertDialog.setMessage("Are you sure you want delete this?");

                alertDialog.setIcon(R.drawable.prefered_icon);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();

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
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(getActivity(), position + " long click", Toast.LENGTH_SHORT).show();


                return false;
            }
        });


       ReviewGrosseryListActivity.done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


        if (!item_detail.isEmpty())


        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            alertDialog.setTitle("");

            alertDialog.setMessage("Are you sure to place this order ?");

            alertDialog.setIcon(R.drawable.prefered_icon);

            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();


                            dialog.cancel();


                            JSONArray jsonArray = new JSONArray();

                            item = item_detail.get(position);


                            for (int i = 0; i < item_detail.size(); i++)


                            {

                                JSONObject lst = new JSONObject();

                                try {

                                    lst.put("quantity", item.getQuantity());
                                    lst.put("img", item.getImage());
                                    lst.put("upc14", item.getUpc14());

                                    lst.put("name", item.getName());
                                    lst.put("manuallyadded", "1");
                                    lst.put("brand_name", item.getBrand_name());


                                    jsonArray.put(lst);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            jsonadat = jsonArray.toString();


                            new UpdateOrder().execute();
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                            alertDialog.setTitle("");

                            alertDialog.setMessage("Your order place successfully.The Zipster will be sent a message to accept your delivery.They will have five minutes for accepet.");

                            alertDialog.setIcon(R.drawable.prefered_icon);

                            alertDialog.setPositiveButton("YES",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Write your code here to execute after dialog
                                            Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();

//                                            ZipSterCountDown fragment2 = new ZipSterCountDown();
//                                            FragmentManager fm = getFragmentManager();
//                                            FragmentTransaction transaction = fm.beginTransaction();
//                                            transaction.replace(R.id.addstore_container1, fragment2);
//                                            transaction.commit();

                                        }
                                    });

                            // Showing Alert Message
                            alertDialog.show();



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
//
//            Intent i = new Intent(getActivity(), PendingFragmentActivity.class);
//            startActivity(i);



        }

else {


            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            alertDialog.setTitle("Alert");

            alertDialog.setMessage("You have to add item first in your list");

            alertDialog.setIcon(R.drawable.prefered_icon);

            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();


                            dialog.cancel();
                        }
                    });
//            alertDialog.setNegativeButton("NO",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });

            // Showing Alert Message
            alertDialog.show();
        }

            }
        });




        return view;
    }
    private void delete(ReviewGroseryDataType reviewGroseryDataType) {


        // delete app
        try {

            new Delete().execute();
            item_detail.remove(item);
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
        }
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    class Delete extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=delete_item&item_id="+APP_DATA.item_orderid);

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
//            Intent i=new Intent(getActivity(), SelectStoreActivity.class);
//            startActivity(i);


        }}

    class UpdateOrder extends AsyncTask<String, String, String> {

        boolean failure = false;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
        @Override

        protected String doInBackground(String... args) {

            // TODO Auto-generated method stub

            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                JSONObject json=jsonParserPost.makeHttpRequest("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=add_order&grocerydetails="  + URLEncoder.encode(jsonadat, "UTF-8")+","+"&userid="  +URLEncoder.encode(APP_DATA.userid, "UTF-8")+"&storeid="+APP_DATA.store_id+"&zipsterid=14&orderdate=2015-10-12&ordertime=12:10&zipsterwindowid=368&total_cost="+APP_DATA.cost+"&location=bangur%20avenue&state=west%20benagl&city=kolkata&pin=700066", "POST", params);




                Log.d("request!", "starting");

                success = json.getInt(TAG_SUCCESS);

                if (success == 0) {

                    Log.d("User Login!", json.toString());

                    JSONObject c=json.getJSONObject("response");
                    APP_DATA.orderid=c.getString("orderid");


                    loginPreferences = getActivity().getSharedPreferences("loginPreferences", 0);
                    SharedPreferences.Editor editor = loginPreferences.edit();
                    editor.putString("orderid",APP_DATA.orderid);


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
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {

            Toast.makeText(getActivity(),jsonadat,Toast.LENGTH_LONG).show();



        }}



    public class OrderListItem extends AsyncTask<String, String, String> {


        InputStream is;
        String json;
        JSONArray json_arr;

        JSONObject all_news_list_object;

        @Override
        protected void onPreExecute() {


            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... args) {

            try {


                DefaultHttpClient httClient = new DefaultHttpClient();


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=itemlist&order_id="+APP_DATA.item_orderid_for_review+"&user_id="+APP_DATA.userid);

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
                String message= all_news_list_object.getString("message");
              String  code= all_news_list_object.getString("code");

                JSONObject response = all_news_list_object.getJSONObject("response");

                JSONObject basic_details=response.getJSONObject("access_details");

                json_arr=response.getJSONArray("item_details");
                if (json_arr.length() != 0) {

                    for (int i = 0; i < json_arr.length(); i++) {
                        JSONObject Json_Obj_temp;
                        Json_Obj_temp = json_arr.getJSONObject(i);


                        ReviewGroseryDataType obj=new ReviewGroseryDataType(Json_Obj_temp.getString("order_item_id"),
                                Json_Obj_temp.getString("brand_name"),
                                Json_Obj_temp.getString("upc14"),
                                Json_Obj_temp.getString("name"),
                                Json_Obj_temp.getString("image"),
                                Json_Obj_temp.getString("quantity"),
                                Json_Obj_temp.getString("manualy_add"));
                        item_detail.add(obj);



                    }


                }
                else{

                }
            }catch (Exception e){

                e.printStackTrace();

            }


            return null;
        }



        @Override
        protected void onPostExecute(String file_url) {


            adapter = new ReviewListGroseryAdapter(getActivity(),0,item_detail);
            listView.setAdapter(adapter);
            Toast.makeText(getActivity(),"listing",Toast.LENGTH_LONG).show();

        }
    }


}