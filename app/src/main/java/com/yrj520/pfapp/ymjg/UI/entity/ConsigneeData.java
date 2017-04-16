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

public class ConsigneeData {

    /**
     * 请求参数： consignee：收货人	sh_address：地址	sh_phone：收货人手机  provice：省id	city：市id	  area_id：区id 	修改多一个address_id：地址id	 default:设为默认 1是默认  0不默认
     */
    public final String Keyconsignee="consignee";

    public String Keysh_address="sh_address";

    public String Keysh_phone="sh_phone";

    public String Keyprovice="provice";

    public String Keycity="city";

    public String Keyarea_id="area_id";

    public String KeyisDefault="default";

    public String Keyaddress_id="adress_id";

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    private String address_id;

    private String consignee;

    private String sh_address;

    private String sh_phone;

    private String provice;

    private String city;

    private String area_id;

    private String isDefault;

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getSh_address() {
        return sh_address;
    }

    public void setSh_address(String sh_address) {
        this.sh_address = sh_address;
    }

    public String getSh_phone() {
        return sh_phone;
    }

    public void setSh_phone(String sh_phone) {
        this.sh_phone = sh_phone;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }


}
