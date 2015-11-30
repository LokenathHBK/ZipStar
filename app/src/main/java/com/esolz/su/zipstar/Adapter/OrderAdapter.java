package com.esolz.su.zipstar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.esolz.su.zipstar.DataType.OrderDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.customviews.MyriadProLight;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProSemibold;

import java.util.LinkedList;

/**
 * Created by su on 7/9/15.
 */
public class OrderAdapter extends ArrayAdapter<OrderDataType> {

    LinkedList<OrderDataType> item_list=new LinkedList<OrderDataType>();
    OrderDataType abc;
    LayoutInflater mInflater;
    Context context;
    MyriadProSemibold date;
    MyriadProLight time;

    public OrderAdapter(Context context, int resource, LinkedList<OrderDataType> object) {
        super(context, resource, object);


        this.context = context;
        item_list = object;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView=mInflater.inflate(R.layout.order_rowitem,parent,false);



        time=(MyriadProLight)convertView.findViewById(R.id.time);
        date=(MyriadProSemibold)convertView.findViewById(R.id.date);





        abc=item_list.get(position);






        date.setText(abc.getDelivary_date());
        time.setText(abc.getDelivary_time());
        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) convertView.getLayoutParams();
        convertView.setLayoutParams(list);


        return convertView;
    }




}
