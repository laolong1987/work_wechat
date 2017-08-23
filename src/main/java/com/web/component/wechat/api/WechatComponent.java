package com.web.component.wechat.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.utils.HttpUtil;
import com.web.component.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/8/22.
 */
@Component("wechatComponent")
public class WechatComponent {

    private static final Logger logger = LoggerFactory.getLogger(WechatComponent.class);

    private static final Properties wechatApiConfig;

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

        String response =HttpUtil.httpRequest(url,"POST",message);
        return response;
    }

}
