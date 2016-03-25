package com.lihonghao.app.data.entity;

import java.util.ArrayList;
import java.util.List;

public class AdsEntity {


    /**
     * status : 1
     */

    private ResultEntity result;
    /**
     * id : 3
     * image : http://image.hxchehui.com/upload/adert/thumb/12cfb13ff5b4a1e22245ff675f8c317b.jpg
     * module : news
     * type : content
     * key : 15
     */

    private ArrayList<DataEntity> data;

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public ArrayList<DataEntity> getData() {
        return data;
    }

    public void setData(ArrayList<DataEntity> data) {
        this.data = data;
    }

    public static class ResultEntity {
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class DataEntity {
        private String id;
        private String image;
        private String module;
        private String type;
        private String key;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
