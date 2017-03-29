package com.yrj520.pfapp.ymjg.UI.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.net.Host;


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
    public static void loadCirclePic(Context context, String picUrl, ImageView view,int resourceId) {
        Glide.with(context)
                .load(picUrl)
                .transform(new GlideCircleTransform(context))
                .placeholder(resourceId)
                .error(resourceId)
                .crossFade()
                .into(view);
    }

    /**
     * 获取有地址的图片
     * @param url 图片后缀
     * @return
     */
    public static  String getImageUrl(String url){
        return Host.HOST+url;
    }

}
