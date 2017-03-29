package com.yrj520.pfapp.ymjg.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.entity.OrderData;
import com.yrj520.pfapp.ymjg.UI.utils.ImageUtils;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;

import java.util.ArrayList;
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

public class GoodDetailAdapter extends BaseAdapter {
    private List<OrderData.DataBean.SpecBean> specBeanList=new ArrayList<OrderData.DataBean.SpecBean>();
    private Context mContext;
    private LayoutInflater mInflater;

    public GoodDetailAdapter(Context context){
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }
    public void clearAll(){
        specBeanList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<OrderData.DataBean.SpecBean> SpecBeanList)
    {
        specBeanList.clear();
        specBeanList.addAll(SpecBeanList);
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {
        return specBeanList.size();
    }

    @Override
    public OrderData.DataBean.SpecBean getItem(int position) {
        return specBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final GoodDetailAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_order_detail, null);
            holder = new GoodDetailAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (GoodDetailAdapter.ViewHolder) convertView.getTag();
        }
        final OrderData.DataBean.SpecBean specBean=getItem(position);
        if(!StringUtils.isEmpty(specBean.getOriginal_img())){
            String imageUrl= ImageUtils.getImageUrl(specBean.getOriginal_img());
            ImageUtils.loadCommonPic(mContext,imageUrl,holder.iv_good);
        }
        if(!StringUtils.isEmpty(specBean.getGoods_name())) {
            holder.tv_good_name.setText(specBean.getGoods_name());
        }
        if(!StringUtils.isEmpty(specBean.getKey_name())) {
            holder.tv_good_size.setText(specBean.getKey_name());
        }
        if(!StringUtils.isEmpty(specBean.getStore_count())) {
            holder.tv_store_count.setText(specBean.getStore_count());
        }
        if(!StringUtils.isEmpty(specBean.getGoods_price())) {
            holder.tv_good_price.setText("¥ "+specBean.getGoods_price());
        }
        if(!StringUtils.isEmpty(specBean.getGoods_num())) {
            holder.tv_good_num.setText("共计: "+specBean.getGoods_num());
        }
        if(!StringUtils.isEmpty(specBean.getSumprice())) {
            holder.tv_good_sum_prices.setText(specBean.getSumprice());
        }
        return convertView;
    }

    private class ViewHolder{
        ImageView iv_good;
        TextView tv_good_name;
        TextView tv_good_size;
        TextView tv_store_count;
        TextView tv_good_price;
        TextView tv_good_num;
        TextView tv_good_sum_prices;
        TextView tv_show_all_good;
        TextView tv_total_prices;
        TextView tv_total_num;
        final View root;
        ViewHolder(View root){
            this.root = root;
            tv_good_name = (TextView) root.findViewById(R.id.tv_good_name);
            tv_good_size = (TextView) root.findViewById(R.id.tv_good_size);
            tv_store_count = (TextView) root.findViewById(R.id.tv_store_count);
            tv_good_price = (TextView) root.findViewById(R.id.tv_good_price);
            tv_good_num = (TextView) root.findViewById(R.id.tv_good_num);
            tv_good_sum_prices = (TextView) root.findViewById(R.id.tv_good_sum_prices);
            tv_show_all_good = (TextView) root.findViewById(R.id.tv_show_all_good);
            tv_total_prices = (TextView) root.findViewById(R.id.tv_total_prices);
            tv_total_num = (TextView) root.findViewById(R.id.tv_total_num);
            iv_good = (ImageView) root.findViewById(R.id.iv_good);

        }
    }
}
