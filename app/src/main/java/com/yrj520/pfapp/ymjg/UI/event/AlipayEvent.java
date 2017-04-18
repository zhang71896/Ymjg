package com.yrj520.pfapp.ymjg.UI.event;

/**
 * Created by zry on 17/4/18.
 */

public class AlipayEvent {
    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    //支付相关的事件
    private String mMsg;

    public AlipayEvent(String meg){
        mMsg=meg;
    }
}
