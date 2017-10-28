package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class LeaveApplyModel extends ApplyBaseModel {

    //编号
    private String code;


    //来访人员／事由
    private String reason;


    //请假开始时间
    private String leaveDateStart;

    //请假开始时间
    private String leaveDateEnd;

    //请假类型
    private String leaveType;

    //申请人
    private String applicant;

    //请假天数
    private Integer totalDay;

    //班组意见
    private String groupApproach;


    //部门审核
    private String departmentApproach;

    //人力资源部门审核
    private String humanResourceApproach;

    //公司审核
    private String firmApproach;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeaveDateStart() {
        return leaveDateStart;
    }

    public void setLeaveDateStart(String leaveDateStart) {
        this.leaveDateStart = leaveDateStart;
    }

    public String getLeaveDateEnd() {
        return leaveDateEnd;
    }

    public void setLeaveDateEnd(String leaveDateEnd) {
        this.leaveDateEnd = leaveDateEnd;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Integer getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Integer totalDay) {
        this.totalDay = totalDay;
    }

    public String getGroupApproach() {
        return groupApproach;
    }

    public void setGroupApproach(String groupApproach) {
        this.groupApproach = groupApproach;
    }

    public String getDepartmentApproach() {
        return departmentApproach;
    }

    public void setDepartmentApproach(String departmentApproach) {
        this.departmentApproach = departmentApproach;
    }

    public String getHumanResourceApproach() {
        return humanResourceApproach;
    }

    public void setHumanResourceApproach(String humanResourceApproach) {
        this.humanResourceApproach = humanResourceApproach;
    }

    public String getFirmApproach() {
        return firmApproach;
    }

    public void setFirmApproach(String firmApproach) {
        this.firmApproach = firmApproach;
    }

    public LeaveApplyModel(JSONObject object) {

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

            this.code = data.getString("Qjbh");


            this.applicant = data.getString("Ygxm");

            this.department = data.getString("Depart");

            this.reason = data.getString("Qjly");

            this.leaveType = data.getString("QjType");

            String leaveDateStartStr = data.getString("QjStart");

            String leaveDateEndStr = data.getString("QjEnd");


            if (StringUtils.isEmpty(leaveDateStartStr)) {
                this.leaveDateStart = "--";
            } else {
                this.leaveDateStart =leaveDateStartStr.substring(0,10);
            }

            if (StringUtils.isEmpty(leaveDateEndStr)) {
                this.leaveDateEnd = "--";
            } else {
                this.leaveDateEnd = leaveDateStartStr.substring(0,10);
            }

            this.totalDay = data.getInteger("QjDays");


            this.groupApproach=data.getString("BanzuYJ");

            this.departmentApproach=data.getString("DepartYJ");

            this.humanResourceApproach=data.getString("RzbYJ");

            this.firmApproach=data.getString("GsYJ");

        }

    }


}
