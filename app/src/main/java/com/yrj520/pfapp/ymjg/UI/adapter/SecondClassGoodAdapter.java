package com.yrj520.pfapp.ymjg.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.entity.OneTwoClassGoodData;
import com.yrj520.pfapp.ymjg.UI.fragment.GoodsFragment;
import com.yrj520.pfapp.ymjg.UI.view.base.ui.PurchaseGoodActivity;

import java.util.ArrayList;
import java.util.HashMap;
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

public class SecondClassGoodAdapter extends BaseAdapter {
    public List<OneTwoClassGoodData.DataBean.ArrayBean> mListArrayBean= new ArrayList<OneTwoClassGoodData.DataBean.ArrayBean>();
    private LayoutInflater mInflater;
    private Context mContext;
    HashMap<String,Boolean> states=new HashMap<String,Boolean>();
    private  int selectedIndex=0;

    public SecondClassGoodAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void removeAll() {
        mListArrayBean.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<OneTwoClassGoodData.DataBean.ArrayBean> list) {
        mListArrayBean.clear();
        mListArrayBean.addAll(list);
        notifyDataSetChanged();
    }

    public void clearSelectedIndex(){
        states.put(String.valueOf(selectedIndex), false);
        notifyDataSetChanged();
    }

    public void setSelectedIndex(int position){
        selectedIndex = position;
        states.put(String.valueOf(selectedIndex), true);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListArrayBean.size();
    }

    @Override
    public OneTwoClassGoodData.DataBean.ArrayBean getItem(int position) {
        return mListArrayBean.get(position);
    }

    public int getSelectedIndex(){

        return selectedIndex;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SecondClassGoodAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_radio_button, null);
            holder = new SecondClassGoodAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SecondClassGoodAdapter.ViewHolder) convertView.getTag();
        }
        final OneTwoClassGoodData.DataBean.ArrayBean data = getItem(position);
           if(data.getName()!=null&&data.getName().length()>0) {
               holder.rb_second_class.setText(data.getName());
           }
            holder.rb_second_class.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    //重置，确保最多只有一项被选中
                    for (String key : states.keySet()) {
                        states.put(key, false);
                    }
                    selectedIndex = position;
                    states.put(String.valueOf(position), holder.rb_second_class.isChecked());
                    SecondClassGoodAdapter.this.notifyDataSetChanged();
                    int firstGoodInex = PurchaseGoodActivity.getFirstGoodPosition();
                    GoodsFragment goodsFragment = GoodFragmentAdapter.getFragmentList().get(firstGoodInex);
                    goodsFragment.ChangeThridGoodsFragment(selectedIndex);
                }
            });
            boolean res = false;
            if (states.get(String.valueOf(position)) == null || states.get(String.valueOf(position)) == false) {
                res = false;
                states.put(String.valueOf(position), false);
            } else
                res = true;
            holder.rb_second_class.setChecked(res);
        return convertView;
    }

    private class ViewHolder {
        RadioButton rb_second_class;

        final View root;
        ViewHolder(View root) {
            this.root = root;
            rb_second_class=(RadioButton)root.findViewById(R.id.rb_second_class);
        }
    }
}
