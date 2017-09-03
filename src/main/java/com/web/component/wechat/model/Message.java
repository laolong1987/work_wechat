package com.web.component.wechat.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by admin on 2017/8/23.
 */
public class Message {

    @ApiModelProperty(value = "成员ID列表", required = false)
    protected List<String> touser;

    @ApiModelProperty(value = "部门ID列表", required = false)
    protected List<String> toparty;

    @ApiModelProperty(value = "部门ID列表", required = false)
    protected List<String> totag;

    @ApiModelProperty(value = "消息类型", required = true)
    protected String msgtype;

    @ApiModelProperty(value = "消息类型", required = true)
    protected int agentid;

    @ApiModelProperty(value = "消息类型", required = true)
    protected int safe=0;

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public List<String> getToparty() {
        return toparty;
    }

    public void setToparty(List<String> toparty) {
        this.toparty = toparty;
    }

    public List<String> getTotag() {
        return totag;
    }

    public void setTotag(List<String> totag) {
        this.totag = totag;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public int getAgentid() {
        return agentid;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public int getSafe() {
        return safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }
}
