package com.esolz.su.zipstar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.esolz.su.zipstar.DataType.SerachListviewDataType;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.ZipStar.SearchActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;


/**
 * Created by su on 30/6/15.
 */
public class ListViewAdapter extends ArrayAdapter<SerachListviewDataType> {

    LinkedList<SerachListviewDataType> item_list=new LinkedList<SerachListviewDataType>();
//    LinkedList<BrandwiseDeatailsDatatype> brand=new LinkedList<BrandwiseDeatailsDatatype>();
    SerachListviewDataType obj;
//    BrandwiseDeatailsDatatype brand_wise;


    Context context;
    TextView txt_name;
    TextView brand_name;
    ImageView img_photo;


    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private ArrayList<SerachListviewDataType> mData = new ArrayList<SerachListviewDataType>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater mInflater;



    public ListViewAdapter(Context context, int resource, LinkedList<SerachListviewDataType> object) {
        super(context, resource, object);


        this.context = context;
        item_list = object;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    public void addItem(final SerachListviewDataType item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final SerachListviewDataType item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public SerachListviewDataType getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.card_list_search, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.brand_name);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.card_list_search, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.Itemname);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mData.get(position).getName());

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }

//        convertView=mInflater.inflate(R.layout.card_list_search,parent,false);
//
//        txt_name=(TextView) convertView.findViewById(R.id.Itemname);
//        img_photo=(ImageView)convertView.findViewById(R.id.icon);
//        brand_name=(TextView)convertView.findViewById(R.id.brand_name);
//        obj=item_list.get(position);
//
//
//        brand_name.setText(obj.getBrand_name());
//
//        txt_name.setText(obj.getName());
//        Picasso.with(context).load(item_list.get(position).getImage()).placeholder(R.drawable.abc_dialog_material_background_dark).error(R.drawable.abc_dialog_material_background_light).into(img_photo);
//
//        ViewGroup.LayoutParams list = (ViewGroup.LayoutParams) convertView.getLayoutParams();
//        convertView.setLayoutParams(list);
//
//
//        return convertView;
    }

