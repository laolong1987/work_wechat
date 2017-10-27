package com.web.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class ApplyBaseModel {

    //单号
    protected String orderNum;

    //主题
    protected String subject;

    //模板Id
    protected Integer templateId;

    //申请日期
    protected String applyDate;

    //附件
    protected String attachment;

    //部门
    protected String department;

    //制单日期(申请日期)
    protected String orderDate;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        if (StringUtils.isEmpty(attachment)) {
            this.attachment = "无";

        } else {
            String rgex = "\\(&puot;(.*?)&puot;\\)";
            Pattern pattern = Pattern.compile(rgex);// 匹配的模式
            Matcher m = pattern.matcher(attachment);
            String match = "";
            while (m.find()) {
                String matchRes = m.group(1);
                String name = matchRes.substring(matchRes.lastIndexOf("/") + 1);
                String href = "<a href='http://d.bm21.com.cn:20003" + matchRes + "' >" + name + "</a>";
                match = match + href;
            }
            this.attachment = match;
        }

    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
