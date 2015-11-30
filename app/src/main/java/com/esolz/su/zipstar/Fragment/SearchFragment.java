package com.esolz.su.zipstar.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.esolz.su.zipstar.ZipStar.LandingActivity;
import com.esolz.su.zipstar.ZipStar.PendingFragmentActivity;
import com.esolz.su.zipstar.customviews.MyriadProRegular;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import java.util.LinkedList;

import static java.lang.Thread.sleep;

/**
 * Created by su on 31/8/15.
 */
public class SearchFragment extends Fragment {
    View view;
    String code;
    ListView list;
    EditText edt;
    ImageView searchbtn;
    Item abc;
    Contact localdb;
    String c;
    LinkedList<SerachListviewDataType> item_detail=new LinkedList<SerachListviewDataType>();
        LinkedList<ListDataType> brand;
    EntryAdapter adapter;
    ProgressBar prgbar;
   public static String  search,searchview;
    TextView serchbox1;
    String getdata;
    MyriadProRegular next;
    Button title_menupopuptrack;
    LinearLayout actionBa1r,manualyadd;
    LandingActivity landingActivity;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
String flag;
    LinkedList<Item> data = new LinkedList<Item>();
    ImageView abcd11;
    ScrollView progresslayout;
    RelativeLayout  backlayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_main, container, false);
        getActivity().getPackageManager();
        flag=LandingActivity.flag;
        landingActivity= new LandingActivity();

        abcd11=(ImageView)view.findViewById(R.id.abcd11);
        next=(MyriadProRegular)view.findViewById(R.id.next);
        edt=(EditText)view.findViewById(R.id.serchbox);
        actionBa1r=(LinearLayout)view.findViewById(R.id.actionBa1r);
        manualyadd=(LinearLayout)view.findViewById(R.id.manualyadd);
        prgbar=(ProgressBar)view.findViewById(R.id.prgbar);
        searchbtn=(ImageView)view.findViewById(R.id.searchbutton);
        list=(ListView)view.findViewById(R.id.listview);
        serchbox1=(TextView)view.findViewById(R.id.serchbox1);
        progresslayout=(ScrollView)view.findViewById(R.id.progresslayout);
        searchview=edt.getText().toString();
        serchbox1.setText(searchview);
        title_menupopuptrack=(Button)view.findViewById(R.id.title_menupopuptrack);
        backlayout=(RelativeLayout)view.findViewById(R.id.backlayout);

        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( APP_DATA.page_set.equals("0")) {

                    startActivity(new Intent(getActivity(), LandingActivity.class));


                } else if( APP_DATA.page_set.equals("1")) {

                    startActivity(new Intent(getActivity(),LandingActivity.class));
                }
                else {
                    Toast.makeText(getActivity(),"3",Toast.LENGTH_LONG).show();
                }
            }
        });
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                        actionBa1r.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        search = edt.getText().toString();
                      if(search.length()>0)
                      {
                          searchview = edt.getText().toString();
                          try {
                              searchview= URLEncoder.encode(searchview, "UTF-8");
                          } catch (UnsupportedEncodingException e) {
                              e.printStackTrace();
                          }
                          serchbox1.setText(searchview);
                          new SearchResult().execute();
                      }
else {
                          serchbox1.setText(searchview);
                      }

                    }
                }, 5000);
            }

            @Override
            public void afterTextChanged(Editable s) {

    }
        });


//        title_menupopuptrack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if ( APP_DATA.page_set.equals("0")) {
//
//
//                    APP_DATA.page_set=flag;
//
//                    startActivity(new Intent(getActivity(),LandingActivity.class));
//
//
//                }
//                else if( APP_DATA.page_set.equals("1"))
//                {
//                    startActivity(new Intent(getActivity(),LandingActivity.class));
//
//
//
//
//                                }
//                else {
//                    startActivity(new Intent(getActivity(),LandingActivity.class));
//                    Toast.makeText(getActivity(),"3",Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( APP_DATA.page_set.equals("0")) {

                    startActivity(new Intent(getActivity(), LandingActivity.class));


                } else if( APP_DATA.page_set.equals("1")) {

                    startActivity(new Intent(getActivity(),LandingActivity.class));
                }
                else {
                    Toast.makeText(getActivity(),"3",Toast.LENGTH_LONG).show();
                }



            }
        });
