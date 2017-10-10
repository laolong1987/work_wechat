package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.StringUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class CredentialsApplyModel extends ApplyBaseModel {


    private String id;

    //申请人
    private String applicant;

    //证件类型
    private String cardType;

    //补办原因
    private String reason;

    //部门审核
    private String approachResult;

    //签收
    private String receipted;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApproachResult() {
        return approachResult;
    }

    public void setApproachResult(String approachResult) {
        this.approachResult = approachResult;
    }

    public String getReceipted() {
        return receipted;
    }

    public void setReceipted(String receipted) {
        this.receipted = receipted;
    }

    public CredentialsApplyModel(JSONObject object) {

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

            this.applicant = data.getString("name");

            this.id=data.getString("gh");

            this.department = data.getString("szbm");

            if (!StringUtil.isStrEmpty(data.getString("zjlx1"))) {
                this.cardType = data.getString("zjlx1");
            } else if (!StringUtil.isStrEmpty(data.getString("zjlx2"))) {
                this.cardType = data.getString("zjlx2");
            } else {
                this.cardType = "未知";
            }

            this.reason = data.getString("bbyy");


            this.approachResult = data.getString("bmyj");

            this.receipted = data.getString("blqs");

        }

    }


}
