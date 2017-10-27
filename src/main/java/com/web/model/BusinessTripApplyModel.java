package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class BusinessTripApplyModel extends ApplyBaseModel {

    //申请人
    private String applicant;

    //预计开始日期
    private String startTime;

    //天数
    private String days;

    //人数
    private String peopleNum;

    //领队
    private String leader;

    //领队用户名
    private String leaderUserName;

    //理由
    private String reason;

    //购票类型
    private String buyTicketType;

    //住宿类型
    private String hotelType;

    //到达地点数量
    private String arriveAddressNum;

    //部门领导审核签证
    private String deptApproach;

    //总经理审批意见
    private String GMApproach;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeaderUserName() {
        return leaderUserName;
    }

    public void setLeaderUserName(String leaderUserName) {
        this.leaderUserName = leaderUserName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBuyTicketType() {
        return buyTicketType;
    }

    public void setBuyTicketType(String buyTicketType) {
        this.buyTicketType = buyTicketType;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getArriveAddressNum() {
        return arriveAddressNum;
    }

    public void setArriveAddressNum(String arriveAddressNum) {
        this.arriveAddressNum = arriveAddressNum;
    }

    public String getDeptApproach() {
        return deptApproach;
    }

    public void setDeptApproach(String deptApproach) {
        this.deptApproach = deptApproach;
    }

    public String getGMApproach() {
        return GMApproach;
    }

    public void setGMApproach(String GMApproach) {
        this.GMApproach = GMApproach;
    }

    public BusinessTripApplyModel(JSONObject object) {

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

            this.department = data.getString("Bumen");

            String startTimeStr = data.getString("Gcrq");

            if (StringUtils.isEmpty(startTimeStr)) {
                this.startTime = "--";
            } else {
                this.startTime = DateUtil.formatUTCDate(startTimeStr, "yyyy-MM-dd");
            }

            this.days = data.getString("Gcts");

            this.applicant = data.getString("Gcry");

            this.peopleNum = data.getString("Gcrs");

            this.leader = data.getString("Gcldxm");

            this.leaderUserName = data.getString("Gcldxmid");

            this.reason = data.getString("Gcsy");

            this.buyTicketType = data.getString("Gplx");

            this.hotelType = data.getString("Zslx");

            this.arriveAddressNum = data.getString("Gcdwdd");

            this.deptApproach = data.getString("Bmldshqz");

            this.GMApproach = data.getString("Gsldshqz");


        }

    }


}
