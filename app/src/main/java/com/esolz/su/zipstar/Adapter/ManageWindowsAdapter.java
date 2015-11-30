package com.esolz.su.zipstar.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.ManageWindowDataType;
import com.esolz.su.zipstar.Fragment.ManageWindowsFragment;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.DatabaseHandler;
import com.esolz.su.zipstar.customviews.MyriadProBold;
import com.esolz.su.zipstar.customviews.MyriadProLight;
import com.esolz.su.zipstar.customviews.MyriadProRegular;
import com.esolz.su.zipstar.customviews.MyriadProSemibold;

import java.util.LinkedList;

/**
 * Created by su on 7/9/15.
 */
public class ManageWindowsAdapter extends ArrayAdapter<ManageWindowDataType> {

    LinkedList<ManageWindowDataType> item_list=new LinkedList<ManageWindowDataType>();
    boolean[] itemChecked;
    ManageWindowDataType abc;
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
    public static ManageWindowDataType manageWindowDataType;
    public static MyriadProRegular multipel;

    public ManageWindowsAdapter(Context context, int resource, LinkedList<ManageWindowDataType> object) {
        super(context, resource, object);


        this.context = context;
        item_list = object;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView=mInflater.inflate(R.layout.managewindow_row_item,parent,false);




        ManageWindowDataType p = getProduct(position);

         ((MyriadProSemibold) convertView.findViewById(R.id.time_slot_start)).setText(p.getTime_slot_start());
        ((MyriadProSemibold) convertView.findViewById(R.id.time_slot_end)).setText(p.getTime_slot_end());
        multipel= ((MyriadProRegular) convertView.findViewById(R.id.multipel));
        multipel.setText(p.getMultiply());


        multipel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        ManageWindowsFragment.multipel_layout.setVisibility(View.VISIBLE);
                manageWindowDataType=item_list.get(position);
            }
        });

//        txt_name=(MyriadProLight)convertView.findViewById(R.id.tv_name);
//        brandname=(MyriadProBold)convertView.findViewById(R.id.brand_name);
        checkBox=(CheckBox)convertView.findViewById(R.id.checkbox);

        checkBox.setOnCheckedChangeListener(myCheckChangList);
        checkBox.setTag(position);
        checkBox.setChecked(p.checkedBox);

        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) convertView.getLayoutParams();
        convertView.setLayoutParams(list);


        return convertView;
    }

    ManageWindowDataType getProduct(int position) {
        return ((ManageWindowDataType) getItem(position));
    }

    public LinkedList<ManageWindowDataType> getBox() {
        LinkedList<ManageWindowDataType> box = new LinkedList<ManageWindowDataType>();
        for (ManageWindowDataType p : item_list) {
            if (p.checkedBox) {
                box.add(p);
            }
            else
            {
                box.add(p);
            }
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