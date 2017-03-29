package com.yrj520.pfapp.ymjg.UI.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.yrj520.pfapp.ymjg.R;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * 支付相关的信息选择对话框
 * @author Rock
 * @version 1.0
 */

public class PayMessageDialog extends Dialog{
    private Context mContext;
    public PayMessageDialog(Context context) {
        super(context);
        mContext=context;
        setCustomView();
    }

    private void setCustomView() {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.item_order_detail,null);
        super.setContentView(view);
    }
    /*public static class Builder {
        public PayMessageDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final PayMessageDialog dialog = new PayMessageDialog();
            return dialog;
        }
    }*/
}
