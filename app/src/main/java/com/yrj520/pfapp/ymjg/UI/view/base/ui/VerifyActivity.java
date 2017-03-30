package com.yrj520.pfapp.ymjg.UI.view.base.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.photo.MediaChoseActivity;
import com.yrj520.pfapp.ymjg.UI.utils.PermissionUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;

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

    private EditText et_id;

    private EditText et_name;

    private Uri picPath;

    private ImageView iv_idz;

    private ImageView iv_idf;

    private ImageView iv_bussiness_lience;

    //0:iv_idz 1:iv_idf 2:iv_bussiness_lience
    private int photoType=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_activity);

        initViews();

        initClickListenner();
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
    }

    private void initViews() {
        et_id= (EditText) findViewById(R.id.et_id);
        et_name= (EditText) findViewById(R.id.et_name);

        iv_idz=(ImageView)findViewById(R.id.iv_idz);
        iv_idf=(ImageView)findViewById(R.id.iv_idf);
        iv_bussiness_lience=(ImageView)findViewById(R.id.iv_bussiness_lience);

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

    private ImageView getImageViewByPhotoType(){
        if(photoType==1){
            return iv_idz;
        }
        if(photoType==2){
            return iv_idf;
        }
        if(photoType==3){
            return iv_bussiness_lience;
        }
        return null;
    }
}