//        edt.addTextChangedListener(new TextWatcher() {
//
//                                              @Override
//                                              public void afterTextChanged(Editable arg0) {
//                                                  // TODO Auto-generated method stub
////                                                  new SearchResult();
//
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
////                                                          manualyadd.setVisibility(View.VISIBLE);
//                                                          list.setVisibility(View.GONE);
//                                                          actionBa1r.setVisibility(View.GONE);
//                                                          new SearchResult().execute();
//                                                      }
//                                                  }.start();
//
//                                              }
//
//                                              @Override
//                                              public void beforeTextChanged(CharSequence arg0, int arg1,
//                                                                            int arg2, int arg3) {
//                                                  // TODO Auto-generated method stub
//
//
//                                              }
//
//                                              @Override
//                                              public void onTextChanged(CharSequence arg0, int arg1, int arg2,
//                                                                        int arg3) {
//                                                  // TODO Auto-generated method stub
//
//                                                  edt.requestFocus();
////                                                  final ArrayList<Country> itemArray = new ArrayList<Country>();
//                                                  int textlength = 0;
//                                                  textlength = edt.getText().length();
////                                                  actionBa1r.setVisibility(View.GONE);
////                                                  list.setVisibility(View.VISIBLE);
////                                                  search = edt.getText().toString();
//                                                  search = edt.getText().toString();
//                                                  searchview=edt.getText().toString();
//                                                  serchbox1.setText(searchview);
////                                                  Toast.makeText(getActivity(),search,Toast.LENGTH_LONG).show();
//////
////                                                  new SearchResult();
//
//
////                                                  new CountDownTimer(10000, 1000) {
////
////                                                      public void onTick(long millisUntilFinished) {
////                                                          Toast.makeText(getActivity(),search + "time start",Toast.LENGTH_LONG).show();
////
////                                                      }
////                                                      public void onFinish() {
////                                                          Toast.makeText(getActivity(),search + "time end",Toast.LENGTH_LONG).show();
////
////                                                          search = edt.getText().toString();
////                                                          actionBa1r.setVisibility(View.GONE);
////                                                          list.setVisibility(View.VISIBLE);
////                                                          new SearchResult().execute();
////                                                      }
////                                                  }.start();
//
//
//                                              }
//
//                                          }
//        );


        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                actionBa1r.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);

                search = edt.getText().toString();
                if(search.length()>0)
                {
                    searchview = edt.getText().toString();
                    try {
                        searchview= URLEncoder.encode(searchview, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    serchbox1.setText(searchview);
                    new SearchResult().execute();
                }
                else
                {
                    serchbox1.setText(searchview);

                }

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
                
//                c=Item.getUpc14();

//                c=m.getQnt();


                DataBaseCreator db = new DataBaseCreator(getActivity());

                if (APP_DATA.page_set.equals("0")) {

                    db.addList(new ListDataType(Item));
//                    db.addList(new ListDataType(c));
//                   landingActivity.SelectItem(0);
//                    startActivity(new Intent(getActivity(), LandingActivity.class));

                } else
                {
                    db.addFav(new FavouriteDataType(Item));
//                   landingActivity.SelectItem(1);
//                   fragmentTransaction = fragmentManager.beginTransaction();
//                   FavoritesFragment bookapnt = new FavoritesFragment();
//
//                   fragmentTransaction.replace(R.id.container,
//                           bookapnt);
//                   fragmentTransaction.commit();
//                    landingActivity.Favitem();

                }
//                Toast.makeText(getActivity(), Item.getBrand_name(), Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(), Item.getName(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), LandingActivity.class));
            }
        });



        return view;
    }

//    public class ScheduledTask extends TimerTask {
//
////        Date now; // to display current time
//
//        // Add your task here
//        public void run() {
//          new SearchResult();
//            Toast.makeText(getActivity(), search, Toast.LENGTH_LONG).show();// initialize date
////            System.out.println("Time is :" + now); // Display current time
//        }
//    }


//    public class SchedulerMain {
//        public void main(String args[]) throws InterruptedException {
//
//            Timer time = new Timer(); // Instantiate Timer Object
//            ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
//            time.schedule(st, 0, 10000); // Create Repetitively task for every 10 secs
//
//            //for demo only.
//            for (int i = 0; i <= 5; i++) {
//                System.out.println("Execution in Main Thread...." + i);
//                Thread.sleep(2000);
//                if (i == 5) {
//                    System.out.println("Application Terminates");
//                    System.exit(0);
//                }
//            }
//        }
//    }



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
                item_detail.clear();
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
//                        BrandwiseDeatailsDatatype obj=new BrandwiseDeatailsDatatype(Json_Obj_temp.getString("brand_name"),
//                                Json_Obj_temp.getInt("total_item_of_this_brand"),
//                                item_detail);


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
    progresslayout.setVisibility(View.GONE);

}

//            Toast.makeText(getActivity(),json,Toast.LENGTH_LONG).show();

        }
    }


}