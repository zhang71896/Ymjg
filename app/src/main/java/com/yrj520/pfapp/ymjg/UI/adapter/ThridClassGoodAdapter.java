package com.yrj520.pfapp.ymjg.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yrj520.pfapp.ymjg.UI.entity.ThridGoodsData;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class ThridClassGoodAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<ThridGoodsData.DataBean> dataBeanList;

    public void addAll(List<ThridGoodsData.DataBean> list) {
        dataBeanList.addAll(list);
        notifyDataSetChanged();
    }

    public ThridClassGoodAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public ThridGoodsData.DataBean getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
