package com.esolz.su.zipstar.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.EntryAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.Item;
import com.esolz.su.zipstar.DataType.SectionItem;
import com.esolz.su.zipstar.DataType.SerachListviewDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.FavouriteDataType;
import com.esolz.su.zipstar.SqlLiteDataBase.ListDataType;
import com.esolz.su.zipstar.ZipStar.ReviewGrosseryListActivity;
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
public class ReviewSearchFragment extends Fragment {
    View view;
    String code;
    ListView list;
    EditText edt;
    ImageButton searchbtn;
    Item abc;
    Contact localdb;
    String c;
    LinkedList<SerachListviewDataType> item_detail=new LinkedList<SerachListviewDataType>();
        LinkedList<ListDataType> brand;
    EntryAdapter adapter;
    ProgressBar prgbar;
    String search,searchview;
    TextView serchbox1;
    String getdata,get_order_id,brand_name,upc14,name,image,quantity,manualy_add;

    Button title_menupopuptrack;
    LinearLayout actionBa1r,manualyadd;
    ReviewGrosseryListActivity reviewGrosseryListActivity;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    String flag,strtext;
    LinkedList<Item> data = new LinkedList<Item>();
    ImageView abcd11;
    ScrollView progresslayout;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        view = inflater.inflate(R.layout.activity_main, container, false);
        getActivity().getPackageManager();
        flag=ReviewGrosseryListActivity.flag;
        reviewGrosseryListActivity= new ReviewGrosseryListActivity();



        abcd11=(ImageView)view.findViewById(R.id.abcd11);

        edt=(EditText)view.findViewById(R.id.serchbox);
        actionBa1r=(LinearLayout)view.findViewById(R.id.actionBa1r);
        manualyadd=(LinearLayout)view.findViewById(R.id.manualyadd);
        prgbar=(ProgressBar)view.findViewById(R.id.prgbar);
        searchbtn=(ImageButton)view.findViewById(R.id.searchbutton);
        list=(ListView)view.findViewById(R.id.listview);
        serchbox1=(TextView)view.findViewById(R.id.serchbox1);
        progresslayout=(ScrollView)view.findViewById(R.id.progresslayout);
        searchview=edt.getText().toString();
        serchbox1.setText(searchview);
        title_menupopuptrack=(Button)view.findViewById(R.id.title_menupopuptrack);

        title_menupopuptrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReviewGrosseryListActivity.class));

            }
        });


        edt.addTextChangedListener(new TextWatcher() {

                                              @Override
                                              public void afterTextChanged(Editable arg0) {
                                                  // TODO Auto-generated method stub
//                                                  new SearchResult();

                                                  new CountDownTimer(10000, 1000) {

                                                      public void onTick(long millisUntilFinished) {
                                                          Toast.makeText(getActivity(),search + "time start",Toast.LENGTH_LONG).show();

                                                      }
                                                      public void onFinish() {
                                                          Toast.makeText(getActivity(),search + "time end",Toast.LENGTH_LONG).show();

                                                          search = edt.getText().toString();
                                                          actionBa1r.setVisibility(View.GONE);
                                                          list.setVisibility(View.VISIBLE);
                                                          new SearchResult().execute();
                                                      }
                                                  }.start();

                                              }

                                              @Override
                                              public void beforeTextChanged(CharSequence arg0, int arg1,
                                                                            int arg2, int arg3) {
                                                  // TODO Auto-generated method stub


                                              }

                                              @Override
                                              public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                                                        int arg3) {
                                                  // TODO Auto-generated method stub

                                                  edt.requestFocus();
//                                                  final ArrayList<Country> itemArray = new ArrayList<Country>();
                                                  int textlength = 0;
                                                  textlength = edt.getText().length();
//                                                  actionBa1r.setVisibility(View.GONE);
//                                                  list.setVisibility(View.VISIBLE);
//                                                  search = edt.getText().toString();
                                                  search = edt.getText().toString();
                                                  searchview=edt.getText().toString();
                                                  serchbox1.setText(searchview);
//                                                  Toast.makeText(getActivity(),search,Toast.LENGTH_LONG).show();
////
//                                                  new SearchResult();


//                                                  new CountDownTimer(10000, 1000) {
//
//                                                      public void onTick(long millisUntilFinished) {
//                                                          Toast.makeText(getActivity(),search + "time start",Toast.LENGTH_LONG).show();
//
//                                                      }
//                                                      public void onFinish() {
//                                                          Toast.makeText(getActivity(),search + "time end",Toast.LENGTH_LONG).show();
//
//                                                          search = edt.getText().toString();
//                                                          actionBa1r.setVisibility(View.GONE);
//                                                          list.setVisibility(View.VISIBLE);
//                                                          new SearchResult().execute();
//                                                      }
//                                                  }.start();


                                              }

                                          }
        );


        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                actionBa1r.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);

                search = edt.getText().toString();
                searchview = edt.getText().toString();
                serchbox1.setText(searchview);
                new SearchResult().execute();

