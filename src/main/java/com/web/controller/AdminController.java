package com.web.controller;


import com.utils.MD5Util;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gaoyang on 16/3/1.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/jsp/manage/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        HttpServletResponse response) {
//        if (user.getUsername() != null && !"".equals(user.getUsername()) && user.getPwd() != null && !"".equals(user.getPwd())) {
//            String pwd = MD5Util.string2MD5(user.getPwd() + user.getUsername());
//            user.setPwd(pwd);
//            User admin = userService.findUser(user);
//            if (admin != null) {
//                request.getSession().setAttribute("user", admin);
//                return "redirect:index";
//            } else {
//                request.setAttribute("tip", "用户名或密码错误");
//                return "/jsp/manage/login";
//            }
//        }

        return "/jsp/manage/login";
    }


    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response) {
        return "/jsp/manage/index";
    }

    /**
     * 退出清空session
     *
     * @param request
     * @param response
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public String removeuser(HttpServletRequest request,
                             HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        return "redirect:/admin/login";
    }

}
