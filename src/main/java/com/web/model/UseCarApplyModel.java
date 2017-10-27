package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class UseCarApplyModel extends ApplyBaseModel {


    //申请人电话
    private String applicantPhone;


    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //乘车人员
    private String passengers;

    //选择车辆
    private String carType;

    //司机
    private String driver;

    //司机电话
    private String driverPhone;

    //上车地点
    private String address;

    //目的地
    private String destination;

    //车辆用途
    private String reason;


    //申请人
    private String applicant;


    //备注
    private String remark;

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public UseCarApplyModel(JSONObject object) {

        JSONArray dataArray = object.getJSONArray("data");

        if (dataArray.size() > 0) {
            JSONObject data = dataArray.getJSONObject(0);
            String attachment = data.getString("_ATTACHMENTS");
            if (StringUtils.isEmpty(attachment)) {
                setAttachment("无");
            } else {
                setAttachment(attachment);
            }

            this.orderNum = data.getString("_FORMNO");

            this.subject = data.getString("_SUBJECT");

            this.templateId = data.getInteger("_TEMPLATE_ID");

            this.applicant = data.getString("ydr");

            this.applicantPhone = data.getString("ydrphone");

            this.department = data.getString("ydbm");

            this.orderDate = data.getString("ydrq");

            this.startTime = data.getString("starttime");

            this.endTime = data.getString("endtime");

            this.address = data.getString("pickupaddress");

            this.passengers = data.getString("ccry");

            this.driver = data.getString("driver");

            this.driverPhone = data.getString("driverphone");

            this.destination = data.getString("destination");

            this.reason = data.getString("clyt");

            this.carType = data.getString("selectautomobile");

            this.remark = data.getString("remark");


        }

    }


}
