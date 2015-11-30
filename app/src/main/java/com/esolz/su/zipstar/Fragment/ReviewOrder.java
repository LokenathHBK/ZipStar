package com.esolz.su.zipstar.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.ZipStar.PendingFragmentActivity;
import com.esolz.su.zipstar.ZipStar.ReviewGrosseryListActivity;
import com.esolz.su.zipstar.customviews.MyriadProLight;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProSemibold;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by su on 7/10/15.
 */
public class ReviewOrder extends Fragment {

    View view;
    MyriadProRegular date;
    MyriadProLight  time;
    MyriadProSemibold estemated_cost;
    LinearLayout cancel_order;
    MyriadProSemibold zipster_name;
LinearLayout review_grossproduct;
    String zipstername,cost,d_time,d_date,order_id;
ImageView back;

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    SharedPreferences loginPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        zipstername = getArguments().getString("zipster_name");
        cost = getArguments().getString("cost");
        d_time = getArguments().getString("d_time");
        d_date = getArguments().getString("d_date");
        order_id = getArguments().getString("order_id");

        view = inflater.inflate(R.layout.review_order, container, false);

        loginPreferences = getActivity().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        review_grossproduct=(LinearLayout)view.findViewById(R.id.review_grossproduct);
        time=(MyriadProLight)view.findViewById(R.id.time);
        zipster_name=(MyriadProSemibold)view.findViewById(R.id.zipster_name);
        estemated_cost=(MyriadProSemibold)view.findViewById(R.id.cost);
        date=(MyriadProRegular)view.findViewById(R.id.date);
        cancel_order=(LinearLayout)view.findViewById(R.id.cancel_order);
        back=(ImageView)view.findViewById(R.id.back);

        zipster_name.setText(zipstername);
        estemated_cost.setText(cost);
        time.setText(d_time);
        date.setText(d_date);



    cancel_order.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            order_id = getArguments().getString("order_id");

            new CancelOrder().execute();
            Intent i = new Intent(getActivity(), PendingFragmentActivity.class);
            startActivity(i);

        }
    });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                      fragmentTransaction = fragmentManager.beginTransaction();
//                      OrderFragment bookapnt_fragment = new OrderFragment();
//
//                      fragmentTransaction.replace(R.id.pending_container,
//                        bookapnt_fragment);
//                     fragmentTransaction.commit();

                Intent i = new Intent(getActivity(), PendingFragmentActivity.class);
                startActivity(i);

            }
        });

        review_grossproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                order_id = getArguments().getString("order_id");
//                APP_DATA.item_orderid_for_review= getArguments().getString("order_id");
//
//                Intent i = new Intent(getActivity(), ReviewGrosseryListActivity.class);
//
//                startActivity(i);
//                Toast.makeText(getActivity(),order_id,Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    class CancelOrder extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=cancel_order&order_id="+order_id);

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





            Toast.makeText(getActivity(),"canceled successfully", Toast.LENGTH_LONG).show();



        }}


}
