package com.yrj520.pfapp.ymjg.UI.entity;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class RequestPersonMessageData  {

    /**
     * 审核页面：username：用户姓名	idcard:身份证号码	idcardurl：身份证正面照片	usercardurl：身份证反面照片	businessurl：营业执照照片
     */

    public String Keyusername="username";

    public String Keyidcard="idcard";

    public String Keyidcardurl="idcardurl";

    public String Keyusercardurl="usercardurl";

    public String Keybusinessurl="businessurl";

    private String username;

    private String idcard;

    private String idcardurl;

    private String usercardurl;

    private String businessurl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardurl() {
        return idcardurl;
    }

    public void setIdcardurl(String idcardurl) {
        this.idcardurl = idcardurl;
    }

    public String getUsercardurl() {
        return usercardurl;
    }

    public void setUsercardurl(String usercardurl) {
        this.usercardurl = usercardurl;
    }

    public String getBusinessurl() {
        return businessurl;
    }

    public void setBusinessurl(String businessurl) {
        this.businessurl = businessurl;
    }




}
