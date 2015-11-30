package com.esolz.su.zipstar.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.esolz.su.zipstar.Adapter.OpenJobAdapter;
import com.esolz.su.zipstar.DataType.OpenJobDataType;
import com.esolz.su.zipstar.DateTimePicker.DateTimePickerDialog;
import com.esolz.su.zipstar.R;
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
import java.util.Calendar;
import java.util.LinkedList;

/**
 * Created by su on 31/8/15.
 */
public class ManageWindowFragment extends Fragment implements DateTimePickerDialog.DateTimeListener{
    MyriadProRegular done;
  View view;
    String prefered_time;
    MyriadProRegular select_time;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_manage_window, container, false);

        getActivity().getPackageManager();

done=(MyriadProRegular)view.findViewById(R.id.done);
        select_time=(MyriadProRegular)view.findViewById(R.id.select_time);


        final Calendar c = Calendar.getInstance();
      String  yy = String.valueOf(c.get(Calendar.YEAR));
        String   mm = String.valueOf(c.get(Calendar.MONTH));
        String dd = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        ((TextView)view.findViewById(R.id.date)).setText(yy);
        ((TextView) view.findViewById(R.id.hour)).setText(mm);
        ((TextView) view.findViewById(R.id.min)).setText(dd);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String cid= prefered_time;

                ManageWindowsFragment fragment2 = new ManageWindowsFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("CID", cid);
                fragment2.setArguments(args);
                transaction.replace(R.id.delivery_container, fragment2);
                transaction.commit();

            }
        });




        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog();
            }
        });







        return view;
    }

    private void showDateTimeDialog() {
        DateTimePickerDialog pickerDialog = new DateTimePickerDialog(getActivity(), false, this);
        pickerDialog.show();
    }

    @Override
    public void onDateTimeSelected(int year, int month, int day, int hour, int min, int am_pm) {
        String text = day + "/" + month + "/" + year + " - " + hour + ":" + min;
        prefered_time= year + "-" + month + "-" + day;

        String day1 = String.valueOf(day)+ "-" + String.valueOf(month);
        String month1 = String.valueOf(month);
        String hour1 = String.valueOf(hour);
        String min1 = String.valueOf(min);
String qqq=String.valueOf(day);
        String wer= String.valueOf(year);



        if (am_pm != -1)
            text = text + (am_pm == Calendar.AM ? "AM" : "PM");
//        ((TextView) findViewById(R.id.date)).setText(text);
        ((TextView)view.findViewById(R.id.date)).setText(qqq);
        ((TextView) view.findViewById(R.id.hour)).setText(month1);
        ((TextView) view.findViewById(R.id.min)).setText(wer);
//        ((TextView) view.findViewById(R.id.ampm)).setText((am_pm == Calendar.AM ? "AM" : "PM"));
    }
}