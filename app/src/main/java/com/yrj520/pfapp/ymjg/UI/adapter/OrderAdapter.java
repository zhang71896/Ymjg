package com.yrj520.pfapp.ymjg.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.dialog.PayMessageDialog;
import com.yrj520.pfapp.ymjg.UI.entity.OrderData;
import com.yrj520.pfapp.ymjg.UI.fragment.OrderFragment;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ImageUtils;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
import com.yrj520.pfapp.ymjg.UI.utils.PopUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.ui.OrderCooperateActivity;

import org.json.JSONObject;

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

public class OrderAdapter extends BaseAdapter {

    private List<OrderData.DataBean> dataBeanList=new ArrayList<OrderData.DataBean>();

    private Context mContext;

    private LayoutInflater mInflater;
    //getAuditings 1:等待审核 0：审核通过
    //pay status 1:代表已经付款 0：代表未付款
   //$order_status=0 待付款  $order_status=1 全部   $order_status=2 待收货  $order_status=3 已取消 $order_status=4 已完成
    private int mType=0;


    public void clearAll(){
        dataBeanList.clear();
        notifyDataSetChanged();
    }
    public void removeOneData(int position){
        dataBeanList.remove(position);
        notifyDataSetChanged();
    }
    public void addAll(OrderData orderData, int type){
        mType=type;
        dataBeanList.clear();
        dataBeanList.addAll(orderData.getData());
        notifyDataSetChanged();
    }

    public OrderAdapter(Context context){
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public OrderData.DataBean getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final OrderAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_order, null);
            holder = new OrderAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (OrderAdapter.ViewHolder) convertView.getTag();
        }
        final OrderData.DataBean dataBean=getItem(position);

        if(!StringUtils.isEmpty(dataBean.getOrdernumber())){
            holder.tv_order_num.setText("订单号: "+dataBean.getOrdernumber());
        }
        if(!StringUtils.isEmpty(dataBean.getAdd_time())){
            holder.tv_order_time.setText(dataBean.getAdd_time());
        }

        if(!StringUtils.isEmpty(dataBean.getMoney())){
            holder.tv_total_prices.setText("¥  "+dataBean.getMoney().toString());
        }
        holder.btn_pay.setVisibility(View.VISIBLE);
        holder.btn_cancel.setVisibility(View.VISIBLE);
        holder.btn_pay.setEnabled(true);
        holder.btn_cancel.setEnabled(true);
        LogUtils.info("mType","mType: "+mType);
        //判断当前是哪一种类型 等待付款
        if(mType==0){
            //未审核通过
            holder.btn_cancel.setText("取消订单");
            if(dataBean.getAuditings().equals("0")) {
                holder.btn_pay.setText("等待审核");
                holder.btn_pay.setEnabled(false);
            }else{
                holder.btn_pay.setText("立即付款");
            }
        }else if(mType==1){
            //全部
            holder.btn_cancel.setText("取消订单");
            holder.btn_pay.setText("立即付款");
        }else if(mType==2){
            //待收货
            //已经付款
            holder.btn_cancel.setText("已收货");
            holder.btn_cancel.setEnabled(false);
            if(dataBean.getPay_status().equals("1")){
                holder.btn_pay.setVisibility(View.INVISIBLE);
            }else{//未付款
                holder.btn_pay.setText("立即付款");
            }
        }else if(mType==3){
            //已取消
            holder.btn_cancel.setVisibility(View.INVISIBLE);
            holder.btn_pay.setText("删除订单");
        }else if(mType==4){
            //已完成
            //已付款
            if(dataBean.getPay_status().equals("1")){
                holder.btn_pay.setText("删除订单");
            }else{//未付款
                holder.btn_cancel.setText("收货待付");
                holder.btn_cancel.setEnabled(false);
                holder.btn_pay.setText("立即付款");
            }
        }
        final OrderData.DataBean.SpecBean specBean=dataBean.getSpec().get(0);
        int sumNumber=0;
        for(int i=0;i<dataBean.getSpec().size();i++){
            int number=0;
            if(!StringUtils.isEmpty(dataBean.getSpec().get(i).getGoods_num())) {
                number = Integer.parseInt(dataBean.getSpec().get(i).getGoods_num());
            }
            sumNumber+=number;
        }
        holder.tv_total_num.setText("共"+sumNumber+"件商品  合计:");
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
        holder.tv_show_all_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.ShowGoodDetailPopWindow(OrderCooperateActivity.getActivity(),v,dataBean.getSpec());
            }
        });
        holder.btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //已经取消或者已经完成已经支付状态下
                if(mType==3||(dataBean.getPay_status().equals("1")&&mType==4)){

                    DeleteOrder(dataBean.getOrder_id(),position);
                }else{
                    PayOrder(dataBean.getMoney(),dataBean.getOrder_id());
                }
            }
        });
        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelOrder(dataBean.getOrder_id(),position);
            }
        });
        return convertView;
    }
    //支付订单
    private void PayOrder(String totalPrices,String orderId){
        PayMessageDialog dialog=new PayMessageDialog(mContext,orderId);
        dialog.initDatas(totalPrices);
        dialog.show();
    }

    private void DeleteOrder(String orderId,final int position){

        UserApi.ChangeOrderStatus(mContext, orderId, "5", new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(mContext,meg);
                if(code.equals("200")){
                   refreshUI(position);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    private void  refreshUI(int position){
        int mFragmentPosition= OrderCooperateActivity.getmPosition();
        //移除当前列表中的数据
        removeOneData(position);
        OrderFragment orderFragment=OrderFragmentAdapter.getFragmentList().get(mFragmentPosition);
        int type=OrderCooperateActivity.titlesIndex[mFragmentPosition];
        orderFragment.setType(type);
        orderFragment.initDatas();
    }

    private void CancelOrder(String orderId, final int position){
        UserApi.ChangeOrderStatus(mContext, orderId, "3", new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(mContext,meg);
                if(code.equals("200")){
                  refreshUI(position);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    private class ViewHolder{
        TextView tv_order_num;
        TextView tv_order_time;
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
        Button btn_pay;
        Button btn_cancel;
        final View root;
        ViewHolder(View root){
            this.root = root;
            tv_order_num = (TextView) root.findViewById(R.id.tv_order_num);
            tv_order_time = (TextView) root.findViewById(R.id.tv_order_time);
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
            btn_pay=(Button)root.findViewById(R.id.btn_pay);
            btn_cancel=(Button)root.findViewById(R.id.btn_cancel);
        }
    }
}
