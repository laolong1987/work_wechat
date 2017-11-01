package com.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.web.component.wechat.WechatConfig;
import com.web.component.wechat.api.WechatComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by admin on 2017/10/10.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private WechatComponent wechatComponent;

    @RequestMapping("/{agentId}")
    public String authorizeStart(@PathVariable("agentId") String agentId, HttpServletRequest request) {
        String state = request.getParameter("state").replaceAll("/", "%2F");;
        String request_url = request.getRequestURL().toString();

        String redirect_url = request_url + "/authorize_code";
        redirect_url = redirect_url.replace(":", "%3A").replaceAll("/", "%2F");
        String startAuthUrl = wechatConfig.getAuthUrl(redirect_url, agentId, state);
        return "redirect:" + startAuthUrl;

    }

    @RequestMapping("/{agentId}/authorize_code")
    public String authorizeCode(@PathVariable("agentId") String agentId, HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String baseUrl= request.getScheme() +"://" + request.getServerName()  + ":" +request.getServerPort() +request.getContextPath();
        String userId = wechatComponent.getAuthUserInfo(code);

        if (userId != null) {
            if(agentId.equalsIgnoreCase(wechatConfig.getApprovalAgentId())){
                request.getSession().setAttribute("approvalUserId",userId);
                //临时使用
                request.getSession().setAttribute("newsUserId",userId);
            }else if (agentId.equalsIgnoreCase(wechatConfig.getNewsAgentId())){
                request.getSession().setAttribute("newsUserId",userId);
            }

            state = state.replaceAll("%2F", "/");
            baseUrl = baseUrl+state;
            return "redirect:" + baseUrl;
        }

        return "/jsp/AuthError";

    }

    @RequestMapping("/test/{userId}")
       public String testUsetid(@PathVariable("userId") String userId, HttpServletRequest request) {
        request.getSession().setAttribute("approvalUserId",userId);
         request.getSession().setAttribute("newsUserId",userId);
         return "redirect:/news/listnews";

       }

}
