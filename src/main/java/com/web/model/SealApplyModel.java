package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class SealApplyModel extends ApplyBaseModel {

    //申请人
    private String applicant;

    //用印类别
    private String useType;

    //印章名称
    private String sealName;

    //管理员
    private String keeper;

    //分管领导
    private String leader;

    //用印事项(目的)
    private String purpose;

    //用印情况
    private String circs;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getSealName() {
        return sealName;
    }

    public void setSealName(String sealName) {
        this.sealName = sealName;
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCircs() {
        return circs;
    }

    public void setCircs(String circs) {
        this.circs = circs;
    }

    public SealApplyModel(JSONObject object) {

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

            this.applicant = data.getString("applyer");

            this.keeper = data.getString("sealmanager");

            this.sealName = data.getString("sealname");

            this.circs = data.getString("Yyqk");

            this.leader = data.getString("chargeleader");

            this.useType = data.getString("Yylb");

            this.purpose = data.getString("Yysx");

            this.department = data.getString("depart");

            this.applyDate = data.getString("applydate");


        }

    }


}
