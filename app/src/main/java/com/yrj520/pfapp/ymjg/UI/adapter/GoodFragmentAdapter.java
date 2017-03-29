package com.yrj520.pfapp.ymjg.UI.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yrj520.pfapp.ymjg.UI.fragment.GoodsFragment;

import java.util.List;

/**
 * Created by zry on 17/4/2.
 */

public class GoodFragmentAdapter extends FragmentStatePagerAdapter {


    private List<String> mTitles;

    public GoodFragmentAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles=titles;
    }





    @Override
    public Fragment getItem(int position) {
        GoodsFragment fragment=new GoodsFragment();
        GoodsFragment.setPosition(position);
        return fragment;
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
