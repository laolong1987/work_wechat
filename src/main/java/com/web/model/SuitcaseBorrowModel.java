package com.web.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class SuitcaseBorrowModel extends ApplyBaseModel {


    //借阅事由
    private String reason;

    //归还日期
    private String returnDate;

    //借阅天数
    private Integer totalDay;

    //申请人
    private String applicant;

    //部门审核
    private String departmentApproachResult;

    //部门审核
    private String approachResult;

    //部门领导审核签证
    private List<SuitcaseDetailModel> detailList;

    public Integer getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Integer totalDay) {
        this.totalDay = totalDay;
    }

    public String getDepartmentApproachResult() {
        return departmentApproachResult;
    }

    public void setDepartmentApproachResult(String departmentApproachResult) {
        this.departmentApproachResult = departmentApproachResult;
    }

    public String getApproachResult() {
        return approachResult;
    }

    public void setApproachResult(String approachResult) {
        this.approachResult = approachResult;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }


    public List<SuitcaseDetailModel> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<SuitcaseDetailModel> detailList) {
        this.detailList = detailList;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public SuitcaseBorrowModel(JSONObject object) {

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

            this.reason = data.getString("Shiyou");


            this.department = data.getString("Bumen");

            String orderDateStr = data.getString("Yjrq");

            if (StringUtils.isEmpty(orderDateStr)) {
                this.orderDate = "--";
            } else {
                this.orderDate = DateUtil.formatUTCDate(orderDateStr, "yyyy-MM-dd");
            }
            String returnDateStr = data.getString("Ghrqz");
            if (StringUtils.isEmpty(returnDateStr)) {
                this.returnDate = "--";
            } else {
                this.returnDate = DateUtil.formatUTCDate(returnDateStr, "yyyy-MM-dd");
            }

            this.applicant = data.getString("Xming");

            this.approachResult = data.getString("Bmldsh");
            this.approachResult = data.getString("Zjbsp");
            this.totalDay = data.getInteger("Yjts");

            JSONArray subdatas = data.getJSONArray("subdatas");
            List<SuitcaseDetailModel> modelList = new ArrayList<>();
            for (int i = 0; i < subdatas.size(); i++) {
                JSONObject sbuData = subdatas.getJSONObject(i);
                SuitcaseDetailModel model = new SuitcaseDetailModel(sbuData);
                modelList.add(model);
            }
            this.detailList = modelList;
        }

    }


}
