package com.web.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class BookBorrowModel extends ApplyBaseModel {


    //借阅事由
    private String reason;

    //归还日期
    private String returnDate;

    //借阅类型
    String borrowType;

    //申请人
    String applicant;

    //明细列表
    List<BookBorrowDetailModel> detailList;

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

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }

    public List<BookBorrowDetailModel> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<BookBorrowDetailModel> detailList) {
        this.detailList = detailList;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public BookBorrowModel(JSONObject object) {

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

            this.reason = data.getString("jysy");

            this.returnDate = data.getString("ghrq");

            this.borrowType = data.getString("jylx");

            this.department = data.getString("tdbm");

            this.orderDate = data.getString("sqrq");

            this.applicant=data.getString("jlr");

            JSONArray subdatas = data.getJSONArray("subdatas");
            List<BookBorrowDetailModel> modelList =new ArrayList<>();
            for (int i = 0; i < subdatas.size(); i++) {
                JSONObject sbuData = subdatas.getJSONObject(i);
                BookBorrowDetailModel model = new BookBorrowDetailModel(sbuData);
                modelList.add(model);
            }
            this.detailList=modelList;
        }

    }


}
