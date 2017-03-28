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

public class LoginResultData {
    /**
     * auditstate：审核状态(1需审核 2正在审核中 3审核通过)  	code=200  message=验证成功     code=400  message=错误操作  code=401
     * message=用户名不存在  ||  密码输入错误  ||  输入信息不规范
     */
    private String auditstate;

    private String code;

    private String message;

    public String getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(String auditstate) {
        this.auditstate = auditstate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
