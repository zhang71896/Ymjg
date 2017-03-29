package com.yrj520.pfapp.ymjg.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.AddressData;
import com.yrj520.pfapp.ymjg.UI.event.AddressEvent;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class AddressAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<AddressData.DataBean> dataBeanList=new ArrayList<AddressData.DataBean>();
    private HashMap<String,Boolean> states=new HashMap<String,Boolean>();
    private  int selectedIndex=0;

    public AddressAdapter(Context context){
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }

    public void clearAll(){
        dataBeanList.clear();
        notifyDataSetChanged();
    }

    public void addAll(AddressData addressData){
        dataBeanList.clear();
        dataBeanList.addAll(addressData.getData());
        notifyDataSetChanged();
    }


    public  void removeSelectedIndex(int position){
        dataBeanList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public AddressData.DataBean getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final AddressAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_address, null);
            holder = new AddressAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (AddressAdapter.ViewHolder) convertView.getTag();
        }
        final AddressData.DataBean dataBean=getItem(position);
        if(!StringUtils.isEmpty(dataBean.getConsignee())) {
            holder.tv_lianxiren.setText(dataBean.getConsignee());
        }
        if(!StringUtils.isEmpty(dataBean.getSh_phone())){
            holder.tv_phone.setText(dataBean.getSh_phone());
        }
        holder.tv_address.setText(dataBean.getProvicename()+dataBean.getCityname()+dataBean.getDistrictname()+dataBean.getSh_address());
        if(dataBean.getDefaultX().equals("1")){
            holder.rb_default.setChecked(true);
            holder.rb_default.setText("收货地址");
        }else{
            holder.rb_default.setChecked(false);
            holder.rb_default.setText("设为收货地址");
        }

        holder.rb_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDefaultAddress(dataBean,position);
            }
        });

        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delAddress(dataBean,position);
            }
        });
        return convertView;
    }


    private void changeDefaultAddress(AddressData.DataBean dataBean, final int position){

        UserApi.SetDefaultAddressApi(mContext, dataBean.getAddress_id(), new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(mContext,meg);
                AddressEvent addressEvent=new AddressEvent(MyConstant.AddAddress);
                EventBus.getDefault().post(addressEvent);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void delAddress(AddressData.DataBean dataBean,final int position){
        UserApi.DeleteAddressApi(mContext, dataBean.getAddress_id(), new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(mContext,meg);
                if(code.equals("200")){
                    removeSelectedIndex(position);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private class ViewHolder{
        ImageView iv_del;
        RadioButton rb_default;
        TextView tv_address;
        TextView tv_phone;
        TextView tv_lianxiren;
        final View root;
        ViewHolder(View root) {
            this.root = root;
            tv_address = (TextView) root.findViewById(R.id.tv_address);
            tv_phone = (TextView) root.findViewById(R.id.tv_phone);
            tv_lianxiren = (TextView) root.findViewById(R.id.tv_lianxiren);
            iv_del = (ImageView) root.findViewById(R.id.iv_del);
            rb_default = (RadioButton) root.findViewById(R.id.rb_default);
        }

    }
}
