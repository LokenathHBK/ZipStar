package com.esolz.su.zipstar.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.customviews.CircleImageView;
import com.esolz.su.zipstar.customviews.MyriadProBold;
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
public class PersonalInformationFragment extends Fragment {
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    JSONParser jsonParser = new JSONParser();
    LinearLayout done,changepassword,logout;
    View  view;
    SharedPreferences loginPreferences;
    MyriadProRegularEdit fname,lname,email,phoneno,location,city,state,pin;
    MyriadProBold  username;
    String name,firstname,lastname,phno,zip,locations,cityy,stater,email_id;
    CircleImageView profilepik;
    ImageView back;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      view = inflater.inflate(R.layout.frag_information, container, false);




        getActivity().getPackageManager();

        loginPreferences = getActivity().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);


        profilepik=(CircleImageView)view.findViewById(R.id.profilepik);
        done=(LinearLayout)view.findViewById(R.id.done);
        username=(MyriadProBold)view.findViewById(R.id.username);
        fname=(MyriadProRegularEdit)view.findViewById(R.id.firstname);
        lname=(MyriadProRegularEdit)view.findViewById(R.id.lastlastname);
        phoneno=(MyriadProRegularEdit)view.findViewById(R.id.phone);
        location=(MyriadProRegularEdit)view.findViewById(R.id.location);
        city=(MyriadProRegularEdit)view.findViewById(R.id.city);
        state=(MyriadProRegularEdit)view.findViewById(R.id.state);
        pin=(MyriadProRegularEdit)view.findViewById(R.id.pin);
        email=(MyriadProRegularEdit)view.findViewById(R.id.email);
        back=(ImageView)view.findViewById(R.id.back);



        fname.setText(APP_DATA.name);
        lname.setText(APP_DATA.lname);
        username.setText(APP_DATA.name);
        email.setText(APP_DATA.email);
        location.setText(APP_DATA.location);
        state.setText(APP_DATA.state);
        phoneno.setText(APP_DATA.phone);
        pin.setText(APP_DATA.pin);
        city.setText(APP_DATA.city);

        firstname=username.getText().toString();
        lastname=fname.getText().toString();
        phno=phoneno.getText().toString();
        zip=pin.getText().toString();
        locations=location.getText().toString();
        cityy=city.getText().toString();
        stater=state.getText().toString();
        email_id=email.getText().toString();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPreferences = getActivity().getSharedPreferences("loginPreferences", 0);


//                name = username.getText().toString();
                firstname = username.getText().toString();
                lastname = fname.getText().toString();
                phno = phoneno.getText().toString();
                zip = pin.getText().toString();
                locations = location.getText().toString();
                cityy = city.getText().toString();
                stater = state.getText().toString();
                email_id = email.getText().toString();

//                APP_DATA.fname = firstname;
//                APP_DATA.city = cityy;
//                APP_DATA.state = stater;
//                APP_DATA.pin = zip;
//                APP_DATA.lname = lastname;
//                APP_DATA.email = email_id;
//                APP_DATA.phone = phno;
//                APP_DATA.location = locations;


                new Updateprofile().execute();
                getActivity().finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                getActivity().finish();

            }
        });


        return view;
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
                        "&city="+cityy+
                        "&state="+stater+
                        "&pin="+zip+
                        "&last_name="+lastname+
                        "&email="+email_id+
                        "&phone="+phno+
                        "&location="+locations);



                Log.d("request!", "starting");

                success = json.getInt(TAG_SUCCESS);

                if (success == 0) {

                    Log.d("User Login!", json.toString());

                    JSONObject c=json.getJSONObject("response");

//                    APP_DATA.userid=c.getString("userid");
                    APP_DATA.name=c.getString("name");
                    APP_DATA.location=c.getString("location");

                    APP_DATA.lname=c.getString("last_name");
                    APP_DATA.phone=c.getString("phone");
                    APP_DATA.email=c.getString("email");
                    APP_DATA.city=c.getString("location_city");
                    APP_DATA.state=c.getString("location_state");
                    APP_DATA.pin=c.getString("location_pin");





                    loginPreferences = getActivity().getSharedPreferences("loginPreferences", 0);
                    SharedPreferences.Editor editor = loginPreferences.edit();
//                    editor.putString("userid",APP_DATA.userid);
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





                Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show();
//            Intent i=new Intent(getActivity(), SelectStoreActivity.class);
//            startActivity(i);


        }}



}