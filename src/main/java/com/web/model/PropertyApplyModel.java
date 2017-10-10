package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.StringUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class PropertyApplyModel extends ApplyBaseModel {


    //申请人
    private String applicant;

    //证件类型
    private String type;


    //主管意见
    private String managerOpinion;

    //信息中心意见
    private String ICOpinion;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManagerOpinion() {
        return managerOpinion;
    }

    public void setManagerOpinion(String managerOpinion) {
        this.managerOpinion = managerOpinion;
    }

    public String getICOpinion() {
        return ICOpinion;
    }

    public void setICOpinion(String ICOpinion) {
        this.ICOpinion = ICOpinion;
    }

    public PropertyApplyModel(JSONObject object) {

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

            this.applicant = data.getString("sqr");


            this.department = data.getString("sqbm");

            this.orderDate = data.getString("sqrq");

            this.type=data.getString("zclx");

            this.managerOpinion=data.getString("HOption");

            this.ICOpinion =data.getString("ICOption");


        }

    }


}
