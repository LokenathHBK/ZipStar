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
import com.esolz.su.zipstar.DataType.ReviewGroseryDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.ZipStar.ReviewGrosseryListActivity;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProLight;

import java.util.LinkedList;

/**
 * Created by su on 7/9/15.
 */
public class ReviewListGroseryAdapter extends ArrayAdapter<ReviewGroseryDataType> {

    LinkedList<ReviewGroseryDataType> item_list=new LinkedList<ReviewGroseryDataType>();

    ReviewGroseryDataType abc;
    LayoutInflater mInflater;
    Context context;
    MyriadProLight txt_name;
    MyriadProBold brandname;
    TextView quantity;
    ImageView img_photo;
   ReviewGrosseryListActivity reviewGrosseryListActivity;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    SharedPreferences sharedpreferences;

    public ReviewListGroseryAdapter(Context context, int resource, LinkedList<ReviewGroseryDataType> object) {
        super(context, resource, object);


        this.reviewGrosseryListActivity = (ReviewGrosseryListActivity) context;
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


                APP_DATA.item_orderid=abc.getOrder_item_id();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("item_orderid",APP_DATA.item_orderid);


                Toast.makeText(getContext(), APP_DATA.item_orderid, Toast.LENGTH_LONG).show();

                editor.commit();


                reviewGrosseryListActivity.viesvisible();



            }
        });

        abc=item_list.get(position);
        brandname.setText(abc.getBrand_name());
        txt_name.setText(abc.getName());
        quantity.setText(abc.getQuantity());
        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) convertView.getLayoutParams();
        convertView.setLayoutParams(list);
        return convertView;
    }

}
