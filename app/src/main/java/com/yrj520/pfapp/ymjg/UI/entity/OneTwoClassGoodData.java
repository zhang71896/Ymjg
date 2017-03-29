package com.yrj520.pfapp.ymjg.UI.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zry on 17/4/2.
 */

public class OneTwoClassGoodData {


    /**
     * data : [{"cid":"32","level":"1","name":"配材配草","array":[{"cid":"31","level":"2","name":"鲜切花","array":[]},{"cid":"11","level":"2","name":"鲜花速递","array":[]}]},{"cid":"9","level":"1","name":"鲜花","array":[{"cid":"31","level":"2","name":"鲜切花","array":[]},{"cid":"11","level":"2","name":"鲜花速递","array":[]}]}]
     * code : 200
     */

    private String code;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cid : 32
         * level : 1
         * name : 配材配草
         * array : [{"cid":"31","level":"2","name":"鲜切花","array":[]},{"cid":"11","level":"2","name":"鲜花速递","array":[]}]
         */

        private String cid;
        private String level;
        private String name;
        private List<ArrayBean> array=new ArrayList<ArrayBean>();

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ArrayBean> getArray() {
            return array;
        }

        public void setArray(List<ArrayBean> array) {
            this.array = array;
        }

        public static class ArrayBean {
            /**
             * cid : 31
             * level : 2
             * name : 鲜切花
             * array : []
             */

            private String cid;
            private String level;
            private String name;
            private List<?> array;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<?> getArray() {
                return array;
            }

            public void setArray(List<?> array) {
                this.array = array;
            }
        }
    }
}
