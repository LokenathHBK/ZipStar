package com.esolz.su.zipstar.Fragment;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.FavGroseryAdapter;
import com.esolz.su.zipstar.Adapter.ListGroseryAdapter;
import com.esolz.su.zipstar.DataType.APP_DATA;
import com.esolz.su.zipstar.DataType.SerachListviewDataType;
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

import java.util.LinkedList;
import java.util.List;

/**
 * Created by su on 31/8/15.
 */
public class FavoritesFragment extends Fragment {
    SwipeMenuListView swipelistview;
    View view;
    LinearLayout actionBa1r;
    LinkedList<FavouriteDataType> item_detail=new LinkedList<FavouriteDataType>();
    FavGroseryAdapter adapter;
    private SwipeMenuListView ListView;
    Context context;
    String deletedata;
    DataBaseCreator db;
Contact contact;
    FavouriteDataType item;
    String a,b,c;
    int position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_grocery_favorite, container, false);


        

        getActivity().getPackageManager();


        ListView = (SwipeMenuListView) view.findViewById(R.id.listView);



        LandingActivity.additem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//
                showResult(arg0);


//

            }
        });


        db = new DataBaseCreator(getActivity());
        item_detail=db.getAllFavList();

        adapter = new FavGroseryAdapter(getActivity(),0,item_detail);
        actionBa1r=(LinearLayout)view.findViewById(R.id.actionBa1r);
        if(item_detail.size()>0) {
            actionBa1r.setVisibility(View.GONE);
        }
        else
        {
            actionBa1r.setVisibility(View.VISIBLE);

        }

        ListView.setAdapter(adapter);


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
                a=item.get_name();
                b=item.get_phone_number();
                c=item.getUpcid();
//                SerachListviewDataType Item = (SerachListviewDataType) item_detail.get(position);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                alertDialog.setTitle("Confirm Delete...");

                alertDialog.setMessage("Are you sure you want delete this?");

                alertDialog.setIcon(R.drawable.prefered_icon);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getActivity(), "You clicked on YES", Toast.LENGTH_SHORT).show();

                                delete(item);
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

        return view;
    }
    private void delete(FavouriteDataType abc) {


        // delete app
        try {

            db.deleteFavlistiten(item);
            item_detail.remove(item);
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
        }
    }

//    private void open(Contact item) {
//        // open app
//        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
//        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        resolveIntent.setPackage(item.getName());
//        List<ResolveInfo> resolveInfoList = context.getPackageManager()
//                .queryIntentActivities(resolveIntent, 0);
//        if (resolveInfoList != null && resolveInfoList.size() > 0) {
//            ResolveInfo resolveInfo = resolveInfoList.get(0);
//            String activityPackageName = resolveInfo.activityInfo.packageName;
//            String className = resolveInfo.activityInfo.name;
//
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            ComponentName componentName = new ComponentName(
//                    activityPackageName, className);
//
//            intent.setComponent(componentName);
//            startActivity(intent);
//        }
//
//
//    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    public void showResult(View v) {
        String result = "Selected Product are :";
        String totalAmount="0";

        item=item_detail.get(position);
        for (FavouriteDataType p : adapter.getBox()) {
            if (p.checkedBox){

                DataBaseCreator db = new DataBaseCreator(getActivity());

                db.addfromfavList(new ListDataType(p));

                result += "\n" + p.get_name();
                totalAmount+=p.get_phone_number();



                Intent i=new Intent(getActivity(),LandingActivity.class);
                startActivity(i);
            }
            else{Toast.makeText(getActivity(), "select some item", Toast.LENGTH_LONG).show();}

        }
        Toast.makeText(getActivity(), result+"\n"+"Total Amount:="+totalAmount, Toast.LENGTH_LONG).show();
//        Intent i=new Intent(getActivity(),LandingActivity.class);
//        startActivity(i);

    }

    @Override
    public void onResume() {
        super.onResume();
        db = new DataBaseCreator(getActivity());
item_detail.clear();
        item_detail=db.getAllFavList();

        adapter = new FavGroseryAdapter(getActivity(),0,item_detail);


        ListView.setAdapter(adapter);
    }
}