//                adapter = new ListViewAdapter(SearchActivity.this,0,item_detail);
//                for (int i = 1; i < 30; i++) {
//                    adapter.addItem(abc);
//                    if (i % 4 == 0) {
//                        adapter.addSectionHeaderItem(abc);
//                    }
//                }
//                list.setAdapter(adapter);

            }

        });
        abcd11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(),MannualyAddedFragment.class);
                startActivity(i);


            }

        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SectionItem brand_name=(SectionItem)data.get(position);
                SerachListviewDataType Item = (SerachListviewDataType) data.get(position);
//                ListDataType m=(ListDataType) brand.get(position);


                getdata = Item.getName();




                brand_name=Item.getBrand_name();
                        upc14=Item.getUpc14();
                        name=Item.getName();
                        image=Item.getImage();
                        quantity=Item.getQnt();
                        manualy_add="1";
//                c=Item.getUpc14();

//                c=m.getQnt();



                if (flag.equals("0")) {



                    brand_name=Item.getBrand_name();
                    upc14=Item.getUpc14();
                    name=Item.getName();
                    image=Item.getImage();
                    quantity=Item.getQnt();
                    manualy_add="1";
                    new AddtoReviewList();
                    startActivity(new Intent(getActivity(), ReviewGrosseryListActivity.class));


                } else {
                    DataBaseCreator db = new DataBaseCreator(getActivity());

                    db.addFav(new FavouriteDataType(Item));


                }
//                Toast.makeText(getActivity(), Item.getBrand_name(), Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(), Item.getName(), Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getActivity(), ReviewGrosseryListActivity.class));
            }
        });



        return view;
    }

    class AddtoReviewList extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=add_item&get_order_id="+APP_DATA.item_orderid_for_review+"&brand_name="+brand_name+"&upc14="+upc14+"&name="+name+"&image="+image+"&quantity="+quantity+"&manualy_add="+manualy_add);

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



            Toast.makeText(getActivity(),strtext,Toast.LENGTH_LONG).show();




        }}




    public class SearchResult extends AsyncTask<String, String, String> {


        InputStream is;
        String json;
        JSONArray json_arr;

        JSONObject all_news_list_object;

        @Override
        protected void onPreExecute() {


            super.onPreExecute();
            progresslayout.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... args) {

            try {


                DefaultHttpClient httClient = new DefaultHttpClient();


                HttpGet http_get= new HttpGet("http://esolz.co.in/lab4/grocerry/search_json.php?key="+search+"&size=100");

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
                code= all_news_list_object.getString("code");
//                LinkedList<Item> data = new LinkedList<Item>();
                //String total_data = all_news_list_object.getString("all_user");
                JSONObject response = all_news_list_object.getJSONObject("response");

                JSONObject basic_details=response.getJSONObject("basic_details");
                json_arr=response.getJSONArray("brand_wise_details");
                if (json_arr.length() != 0) {

                    for (int i = 0; i < json_arr.length(); i++) {
                        JSONObject Json_Obj_temp;
                        Json_Obj_temp = json_arr.getJSONObject(i);
                        data.add(new SectionItem(Json_Obj_temp.getString("brand_name")));
                        JSONArray item_details=Json_Obj_temp.getJSONArray("item_details");
                        if(item_details.length()>0)
                        {
                            for (int j=0;j<item_details.length();j++)
                            {
                                JSONObject jobj=item_details.getJSONObject(j);
                                SerachListviewDataType object=new SerachListviewDataType(Json_Obj_temp.getString("brand_name"),jobj.getString("upc14"),jobj.getString("name"),jobj.getString("img"),"1",search);
//                                item_detail.add(object);
                                data.add(new SerachListviewDataType(Json_Obj_temp.getString("brand_name"),jobj.getString("upc14"),jobj.getString("name"),jobj.getString("img"),"1",search));
                            }
                        }



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

if (code.equals("0")) {
    Toast.makeText(getActivity(), code, Toast.LENGTH_LONG).show();
    progresslayout.setVisibility(View.GONE);
    adapter = new EntryAdapter(getActivity(),data,search);
    list.setAdapter(adapter);
}else {
    manualyadd.setVisibility(View.VISIBLE);
    list.setVisibility(View.GONE);
    actionBa1r.setVisibility(View.GONE);


}


        }
    }


}