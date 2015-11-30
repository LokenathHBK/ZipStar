package com.esolz.su.zipstar.ZipStar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;

import com.esolz.su.zipstar.Fragment.SelectStore;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProLight;
import com.esolz.su.zipstar.customviews.MyriadProLightEdit;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProRegularEdit;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by su on 31/8/15.
 */
public class LoginActivity  extends Activity {


    MyriadProRegular signup;
    MyriadProBold signupnow;
    MyriadProLight fpass;
    MyriadProRegularEdit phno,loginpswd;
//    MyriadProLightEdit currentpaswd;
    MyriadProLightEdit newpaswd,confirmpaswd;
    MyriadProLightEdit vcode;
    Button code_submit,change_passwd;
    LinearLayout verify_layout,new_pass,progressbar;
    ;

    String success,success1,success2;
    String code;
    String psword,phone,verifymail;
    String userid;
    String user_id,newpass,confrm;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    JSONParser jsonParser = new JSONParser();
    boolean flag=true;
    SharedPreferences loginPreferences;
    LandingActivity landingActivity;
    PreferredOrderTimeFActivity preferredOrderTimeFActivity;
    String flag1;
    String flag2;
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    private   String LOGIN_URL = "http://esolz.co.in/lab4/grocerry/appconnect/login_signup.php?mode=login&username="+phone+"&password="+psword+"&device_token=&device_type=3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);
        flag1=LandingActivity.flag;
        flag2=PreferredOrderTimeFActivity.flag;

        landingActivity= new LandingActivity();
        preferredOrderTimeFActivity=new PreferredOrderTimeFActivity();


        phno=(MyriadProRegularEdit)findViewById(R.id.phno);

        loginpswd=(MyriadProRegularEdit)findViewById(R.id.loginpswd);

        change_passwd=(Button)findViewById(R.id.submit2);

        signup=(MyriadProRegular)findViewById(R.id.btnlogin);

        fpass=(MyriadProLight)findViewById(R.id.fpass);

        signupnow=(MyriadProBold)findViewById(R.id.signupnow);


        progressbar=(LinearLayout)findViewById(R.id.progressbar);
        verify_layout=(LinearLayout)findViewById(R.id.verify_layout);
        new_pass=(LinearLayout)findViewById(R.id.new_pass);

//        currentpaswd=(MyriadProLightEdit)findViewById(R.id.currentpaswd);

        confirmpaswd=(MyriadProLightEdit)findViewById(R.id.confirmpaswd);
        newpaswd=(MyriadProLightEdit)findViewById(R.id.newpaswd);


        vcode=(MyriadProLightEdit)findViewById(R.id.currentpaswd);
        code_submit=(Button)findViewById(R.id.submit);



        signupnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegestrationActivity.class);
                startActivity(i);
            }
        });


        fpass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(LoginActivity.this);
                View promptsView = li.inflate(R.layout.forgetpassword, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        LoginActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final MyriadProLightEdit userInput = (MyriadProLightEdit) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Reset",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        // get user input and set it to result
                                        // edit text
                                        verifymail = userInput.getText().toString();
                                        if (isValidEmail(verifymail)) {
                                            Toast.makeText(LoginActivity.this,"Check your Email inbox for Verification Code",Toast.LENGTH_LONG).show();
                                            new ForgetPassword().execute();
                                        }
                                        else
                                        {
                                            userInput.setError("Give Valid Email");
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }

        });

        code_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code=vcode.getText().toString();
                if (code!=null || code.length()>0) {
                    new VerifyCode().execute();
                }
                else
                {
                    vcode.setError("Please Fill The code Field");
                }
            }
        });

        change_passwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "click submit", Toast.LENGTH_LONG).show();

                newpass=newpaswd.getText().toString();

                confrm=confirmpaswd.getText().toString();


                if(!isValidPassword(newpass))
                {
                    newpaswd.setError("New Password Blank");
                    Toast.makeText(LoginActivity.this, newpass, Toast.LENGTH_LONG).show();

                }
                else
                {
                    if(newpass.equals(confrm))
                    {                    Toast.makeText(LoginActivity.this, confrm, Toast.LENGTH_LONG).show();

                        new NewPasswordSet().execute();
                        Toast.makeText(LoginActivity.this, "paswd set", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(LoginActivity.this,LandingActivity.class);
                        startActivity(i);

                    }
                    else
                    {

                        confirmpaswd.setText("Password Does Not Match'");
                    }

                }


            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            String MobilePattern = "[0-9]{10}";
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                phone=phno.getText().toString();
                if(phone==null)
                {
                    phno.setError("Phone Number Can't be Blank");

                    flag=false;
                }
                else
                {
                    psword=loginpswd.getText().toString();

                    if(psword==null)
                    {
                        loginpswd.setError("Password Can't Be Blank");
                    }

                    else
                    {
                        flag=true;
                        LOGIN_URL = "http://esolz.co.in/lab4/grocerry/appconnect/login_signup.php?mode=login&username="+phone+""
                                +"&password="+psword+
                                "&device_token=&device_type=3";



                        new LoginUser().execute();

                    }
                }




            }
        });

    }


    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length()>0) {
            return true;
        }
        return false;
    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    //----------------------------------------LOGIN USSER----------------------------------------//

    class LoginUser extends AsyncTask<String, String, String> {

        boolean failure = false;
        @Override
        protected void onPreExecute() {
            progressbar.setVisibility(View.VISIBLE);

            super.onPreExecute();
        }
        @Override

        protected String doInBackground(String... args) {

            // TODO Auto-generated method stub

            int success;
            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                JSONObject json=jsonParser.getJSONFromUrl(LOGIN_URL);

                Log.d("request!", "starting");

                success = json.getInt(TAG_SUCCESS);

                if (success == 0) {

                    Log.d("User Login!", json.toString());

                    JSONObject c=json.getJSONObject("response");

                    APP_DATA.userid=c.getString("userid");
                    APP_DATA.name=c.getString("username");
                    APP_DATA.image=c.getString("image");
                    APP_DATA.thumb=c.getString("thumb");
                    APP_DATA.location=c.getString("location");

                    APP_DATA.lname=c.getString("last_name");
                    APP_DATA.phone=c.getString("phone");
                    APP_DATA.email=c.getString("email");
                    APP_DATA.city=c.getString("location_city");
                    APP_DATA.state=c.getString("location_state");
                    APP_DATA.pin=c.getString("location_pin");





                    loginPreferences = getSharedPreferences("loginPreferences", 0);
                    SharedPreferences.Editor editor = loginPreferences.edit();
                    editor.putString("userid",APP_DATA.userid);
                    editor.putString("username",APP_DATA.name);
                    editor.putString("image",APP_DATA.image);
                    editor.putString("thumb", APP_DATA.thumb);
                    editor.putString("location", APP_DATA.location);


                    editor.putString("last_name",APP_DATA.lname);
                    editor.putString("phone",APP_DATA.phone);
                    editor.putString("email",APP_DATA.email);
                    editor.putString("location_city", APP_DATA.city);
                    editor.putString("location_pin", APP_DATA.pin);
                    editor.putString("location_state", APP_DATA.state);

                    editor.putString("Remember", "remember");
                    editor.commit();


                    if (flag1.equals("3")){
                        Intent i=new Intent(getApplicationContext(),LandingActivity.class);
                        startActivity(i);

                    }else if(flag1.equals("4")){
                        Intent i=new Intent(getApplicationContext(),DeliverFragmentActivity.class);
                        startActivity(i);

                    }else if(flag1.equals("5")){
                        Intent i=new Intent(getApplicationContext(),PendingFragmentActivity.class);
                        startActivity(i);

                    }else if(flag1.equals("6"))
                    { Intent i=new Intent(getApplicationContext(),SettingFragmentActivity.class);
                    startActivity(i);
                    }
                    else {

                        Intent i=new Intent(getApplicationContext(),SelectStore.class);
                        startActivity(i);
                    }




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



            if (file_url != null){
                progressbar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();

            }}}





    //----------------------------------------forget PASSWORD----------------------------------------//

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
    class ForgetPassword extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://203.196.159.37/lab4/grocerry/appconnect/login_signup.php?mode=forgot_pass&email="+verifymail);
                Log.d("request!", "starting");
                success = json.getString(TAG_SUCCESS);
                if (success.equals("0")) {
                    Log.d("User Login!", json.toString());
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

            // dismiss the dialog once product deleted

//            pDialog.dismiss();

            if (file_url != null){

                Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
                if (success.equals("0"))
                {
                    verify_layout.setVisibility(View.VISIBLE);
//                    currentpaswd.requestFocus();
                }

            }

        }

    }

    //----------------------------------------VERIFY CODE----------------------------------------//



    class VerifyCode extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://203.196.159.37/lab4/grocerry/appconnect/login_signup.php?mode=verify_PRC&prc="+code);
                Log.d("request!", "starting");
                success1 = String.valueOf(json.getInt(TAG_SUCCESS));
                if (success.equals(0)) {
                    Log.d("User Login!", json.toString());
                    JSONObject c=json.getJSONObject("response");
                    userid=c.getString("userid");
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

            if (file_url != null){

                Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
                if (success1.equals("0"))
                {
                    verify_layout.setVisibility(View.GONE);
                    new_pass.setVisibility(View.VISIBLE);
//                    newpaswd.requestFocus();
                }

            }}}


   //----------------------------------------CHANGE PASSWORD----------------------------------------//


    class NewPasswordSet extends AsyncTask<String, String, String> {

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
                JSONObject json=jsonParser.getJSONFromUrl("http://203.196.159.37/lab4/grocerry/appconnect/app_connect.php?mode=update_password&id="+user_id+"&password="+newpass);
                Log.d("request!", "starting");
                success = json.getInt(TAG_SUCCESS);
                if (success == 0) {

                    Log.d("User Login!", json.toString());

                    JSONObject c=json.getJSONObject("response");
                    userid=c.getString("userid");


                    APP_DATA.userid=c.getString("userid");
                    APP_DATA.name=c.getString("username");
                    APP_DATA.image=c.getString("image");
                    APP_DATA.thumb=c.getString("thumb");
                    APP_DATA.location=c.getString("location");

                    loginPreferences = getSharedPreferences("loginPreferences", 0);
                    SharedPreferences.Editor editor = loginPreferences.edit();
                    editor.putString("userid",APP_DATA.userid);
                    editor.putString("username",APP_DATA.name);
                    editor.putString("image",APP_DATA.image);
                    editor.putString("thumb",APP_DATA.thumb);
                    editor.putString("location",APP_DATA.location);

                    editor.putString("Remember","remember");
                    editor.commit();
                    Intent i=new Intent(LoginActivity.this, LandingActivity.class);
                    i.putExtra("user_id",userid);
                    startActivity(i);
                    finish();

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
            if (file_url != null){

                Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();


            }

        }
    }}
