package com.esolz.su.zipstar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.esolz.su.zipstar.DataType.TimeWindowDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProSemibold;

import java.util.LinkedList;

/**
 * Created by su on 7/9/15.
 */
public class TimeWindowAdapter extends ArrayAdapter<TimeWindowDataType> {

    LinkedList<TimeWindowDataType> item_list=new LinkedList<TimeWindowDataType>();
    TimeWindowDataType abc;
    LayoutInflater mInflater;
    Context context;
    MyriadProRegular time,date;
    MyriadProSemibold name,price;


    public TimeWindowAdapter(Context context, int resource, LinkedList<TimeWindowDataType> object) {
        super(context, resource, object);


        this.context = context;
        item_list = object;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView=mInflater.inflate(R.layout.time_window_rowitem,parent,false);



        time=(MyriadProRegular)convertView.findViewById(R.id.time);
        date=(MyriadProRegular)convertView.findViewById(R.id.date);

        name=(MyriadProSemibold)convertView.findViewById(R.id.name);
        price=(MyriadProSemibold)convertView.findViewById(R.id.price);



        abc=item_list.get(position);





        name.setText(abc.getUsername());
        price.setText(String.valueOf(abc.getCost()));
        time.setText(abc.getTimeslot());
        date.setText(abc.getSelected_date());
        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) convertView.getLayoutParams();
        convertView.setLayoutParams(list);


        return convertView;
    }




}
