package com.yrj520.pfapp.ymjg.UI.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.entity.OneTwoClassGoodData;

import java.util.List;

/**
 * Created by zry on 17/4/2.
 */

public class GoodsFragment extends Fragment {

    private View viewContent;

    private List<OneTwoClassGoodData.DataBean.ArrayBean> arrayBeanList;

    private String[] list_second_goods;

    private ListView lv_second_goods;

    private GridView gv_thrid_goods;

    private ArrayAdapter arrayAdapter;

    private static int mPoistion=0;

    public  void setmOneTwoClassGoodData(OneTwoClassGoodData oneTwoClassGoodData){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_goods, container, false);
        lv_second_goods = (ListView) viewContent.findViewById(R.id.lv_second_goods);
        gv_thrid_goods = (GridView) viewContent.findViewById(R.id.gv_thrid_goods);

/*
        list_second_goods=mOneTwoClassGoodData.getData().get(mPoistion).getArray();
*/

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, list_second_goods);
        return viewContent;
    }


    public static void setPosition(int position){
        mPoistion=position;
    }
}

