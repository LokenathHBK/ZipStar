package com.esolz.su.zipstar.ZipStar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProRegularEdit;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by su on 31/8/15.
 */
public class RegestrationActivity  extends Activity {


    MyriadProRegular btnlogin;
    MyriadProRegularEdit username,email,phno,loginpswd,conpas;




    static String  modelog;
    private ProgressDialog pDialog;
    SharedPreferences loginPreferences;
    String responseMSG = "", exception = "", urlResponse = "", message = "";
    JSONParser jsonParser = new JSONParser();



    private static  String LOGIN_URL;




    private static final String TAG_SUCCESS = "code";

    private static final String TAG_MESSAGE = "message";

    String   txtusername,txtemail,txtphone_no,txtpassword,confirm_pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.regestration_activity);
        btnlogin = (MyriadProRegular) findViewById(R.id.btnlogin);
        username = (MyriadProRegularEdit) findViewById(R.id.username);
        email = (MyriadProRegularEdit) findViewById(R.id.email);
        phno = (MyriadProRegularEdit) findViewById(R.id.phno);
        loginpswd = (MyriadProRegularEdit) findViewById(R.id.loginpswd);
        conpas = (MyriadProRegularEdit) findViewById(R.id.conpas);

//        txtusername=username.getText().toString();
//        txtemail=email.getText().toString();
//
//        txtphone_no=phno.getText().toString();
//
//        txtpassword=loginpswd.getText().toString();

        confirm_pas=conpas.getText().toString();



        btnlogin.setOnClickListener(new View.OnClickListener() {
            String MobilePattern = "[0-9]{10}";

            @Override
            public void onClick(View v) {
                Integer flag = 1;
                final String user = username.getText().toString();
                if (!isValidName(user)) {
                    username.setError("Please Enter Name");
                    flag = flag * 0;
//					alertDialog.setMessage("Invalid Name minimum 7 charecter long");
//
//					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog, int which) {
//							//Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//						}
//					});
//					alertDialog.show();
                    username.requestFocus();
                    //username.setFocusable(true);
                } else {
                    final String email1 = email.getText().toString();
                    if (!isValidEmail(email1)) {
                        email.setError("Invalid Email");
                        flag = flag * 0;
//						alertDialog.setMessage("Invalid Email");
//
//
//						alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int which) {
//								//Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//							}
//						});
//						alertDialog.show();
                        email.requestFocus();
                    } else {
                        if (!phno.getText().toString().matches(MobilePattern)) {

                            phno.setError("Please enter valid 10 digit phone number");
                            flag = flag * 0;
//							alertDialog.setMessage("Please enter valid 10 digit phone number");
//
//
//							alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog, int which) {
//									//Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//								}
//							});
//							alertDialog.show();
                            phno.requestFocus();
                        }


                    }
                }
                if (flag == 1) {

                    txtusername=username.getText().toString();
                    txtemail=email.getText().toString();

                    txtphone_no=phno.getText().toString();

                    txtpassword=loginpswd.getText().toString();

                    confirm_pas=conpas.getText().toString();
                    new Regestration().execute();
                }
//                Toast.makeText(RegestrationActivity.this,"clickl", Toast.LENGTH_LONG).show();

            }
        });
    }
    private boolean isValidName(String name) {
        if (name != null && name.length() > 0) {
            return true;
        }
        return false;
    }
    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 0) {
            return true;
        }
        return false;
    }

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

    class Regestration extends AsyncTask<String, String, String> {



        /**
         74
         * Before starting background thread Show Progress Dialog
         75
         * */

        boolean failure = false;



        @Override

        protected void onPreExecute() {

            super.onPreExecute();

//            pDialog = new ProgressDialog(SignUp2.this);
//
//            pDialog.setMessage("Creating User...");
//
//            pDialog.setIndeterminate(false);
//
//            pDialog.setCancelable(true);
//
//            pDialog.show();

        }



        @Override

        protected String doInBackground(String... args) {

            // TODO Auto-generated method stub

            // Check for success tag

            String success;


            LOGIN_URL="http://esolz.co.in/lab4/grocerry/appconnect/login_signup.php?mode=register_save&username="+txtusername+
                    "&email="+txtemail+
                    "&password=" +txtpassword+
                    "&phone=" +txtphone_no+
                    "&device_token=%28null%29" +
                    "&device_type=3" +
                    "&location_city=%28null%29" +
                    "&location_state=%28null%29" +
                    "&location_pin=%28null%29" +
                    "&location_lat=0.000000" +
                    "&location_lang=0.000000";









            try {
                exception = "";
                responseMSG = "";
                urlResponse = "";
                message = "";
                DefaultHttpClient httpclient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(LOGIN_URL);
                HttpResponse response;
                response = httpclient.execute(httpget);
                HttpEntity httpentity = response.getEntity();
                InputStream is = httpentity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                urlResponse = sb.toString();
                JSONObject jOBJ = new JSONObject(urlResponse);
                JSONObject c=jOBJ.getJSONObject("response");

                APP_DATA.userid=c.getString("userid");
                APP_DATA.name=c.getString("username");
                APP_DATA.phone=c.getString("phone");
                APP_DATA.image=c.getString("image");
                APP_DATA.thumb=c.getString("thumb");
                APP_DATA.location=c.getString("location");

//                loginPreferences = getSharedPreferences("loginPreferences", 0);
//                SharedPreferences.Editor editor = loginPreferences.edit();
//                editor.putString("userid",APP_DATA.userid);
//                editor.putString("username",APP_DATA.name);
//                editor.putString("phone",APP_DATA.phone);
//                editor.putString("image",APP_DATA.image);
//                editor.putString("thumb",APP_DATA.thumb);
//                editor.putString("location",APP_DATA.location);
//                editor.commit();
                responseMSG = jOBJ.getString("response");
                message = jOBJ.getString("code");
                return message;

            } catch (Exception e) {
                exception = e.toString();
            }




            return null;



        }


        protected void onPostExecute(String file_url) {

            // dismiss the dialog once product deleted

//            pDialog.dismiss();

            if (file_url != null){

                Toast.makeText(RegestrationActivity.this, file_url, Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegestrationActivity.this, LoginActivity.class));
            }

        }

    }

}
