package com.esolz.su.zipstar.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.SerachListviewDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SqlLiteDataBase.DatabaseHandler;
import com.esolz.su.zipstar.SqlLiteDataBase.ListDataType;
import com.esolz.su.zipstar.ZipStar.LandingActivity;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProLight;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

/**
 * Created by su on 7/9/15.
 */
public class ListGroseryAdapter extends ArrayAdapter<ListDataType> {

    LinkedList<ListDataType> item_list=new LinkedList<ListDataType>();

    ListDataType abc;
    LayoutInflater mInflater;
    Context context;
    MyriadProLight txt_name;
    MyriadProBold brandname;
    TextView quantity;
    ImageView img_photo;
    DatabaseHandler databaseHandler;
   LandingActivity landingActivity;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    SharedPreferences sharedpreferences;

    public ListGroseryAdapter(Context context, int resource, LinkedList<ListDataType> object) {
        super(context, resource, object);


        this.landingActivity = (LandingActivity) context;
        item_list = object;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView=mInflater.inflate(R.layout.list_item,parent,false);

        quantity=(TextView)convertView.findViewById(R.id.quantity);
        txt_name=(MyriadProLight)convertView.findViewById(R.id.tv_name);
        brandname=(MyriadProBold)convertView.findViewById(R.id.brand_name);



//        quantity.setText("1");



        sharedpreferences = getContext().getSharedPreferences("favlist", Context.MODE_PRIVATE);
        quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abc = item_list.get(position);

                APP_DATA.lpname=abc.getName();
                APP_DATA.lbname=abc.getPhoneNumber();
                APP_DATA.detailrow=quantity.getText().toString();
                abc.setQnt(APP_DATA.detailrow);

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("lpname",APP_DATA.lpname);

                editor.putString("lbname", APP_DATA.lbname);
                editor.putString("lqnt", APP_DATA.detailrow);
                Toast.makeText(getContext(), APP_DATA.detailrow, Toast.LENGTH_LONG).show();

                editor.commit();


                landingActivity.viesvisible();



            }
        });

        abc=item_list.get(position);



//        String qunty=abc.getQnt();abc.setQnt(quantity.getText().toString());


//        brand_name.setText(abc.getBrand_name());
        brandname.setText(abc.getPhoneNumber());
        txt_name.setText(abc.getName());
        quantity.setText(abc.getQnt());
//        if (abc.getQnt() == null)
//
//        {quantity.setText("1");
//        }
//        else {quantity.setText(APP_DATA.detailrow);}


//        quantity.setText(abc.getQnt());

//        quantity.setText(qunty);
        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) convertView.getLayoutParams();
        convertView.setLayoutParams(list);


        return convertView;
    }

}
