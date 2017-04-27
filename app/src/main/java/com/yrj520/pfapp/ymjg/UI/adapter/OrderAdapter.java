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
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.dialog.PayMessageDialog;
import com.yrj520.pfapp.ymjg.UI.entity.OrderData;
import com.yrj520.pfapp.ymjg.UI.event.PersonalMessagEvent;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ImageUtils;
import com.yrj520.pfapp.ymjg.UI.utils.PopUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.ui.OrderCooperateActivity;

import org.json.JSONObject;

import java.util.ArrayList;
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
        String myOrderStatus=dataBean.getOrder_status();
        String payStatus=dataBean.getPay_status();

        //getAuditings 1:等待审核 0：审核通过
        //pay status 1:代表已经付款 0：代表未付款
        //$order_status=0 待付款  $order_status=1 全部   $order_status=2 待收货  $order_status=3 已取消 $order_status=4 已完成

        //全部订单栏位的情况
        //全部订单中 未发货&&待付款 mType是当前栏位 0 待付款 1 全部 2 待收货 3 已取消 4 已完成
        if((myOrderStatus.equals("0")&&(mType==1))){
            holder.btn_cancel.setText("取消订单");
            if(dataBean.getAuditings().equals("1")) {
                holder.btn_pay.setText("等待审核");
                holder.btn_pay.setEnabled(false);
            }else{
                holder.btn_pay.setText("立即付款");
            }
        }

        //全部订单中 已发货&&(待付款||已付款)
        if(payStatus.equals("0")&&(mType==1))
        {

                holder.btn_cancel.setText("已收货");
                holder.btn_pay.setText("立即付款");
        }

        if(payStatus.equals("1")&&mType==1){

            holder.btn_pay.setText("已收货");
        }

        if(payStatus.equals("0")&&mType==1&&payStatus.equals("0")){
            holder.btn_cancel.setText("取消订单");
        }

        //全部订单中 已完成 已付款||已取消
        if(((myOrderStatus.equals("3")||(myOrderStatus.equals("4")&&payStatus.equals("1"))&&(mType==1)))){
            holder.btn_cancel.setVisibility(View.INVISIBLE);
            holder.btn_pay.setText("删除订单");
        }

        //  全部订单中 已完成 未付款
        if((myOrderStatus.equals("4")&&payStatus.equals("0"))&&(mType==1)){
            holder.btn_cancel.setText("收货待付");
            holder.btn_cancel.setEnabled(false);
            holder.btn_pay.setText("立即付款");
        }

        //待收货
        if(mType==2){
            //待收货
            //已经付款
            if(dataBean.getPay_status().equals("1")){
                holder.btn_cancel.setVisibility(View.INVISIBLE);
                holder.btn_pay.setText("已收货");
            }else{//未付款
                holder.btn_cancel.setText("已收货");
                /*holder.btn_cancel.setEnabled(false);*/
                holder.btn_pay.setText("立即付款");
            }
        }
        //待付款
        if(mType==0){
            holder.btn_cancel.setText("取消订单");
            if(dataBean.getAuditings().equals("1")) {
                holder.btn_pay.setText("等待审核");
                holder.btn_pay.setEnabled(false);
            }else{
                holder.btn_pay.setText("立即付款");
            }

        }
        //已取消
        if(mType==3){
            //已取消
            holder.btn_cancel.setVisibility(View.INVISIBLE);
            holder.btn_pay.setText("删除订单");
        }
        if(mType==4){
            //已完成 已经付款
            if(dataBean.getPay_status().equals("1")) {
                holder.btn_cancel.setVisibility(View.INVISIBLE);
                holder.btn_pay.setText("删除订单");
            }else{//未付款
                holder.btn_cancel.setText("收货待付");
                holder.btn_cancel.setEnabled(false);
                holder.btn_pay.setText("立即付款");
            }

        }
        final OrderData.DataBean.SpecBean specBean=getItem(position).getSpec().get(0);
        int sumNumber=0;
        for(int i=0;i<getItem(position).getSpec().size();i++){
            int number=0;
            if(!StringUtils.isEmpty(getItem(position).getSpec().get(i).getGoods_num())) {
                number = Integer.parseInt(getItem(position).getSpec().get(i).getGoods_num());
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
              /*  //1. 已经取消    2.已经完成已经支付状态下   3.所有订单支付完成之后
                if(mType==3||(dataBean.getPay_status().equals("1")&&dataBean.getOrder_status().equals("4"))||dataBean.getOrder_status().equals("3")){
                    DeleteOrder(dataBean.getOrder_id(),position);

                }//1.所有订单 2.
                else if((dataBean.getOrder_status().equals("2")||mType==2||mType==1)&&dataBean.getPay_status().equals("1")){
                    GetGood(dataBean.getOrder_id(),position);
                }else{
                    PayOrder(dataBean.getMoney(),dataBean.getOrder_id(),dataBean.getOrdernumber(),dataBean.getMoney());
                }*/
                //全部订单中 未发货&&待付款&&审核通过 按钮含义:立即付款
                if(dataBean.getOrder_status().equals("0")&&(mType==1)&&dataBean.getAuditings().equals("0")){
                    PayOrder(dataBean.getMoney(),dataBean.getOrder_id(),dataBean.getOrdernumber(),dataBean.getMoney());
                }
                //全部订单中 已发货&&(待付款) 按钮含义:立即付款
                if((dataBean.getOrder_status().equals("2")&&(mType==1)&&dataBean.getPay_status().equals("0"))){
                    PayOrder(dataBean.getMoney(),dataBean.getOrder_id(),dataBean.getOrdernumber(),dataBean.getMoney());
                }

                //全部订单中 已发货&&(已付款) 按钮含义: 已收货
                if((dataBean.getOrder_status().equals("2")&&(mType==1)&&dataBean.getPay_status().equals("1"))){
                    GetGood(dataBean.getOrder_id(),position);
                }

                //全部订单中 已取消||已完成 按钮含义:删除订单
                if((dataBean.getOrder_status().equals("3")||((dataBean.getOrder_status().equals("4")&&dataBean.getPay_status().equals("1")))&&(mType==1))){
                    DeleteOrder(dataBean.getOrder_id(),position);
                }


                ////全部订单中 已完成 未付款 按钮含义:立即付款
                if(mType==1&&dataBean.getPay_status().equals("0")&&dataBean.getOrder_status().equals("4")){
                    PayOrder(dataBean.getMoney(),dataBean.getOrder_id(),dataBean.getOrdernumber(),dataBean.getMoney());
                }

                //待收货 已经付款  按钮含义:已收货
                if(mType==2&&dataBean.getPay_status().equals("1")){
                    GetGood(dataBean.getOrder_id(),position);
                }


                //待收货 未付款  按钮含义:付款
                if(mType==2&&dataBean.getPay_status().equals("0")){
                    PayOrder(dataBean.getMoney(),dataBean.getOrder_id(),dataBean.getOrdernumber(),dataBean.getMoney());
                }
                //待付款
                if(mType==0){
                    PayOrder(dataBean.getMoney(),dataBean.getOrder_id(),dataBean.getOrdernumber(),dataBean.getMoney());
                }
                //已经取消 按钮含义:删除订单
                if(mType==3){
                    DeleteOrder(dataBean.getOrder_id(),position);
                }

                //已经完成  已经付款 按钮含义:删除订单
                if(mType==4&&dataBean.getPay_status().equals("1")){
                    DeleteOrder(dataBean.getOrder_id(),position);
                }

                //已经完成  未付款 按钮含义:立即付款
                if(mType==4&&dataBean.getPay_status().equals("0")){
                    PayOrder(dataBean.getMoney(),dataBean.getOrder_id(),dataBean.getOrdernumber(),dataBean.getMoney());
                }
            }
        });
        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //全部订单中 未发货&&待付款 按钮含义:取消订单
                if((dataBean.getOrder_status().equals("0")&&(mType==1))) {
                    CancelOrder(dataBean.getOrder_id(), position);
                }
                //全部订单中 已发货&&(待付款) 按钮含义:已收货
                if(((dataBean.getOrder_status().equals("2")&&(mType==1)&&dataBean.getPay_status().equals("0")))){
                    GetGood(dataBean.getOrder_id(),position);
                }

                //待收货 已经付款  按钮含义:已收货
                if(mType==2&&dataBean.getPay_status().equals("0")) {
                    GetGood(dataBean.getOrder_id(),position);
                }
                //待付款
                if(mType==0){
                    CancelOrder(dataBean.getOrder_id(), position);
                }
            }
        });
        return convertView;
    }

    /**
     * 已收货
     * @param orderId 订单ID
     * @param position 订单的位置
     * @param
     */
    private void GetGood(String orderId,final int position){
        //4代表收货完成  0代表收货待付款
        UserApi.ChangeOrderStatus(mContext, orderId, "4", new HttpUtil.RequestBack() {
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

    //支付订单
    private void PayOrder(String totalPrices,String orderId,String orderNum,String money){
        PayMessageDialog dialog=new PayMessageDialog(mContext,orderId,orderNum,money);
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
/*
        int mFragmentPosition= OrderCooperateActivity.getmPosition();
*/
        //移除当前列表中的数据
        removeOneData(position);
        for(int i=0;i<OrderFragmentAdapter.getFragmentList().size();i++){
            if(i!=position) {
                OrderFragmentAdapter.getFragmentList().get(i).initDatas();
            }
        }
     /*  .get(mFragmentPosition);
    *//*    int type=OrderCooperateActivity.titlesIndex[mFragmentPosition];
        orderFragment.setType(type);*//*
        orderFragment.initDatas();*/
    }

    private void CancelOrder(String orderId, final int position){
        UserApi.ChangeOrderStatus(mContext, orderId, "3", new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(mContext,meg);
                if(code.equals("200")){
                    //修改主页个人信息
                    EventBus.getDefault().post(new PersonalMessagEvent(MyConstant.UpdatePersonalMessage));
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
