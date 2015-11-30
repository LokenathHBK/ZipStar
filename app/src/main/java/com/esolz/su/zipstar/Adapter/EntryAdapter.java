package com.esolz.su.zipstar.Adapter;

/**
 * Created by su on 22/9/15.
 */

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.Item;
import com.esolz.su.zipstar.DataType.SectionItem;
import com.esolz.su.zipstar.DataType.SerachListviewDataType;
import com.esolz.su.zipstar.ImageTransformation.Trns;
import com.esolz.su.zipstar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;

public class EntryAdapter extends ArrayAdapter<Item> {

    private Context context;
    private LinkedList<Item> items;
    private LayoutInflater vi;

    String searchwrd;

    public EntryAdapter(Context context, LinkedList<Item> items,String search) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        this.searchwrd=search;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
        if (i != null) {
            if(i.isSection()){
                SectionItem si = (SectionItem)i;
                v = vi.inflate(R.layout.list_item_section, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
                sectionView.setText(si.getTitle());

            }else{
                SerachListviewDataType ei = (SerachListviewDataType)i;
                v = vi.inflate(R.layout.list_item_entry, null);
                final TextView title = (TextView)v.findViewById(R.id.list_item_entry_title);
                final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);

                final ImageView icon = (ImageView)v.findViewById(R.id.list_item_entry_drawable);

                if (title != null) {
                    // String you want to perform on
                    String toChange = ei.name;


                    StringBuilder rackingSystemSb = new StringBuilder(searchwrd.toLowerCase());
                    rackingSystemSb.setCharAt(0, Character.toUpperCase(rackingSystemSb.charAt(0)));
                    searchwrd = rackingSystemSb.toString();


// Matches all characters, numbers, underscores and dashes followed by '#'
// Does not match '#' followed by space or any other non word characters
                    toChange = toChange.replaceFirst(searchwrd,
                            "<font color='#000080'>" + "$0" +  "</font>");

// Encloses the matched characters with html font tags

// Html#fromHtml(String) returns a Spanned object
                    title.setText(Html.fromHtml(toChange));
//                    title.setText(ei.name);
                }
                if(icon != null)
                    Picasso.with(context).load(ei.image).transform(new Trns()).resize(400, 400).centerInside().placeholder(R.drawable.abc_dialog_material_background_dark).error(R.drawable.abc_dialog_material_background_light).into(icon);

            }
        }
        return v;
    }

}