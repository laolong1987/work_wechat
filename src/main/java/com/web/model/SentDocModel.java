package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class SentDocModel extends ApplyBaseModel {

    //沪 外
    private String dn;

    //单位名称
    private String department;

    //二司
    private String sntype;

    //字
    private String noyear;

    //第几号
    private String no;

    //主办部门
    private String sponsor;

    //会签
    private String hqbm;

    //会签人员
    private String hqbmid;


    //核稿
    private String audit;

    //拟稿人
    private String writer;


    //签发
    private String approval;


    //事由
    private String reason;

    //主送单位
    private String mainSender;

    //内部抄送
    private String departmentAudit;

    //抄送单位
    private String ccername2;
    //抄报单位
        private String ccername1;


    //缮印
    private String kid;

    //会签意见
    private String reviewer;


    //校对
    private String checkperson;

    //份
    private String fen;


    //印发日期
    private String sentDate;


    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSntype() {
        return sntype;
    }

    public void setSntype(String sntype) {
        this.sntype = sntype;
    }

    public String getNoyear() {
        return noyear;
    }

    public void setNoyear(String noyear) {
        this.noyear = noyear;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getHqbm() {
        return hqbm;
    }

    public void setHqbm(String hqbm) {
        this.hqbm = hqbm;
    }

    public String getHqbmid() {
        return hqbmid;
    }

    public void setHqbmid(String hqbmid) {
        this.hqbmid = hqbmid;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMainSender() {
        return mainSender;
    }

    public void setMainSender(String mainSender) {
        this.mainSender = mainSender;
    }

    public String getDepartmentAudit() {
        return departmentAudit;
    }

    public void setDepartmentAudit(String departmentAudit) {
        this.departmentAudit = departmentAudit;
    }

    public String getCcername2() {
        return ccername2;
    }

    public void setCcername2(String ccername2) {
        this.ccername2 = ccername2;
    }

    public String getCcername1() {
        return ccername1;
    }

    public void setCcername1(String ccername1) {
        this.ccername1 = ccername1;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getCheckperson() {
        return checkperson;
    }

    public void setCheckperson(String checkperson) {
        this.checkperson = checkperson;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public SentDocModel(JSONObject object) {

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

            this.dn = data.getString("dn");

            this.department = data.getString("FWBM");

            this.applyDate = data.getString("gsfwdate");

            this.sentDate = data.getString("YZdatetime");

            this.sntype = data.getString("sntype");

            this.no = data.getString("no");

            this.noyear = data.getString("noyear");

            this.sponsor= data.getString("hostdept");

            this.hqbm = data.getString("hqbm");

            this.audit = data.getString("audit");

            this.writer=data.getString("writer");

            this.approval=data.getString("approval");

            this.reason=data.getString("reason");

            this.mainSender=data.getString("mainsender");

            this.departmentAudit=data.getString("departmentaudit");

            this.ccername1=data.getString("ccername1");

            this.ccername2=data.getString("ccername2");

            this.reviewer =data.getString("reviewer");

            this.kid=data.getString("kid");

            this.checkperson=data.getString("checkperson");

            this.fen=data.getString("fen");


        }

    }


}
