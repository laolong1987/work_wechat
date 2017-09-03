package com.web.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/8/28.
 */
@Component
@PropertySource(value = {"classpath:web-service-api.properties"})
public class WebServiceApiConfig {

    @Value("${base}")
    private String base;

    @Value("${GetWaitProcessNotice}")
    private String getWaitProcessNotice;

    @Value("${GetFormInstance}")
    private String getFormInstance;

    @Value("${GetMayProcessItems}")
    private String getMayProcessItems;

    public String getGetWaitProcessNotice() {
        return getWaitProcessNotice;
    }

    public void setGetWaitProcessNotice(String getWaitProcessNotice) {
        this.getWaitProcessNotice = getWaitProcessNotice;
    }

    public String getGetFormInstance() {
        return getFormInstance;
    }

    public void setGetFormInstance(String getFormInstance) {
        this.getFormInstance = getFormInstance;
    }

    public String getGetMayProcessItems() {
        return getMayProcessItems;
    }

    public void setGetMayProcessItems(String getMayProcessItems) {
        this.getMayProcessItems = getMayProcessItems;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
