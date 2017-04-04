package com.yrj520.pfapp.ymjg.UI.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zry on 17/4/2.
 */

public class IndexData {
    private String code;

    private UserData user=new UserData();

    private String order;

    public List<MessageData> getMesage() {
        return mesage;
    }

    public void setMesage(List<MessageData> mesage) {
        this.mesage = mesage;
    }

    private List<MessageData> mesage=new ArrayList<MessageData>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }




}
