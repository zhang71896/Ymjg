package com.yrj520.pfapp.ymjg.UI.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yrj520.pfapp.ymjg.R;


public class ImageUtils {

    /**
     * 加载普通图片
     *
     * @param context 上下文
     * @param picUrl  图片地址
     * @param view    加载的view
     */
    public static void loadCommonPic(Context context, String picUrl, ImageView view) {
        Glide.with(context)
                .load(picUrl)
                .centerCrop()
                .placeholder(R.mipmap.header)
                .error(R.mipmap.header)
                .crossFade()
                .into(view);
    }

    /**
     * 加载圆形图片
     *
     * @param context 上下文
     * @param picUrl  图片地址
     * @param view    加载的view
     */
    public static void loadCirclePic(Context context, String picUrl, ImageView view) {
        Glide.with(context)
                .load(picUrl)
                .transform(new GlideCircleTransform(context))
                .placeholder(R.mipmap.header)
                .error(R.mipmap.header)
                .crossFade()
                .into(view);
    }


}
