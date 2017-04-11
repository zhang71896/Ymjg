package com.yrj520.pfapp.ymjg.UI.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yrj520.pfapp.ymjg.UI.entity.OneTwoClassGoodData;
import com.yrj520.pfapp.ymjg.UI.fragment.GoodsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zry on 17/4/2.
 */

public class GoodFragmentAdapter extends FragmentStatePagerAdapter {


    private List<String> mTitles;

    private OneTwoClassGoodData mOneTwoClassGoodData;

    private static List<GoodsFragment> fragmentList=new ArrayList<GoodsFragment>();

    public static List<GoodsFragment> getFragmentList(){
        return fragmentList;
    }

    public GoodFragmentAdapter(FragmentManager fm, List<String> titles, OneTwoClassGoodData oneTwoClassGoodData) {
        super(fm);
        mTitles=titles;
        mOneTwoClassGoodData=oneTwoClassGoodData;
    }
    @Override
    public Fragment getItem(int position) {
        GoodsFragment fragment=new GoodsFragment();
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
