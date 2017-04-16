package com.yrj520.pfapp.ymjg.UI.event;

/**
 * Created by zry on 17/4/16.
 */

public class AddressEvent {
    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    //地址相关的事件
    private String mMsg;

    public AddressEvent(String meg){
        mMsg=meg;
    }
}
