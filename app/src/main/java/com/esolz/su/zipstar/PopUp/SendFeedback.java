package com.esolz.su.zipstar.PopUp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.ZipStar.SearchActivity;
import com.esolz.su.zipstar.ZipStar.SettingFragmentActivity;
import com.esolz.su.zipstar.customviews.MyriadProLightEdit;
import com.esolz.su.zipstar.helper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by su on 12/10/15.
 */
public class SendFeedback extends PopupWindow {
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";
    SharedPreferences loginPreferences;
    View popupView;
    Context context;
    MyriadProLightEdit feedback;
    LinearLayout send,cancel;
String getfeedback;


    public SendFeedback(final Context context) {
        super(context);

        this.context = context;

        setContentView(LayoutInflater.from(context).inflate(
                R.layout.popup_feedback, null));
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupView = getContentView();
        setFocusable(true);

        feedback=(MyriadProLightEdit)popupView.findViewById(R.id.feedback);
        send=(LinearLayout)popupView.findViewById(R.id.send);
        cancel=(LinearLayout)popupView.findViewById(R.id.cancel);
        getfeedback=feedback.getText().toString();
        getmoreLayouts();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfeedback = feedback.getText().toString();

                new Feedback().execute();
               dismiss();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfeedback=feedback.getText().toString();

//        context.startActivity(new Intent(context, SettingFragmentActivity.class));
                dismiss();

            }
        });

    }

    public void getmoreLayouts () {


    }

    class Feedback extends AsyncTask<String, String, String> {

        boolean failure = false;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
        @Override

        protected String doInBackground(String... args) {

            // TODO Auto-generated method stub

            String success;
            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                JSONObject json=jsonParser.getJSONFromUrl("http://esolz.co.in/lab4/grocerry/appconnect/app_connect.php?mode=send_feedback_mail&sender_uid="+ APP_DATA.userid+"&feedback=very%20nice%20app");

                Log.d("request!", "starting");

                success = json.getString(TAG_SUCCESS);

                if (success.equals("0")) {

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

            dismiss();


        }}




}
