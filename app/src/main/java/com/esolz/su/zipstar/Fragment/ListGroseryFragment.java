package com.esolz.su.zipstar.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.FavGroseryAdapter;
import com.esolz.su.zipstar.Adapter.ListGroseryAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.Item;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.DatabaseHandler;
import com.esolz.su.zipstar.SqlLiteDataBase.FavouriteDataType;
import com.esolz.su.zipstar.SqlLiteDataBase.ListDataType;
import com.esolz.su.zipstar.SwipeListView.SwipeMenu;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuCreator;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuItem;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;
import com.esolz.su.zipstar.ZipStar.LandingActivity;
import com.esolz.su.zipstar.ZipStar.PreferredOrderTimeFActivity;

import java.util.LinkedList;

/**
 * Created by su on 31/8/15.
 */
public class ListGroseryFragment extends Fragment {
    SwipeMenuListView swipelistview;
    View view;
    DataBaseCreator db;
    LinkedList<ListDataType> item_detail=new LinkedList<ListDataType>();
    ListGroseryAdapter adapter;
    private SwipeMenuListView ListView;
    Context context;
Contact contact;
    ListDataType item;
    String a,b,c;
    LinearLayout actionBa1r;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_grocery_list, container, false);


        

        getActivity().getPackageManager();

        Bundle bundle = this.getArguments();
        ListView = (SwipeMenuListView) view.findViewById(R.id.listView);
        db = new DataBaseCreator(getActivity());

        item_detail=db.getAllList();
        adapter = new ListGroseryAdapter(getActivity(), 0, item_detail);


        ListView.setAdapter(adapter);

        actionBa1r=(LinearLayout)view.findViewById(R.id.actionBa1r);
        if(item_detail.size()>0) {
            actionBa1r.setVisibility(View.GONE);
        }
        else
        {
            actionBa1r.setVisibility(View.VISIBLE);

        }
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity());

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));

                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
       
        ListView.setMenuCreator(creator);

        // step 2. listener item click event
        ListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                item = item_detail.get(position);
                a=item.getName();
                b=item.getPhoneNumber();



//                delete(item);


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("Confirm Delete...");

                alertDialog.setMessage("Are you sure you want delete this?");

                alertDialog.setIcon(R.drawable.prefered_icon);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();

                                delete();
                            }
                        });
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,	int which) {
                                dialog.cancel();
                            }
                        });

                // Showing Alert Message
                alertDialog.show();










                return false;
            }
        });
        // set SwipeListener
        ListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(getActivity(), position + " long click", Toast.LENGTH_SHORT).show();


                return false;
            }
        });


       LandingActivity.done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//

        if (!item_detail.isEmpty())


        {  Intent i = new Intent(getActivity(), PreferredOrderTimeFActivity.class);
            startActivity(i);}

else {


            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            alertDialog.setTitle("Alert");

            alertDialog.setMessage("You have to add item first in your list");

            alertDialog.setIcon(R.drawable.prefered_icon);

            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();


                            dialog.cancel();
                        }
                    });
//            alertDialog.setNegativeButton("NO",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });

            // Showing Alert Message
            alertDialog.show();
        }

            }
        });




        return view;
    }
    private void delete() {


        // delete app
        try {

            db.deletelistiten(item);
            item_detail.remove(item);
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
        }
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}