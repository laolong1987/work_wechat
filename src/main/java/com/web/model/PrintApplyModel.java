package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class PrintApplyModel extends ApplyBaseModel {

    //申请人
    private String applicant;

    //交货时间
    private String finishDate;

    //供应商
    private String supplier;

    //印刷内容
    private String printContent;

    //印刷内容
    private String finishedContent;

    //核定价格
    private Integer price;

    //部门审核
    private String departmentApproach;

    //总经办意见
    private String approachResult;

    //备注
    private String remark;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPrintContent() {
        return printContent;
    }

    public void setPrintContent(String printContent) {
        this.printContent = printContent;
    }

    public String getFinishedContent() {
        return finishedContent;
    }

    public void setFinishedContent(String finishedContent) {
        this.finishedContent = finishedContent;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDepartmentApproach() {
        return departmentApproach;
    }

    public void setDepartmentApproach(String departmentApproach) {
        this.departmentApproach = departmentApproach;
    }

    public String getApproachResult() {
        return approachResult;
    }

    public void setApproachResult(String approachResult) {
        this.approachResult = approachResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public PrintApplyModel(JSONObject object) {

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


            this.applicant = data.getString("Tbren");


            String finishedDateStr = data.getString("jhrq");

            if (StringUtils.isEmpty(finishedDateStr)) {
                this.finishDate = "--";
            } else {
                this.finishDate = DateUtil.formatUTCDate(finishedDateStr, "yyyy-MM-dd");
            }
            this.supplier=data.getString("Zhuanye");

            this.printContent=data.getString("YSNR");

            this.departmentApproach=data.getString("Aqyyj");

            this.approachResult =data.getString("Bmzryj");

            this.department = data.getString("Sbbm");
            this.finishedContent=data.getString("JHRN");

            this.price=data.getInteger("HDJG");

        }

    }


}
