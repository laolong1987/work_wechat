package com.web.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by admin on 2017/9/20.
 */
public class MaterialDetailModel {

    //物资编码
    private String materialCode;

    //物资类型
    private String materialType;

    //物资名称
    private String materialName;

    //单位
    private String unit;

    //物资数量
    private Integer materialNum;

    //费用
    private Integer fee;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(Integer materialNum) {
        this.materialNum = materialNum;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public MaterialDetailModel(JSONObject object) {
        this.materialCode = object.getString("Wzbm");
        this.materialType = object.getString("Wzlx");
        this.materialName = object.getString("Wzmc");
        this.unit = object.getString("Danwei");
        this.fee = object.getInteger("Jine");
        this.materialNum=object.getInteger("Shull");

    }
}
