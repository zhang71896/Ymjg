package com.yrj520.pfapp.ymjg.UI.entity;

/**
 * Created by zry on 17/4/18.
 */

public class PersonData {


    /**
     * code : 200
     * data : {"lianxiren":"asjdiosja","userimg":"true","phone":"15208266461","address":"","provicename":null,"cityname":null,"districtname":null}
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
         * lianxiren : asjdiosja
         * userimg : true
         * phone : 15208266461
         * address :
         * provicename : null
         * cityname : null
         * districtname : null
         */

        private String lianxiren;
        private String userimg;
        private String phone;
        private String address;
        private Object provicename;
        private Object cityname;
        private Object districtname;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getProvicename() {
            return provicename;
        }

        public void setProvicename(Object provicename) {
            this.provicename = provicename;
        }

        public Object getCityname() {
            return cityname;
        }

        public void setCityname(Object cityname) {
            this.cityname = cityname;
        }

        public Object getDistrictname() {
            return districtname;
        }

        public void setDistrictname(Object districtname) {
            this.districtname = districtname;
        }
    }
}
