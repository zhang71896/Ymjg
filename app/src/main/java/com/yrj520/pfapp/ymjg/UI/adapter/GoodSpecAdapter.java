package com.yrj520.pfapp.ymjg.UI.adapter;

import android.content.Context;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.GoodSpecData;
import com.yrj520.pfapp.ymjg.UI.event.CartRefreshEvent;
import com.yrj520.pfapp.ymjg.UI.filter.InputMaxLimitFilter;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;

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

public class GoodSpecAdapter extends BaseAdapter {
    private List<GoodSpecData.DataBean.SpecBean> mListArrayBean= new ArrayList<GoodSpecData.DataBean.SpecBean>();
    private LayoutInflater mInflater;
    private Context mContext;
    private List<GoodSizeBean> goodSizeBeanList=new ArrayList<GoodSizeBean>();
    private int store_num =0 ;
    private int max_store_num=0;
    private float totalPrices=0.0f;
    private int totalCount=0;
    private String good_id;


    public GoodSpecAdapter(Context context){
        mContext=context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void clearAll(){
        mListArrayBean.clear();
        goodSizeBeanList.clear();
        notifyDataSetChanged();
    }

    public void addAll(GoodSpecData goodSize) {
        mListArrayBean.clear();
        mListArrayBean.addAll(goodSize.getData().getSpec());
        goodSizeBeanList.clear();

        for(int i=0;i<goodSize.getData().getSpec().size();i++){
            GoodSpecData.DataBean.SpecBean  specBean=getItem(i);
            GoodSizeBean goodSizeBean=new GoodSizeBean();
            goodSizeBean.setGood_num(0);
            goodSizeBean.setGood_price(Float.parseFloat(specBean.getPrice().toString()));
            goodSizeBeanList.add(goodSizeBean);
        }
        good_id=goodSize.getData().getGoods_id();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListArrayBean.size();
    }

    @Override
    public GoodSpecData.DataBean.SpecBean getItem(int position) {
        return mListArrayBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final GoodSpecAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_good_spec, null);
            holder = new GoodSpecAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (GoodSpecAdapter.ViewHolder) convertView.getTag();
        }
        final GoodSpecData.DataBean.SpecBean data = getItem(position);

        if(!StringUtils.isEmpty(data.getKey_name())){
            holder.tv_keyname.setText(data.getKey_name());
        }

        if(!StringUtils.isEmpty(data.getPrice())){
            holder.tv_good_price.setText("¥ "+data.getPrice());
        }
        if(!StringUtils.isEmpty(data.getGoods_num())) {
            holder.et_store_num.setText(data.getGoods_num() + "");
        }else{
            holder.et_store_num.setText("0");
        }
        if(!StringUtils.isEmpty(data.getStore_count())) {
            holder.tv_stock.setText("(库存:" + data.getStore_count() + ")");
            max_store_num=Integer.parseInt(data.getStore_count().toString());
            String store_num_str = holder.et_store_num.getText().toString();
            if (!StringUtils.isEmpty(store_num_str)) {
                store_num = Integer.parseInt(store_num_str);
            }

            holder.rl_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(max_store_num>store_num) {
                        store_num++;
                        OperateGoodsNum(data, holder.tv_stock, holder.et_store_num,position);
                    }
                }
            });

            holder.rl_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (store_num > 0) {
                        store_num--;
                    } else {
                        store_num = 0;
                    }
                    OperateGoodsNum(data, holder.tv_stock, holder.et_store_num,position);
                }
            });
            String storeCount="999";
            String goodsNums="0";

            if(data.getStore_count()!=null&&data.getStore_count().length()>0){
                storeCount=data.getStore_count().toString();
            }
            if(data.getGoods_num()!=null&&data.getGoods_num().length()>0){
                goodsNums=data.getGoods_num().toString();
            }
            final String  fgoodsNums=goodsNums;
            holder.et_store_num.setFilters(new InputFilter[]{new InputMaxLimitFilter("0", storeCount)});

            holder.et_store_num.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        if(!StringUtils.isEmpty(holder.et_store_num.getText())) {
                            if(!fgoodsNums.equals(holder.et_store_num.getText().toString())) {
                                store_num = Integer.parseInt(holder.et_store_num.getText().toString());
                                OperateGoodsNum(data, holder.tv_stock, holder.et_store_num, position);
                            }
                        }
                    }
                }
            });
            holder.et_store_num.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE||actionId==EditorInfo.IME_ACTION_NEXT) {
                        // do something
                        if(!StringUtils.isEmpty(holder.et_store_num.getText())) {
                            if(!fgoodsNums.equals(holder.et_store_num.getText().toString())) {
                                store_num = Integer.parseInt(holder.et_store_num.getText().toString());
                                OperateGoodsNum(data, holder.tv_stock, holder.et_store_num, position);
                            }
                            return true;
                        }else{
                            return  false;
                        }
                    }
                    return false;
                }
            });


        }

        return convertView;
    }


    private void OperateGoodsNum(GoodSpecData.DataBean.SpecBean data, TextView tv_stock, final EditText et_store_num, final int position) {
        if(store_num>=0) {
            String goodsNum = store_num + "";
            UserApi.OperateGoodsNumApi(mContext, good_id, data.getSgp_id().toString(), goodsNum, new HttpUtil.RequestBack() {
                @Override
                public void onSuccess(JSONObject response) {
                    String code = response.optString("code");
                    String goods_num = "0";
                    if (code.equals("200")) {
                        goods_num = response.optString("goods_num");
                        int num = Integer.parseInt(goods_num);
                        et_store_num.setText(goods_num);
                        goodSizeBeanList.get(position).setGood_num(num);
                        ChangeTotalPrices();
                    }
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
    }

    private void ChangeTotalPrices(){
        totalCount=0;
        totalPrices=0.0f;
        for(int i=0;i<goodSizeBeanList.size();i++){
            int goodNum=goodSizeBeanList.get(i).getGood_num();
            totalCount+=goodNum;
            float prices=goodSizeBeanList.get(i).getGood_price();
            totalPrices+=prices*goodNum;
        }
        CartRefreshEvent cartRefreshEvent =new CartRefreshEvent(MyConstant.UpdateShopCart);
        cartRefreshEvent.setTotalCount(totalCount);
        cartRefreshEvent.setTotalPrices(totalPrices);
        EventBus.getDefault().post(cartRefreshEvent);
    }

    private class ViewHolder {
        TextView tv_keyname;
        TextView tv_stock;
        RelativeLayout rl_add;
        EditText et_store_num;
        RelativeLayout rl_minus;
        TextView tv_good_price;
        final View root;
        ViewHolder(View root) {
            this.root = root;
            tv_keyname=(TextView)root.findViewById(R.id.tv_keyname);
            tv_stock=(TextView)root.findViewById(R.id.tv_stock);
            rl_add=(RelativeLayout)root.findViewById(R.id.rl_add);
            et_store_num=(EditText) root.findViewById(R.id.et_store_num);
            rl_minus=(RelativeLayout) root.findViewById(R.id.rl_minus);
            tv_good_price=(TextView)root.findViewById(R.id.tv_good_price);
        }
    }

    private class GoodSizeBean{
        //个数
        int good_num;
        //单价
        float good_price;

        public int getGood_num() {
            return good_num;
        }

        public void setGood_num(int good_num) {
            this.good_num = good_num;
        }

        public float getGood_price() {
            return good_price;
        }

        public void setGood_price(float good_price) {
            this.good_price = good_price;
        }
    }
}
