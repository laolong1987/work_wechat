package com.web.controller;


import com.utils.ConvertUtil;
import com.utils.MD5Util;
import com.web.entity.Admin;
import com.web.service.OrgService;
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

    @Autowired
    private OrgService orgService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/jsp/manage/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        HttpServletResponse response) {
        String username= ConvertUtil.safeToString(request.getParameter("username"),"");
        String password= ConvertUtil.safeToString(request.getParameter("pwd"),"");

        if (!"".equals(username) && !"".equals(password)) {
            Admin admin = userService.findUser(username,password);
            if (admin != null) {
                request.getSession().setAttribute("user", admin);
                return "redirect:index";
            } else {
                request.setAttribute("tip", "用户名或密码错误");
                return "/jsp/manage/login";
            }
        }

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
        return "/jsp/manage/tologin";
    }

    @RequestMapping(value = "/savepassword", method = RequestMethod.POST)
    @ResponseBody
    public String save_password( HttpServletRequest request,HttpServletResponse response) {
        String result="";
        Admin user= (Admin) request.getSession().getAttribute("user");
        String password = request.getParameter("password");
        String confirmPwd =request.getParameter("confirmPwd");
        if( !"".equals(confirmPwd) && !"".equals(password) && password!=null && confirmPwd!=null ){
            if(password.equals(confirmPwd)){
                user.setPassword(password);
                userService.saveUser(user);
                result="success";
            }else{
                result="The passwords you typed do not match. Type the same password in both text boxes.";
            }
        }else{
            result="password can't be null";
        }
        return result;
    }

    /**
     * 更新部门信息
     *
     * @param request
     * @param response
     *
     * @return
     */
    @RequestMapping(value = "/updateuser")
    public String updateuser(HttpServletRequest request,
                             HttpServletResponse response) {
        System.out.println("----updateDept----");
        orgService.updateDept();
        System.out.println("----updateEmp----");
        orgService.updateEmp();
        return "/jsp/manage/tologin";
    }

}
