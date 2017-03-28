package com.yrj520.pfapp.ymjg.UI.net.okhttp.builder;


import com.yrj520.pfapp.ymjg.UI.net.okhttp.OkHttpUtils;
import com.yrj520.pfapp.ymjg.UI.net.okhttp.request.OtherRequest;
import com.yrj520.pfapp.ymjg.UI.net.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
