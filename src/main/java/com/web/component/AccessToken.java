package com.web.component;

import java.io.*;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessToken {

    private volatile static AccessToken uniqueInstance;

    private String corpid;
    private String corpSecret;
    private long createtime = 0;
    private String token;
    private Map<String, JSONObject> tokens;
    private Map<String, String> agentIdAndSecret;

    private static final Logger logger = LoggerFactory.getLogger(AccessToken.class);

    private static final Properties wechatApiConfig;


    static {
        wechatApiConfig = new Properties();
        try (InputStream in = AccessToken.class.getClassLoader().getResourceAsStream("wechat-api.properties")) {
            wechatApiConfig.load(in);
        } catch (IOException ioe) {
            logger.warn("Failed to load error configuration file", ioe);
        }
    }


    private AccessToken() {
        corpid =wechatApiConfig.getProperty("corpID");
        createtime = new Date().getTime() / 1000;
        tokens = new TreeMap<>();
        agentIdAndSecret = new HashMap<>();
        agentIdAndSecret.put(wechatApiConfig.getProperty("newsAgentId"), wechatApiConfig.getProperty("newsSecret") );
        agentIdAndSecret.put(wechatApiConfig.getProperty("approvalAgentId"), wechatApiConfig.getProperty("approvalSecret"));
        agentIdAndSecret.put(wechatApiConfig.getProperty("repastApplyUserId"),wechatApiConfig.getProperty("repastApplySecret"));
        agentIdAndSecret.put(wechatApiConfig.getProperty("leaveApplyUserId"),wechatApiConfig.getProperty("leaveApplySecret"));
        agentIdAndSecret.put(wechatApiConfig.getProperty("carApplyUserId"),wechatApiConfig.getProperty("carApplySecret"));
    }

    public static AccessToken getInstance() {
        if (uniqueInstance == null) {
            synchronized (AccessToken.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new AccessToken();
                    uniqueInstance.generateToken();
                }
            }
        }
        return uniqueInstance;
    }


    public String getUpToken() {
        Date date = new Date();
        long now = date.getTime() / 1000;
        if (now - createtime > 3000) {
            this.generateToken();
        }
        return token;
    }


    public String getUpToken(String agentId) {

        Date date = new Date();
        long now = date.getTime() / 1000;
        if (!tokens.containsKey(agentId) || (now - tokens.get(agentId).getLong("createTime") > 3000)) {
                this.generateToken(agentId);
        }

        return tokens.get(agentId).getString("token");
    }

    synchronized private void generateToken(String agentId) {
        Date date = new Date();
        long now = date.getTime() / 1000;
        if (!tokens.containsKey(agentId) || (now - tokens.get(agentId).getLong("createTime") > 3000)) {

            String accesstokenURL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + this.corpid + "&corpsecret=" + agentIdAndSecret.get(agentId);
            String accessTokenInfo = HttpUtil.getUrl(accesstokenURL);
            try {
                accessTokenInfo = new String(accessTokenInfo.getBytes("ISO-8859-1"), "utf-8");

            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject tokenInfo = JSONObject.parseObject(accessTokenInfo);
            String token = tokenInfo.getString("access_token");
            JSONObject tokenObj = new JSONObject();
            tokenObj.put("token", token);
            tokenObj.put("createTime", now);
            tokens.put(agentId, tokenObj);

        }


    }


    private void generateToken() {
        createtime = new Date().getTime() / 1000;
        String accesstokenURL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + this.corpid + "&corpsecret=" + this.corpSecret;
        String accessTokenInfo = HttpUtil.getUrl(accesstokenURL);
        try {
            accessTokenInfo = new String(accessTokenInfo.getBytes("ISO-8859-1"), "utf-8");

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject tokenInfo = JSONObject.parseObject(accessTokenInfo);
        this.token = tokenInfo.getString("access_token");
    }

    public static void main(String[] args) {
        String t = AccessToken.getInstance().getUpToken("1000005");
        System.out.println(t);
    }

}
