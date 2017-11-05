package com.web.component.wechat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/10/10.
 */
@Component
@PropertySource(value = {"classpath:wechat-api.properties"})
public class WechatConfig {

    @Value("${corpID}")
    private String corpID;

    @Value("${corpSecret}")
    private String corpSecret;

    @Value("${approvalAgentId}")
    private String approvalAgentId;

    @Value("${newsAgentId}")
    private String newsAgentId;

    @Value("${messageApi}")
    private String messageApi;

    @Value("${authUrl}")
    private String authUrl;

    @Value("${getAuthUserInfoUrl}")
    private String getAuthUserInfoUrl;

    @Value("${createUser}")
    private String createUser;

    @Value("${contactsSecret}")
    private String contactsSecret;

    @Value("${approvalSecret}")
    private String approvalSecret;

    @Value("${createDepartment}")
    private String createDepartment;

    @Value("${secret}")
    private String secret;


    public String getCorpID() {
        return corpID;
    }

    public void setCorpID(String corpID) {
        this.corpID = corpID;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    public String getApprovalAgentId() {
        return approvalAgentId;
    }

    public void setApprovalAgentId(String approvalAgentId) {
        this.approvalAgentId = approvalAgentId;
    }

    public String getNewsAgentId() {
        return newsAgentId;
    }

    public void setNewsAgentId(String newsAgentId) {
        this.newsAgentId = newsAgentId;
    }

    public String getMessageApi() {
        return messageApi;
    }

    public void setMessageApi(String messageApi) {
        this.messageApi = messageApi;
    }

    public String getAuthUrl(String redirectUri, String agentId, String state) {
        String realUrl = authUrl;
        if (StringUtils.isEmpty(state))
            state = "STATE";
        realUrl = realUrl.replace("{REDIRECT_URI}", redirectUri).replace("{AGENTID}", agentId).replace("{STATE}", state);
        return realUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }


    public String getGetAuthUserInfoUrl(String accessToken, String code) {
        String realUrl = getAuthUserInfoUrl;
        realUrl = realUrl.replace("{ACCESS_TOKEN}", accessToken).replace("{CODE}", code);
        return realUrl;
    }

    public void setGetAuthUserInfoUrl(String getAuthUserInfoUrl) {
        this.getAuthUserInfoUrl = getAuthUserInfoUrl;
    }


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public String getCreateDepartment() {
        return createDepartment;
    }

    public void setCreateDepartment(String createDepartment) {
        this.createDepartment = createDepartment;
    }

    public String getContactsSecret() {
        return contactsSecret;
    }

    public void setContactsSecret(String contactsSecret) {
        this.contactsSecret = contactsSecret;
    }

    public String getApprovalSecret() {
        return approvalSecret;
    }

    public void setApprovalSecret(String approvalSecret) {
        this.approvalSecret = approvalSecret;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public String getGetAuthUserInfoUrl() {
        return getAuthUserInfoUrl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
