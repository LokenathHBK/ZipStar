package com.esolz.su.zipstar.ZipStar;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.EntryAdapter;
import com.esolz.su.zipstar.Adapter.ListViewAdapter;
import com.esolz.su.zipstar.DataType.BrandwiseDeatailsDatatype;
import com.esolz.su.zipstar.DataType.Item;
import com.esolz.su.zipstar.DataType.SectionItem;
import com.esolz.su.zipstar.DataType.SerachListviewDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SqlLiteDataBase.DatabaseHandler;

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
import java.util.LinkedList;


public class SearchActivity extends Activity {
    ListView list;
    EditText edt;
    ImageButton searchbtn;
    Item abc;
    Contact localdb;
    LinkedList<SerachListviewDataType> item_detail=new LinkedList<SerachListviewDataType>();
//    LinkedList<BrandwiseDeatailsDatatype> brand;
    EntryAdapter adapter;
    ProgressBar prgbar;
    String search,searchview;
    TextView serchbox1;
    String getdata;
    Button title_menupopuptrack;
    LinearLayout actionBa1r;

    LinkedList<Item> data = new LinkedList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt=(EditText)findViewById(R.id.serchbox);
        actionBa1r=(LinearLayout)findViewById(R.id.actionBa1r);
        prgbar=(ProgressBar)findViewById(R.id.prgbar);
        searchbtn=(ImageButton)findViewById(R.id.searchbutton);
        list=(ListView)findViewById(R.id.listview);
        serchbox1=(TextView)findViewById(R.id.serchbox1);
        searchview=edt.getText().toString();
        serchbox1.setText(searchview);
        title_menupopuptrack=(Button)findViewById(R.id.title_menupopuptrack);
        title_menupopuptrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this,LandingActivity.class));

            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                actionBa1r.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);

                search = edt.getText().toString();
                searchview=edt.getText().toString();
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SerachListviewDataType Item = (SerachListviewDataType)data.get(position);
                getdata=Item.getName();


                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                db.addContact(new Contact(Item));
//                Toast.makeText(getApplicationContext(), Item.getName(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(SearchActivity.this,LandingActivity.class));
            }
        });

    }
    public class SearchResult extends AsyncTask<Void,Void,Void> {


        InputStream is;
        String json;
        JSONArray json_arr;

        JSONObject all_news_list_object;

        @Override
        protected void onPreExecute() {



            super.onPreExecute();
            prgbar.setVisibility(View.VISIBLE);
        }


        @Override
        protected Void doInBackground(Void... params) {

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
                String code= all_news_list_object.getString("code");
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
            }catch (Exception e){

                e.printStackTrace();

            }


            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            prgbar.setVisibility(View.GONE);
            adapter = new EntryAdapter(SearchActivity.this,data,search);
            list.setAdapter(adapter);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
