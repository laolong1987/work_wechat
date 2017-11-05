package com.web.controller;

import java.io.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.web.component.wechat.WechatConfig;
import com.web.component.wechat.api.WechatComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 2017/8/23.
 */
@RestController
@RequestMapping("message")
public class WechatMessageApiController {

    @Autowired
    private WechatComponent wechatComponent;

    @Autowired
    private WechatConfig wechatConfig;

    @RequestMapping(value = "sent", method = RequestMethod.POST)
    public String sent(@RequestParam(value = "secret", required = true) String secret, HttpServletRequest request, HttpServletResponse response) {
        JSONObject error = new JSONObject();
        if (!secret.equals(wechatConfig.getSecret())) {
            error.put("errcode", 40001);
            error.put("errmsg","不合法的secret参数");
            return error.toString();
        }

        InputStream inputStream = null;
        String res;

        try {
            StringBuffer buffer = new StringBuffer();
            inputStream = request.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream);
            BufferedReader bufferedReader = new BufferedReader(
                inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            String message = URLDecoder.decode(buffer.toString(), "utf-8");
            ;

            res = wechatComponent.sentMpNews(message);

        } catch (Exception e) {
            error.put("errcode", -1);
            error.put("errmsg", e.getMessage());
            res = error.toString();
        }
        return res;
    }

}
