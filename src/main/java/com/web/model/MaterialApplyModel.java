package com.web.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/24.
 */
public class MaterialApplyModel extends ApplyBaseModel {

    //审核人
    private String reviewer;

    //申请人
    private String applicant;

    //附注
    private String remark;

    private  List<MaterialDetailModel> detailList;

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<MaterialDetailModel> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<MaterialDetailModel> detailList) {
        this.detailList = detailList;
    }

    public MaterialApplyModel(JSONObject object) {

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

            this.department=data.getString("Bumen");

            String date = data.getString("Riqi");

            if (StringUtils.isEmpty(date)) {
                this.orderDate = "--";
            } else {
                this.orderDate = DateUtil.formatUTCDate(date, "yyyy-MM-dd HH:mm");
            }

            this.applicant=data.getString("Lyren");
            this.remark=data.getString("Fulu");
            this.reviewer=data.getString("Zhuguan");
            JSONArray subdatas = data.getJSONArray("subdatas");
            List<MaterialDetailModel> modelList = new ArrayList<>();
            for (int i = 0; i < subdatas.size(); i++) {
                JSONObject sbuData = subdatas.getJSONObject(i);
                MaterialDetailModel model = new MaterialDetailModel(sbuData);
                modelList.add(model);
            }
            this.detailList = modelList;
        }

    }
}
