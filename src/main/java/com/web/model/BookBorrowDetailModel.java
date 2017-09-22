package com.web.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/20.
 */
public class BookBorrowDetailModel {


    //序号
    private String number;

    //文件名称
    private String filename;

    //档案编号
    private String fileNum;

    //备注
    private String remark;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BookBorrowDetailModel(JSONObject object){
        this.filename=object.getString("filename");
        this.fileNum=object.getString("Dabh");
        this.number=object.getString("Xh");
        this.remark=object.getString("Bz");
    }
}
