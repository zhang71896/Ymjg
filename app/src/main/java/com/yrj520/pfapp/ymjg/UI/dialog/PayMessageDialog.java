package com.yrj520.pfapp.ymjg.UI.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.view.base.ui.ActivityAddress;

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
    private TextView tv_close;
    private TextView tv_phone;
    private TextView tv_lianxiren;
    private TextView tv_address;
    private  TextView tv_pay_value;
    private RelativeLayout rl_address;
    private Button btn_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay_choice);
        tv_close=(TextView) findViewById(R.id.tv_close);
        tv_phone=(TextView) findViewById(R.id.tv_phone);
        tv_lianxiren=(TextView)findViewById(R.id.tv_lianxiren);
        tv_address=(TextView)findViewById(R.id.tv_address);
        tv_pay_value=(TextView)findViewById(R.id.tv_pay_value);
        btn_pay=(Button)findViewById(R.id.btn_pay);
        rl_address=(RelativeLayout)findViewById(R.id.rl_address);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        initClickListenner();
        initDatas();
    }

    public PayMessageDialog(Context context) {
        super(context);
        mContext=context;
    }

    private void setCustomView() {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_pay_choice,null);
        this.setContentView(view);

    }

    private void initClickListenner() {
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        rl_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext, ActivityAddress.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    private void initDatas() {

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
