package com.yrj520.pfapp.ymjg.UI.event;

/**
 * Created by zry on 17/4/18.
 */

public class PersonalMessagEvent {
    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    //个人信息的事件
    private String mMsg;

    public PersonalMessagEvent(String meg){
        mMsg=meg;
    }
}
