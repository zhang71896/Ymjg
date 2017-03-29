package com.yrj520.pfapp.ymjg.UI.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yrj520.pfapp.ymjg.UI.fragment.OrderFragment;
import com.yrj520.pfapp.ymjg.UI.view.base.ui.OrderCooperateActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zry on 17/4/2.
 */

public class OrderFragmentAdapter extends FragmentStatePagerAdapter {


    private List<String> mTitles;


    private static List<OrderFragment> fragmentList=new ArrayList<OrderFragment>();

    public static List<OrderFragment> getFragmentList(){
        return fragmentList;
    }

    public OrderFragmentAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        fragmentList.clear();
        mTitles=titles;
    }
    @Override
    public Fragment getItem(int position) {
        OrderFragment fragment=new OrderFragment();
        int type= OrderCooperateActivity.titlesIndex[position];
        fragment.setType(position);
        fragmentList.add(fragment);

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
                 return mTitles.get(position);
    }


}
