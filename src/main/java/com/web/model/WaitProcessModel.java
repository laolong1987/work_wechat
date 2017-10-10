package com.web.model;

import com.alibaba.fastjson.JSONObject;
import com.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class WaitProcessModel {

    private String  orderNum;

    private String templateType;

    private String writer;

    private String writerName;

    private String writeDate;

    private String sendBy;

    private String sendByName;

    private String receiverName;

    private String sendTime;

    private String subject;

    private Integer templateId;

    private Integer dataId;

    private String status;

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public String getSendByName() {
        return sendByName;
    }

    public void setSendByName(String sendByName) {
        this.sendByName = sendByName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
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

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public WaitProcessModel(JSONObject object) {

        this.dataId = object.getInteger("dataid");
        this.templateType = object.getString("templatetype");
        this.writer = object.getString("writer");
        this.writerName = object.getString("writername");
        String writeDateStr = object.getString("writedate");
        ;
        if (StringUtils.isEmpty(writeDateStr)) {
            this.writeDate = "--";
        } else {
            this.writeDate = DateUtil.formatUTCDate(writeDateStr, "MM-dd HH:mm");
        }

        String sendTimeStr =  object.getString("sendtime");
        if (StringUtils.isEmpty(writeDateStr)) {
            this.sendTime = "--";
        } else {
            this.sendTime = DateUtil.formatUTCDate(sendTime, "MM-dd HH:mm");
        }


        this.sendBy = object.getString("sendby");
        this.sendByName = object.getString("sendbyname");
        this.receiverName = object.getString("receivername");

        this.subject = object.getString("subject");
        this.templateId = object.getInteger("templateid");

        this.orderNum=object.getString("_FORMNO");

    }
}
