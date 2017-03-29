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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.PersonMessageData;
import com.yrj520.pfapp.ymjg.UI.entity.UserData;
import com.yrj520.pfapp.ymjg.UI.net.Host;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.photo.MediaChoseActivity;
import com.yrj520.pfapp.ymjg.UI.utils.GlideCircleTransform;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
import com.yrj520.pfapp.ymjg.UI.utils.PermissionUtils;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import okhttp3.Request;

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

    private TextView tv_left;

    private TextView tv_center;

    private UserData mUserData;

    private Uri picPath;

    private String myImgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        mUserData=IndexActivity.getUserData();
        initViews();
        initClickListenner();
        setViews();
    }

    private void setViews() {
        tv_center.setText("账户管理");
        String imgUrl= Host.HOST+mUserData.getUserimg();
        if(!StringUtils.isEmpty(imgUrl)) {
            Glide.with(this)
                    .load(imgUrl).transform(new GlideCircleTransform(this))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .placeholder(R.mipmap.header)
                    .error(R.mipmap.header)
                    .skipMemoryCache(true) //跳过内存缓存
                    .into(iv_header);
            tv_shop_name.setText(mUserData.getUsername());
            tv_account.setText(mUserData.getPhone());

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
                intent.putExtra("shopName",mUserData.getUsername());
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

            }
        });

        rl_address_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        tv_left.setOnClickListener(new View.OnClickListener() {
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
                    uploadRes(MyConstant.FileNameHeader,new File(new URI(picPath.toString())));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    LogUtils.info("printStackTrace: "+e.getMessage());
                }
                Glide.with(AccountManageActivity.this)
                        .load(picPath)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .centerCrop()
                        .placeholder(R.mipmap.add)
                        .error(R.mipmap.add)
                        .skipMemoryCache(true) //跳过内存缓存
                        .into(iv_header);
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
        tv_left=(TextView) findViewById(R.id.tv_left);
        tv_center=(TextView) findViewById(R.id.tv_center);
    }
}
