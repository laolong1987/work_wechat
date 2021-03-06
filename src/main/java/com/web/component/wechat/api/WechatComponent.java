package com.web.component.wechat.api;

import java.io.*;
import java.util.Date;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import com.utils.HttpUtil;
import com.web.component.AccessToken;
import com.web.component.wechat.WechatConfig;
import com.web.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/8/22.
 */
@Component("wechatComponent")
public class WechatComponent {

    private static final Logger logger = LoggerFactory.getLogger(WechatComponent.class);

    private static final Properties wechatApiConfig;

    @Autowired
    private WechatConfig wechatConfig;

    static {
        wechatApiConfig = new Properties();
        try (InputStream in = WechatComponent.class.getClassLoader().getResourceAsStream("wechat-api.properties")) {
            wechatApiConfig.load(in);
        } catch (IOException ioe) {
            logger.warn("Failed to load error configuration file", ioe);
        }
    }

    public String sentMpNews(String message) throws Exception {
        String url = wechatApiConfig.getProperty("messageApi") + AccessToken.getInstance().getUpToken();

        String response = HttpUtil.httpRequest(url, "POST", message);
        return response;
    }

    public String getAuthUserInfo(String code,String agentId) {
        String url = wechatConfig.getGetAuthUserInfoUrl(AccessToken.getInstance().getUpToken(agentId), code);
        String response = HttpUtil.httpRequest(url, "GET", null);
        String userId = null;
        JSONObject resJson = JSONObject.parseObject(response);
        if (resJson.getInteger("errcode") == 0 && resJson.containsKey("UserId")) {
            userId = resJson.getString("UserId");
        }
        return userId;
    }

    public boolean createUser(JSONObject jsonObject) {
        String url = wechatConfig.getCreateUser() +  generateToken(wechatConfig.getContactsSecret());

        String response = HttpUtil.httpRequest(url, "POST", jsonObject.toString());
        boolean created = false;
        System.out.println(response);
        JSONObject resJson = JSONObject.parseObject(response);
        if (resJson.getInteger("errcode") == 0) {
            created = true;
        }
        return created;
    }

    public String createDepartment(JSONObject jsonObject) {
        String url = wechatConfig.getCreateDepartment() + generateToken(wechatConfig.getContactsSecret());

        String response = HttpUtil.httpRequest(url, "POST", jsonObject.toString());
        return response;
    }

    public String generateToken(String corpSecret) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + wechatConfig.getCorpID() + "&corpsecret=" + corpSecret;
        String accessTokenInfo = HttpUtil.getUrl(url);
        try {
            accessTokenInfo = new String(accessTokenInfo.getBytes("ISO-8859-1"), "utf-8");

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject tokenInfo = JSONObject.parseObject(accessTokenInfo);
       String token = tokenInfo.getString("access_token");

       return token;
    }


}
