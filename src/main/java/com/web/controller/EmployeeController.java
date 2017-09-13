package com.web.controller;


import com.common.SearchTemplate;
import com.utils.ConvertUtil;
import com.utils.DateUtil;
import com.web.entity.News;
import com.web.service.NewsService;
import com.web.service.OrgService;
import com.web.service.UploadFileService;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyang on 16/3/1.
 */

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {


    @Autowired
    OrgService orgService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/jsp/manage/listemp";
    }

    @RequestMapping(value = "/searchlist", method = RequestMethod.POST)
    @ResponseBody
    public Object searchlist(@RequestBody Map<String, String> param, HttpServletRequest request) {

        return orgService.searchEmployee(param).getResult();
    }

    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    @ResponseBody
    public Object remove(@RequestBody List<Map> params){
        for (Map map : params) {
            Integer aid=ConvertUtil.safeToInteger(map.get("aid"),0);
            if(0!=aid){
                userService.removeUser(aid);
            }
        }
        return "success";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody List<Map> params){
        for (Map map : params) {
            Integer aid=ConvertUtil.safeToInteger(map.get("aid"),0);

            if(0==aid){
                userService.addUser(map);
            }
        }
        return "success";
    }
}
