package com.esolz.su.zipstar.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.esolz.su.zipstar.Adapter.FavGroseryAdapter;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.SqlLiteDataBase.Contact;
import com.esolz.su.zipstar.SqlLiteDataBase.DataBaseCreator;
import com.esolz.su.zipstar.SqlLiteDataBase.FavouriteDataType;
import com.esolz.su.zipstar.SwipeListView.SwipeMenu;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuCreator;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuItem;
import com.esolz.su.zipstar.SwipeListView.SwipeMenuListView;

import java.util.LinkedList;

/**
 * Created by su on 31/8/15.
 */
public class MessageFragment extends Fragment {
    SwipeMenuListView swipelistview;
    View view;

    LinkedList<FavouriteDataType> item_detail=new LinkedList<FavouriteDataType>();
    FavGroseryAdapter adapter;
    private SwipeMenuListView ListView;
    Context context;
    String deletedata;
    DataBaseCreator db;
Contact contact;
    FavouriteDataType item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_pending_message, container, false);


        getActivity().getPackageManager();


      return view;


    }
}