package com.jock.pickerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.entity.GoodSpecData;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zry on 17/5/16.
 */

public class DIalogGoodDetailAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mInflater;


    private List<GoodSpecData.DataBean.AttrBean> mAttryBeanList=new ArrayList<GoodSpecData.DataBean.AttrBean>();

    public  DIalogGoodDetailAdapter(Context context){
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }

    public void AddAll(List<GoodSpecData.DataBean.AttrBean> ArrayBeanList){
        mAttryBeanList.clear();
        mAttryBeanList.addAll(ArrayBeanList);
        this.notifyDataSetChanged();
    }

    public void DeleteAll(){
        mAttryBeanList.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAttryBeanList.size();
    }

    @Override
    public GoodSpecData.DataBean.AttrBean getItem(int position) {
        return mAttryBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DIalogGoodDetailAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_good_detail, null);
            holder = new DIalogGoodDetailAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (DIalogGoodDetailAdapter.ViewHolder) convertView.getTag();
        }
        final GoodSpecData.DataBean.AttrBean attrBean=getItem(position);
        if(attrBean!=null) {
            if(!StringUtils.isEmpty(attrBean.getAttr_name())) {
                holder.tv_tag.setText(attrBean.getAttr_name());
            }
            if(!StringUtils.isEmpty(attrBean.getAttr_value())) {
                holder.tv_name.setText(attrBean.getAttr_value());
            }
        }
        return convertView;
    }

    private class ViewHolder{

        TextView tv_tag;

        TextView tv_name;

        final View root;

        ViewHolder(View root){

            this.root=root;
            tv_tag=(TextView)root.findViewById(R.id.tv_tag);
            tv_name=(TextView)root.findViewById(R.id.tv_name);
        }
    }
}
