package com.web.component;

import com.alibaba.fastjson.JSONObject;
import com.utils.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JsapiTicket {

    private volatile static JsapiTicket uniqueInstance;

    private long createtime = 0;
    private String jsapi_ticket="";

    private JsapiTicket() {
        createtime = new Date().getTime() / 1000;
    }

    public static JsapiTicket getInstance() {
        if (uniqueInstance == null) {
            synchronized (JsapiTicket.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new JsapiTicket();
                    uniqueInstance.generateToken();
                }
            }
        }
        return uniqueInstance;
    }

    public String getUpToken() {
        Date date = new Date();
        long now = date.getTime() / 1000;
        if (now - createtime > 7000) {
            this.generateToken();
        }
        return jsapi_ticket;
    }

    private void generateToken() {
        createtime = new Date().getTime() / 1000;
        String accesstokenURL = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=" +
                AccessToken.getInstance().getUpToken();
        String accessTokenInfo = HttpUtil.getUrl(accesstokenURL);
        try {
            accessTokenInfo = new String(accessTokenInfo.getBytes("ISO-8859-1"), "utf-8");

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject tokenInfo = JSONObject.parseObject(accessTokenInfo);
        jsapi_ticket = tokenInfo.getString("ticket");
    }

    public static void main(String[] args) {
        String t = JsapiTicket.getInstance().getUpToken();
        System.out.println(t);
    }

}
