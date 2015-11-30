package com.esolz.su.zipstar.Fragment;



import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.esolz.su.zipstar.Fragment.FavoritesFragment;
import com.esolz.su.zipstar.Fragment.ListGroseryFragment;
import com.esolz.su.zipstar.R;
import com.esolz.su.zipstar.ZipStar.SearchActivity;

/**
 * Created by su on 31/8/15.
 */
public class GroceryListFragmetActivity extends Fragment {

FragmentTransaction fragmentTransaction;
FragmentManager fragmentManager;
    View v1, v2,view;
LinearLayout tab1,tab2,done,additem;






    LinearLayout lttab1, lttab2;
    private PopupWindow window;
    Button additemto;
    private PopupWindow pwindo;
    Point p;

    TextView txtind, addgrp, addcontact, flw, flwing, pendng;
    TextView title_menupopuptrack, txtmsg;
    SharedPreferences shared;
    String usrid, url, urlJsonObj, follow, following, verifymail;
    ImageButton home;









    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.grosery_list_frame, container, false);

        fragmentManager = getFragmentManager();
        additemto = (Button) view.findViewById(R.id.additemto);
        tab1 = (LinearLayout) view.findViewById(R.id.tab1);
        tab2 = (LinearLayout) view.findViewById(R.id.tab2);
        v1 = (View) view.findViewById(R.id.v1);
        v2 = (View) view.findViewById(R.id.v2);
//
        done = (LinearLayout) view.findViewById(R.id.done);
        additem = (LinearLayout) view.findViewById(R.id.additem);
        additemto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//
                Intent i=new Intent(getActivity(),SearchActivity.class);
                startActivity(i);
            }
        });

        tab1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Toast.makeText(getActivity(), "helll", Toast.LENGTH_LONG).show();
                done.setVisibility(View.VISIBLE);

                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                additem.setVisibility(View.GONE);
                fragmentTransaction = fragmentManager.beginTransaction();
                ListGroseryFragment bookapnt_fragment = new ListGroseryFragment();

                fragmentTransaction.replace(R.id.frgtrackteam,
                        bookapnt_fragment);
                fragmentTransaction.commit();

            }
        });

//
        tab2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Toast.makeText(getActivity(), "helllooooo", Toast.LENGTH_LONG).show();
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.GONE);
                additem.setVisibility(View.VISIBLE);
                done.setVisibility(View.GONE);
                fragmentTransaction = fragmentManager.beginTransaction();
                FavoritesFragment bookapnt = new FavoritesFragment();

                fragmentTransaction.replace(R.id.frgtrackteam,
                        bookapnt);
                fragmentTransaction.commit();
//                SelectItem(0);
            }
        });


//                tab1.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View arg0) {
//
//                        SelectItem(1);
//                    }
//                });

                return view;
            }


//    public void clickGrpFollow(View v) {
//        SelectItem(1);
//        window.dismiss();
//    }
//
//    public void clickIndfollowing(View v) {
//        SelectItem(2);
//        window.dismiss();
//    }
//
//    public void clickGrpfollowing(View v) {
//        SelectItem(3);
//        window.dismiss();
//    }


            public void SelectItem(int possition) {

                Fragment fragment = null;
                Bundle args = new Bundle();
                switch (possition) {
                    case 0:
                        fragment = new ListGroseryFragment();

                        ///mDrawerLayout.closeDrawer(mDrawerPane);
                        ///layout2.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        //mDrawerLayout.closeDrawer(mDrawerPane);
                        fragment = new FavoritesFragment();

                        break;

                    default:
                        break;
                }
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.frgtrackteam, fragment)
                        .commit();
            }


        }
















