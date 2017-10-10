package com.web.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by admin on 2017/9/24.
 */
public class SuitcaseDetailModel {

    //旅行箱ID
    private String suitcaseId;

    //品牌
    private String brand;

    //规格
    private String size;
    //  单位
    private String unit;

    //数量
    private Integer borrowNum;



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(Integer borrowNum) {
        this.borrowNum = borrowNum;
    }

    public SuitcaseDetailModel(JSONObject object){

        this.brand = object.getString("Pinp");

        this.borrowNum=object.getInteger("Shull");

        this.unit=object.getString("Danwei");

        this.size=object.getString("Guige");

    }
}
