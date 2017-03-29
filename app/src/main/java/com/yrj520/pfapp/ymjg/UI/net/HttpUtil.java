package com.yrj520.pfapp.ymjg.UI.net;

import android.content.Context;

import com.yrj520.pfapp.ymjg.R;
import com.yrj520.pfapp.ymjg.UI.config.AppData;
import com.yrj520.pfapp.ymjg.UI.net.okhttp.OkHttpUtils;
import com.yrj520.pfapp.ymjg.UI.net.okhttp.callback.JsonCallback;
import com.yrj520.pfapp.ymjg.UI.utils.LogUtils;
import com.yrj520.pfapp.ymjg.UI.utils.NetUtils;
import com.yrj520.pfapp.ymjg.UI.utils.StringUtils;
import com.yrj520.pfapp.ymjg.UI.utils.ToastUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    private static class MyJsonCallback extends JsonCallback {
        private Context context;
        private RequestBack onBack;

        private MyJsonCallback(Context context, RequestBack onBack) {
            this.context = context;
            this.onBack = onBack;

        }

        @Override
        public void onBefore(Request request, int id) {
            super.onBefore(request, id);
            onBack.onBefore(request);
        }

        @Override
        public void onAfter(int id) {
            super.onAfter(id);
            onBack.onAfter();
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            super.inProgress(progress, total, id);
        }

        @Override
        public void onResponse(JSONObject response, int id) {
            handleResponse(context, response, onBack, id);
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtils.error(e.getMessage());
            onBack.onError(new Exception());
            if (!NetUtils.isConnected(context)) {
                ToastUtils.showShort(context, R.string.please_connected_net);
            } else
                ToastUtils.showShort(context, R.string.net_status);
        }
    }

    /* *
     * 通过get从服务器获取数据
     *
     * @param context 上下文
     * @param id      请求接口定义的id
     * @param reqUrl  请求URL
     * @param onBack  回调
     */
    public static void doGet(Context context, Integer id, String reqUrl,
                             Map<String, String> params, RequestBack onBack) {
        LogUtils.info("method: GET\nreqUrl:" + reqUrl + "\nparams:" + params.toString());
        OkHttpUtils.get()
                .url(reqUrl).params(params).build()
                .execute(new MyJsonCallback(context, onBack));
    }

    /* *
     * 提交表单数据到服务器
     *
     * @param context 上下文
     * @param id      请求接口定义的id
     * @param reqUrl  请求URL
     * @param params  请求参数
     * @param onBack  回调
     */
    public static void doPost(Context context, Integer id, String reqUrl,
                              Map<String, String> params, RequestBack onBack) {
        LogUtils.info("method: POST\nreqUrl:" + reqUrl + "\nparams:" + params.toString());
        OkHttpUtils.post().addHeader("Authorization", AppData.getAppData(context).getTokenValue())
                .url(reqUrl)
                .params(params).build()
                .execute(new MyJsonCallback(context, onBack));
    }

    /**
     * 通过post上传文件到服务器
     *
     * @param context 上下文
     * @param id      请求接口定义的id
     * @param reqUrl  请求URL
     * @param params  请求参数
     * @param file    请求文件
     * @param onBack  回调
     */
    public static void doPostFile(Context context, Integer id, String reqUrl,
                                  Map<String, String> params, File file, RequestBack onBack) {
        LogUtils.info("method: POST\nreqUrl:" + reqUrl + "\nparams:" + params.toString());
        OkHttpUtils.post().id(id)
                .addHeader("Authorization", AppData.getAppData(context).getTokenValue())
                .addFile("file", "file213.jpg", file)
                .url(reqUrl).params(params).build()
                .execute(new MyJsonCallback(context, onBack));
    }

     /**
     * 通过doPut从服务器获取数据
     *
     * @param context 上下文
     * @param id      请求接口定义的id
     * @param reqUrl  请求URL
     * @param onBack  回调
     */
    public static void doPut(Context context, Integer id, RequestBody requestBody,
                             String reqUrl, RequestBack onBack) {
        // RequestBody requestBody = RequestBody.create(mediaType, content);
        LogUtils.info("method: PUT\nreqUrl:" + reqUrl);

        OkHttpUtils.put().id(id).addHeader("productId", "10")
                .addHeader("Authorization", AppData.getAppData(context).getTokenValue())
                .requestBody(requestBody)
                .url(reqUrl).build()
                .execute(new MyJsonCallback(context, onBack));
    }


    public static void doPatch(Context context, Integer id, RequestBody requestBody,
                               String reqUrl, RequestBack onBack) {
        LogUtils.debug("method: PATCH\nreqUrl:" + reqUrl);

        OkHttpUtils.patch().id(id).addHeader("productId", "10")
                .requestBody(requestBody)
                .url(reqUrl).build()
                .execute(new MyJsonCallback(context, onBack));
    }

    public static void doDelete(Context context, Integer id, RequestBody requestBody,
                                String reqUrl, RequestBack onBack) {
        LogUtils.info("method: DELETE\nreqUrl:" + reqUrl);

        OkHttpUtils.delete().id(id).addHeader("productId", "10")
                .requestBody(requestBody)
                .url(reqUrl).build()
                .execute(new MyJsonCallback(context, onBack));
    }

     /**
     * http 请求后，
     *
     * @param context  上下文
     * @param response 返回数据
     */
    private static void handleResponse(Context context, JSONObject response, RequestBack onBack, int requestId) {
        LogUtils.info(response.toString());
        if (StringUtils.isEmpty(response.toString())) return;
        onBack.onSuccess(response);

       /* //判断请求接口是否有返回参数
        if (requestId == Constant.REQUEST_ID_NULL_RETURN) return;
      *//*  int status = response.optJSONObject("result").optInt("status");
        String msg = response.optJSONObject("result").optString("msg");*//*
        if (StringUtils.isResponseOk(status)) {
            onBack.onSuccess(response);
        } else if (StringUtils.isInvalidToken(status)) {//判断token失效
            *//*ToastUtils.showShort(context, R.string.login_lose_efficacy);
            AppData.getAppData(context).setTokenValue("");
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //intent.putExtra("GO_PAGE", context.getClass().getSimpleName());
            context.startActivity(intent);*//*
        } else if (status == 2000) {
            ToastUtils.showShort(context, R.string.net_status);
        } else {
            ToastUtils.showShort(context, msg);
        }*/
    }

    /**
     * 回调接口
     */
    public static abstract class RequestBack {
        public abstract void onSuccess(JSONObject response);

        public abstract void onError(Exception e);

        public void onAfter() {
        }

        public void onBefore(Request request) {
        }
    }
}
