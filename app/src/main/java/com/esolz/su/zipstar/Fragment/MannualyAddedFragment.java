package com.esolz.su.zipstar.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.DatabaseHandler;
import com.esolz.su.zipstar.SqlLiteDataBase.FavouriteDataType;
import com.esolz.su.zipstar.SqlLiteDataBase.ListDataType;
import com.esolz.su.zipstar.ZipStar.LandingActivity;
import com.esolz.su.zipstar.customviews.CircleImageView;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProRegularEdit;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by su on 31/8/15.
 */
public class MannualyAddedFragment extends FragmentActivity {
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParser jsonParser = new JSONParser();
    LinearLayout changepassword,logout,dropdown,listview_visibel;
    View  view;
    SharedPreferences loginPreferences;
    MyriadProRegularEdit item,brand,type;
    String sitem,sbrand,stype;
    CircleImageView profilepik;
    LandingActivity landingActivity;
    DataBaseCreator db = new DataBaseCreator(MannualyAddedFragment.this);
    String flag;
    ListView listView;
    String itemValue;
    MyriadProRegular ok;
    TextView done;
    MyriadProRegular txtqnt;
    boolean flag1=true;

    String strqnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.frag_manuallyadded);

        flag=LandingActivity.flag;
        flag=APP_DATA.page_set;
        landingActivity= new LandingActivity();

//        flag=LandingActivity.flag;
//        landingActivity= new LandingActivity();

        txtqnt=(MyriadProRegular)findViewById(R.id.txtqnt);
        done=(TextView)findViewById(R.id.done);
        listView = (ListView) findViewById(R.id.listviewdropdown);
        ok = (MyriadProRegular) findViewById(R.id.ok);
        dropdown=(LinearLayout)findViewById(R.id.dropdown);
        listview_visibel=(LinearLayout)findViewById(R.id.listview_visibel);
        item=(MyriadProRegularEdit)findViewById(R.id.item);
        brand=(MyriadProRegularEdit)findViewById(R.id.brand);
        type=(MyriadProRegularEdit)findViewById(R.id.type);
        final String[] values = new String[] { "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32"


        };




        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.quant_row_item, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);


        sitem=item.getText().toString();
        sbrand=brand.getText().toString();
        stype=type.getText().toString();






        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stype=type.getText().toString();
//                String a=txtqnt.getText().toString();



                sitem=item.getText().toString();
                if(sitem==null)
                {
                    item.setError("Item  Can't be Blank");

                    flag1=false;
                }
                else
                {
                    sbrand=brand.getText().toString();

                    if(sbrand==null)
                    {
                        brand.setError("Brand Can't Be Blank");
//                        flag1=false;
                    }
                        else {

                        String a=txtqnt.getText().toString();

                        if(a==null)
                        {
                            txtqnt.setError("Quant  Can't be Blank");

//                            flag1=false;
                        }


                        else
                    {
                        flag1=true;
//                      data base

                        if (flag.equals("0")) {
                            db.addList(new ListDataType(a,sitem,sbrand));
//                            Intent i=new Intent(getApplicationContext(),LandingActivity.class);
//                            startActivity(i);

//                            Toast.makeText(getApplicationContext(),"list item",Toast.LENGTH_LONG).show();
                        } else {
                            db.addManualfav(new FavouriteDataType(a, sitem, sbrand));
//                            Toast.makeText(getApplicationContext(),"fav item",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(getApplicationContext(),LandingActivity.class);
                            startActivity(i);
                        }

                    }




                    }



//                    else
//                    {
//                        flag1=true;
////                      data base
//
//                        if (flag.equals("0")) {
//                            db.addList(new ListDataType(a,sitem,sbrand));
//                            Intent i=new Intent(getApplicationContext(),LandingActivity.class);
//                            startActivity(i);
//
//                            Toast.makeText(getApplicationContext(),"list item",Toast.LENGTH_LONG).show();
//                        } else {
//                            db.addManualfav(new FavouriteDataType(a,sitem,sbrand));
//                            Toast.makeText(getApplicationContext(),"fav item",Toast.LENGTH_LONG).show();
//                            Intent i=new Intent(getApplicationContext(),LandingActivity.class);
//                            startActivity(i);
//                        }
//
//                    }
                }




//                if (flag.equals("0")) {
//                    db.addList(new ListDataType(a,sitem,sbrand));
//                    Intent i=new Intent(getApplicationContext(),LandingActivity.class);
//                    startActivity(i);
////                    db.addList(new ListDataType(c));
////                   landingActivity.SelectItem(0);
////                    startActivity(new Intent(getActivity(), LandingActivity.class));
//                    Toast.makeText(getApplicationContext(),"list item",Toast.LENGTH_LONG).show();
//                } else {
//                     db.addManualfav(new FavouriteDataType(a,sitem,sbrand));
//Toast.makeText(getApplicationContext(),"fav item",Toast.LENGTH_LONG).show();
//                    Intent i=new Intent(getApplicationContext(),LandingActivity.class);
//                    startActivity(i);
//                }



            }
        });

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
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
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

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//

                listview_visibel.setVisibility(View.GONE);

//                DataBaseCreator db = new DataBaseCreator(getApplicationContext());
//
//                db.Update_Contact(new ListDataType(APP_DATA.detailrow, APP_DATA.lpname, APP_DATA.lbname));
//                Toast.makeText(getApplicationContext(), APP_DATA.detailrow, Toast.LENGTH_LONG).show();
//
//                view.setVisibility(View.GONE);
                   txtqnt.setText(itemValue);

            }
        });

        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listview_visibel.setVisibility(View.VISIBLE);


            }
        });


    }


    public void Reset_Text() {

        item.getText().clear();
        brand.getText().clear();
        type.getText().clear();

    }






}