package com.lihonghao.app.data.entity;

import java.util.ArrayList;
import java.util.List;

public class AtsEntity {


    /**
     * status : 1
     */

    private ResultEntity result;
    /**
     * auto_series_id : 2468
     * auto_id : 277
     * auto_brand_id : 192
     * auto_name : 奥德赛
     * thumb : http://image.hxchehui.com/upfiles/auto/thumb/a652e9c00f3911e5b9f9fcaa14c1223d.jpg
     * auto_series_name : 2.4L 至尊版
     * year : 2015
     * sale : 1
     * price : 29.98
     * ats_price : 28
     * displacement : 2.40
     * auto_type : 0
     * engine : CVT无级变速
     * gearbox : 4830*1805*1695
     * intake : L
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
        private String auto_series_id;
        private String auto_id;
        private String auto_brand_id;
        private String auto_name;
        private String thumb;
        private String auto_series_name;
        private String year;
        private String sale;
        private String price;
        private String ats_price;
        private String displacement;
        private String auto_type;
        private String engine;
        private String gearbox;
        private String intake;

        public String getAuto_series_id() {
            return auto_series_id;
        }

        public void setAuto_series_id(String auto_series_id) {
            this.auto_series_id = auto_series_id;
        }

        public String getAuto_id() {
            return auto_id;
        }

        public void setAuto_id(String auto_id) {
            this.auto_id = auto_id;
        }

        public String getAuto_brand_id() {
            return auto_brand_id;
        }

        public void setAuto_brand_id(String auto_brand_id) {
            this.auto_brand_id = auto_brand_id;
        }

        public String getAuto_name() {
            return auto_name;
        }

        public void setAuto_name(String auto_name) {
            this.auto_name = auto_name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getAuto_series_name() {
            return auto_series_name;
        }

        public void setAuto_series_name(String auto_series_name) {
            this.auto_series_name = auto_series_name;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAts_price() {
            return ats_price;
        }

        public void setAts_price(String ats_price) {
            this.ats_price = ats_price;
        }

        public String getDisplacement() {
            return displacement;
        }

        public void setDisplacement(String displacement) {
            this.displacement = displacement;
        }

        public String getAuto_type() {
            return auto_type;
        }

        public void setAuto_type(String auto_type) {
            this.auto_type = auto_type;
        }

        public String getEngine() {
            return engine;
        }

        public void setEngine(String engine) {
            this.engine = engine;
        }

        public String getGearbox() {
            return gearbox;
        }

        public void setGearbox(String gearbox) {
            this.gearbox = gearbox;
        }

        public String getIntake() {
            return intake;
        }

        public void setIntake(String intake) {
            this.intake = intake;
        }
    }
}
