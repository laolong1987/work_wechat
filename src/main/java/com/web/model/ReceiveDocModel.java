package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class ReceiveDocModel extends ApplyBaseModel {

    //收文编号
    private String receiveNum;

    //沪外电二司
    private String SWBMJC;

    //收字第几号
    private String no;

    //收到日期
    private String receivedDate;


    //来文机关
    private String sentDept;

    //来文字号
    private String zihao;

    //来文第几号
    private String zihaoSize;

    //来文日期
    private String sentDate;

    //附件
    private String attach;

    //事由
    private String reason;


    //主办人员
    private String sponsor;

    //阅处人员
    private String reviewPeople;

    //拟办和批示
    private String instructions;

    //待阅人员
    private String waitReaders;

    //审阅完成日期
    private String finishDate;


    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getSWBMJC() {
        return SWBMJC;
    }

    public void setSWBMJC(String SWBMJC) {
        this.SWBMJC = SWBMJC;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getSentDept() {
        return sentDept;
    }

    public void setSentDept(String sentDept) {
        this.sentDept = sentDept;
    }

    public String getZihao() {
        return zihao;
    }

    public void setZihao(String zihao) {
        this.zihao = zihao;
    }

    public String getZihaoSize() {
        return zihaoSize;
    }

    public void setZihaoSize(String zihaoSize) {
        this.zihaoSize = zihaoSize;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getReviewPeople() {
        return reviewPeople;
    }

    public void setReviewPeople(String reviewPeople) {
        this.reviewPeople = reviewPeople;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getWaitReaders() {
        return waitReaders;
    }

    public void setWaitReaders(String waitReaders) {
        this.waitReaders = waitReaders;
    }

    public ReceiveDocModel(JSONObject object) {

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

            this.receiveNum = data.getString("Swbh");

            this.SWBMJC = data.getString("SWBMJC");

            this.no = data.getString("no");

            this.receivedDate = data.getString("gsswdate");

            this.sentDept = data.getString("LWJG");

            this.zihao = data.getString("Zihao");

            this.zihaoSize = data.getString("ZihaoSize");

            this.sentDate = data.getString("LWRQ");

            this.attach = data.getString("attach");

            this.reason = data.getString("reason");

            this.sponsor = data.getString("ZBRY");

            this.reviewPeople = data.getString("YCRY");

            this.instructions = data.getString("pibanyijian");

            this.waitReaders = data.getString("XZDYRY");

            this.finishDate = data.getString("datetime");

        }

    }


}
