package com.web.model;

import com.alibaba.fastjson.JSONObject;
import com.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/9/18.
 */
public class NoticeListModel {

    private Integer id;

    private String actor;

    private String receiverName;

    private String processBy;

    private String action;

    private String content;

    private String processTime;

    private String processByName;

    private String noticeType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getProcessBy() {
        return processBy;
    }

    public void setProcessBy(String processBy) {
        this.processBy = processBy;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getProcessByName() {
        return processByName;
    }

    public void setProcessByName(String processByName) {
        this.processByName = processByName;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public NoticeListModel(JSONObject object) {
        this.id = object.getInteger("ID");

        this.action = object.getString("Action");

        this.actor = object.getString("Actor");

        this.receiverName = object.getString("ReceiverName");

        this.processBy = object.getString("ProcessBy");

        this.content = object.getString("Content");

        String dataStr=object.getString("ProcessTime");
        if(StringUtils.isEmpty(dataStr)){
            this.processTime=DateUtil.getCurrentTime("MM-dd HH:mm");
        }else{
            this.processTime=DateUtil.formatUTCDate(dataStr,"MM-dd HH:mm");
        }

        this.processByName=object.getString("ProcessByName");

        this.noticeType=object.getString("NoticeType");
    }
}
