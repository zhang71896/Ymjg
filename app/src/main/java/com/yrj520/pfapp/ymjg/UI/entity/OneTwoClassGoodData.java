package com.yrj520.pfapp.ymjg.UI.entity;

import java.util.List;

/**
 * Created by zry on 17/4/2.
 */

public class OneTwoClassGoodData {


    /**
     * 获取一二级商品目录
     * data : [{"cid":"2","level":"1","name":"绿植","array":[{"cid":"6","level":"2","name":"树木盆景"},{"cid":"7","level":"2","name":"树木盆景"}]},{"cid":"1","level":"1","name":"鲜切花","array":[{"cid":"3","level":"2","name":"花束"},{"cid":"4","level":"2","name":"花篮"},{"cid":"5","level":"2","name":"花盒"}]}]
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
         * cid : 2
         * level : 1
         * name : 绿植
         * array : [{"cid":"6","level":"2","name":"树木盆景"},{"cid":"7","level":"2","name":"树木盆景"}]
         */

        private String cid;
        private String level;
        private String name;
        private List<ArrayBean> array;

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
             * cid : 6
             * level : 2
             * name : 树木盆景
             */

            private String cid;
            private String level;
            private String name;

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
        }
    }
}
