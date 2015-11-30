package com.esolz.su.zipstar.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.SelectStoreDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.DatabaseHandler;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProLight;
import com.esolz.su.zipstar.customviews.MyriadProSemibold;

import java.util.LinkedList;

/**
 * Created by su on 7/9/15.
 */
public class SelectStoreAdapter extends ArrayAdapter<SelectStoreDataType> {

    LinkedList<SelectStoreDataType> item_list=new LinkedList<SelectStoreDataType>();
    SelectStoreDataType abc;
    LayoutInflater mInflater;
    Context context;
    MyriadProLight txt_name;
    MyriadProSemibold brandname;


    public SelectStoreAdapter(Context context, int resource, LinkedList<SelectStoreDataType> object) {
        super(context, resource, object);


        this.context = context;
        item_list = object;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView=mInflater.inflate(R.layout.select_store_rowitem,parent,false);



        txt_name=(MyriadProLight)convertView.findViewById(R.id.location);
        brandname=(MyriadProSemibold)convertView.findViewById(R.id.product_name);





        abc=item_list.get(position);






        txt_name.setText(abc.getStore_address1());
        brandname.setText(abc.getStore_namewow());
        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) convertView.getLayoutParams();
        convertView.setLayoutParams(list);


        return convertView;
    }




}
