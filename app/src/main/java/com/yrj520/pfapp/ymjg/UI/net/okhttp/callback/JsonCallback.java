package com.yrj520.pfapp.ymjg.UI.net.okhttp.callback;

import org.json.JSONObject;

import okhttp3.Response;

public abstract class JsonCallback extends Callback<JSONObject> {

    @Override
    public JSONObject parseNetworkResponse(Response response, int id) throws Exception {
        return new JSONObject(response.body().string());
    }

}
