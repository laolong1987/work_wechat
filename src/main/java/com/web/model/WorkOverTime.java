package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/16.
 */
public class WorkOverTime extends ApplyBaseModel {


    //预计工作时间
    private String estimatedTime;

    //工作内容
    private String content;

    //加班人员
    private String staff;


    //制单人员
    private String operator;

    //部门主任
    private String divisionChief;

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDivisionChief() {
        return divisionChief;
    }

    public void setDivisionChief(String divisionChief) {
        this.divisionChief = divisionChief;
    }


    public WorkOverTime() {

    }

    public WorkOverTime(JSONObject object) {
        JSONArray dataArray = object.getJSONArray("data");

        if (dataArray.size() > 0) {
            JSONObject data = dataArray.getJSONObject(0);

            this.applyDate = DateUtil.formatUTCDate(data.getString("JiabanRQ"), "yyyy-MM-dd HH:mm:ss");
            String attachment = data.getString("_ATTACHMENTS");
            if (StringUtils.isEmpty(attachment)) {
                setAttachment("无");
            } else {
                setAttachment(attachment);
            }

            this.content = data.getString("Gznr");

            this.divisionChief = data.getString("Bmzr");

            this.estimatedTime = DateUtil.formatUTCDate(data.getString("YjGzsj"), "yyyy-MM-dd HH:mm:ss");

            this.operator = data.getString("TDr");

            this.orderDate = DateUtil.formatUTCDate(data.getString("TDrq"), "yyyy-MM-dd HH:mm:ss");

            this.orderNum = data.getString("_FORMNO");

            this.staff = data.getString("JbRyxm");

            this.subject = data.getString("_SUBJECT");

            this.templateId = data.getInteger("_TEMPLATE_ID");

            this.department = data.getString("TDbm");


        }

    }


}
