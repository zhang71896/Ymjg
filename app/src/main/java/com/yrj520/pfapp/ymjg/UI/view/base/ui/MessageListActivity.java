package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.adapter.MessageAdapter;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class MessageListActivity  extends Activity{
    private ListView lv_message_list;
    private RelativeLayout rl_left;
    private TextView tv_center;
    private MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        initViews();
        initDatas();
        initClickListenner();
    }

    private void initDatas() {
        messageAdapter=new MessageAdapter(MessageListActivity.this);
        messageAdapter.addAll(IndexActivity.getMessageDataList());
        lv_message_list.setAdapter(messageAdapter);
    }

    private void initClickListenner() {
        rl_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        lv_message_list=(ListView) findViewById(R.id.lv_message_list);
        rl_left=(RelativeLayout) findViewById(R.id.rl_left);
        tv_center=(TextView)findViewById(R.id.tv_center);
        tv_center.setText("消息列表");
    }
}
