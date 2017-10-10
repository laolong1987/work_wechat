package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class ReceiveDocModel extends ApplyBaseModel {

    //字
    private String  no;

    //第几号
    private String noYear;

    //印发日期
    private String printDate;

    //主办部门
    private String hostDept;

//    private String

    //附件
    private String attach;


    //事由
    private String reason;

    //用餐时间
    private String repastTime;


    //用餐日期
    private String repastDate;

    //用餐种类
    private String repastType;

    //申请人
    private String applicant;

    //用餐标准
    private String standard;

    //用餐地点
    private String address;

    //用餐数量
    private Integer foodNumber;

    //备注
    private String remark;


    //实际用餐标准
    private Integer actuallyStandard;

    //实际用餐数量
    private String actuallyFoodNumber;


    //共消费金额
    private Integer totalConsumption;

    //部门审核
    private String approachResult;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRepastTime() {
        return repastTime;
    }

    public void setRepastTime(String repastTime) {
        this.repastTime = repastTime;
    }

    public String getRepastDate() {
        return repastDate;
    }

    public void setRepastDate(String repastDate) {
        this.repastDate = repastDate;
    }

    public String getRepastType() {
        return repastType;
    }

    public void setRepastType(String repastType) {
        this.repastType = repastType;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(Integer foodNumber) {
        this.foodNumber = foodNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getActuallyStandard() {
        return actuallyStandard;
    }

    public void setActuallyStandard(Integer actuallyStandard) {
        this.actuallyStandard = actuallyStandard;
    }

    public String getActuallyFoodNumber() {
        return actuallyFoodNumber;
    }

    public void setActuallyFoodNumber(String actuallyFoodNumber) {
        this.actuallyFoodNumber = actuallyFoodNumber;
    }

    public Integer getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(Integer totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public String getApproachResult() {
        return approachResult;
    }

    public void setApproachResult(String approachResult) {
        this.approachResult = approachResult;
    }

    public ReceiveDocModel(JSONObject object) {

        JSONArray dataArray = object.getJSONArray("data");

        if (dataArray.size() > 0) {
            JSONObject data = dataArray.getJSONObject(0);
            this.attachment = data.getString("_ATTACHMENTS");
            if (StringUtils.isEmpty(attachment)) {
                this.attachment = "无";
            }

            this.orderNum = data.getString("_FORMNO");

            this.subject = data.getString("_SUBJECT");

            this.templateId = data.getInteger("_TEMPLATE_ID");

            this.applicant=data.getString("sqr");

            this.department = data.getString("sqbm");

            this.reason = data.getString("lfdwjry");

            this.repastType = data.getString("yctype");

            this.repastTime = data.getString("yczl");

            this.repastDate = data.getString("ycrq");

            this.standard = data.getString("ycbz");

            this.address = data.getString("ycdd");

            this.foodNumber =data.getInteger("ycsl");

            this.remark=data.getString("ycremark");

            this.actuallyStandard=data.getInteger("sjycbz");

            this.actuallyFoodNumber =data.getString("sjycsl");

            this.totalConsumption =data.getInteger("xfje");
            this.approachResult=data.getString("bmsh");

        }

    }


}
