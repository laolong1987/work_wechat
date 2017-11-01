package com.web.component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.utils.HttpUtil;

public class AccessToken {

    private volatile static AccessToken uniqueInstance;

    private String corpid;
    private String corpSecret;
    private long createtime = 0;
    private String token;

    private AccessToken() {
        corpid = "wwee9bb9537a842b61";
        corpSecret = "wiP2KVvw5wg7qJYGGOPvgH8ARE4a8y9mzvpQrU5uLWE";
        createtime = new Date().getTime() / 1000;
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
        String t = AccessToken.getInstance().getUpToken();
        System.out.println(t);
    }

}
