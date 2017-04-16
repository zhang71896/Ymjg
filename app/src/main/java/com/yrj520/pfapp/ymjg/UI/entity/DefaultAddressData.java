package com.yrj520.pfapp.ymjg.UI.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zry on 17/4/16.
 */

public class DefaultAddressData {

    /**
     * code : 200
     * data : {"address_id":"51","consignee":"123214","sh_address":"123","sh_phone":"1232131233","default":"1","provicename":"北京","cityname":"北京市","districtname":"密云县"}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address_id : 51
         * consignee : 123214
         * sh_address : 123
         * sh_phone : 1232131233
         * default : 1
         * provicename : 北京
         * cityname : 北京市
         * districtname : 密云县
         */

        private String address_id;
        private String consignee;
        private String sh_address;
        private String sh_phone;
        @SerializedName("default")
        private String defaultX;
        private String provicename;
        private String cityname;
        private String districtname;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

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

        public String getDefaultX() {
            return defaultX;
        }

        public void setDefaultX(String defaultX) {
            this.defaultX = defaultX;
        }

        public String getProvicename() {
            return provicename;
        }

        public void setProvicename(String provicename) {
            this.provicename = provicename;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getDistrictname() {
            return districtname;
        }

        public void setDistrictname(String districtname) {
            this.districtname = districtname;
        }
    }
}
