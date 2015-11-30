package com.esolz.su.zipstar.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SqlLiteDataBase.DatabaseHandler;
import com.esolz.su.zipstar.SqlLiteDataBase.FavouriteDataType;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProLight;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by su on 7/9/15.
 */
public class FavGroseryAdapter extends ArrayAdapter<FavouriteDataType> {

    LinkedList<FavouriteDataType> item_list=new LinkedList<FavouriteDataType>();
    boolean[] itemChecked;
    FavouriteDataType abc;
    LayoutInflater mInflater;
    Context context;
    TextView brand_name;
    ImageView img_photo;
    MyriadProLight txt_name;
    MyriadProBold brandname;
    DatabaseHandler databaseHandler;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    CheckBox checkBox;
    SharedPreferences sharedpreferences;


    public FavGroseryAdapter(Context context, int resource, LinkedList<FavouriteDataType> object) {
        super(context, resource, object);


        this.context = context;
        item_list = object;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView=mInflater.inflate(R.layout.fav_list_item,parent,false);




        FavouriteDataType p = getProduct(position);

        ((MyriadProLight) convertView.findViewById(R.id.tv_name)).setText(p.get_name());
        ((MyriadProBold) convertView.findViewById(R.id.brand_name)).setText(p.get_phone_number() + "");

//        txt_name=(MyriadProLight)convertView.findViewById(R.id.tv_name);
//        brandname=(MyriadProBold)convertView.findViewById(R.id.brand_name);
        checkBox=(CheckBox)convertView.findViewById(R.id.checkbox);

        checkBox.setOnCheckedChangeListener(myCheckChangList);
        checkBox.setTag(position);
        checkBox.setChecked(p.checkedBox);

//        checkBox.setChecked(false);

//        sharedpreferences = getContext().getSharedPreferences("favlist", Context.MODE_PRIVATE);




//        checkBox.setOnClickListener(new View.OnClickListener() {
//
//
//    @Override
//    public void onClick(View v) {
//        if (((CheckBox) v).isChecked()) {
//
//            abc=item_list.get(position);
//
//            APP_DATA.pname=abc.get_name();
//            APP_DATA.bname=abc.get_phone_number();
//
//            SharedPreferences.Editor editor = sharedpreferences.edit();
//
//            editor.putString("pname",APP_DATA.pname);
//
//            editor.putString("bname", APP_DATA.bname);
//
//
//
//            editor.commit();
//
//
//            Toast.makeText(getContext(),
//                    abc.get_name(), Toast.LENGTH_LONG).show();
//        }
//
//    }
//
//});



//        abc=item_list.get(position);


//        APP_DATA.chkbox= String.valueOf(item_list.get(position));



//        brand_name.setText(abc.getBrand_name());
//
//        txt_name.setText(abc.get_name());
//        brandname.setText(abc.get_phone_number());
        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) convertView.getLayoutParams();
        convertView.setLayoutParams(list);


        return convertView;
    }

    FavouriteDataType getProduct(int position) {
        return ((FavouriteDataType) getItem(position));
    }

    public LinkedList<FavouriteDataType> getBox() {
        LinkedList<FavouriteDataType> box = new LinkedList<FavouriteDataType>();
        for (FavouriteDataType p : item_list) {
            if (p.checkedBox)
                box.add(p);
        }
        return box;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            getProduct((Integer) buttonView.getTag()).checkedBox = isChecked;





        }
    };

}