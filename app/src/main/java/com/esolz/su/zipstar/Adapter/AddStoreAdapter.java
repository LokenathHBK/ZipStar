package com.esolz.su.zipstar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.esolz.su.zipstar.DataType.AddStoreDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProLight;

import java.util.LinkedList;

/**
 * Created by su on 7/9/15.
 */
public class AddStoreAdapter extends ArrayAdapter<AddStoreDataType> {

    LinkedList<AddStoreDataType> item_list=new LinkedList<AddStoreDataType>();
    AddStoreDataType abc;
    LayoutInflater mInflater;
    Context context;
    MyriadProLight txt_name;
    MyriadProBold brandname;


    public AddStoreAdapter(Context context, int resource, LinkedList<AddStoreDataType> object) {
        super(context, resource, object);


        this.context = context;
        item_list = object;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView=mInflater.inflate(R.layout.add_store_rowitem,parent,false);



        txt_name=(MyriadProLight)convertView.findViewById(R.id.location);
        brandname=(MyriadProBold)convertView.findViewById(R.id.product_name);





        abc=item_list.get(position);






        txt_name.setText(abc.getStore_address1());
        brandname.setText(abc.getStore_namewow());
        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) convertView.getLayoutParams();
        convertView.setLayoutParams(list);


        return convertView;
    }




}
