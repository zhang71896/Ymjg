package com.yrj520.pfapp.ymjg.UI.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.entity.OneTwoClassGoodData;

/**
 * Created by zry on 17/4/2.
 */

public class GoodsFragment extends Fragment {

    private View viewContent;

    private OneTwoClassGoodData mOneTwoClassGoodData;

    private String[] list_second_goods;

    private ListView lv_second_goods;

    private ListView lv_thrid_goods;

    private ArrayAdapter arrayAdapter;

    public  void setmOneTwoClassGoodData(OneTwoClassGoodData oneTwoClassGoodData){
        mOneTwoClassGoodData=oneTwoClassGoodData;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent =inflater.inflate(R.layout.fragment_goods,container,false);
        lv_second_goods=(ListView)viewContent.findViewById(R.id.lv_second_goods);
        lv_thrid_goods=(ListView)viewContent.findViewById(R.id.lv_thrid_goods);
//        list_second_goods=mOneTwoClassGoodData.
//        arrayAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,list_second_goods);
        return viewContent;
    }
}
