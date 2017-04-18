package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.IndexData;
import com.yrj520.pfapp.ymjg.UI.entity.MessageData;
import com.yrj520.pfapp.ymjg.UI.entity.UserData;
import com.yrj520.pfapp.ymjg.UI.event.PersonalMessagEvent;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.utils.ImageUtils;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import java.util.List;

import de.greenrobot.event.EventBus;
import okhttp3.Request;

/**
 * Title:主页界面
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class IndexActivity extends BaseActivity {

    private RelativeLayout rl_setting;

    private TextView tv_nickname;

    private ImageView iv_header;

    private RelativeLayout rl_centerimage;

    private TextView tv_center_message;

    private LinearLayout ll_purchase_goods;

    private LinearLayout ll_account_manage;

    private RelativeLayout rl_order_cooperate;

    private TextView tv_message_count;

    private static IndexData indexData;

    private int count=0;

    private TimeCount mTimeCount;

    //播放消息的间隔时间
    private int intervalTime=2000;
    //总时长
    private  int totalTime=60000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);
        EventBus.getDefault().register(this);
        initDatas();
        initViews();
        initClickListenner();
    }
    public void onEventMainThread(PersonalMessagEvent personalMessagEvent){
        String msgType=personalMessagEvent.getmMsg();
        if(msgType.equals(MyConstant.UpdatePersonalMessage)){
            //获取购物车相关的信息
            initDatas();
            return;
        }
    }

    private void setViews() {
        if(indexData!=null)
              if(!StringUtils.isEmpty(indexData.getCode())) {
                  //int messageCount = 12;
                  int messageCount=Integer.parseInt(indexData.getOrder());
                  tv_message_count.setVisibility(View.GONE);
                  if (messageCount > 0) {
                      if(messageCount>100){
                          tv_message_count.setText("99+");
                      }else{
                          tv_message_count.setText(messageCount + "");
                      }

                      tv_message_count.setVisibility(View.VISIBLE);
                  }
              }
                  if (indexData.getMesage() != null && indexData.getMesage().size() > 0) {
                      mTimeCount=null;
                      mTimeCount = new TimeCount(totalTime, intervalTime);// 构造CountDownTimer对象
                      mTimeCount.start();// 开始计时
                  }
                String imgUrl=indexData.getUser().getUserimg();
                String nickNmae=indexData.getUser().getLianxiren();
                if(!StringUtils.isEmpty(imgUrl)){
                    ImageUtils.loadCirclePic(IndexActivity.this,ImageUtils.getImageUrl(imgUrl),iv_header,R.mipmap.header);
                }
                tv_nickname.setText(nickNmae);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化获取数据
     */
    private void initDatas() {
        indexData=new IndexData();
        UserApi.IndexApi(IndexActivity.this, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                //获取数据成功
                if(code.equals("200")){
                    ToastUtils.showShort(IndexActivity.this,"获取数据成功!");
                    Gson gson = new Gson();
                    indexData=gson.fromJson(response.toString(),IndexData.class);
                    setViews();
                    return;
                }
                //获取失败
                ToastUtils.showShort(IndexActivity.this,"获取数据失败!");

            }

            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                showLoading("加载中");
            }

            @Override
            public void onAfter() {
                super.onAfter();
                closeLoading();
            }
        });
    }

    public static List<MessageData> getMessageDataList(){
        return indexData.getMesage();
    }

    public static UserData getUserData(){
        return indexData.getUser();
    }
    private void initClickListenner() {
        rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(IndexActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        ll_purchase_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(IndexActivity.this,PurchaseGoodActivity.class);
                startActivity(intent);
            }
        });

        ll_account_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(IndexActivity.this,AccountManageActivity.class);
                startActivity(intent);
            }
        });

        rl_order_cooperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(IndexActivity.this,OrderCooperateActivity.class);
                startActivity(intent);
            }
        });
        rl_centerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IndexActivity.this,MessageListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews(){
        rl_setting=(RelativeLayout)findViewById(R.id.rl_setting);
        tv_nickname=(TextView)findViewById(R.id.tv_nickname);
        rl_centerimage=(RelativeLayout)findViewById(R.id.rl_centerimage);
        tv_center_message=(TextView)findViewById(R.id.tv_center_message);
        ll_purchase_goods=(LinearLayout) findViewById(R.id.ll_purchase_goods);
        ll_account_manage=(LinearLayout) findViewById(R.id.ll_account_manage);
        rl_order_cooperate=(RelativeLayout)findViewById(R.id.rl_order_cooperate);
        tv_message_count=(TextView)findViewById(R.id.tv_message_count);
        iv_header=(ImageView) findViewById(R.id.iv_header);
    }

    //倒计时计数
    class TimeCount extends CountDownTimer {
        private TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {// 计时完毕时触发
            mTimeCount.cancel();
            mTimeCount=null;
            mTimeCount = new TimeCount(totalTime, intervalTime);// 构造CountDownTimer对象
            mTimeCount.start();// 开始计时
        }
        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            count++;
            if(count==indexData.getMesage().size()-1){
                count=0;
            }
            tv_center_message.setClickable(false);
            tv_center_message.setText(indexData.getMesage().get(count).getTitle());
        }
    }
}
