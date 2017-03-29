package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.PersonData;
import com.yrj520.pfapp.ymjg.UI.entity.PersonMessageData;
import com.yrj520.pfapp.ymjg.UI.event.PersonalMessagEvent;
import com.yrj520.pfapp.ymjg.UI.net.Host;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.photo.MediaChoseActivity;
import com.yrj520.pfapp.ymjg.UI.utils.ImageUtils;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
import com.yrj520.pfapp.ymjg.UI.utils.PermissionUtils;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import de.greenrobot.event.EventBus;
import okhttp3.Request;

import static com.yrj520.pfapp.ymjg.R.id.tv_left;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class AccountManageActivity extends BaseActivity {
    private RelativeLayout rl_header;

    private RelativeLayout rl_shop_name;

    private RelativeLayout rl_account;

    private RelativeLayout rl_reset_password;

    private RelativeLayout rl_my_order;

    private RelativeLayout rl_address_manage;

    private RelativeLayout rl_feedback;

    private RelativeLayout rl_about;

    private ImageView iv_header;

    private TextView tv_shop_name;

    private TextView tv_account;

    private RelativeLayout rl_left;

    private TextView tv_center;

    private Uri picPath;

    private String myImgUrl;

    private PersonData personData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        EventBus.getDefault().register(this);
        initDatas();
        initViews();
        initClickListenner();

    }

    private void initDatas() {
        UserApi.QueryPersonalMessageApi(AccountManageActivity.this, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                if(code.equals("200")){
                    Gson gson=new Gson();
                    personData=gson.fromJson(response.toString(),PersonData.class);
                    setViews();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
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
        tv_center.setText("账户管理");
        String imgUrl= Host.HOST+personData.getData().getUserimg();
        if(!StringUtils.isEmpty(personData.getData().getUserimg())){
            ImageUtils.loadCirclePic(AccountManageActivity.this,imgUrl,iv_header,R.mipmap.header);
            tv_shop_name.setText(personData.getData().getLianxiren());
            tv_account.setText(personData.getData().getPhone());
        }
    }

    private void initClickListenner() {
        rl_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceImageHeader();
            }
        });

        rl_shop_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccountManageActivity.this,MessageUpdateActivity.class);
                intent.putExtra("shopName",personData.getData().getLianxiren());
                startActivity(intent);
            }
        });

        rl_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(AccountManageActivity.this,RegisterActivity.class);
                    intent.putExtra("type",1);
                    startActivity(intent);
            }
        });

        rl_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccountManageActivity.this,OrderCooperateActivity.class);
                startActivity(intent);
            }
        });

        rl_address_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccountManageActivity.this,ActivityAddress.class);
                startActivity(intent);
            }
        });

        rl_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccountManageActivity.this,ActivityFeedBack.class);
                startActivity(intent);
            }
        });

        rl_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccountManageActivity.this,ActivityAboutUs.class);
                startActivity(intent);
            }
        });

        rl_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void choiceImageHeader() {
        if (PermissionUtils.isCameraPermission(AccountManageActivity.this, 0x007)){
            getPic();
        }
    }
    private void getPic(){
        Intent intent = new Intent(this, MediaChoseActivity.class);
        intent.putExtra("crop", true);
        intent.putExtra("crop_image_w", 750);
        intent.putExtra("crop_image_h", 750);
        startActivityForResult(intent, 1000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            picPath = data.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
            if(picPath!=null) {

                try {
                    uploadRes(MyConstant.FileNameBussiness,new File(new URI(picPath.toString())));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    LogUtils.info("printStackTrace: "+e.getMessage());
                }

                ImageUtils.loadCirclePic(AccountManageActivity.this,picPath.toString(),iv_header,R.mipmap.header);

            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //上传头像
    private void uploadRes(String fileName,File file) {
        UserApi.uploadResApi(this, fileName,file, new HttpUtil.RequestBack() {
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                showLoading("请稍后...");
            }

            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                if(code.equals("200")){
                    myImgUrl=response.optString("imgurl");
                    UpdateHeaderImage();
                }

            }

            @Override
            public void onError(Exception e) {
            }

            @Override
            public void onAfter() {
                super.onAfter();
                closeLoading();
            }
        });
    }

    private void UpdateHeaderImage(){
        PersonMessageData pMD=new PersonMessageData();
        pMD.setUserimg(myImgUrl);
        UserApi.UpdatePersonalMessageApi(AccountManageActivity.this, pMD, new HttpUtil.RequestBack() {
            @Override
            public void onSuccess(JSONObject response) {
                String code=response.optString("code");
                String meg=response.optString("meg");
                ToastUtils.showShort(AccountManageActivity.this,meg);
                if(code.equals("200")){
                    PersonalMessagEvent personalMessagEvent=new PersonalMessagEvent(MyConstant.UpdatePersonalMessage);
                    EventBus.getDefault().post(personalMessagEvent);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 0x007:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getPic();
                } else {
                    ToastUtils.showShort(this, "拍照权限被拒绝");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }
    private void initViews() {
        rl_header=(RelativeLayout)findViewById(R.id.rl_header);
        rl_shop_name=(RelativeLayout)findViewById(R.id.rl_shop_name);
        rl_account=(RelativeLayout)findViewById(R.id.rl_account);
        rl_reset_password=(RelativeLayout)findViewById(R.id.rl_reset_password);
        rl_my_order=(RelativeLayout)findViewById(R.id.rl_my_order);
        rl_address_manage=(RelativeLayout)findViewById(R.id.rl_address_manage);
        rl_feedback=(RelativeLayout)findViewById(R.id.rl_feedback);
        rl_about=(RelativeLayout)findViewById(R.id.rl_about);
        iv_header=(ImageView)findViewById(R.id.iv_header);
        tv_shop_name=(TextView) findViewById(R.id.tv_shop_name);
        tv_account=(TextView) findViewById(R.id.tv_account);
        rl_left=(RelativeLayout) findViewById(R.id.rl_left);
        tv_center=(TextView) findViewById(R.id.tv_center);
    }
}
