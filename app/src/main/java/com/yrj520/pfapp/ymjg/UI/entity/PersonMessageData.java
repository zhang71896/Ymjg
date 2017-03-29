package com.yrj520.pfapp.ymjg.UI.entity;

/**
 * Title:返回的个人信息实体类
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class PersonMessageData {
    /**
     * 个人资料页面	lianxiren：商家名字	 userimg：头像地址	address：详细地址	provice：省	city：市  district：区
     */

    public final String Keylianxiren="lianxiren";
    public final String Keyuserimg="userimg";
    public final String keyaddress="address";
    public final String Keyprovice="provice";
    public final String Keycity="city";
    public final String Keydistrict="district";


    private String lianxiren;

    private String userimg;

    private String address;

    private String city;

    private String provice;

    private String district;

    public String getLianxiren() {
        return lianxiren;
    }

    public void setLianxiren(String lianxiren) {
        this.lianxiren = lianxiren;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



}
