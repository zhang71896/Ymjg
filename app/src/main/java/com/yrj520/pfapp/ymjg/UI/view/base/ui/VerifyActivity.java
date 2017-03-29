package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.api.UserApi;
import com.yrj520.pfapp.ymjg.UI.constant.MyConstant;
import com.yrj520.pfapp.ymjg.UI.entity.RequestPersonMessageData;
import com.yrj520.pfapp.ymjg.UI.net.HttpUtil;
import com.yrj520.pfapp.ymjg.UI.photo.MediaChoseActivity;
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

public class VerifyActivity extends BaseActivity {

    private TextView tv_left;

    private TextView tv_center;

    private EditText et_id;

    private EditText et_name;

    private Uri picPath;

    private ImageView iv_idz;

    private ImageView iv_idf;

    private ImageView iv_bussiness_lience;

    private boolean isUploadSuccess=false;

    private Button btn_submit;

    private String myFileName;

    //身份证正面url
    private String userCardUrl;
    //身份证反面url
    private String userImageUrl;
    //营业执照url
    private String bussinessFileUrl;
    //身份证号
    private String idStr;
    //名称
    private String nameStr;

    //0:iv_idz 1:iv_idf 2:iv_bussiness_lience
    private int photoType=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_activity);
        initViews();
        initClickListenner();
        setViews();
    }
    private void setViews(){
        tv_center.setText("资质审核界面");
    }
    private void initClickListenner() {

        iv_idz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoType=1;
                selectPhoto();
            }
        });

        iv_idf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoType=2;
                selectPhoto();
            }
        });

        iv_bussiness_lience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoType=3;
                selectPhoto();
            }
        });
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        SubmitVerify();
            }
        });
    }

    /**
     * 提交审核
     */
    private void SubmitVerify(){
        idStr=et_id.getText().toString();
        nameStr=et_name.getText().toString();
        if(InputCheck()){
            RequestPersonMessageData rPMD=new RequestPersonMessageData();
            rPMD.setIdcard(idStr);
            rPMD.setBusinessurl(bussinessFileUrl);
            rPMD.setIdcardurl(userCardUrl);
            rPMD.setUsercardurl(userImageUrl);
            rPMD.setUsername(nameStr);
            UserApi.UpdateRequestPersonalMessageApi(VerifyActivity.this, rPMD, new HttpUtil.RequestBack() {
                @Override
                public void onSuccess(JSONObject response) {
                    String code=response.optString("code");
                    String meg=response.optString("meg");
                    ToastUtils.showShort(VerifyActivity.this,meg);
                    if(code.equals("200")){
                        Intent intent = new Intent(VerifyActivity.this, WaitingVerifyActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onError(Exception e) {

                }

                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
                    showLoading("提交中...");
                }

                @Override
                public void onAfter() {
                    super.onAfter();
                    closeLoading();
                }
            });




        }

    }

    private boolean InputCheck(){
        boolean isPass=true;
        if(StringUtils.isEmpty(idStr)){
            ToastUtils.showShort(this,R.string.input_right_id);
            et_id.requestFocus();
            return false;
        }

        if(StringUtils.isEmpty(nameStr)){
            ToastUtils.showShort(this,R.string.input_right_name);
            et_name.requestFocus();
            return false;
        }

        if(StringUtils.isEmpty(userCardUrl)){
            ToastUtils.showShort(this,R.string.please_upload_userid);
            return false;
        }
        if(StringUtils.isEmpty(userImageUrl)){
            ToastUtils.showShort(this,R.string.please_upload_userimage);
            return false;
        }
        if(StringUtils.isEmpty(bussinessFileUrl)){
            ToastUtils.showShort(this,R.string.please_upload_bussiness);
            return false;
        }
        return isPass;
    }
    private void initViews() {
        et_id= (EditText) findViewById(R.id.et_id);
        et_name= (EditText) findViewById(R.id.et_name);
        tv_left=(TextView)findViewById(R.id.tv_left);
        tv_center=(TextView)findViewById(R.id.tv_center);
        iv_idz=(ImageView)findViewById(R.id.iv_idz);
        iv_idf=(ImageView)findViewById(R.id.iv_idf);
        iv_bussiness_lience=(ImageView)findViewById(R.id.iv_bussiness_lience);

        btn_submit=(Button) findViewById(R.id.btn_submit);


    }
    
    private void selectPhoto()
    {
        if (PermissionUtils.isCameraPermission(VerifyActivity.this, 0x007)){
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
            ImageView photoId=getImageViewByPhotoType();
            if(picPath!=null) {
                LogUtils.info("picPath: "+picPath);
                try {
                    uploadRes(new File(new URI(picPath.toString())));
                    LogUtils.info("uploadRes: "+picPath);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    LogUtils.info("printStackTrace: "+e.getMessage());
                }
            }
            if(photoId!=null) {
                Glide.with(this)
                        .load(picPath)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .centerCrop()
                        .placeholder(R.mipmap.add)
                        .error(R.mipmap.add)
                        .skipMemoryCache(true) //跳过内存缓存
                        .into(photoId);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    //上传头像
    private void uploadRes(File file) {
        UserApi.uploadResApi(this,myFileName, file, new HttpUtil.RequestBack() {
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                showLoading("请稍后...");
            }

            @Override
            public void onSuccess(JSONObject response) {
                LogUtils.info(response.toString());
                String code=response.optString("code");
                String meg=response.optString("meg");

                if(code.equals("200")){
                    ToastUtils.showShort(VerifyActivity.this,"上传成功");
                    String imgUrl=response.optString("imgurl");
                    SetImgUrl(imgUrl);
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

    private void SetImgUrl(String imgUrl){
        if(photoType==1){
            userCardUrl=imgUrl;
            return;
        }
        if(photoType==2){
            userImageUrl=imgUrl;
            return;
        }
        if(photoType==3){
            bussinessFileUrl=imgUrl;
            return;
        }
    }

    private ImageView getImageViewByPhotoType(){
        if(photoType==1){
            myFileName= MyConstant.FileUserCard;
            return iv_idz;
        }
        if(photoType==2){
            myFileName= MyConstant.FileUserImage;
            return iv_idf;
        }
        if(photoType==3){
            myFileName= MyConstant.FileNameBussiness;
            return iv_bussiness_lience;
        }
        return null;
    }
}
