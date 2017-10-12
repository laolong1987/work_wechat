package com.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utils.ConvertUtil;
import com.utils.DateUtil;
import com.utils.StringUtil;
import com.web.entity.Employee;
import com.web.entity.News;
import com.web.entity.Newsflag;
import com.web.service.NewsService;
import com.web.service.OrgService;
import com.web.service.WorkFromService;
import com.web.service.ws.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyang on 16/3/1.
 */

@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    WorkFromService workFromService;
    @Autowired
    OrgService orgService;

    @RequestMapping(value = "/addleave", method = RequestMethod.GET)
    public String addleave(HttpServletRequest request,HttpServletResponse response) {

        return "/jsp/app/addleave";
    }


    @RequestMapping(value = "/createleave", method = RequestMethod.POST)
    public String createleave(HttpServletRequest request,HttpServletResponse response) {
        String type=ConvertUtil.safeToString(request.getParameter("type"),"");
        String day=ConvertUtil.safeToString(request.getParameter("day"),"");
        String date1=ConvertUtil.safeToString(request.getParameter("date1"),"");
        String date2=ConvertUtil.safeToString(request.getParameter("date2"),"");
        String desc=ConvertUtil.safeToString(request.getParameter("desc"),"");

        Employee employee= orgService.findEmployee("22000345");
        JSONObject data=new JSONObject();
        data.put("Depart",employee.getZzdwmc());
//        data.put("Position","普通职员");
        data.put("QjDays",day+".0");
        data.put("QjEnd",date2);
        data.put("QjStart",date1);
        data.put("QjType",type);
        data.put("Qjly",desc);
        data.put("Ygxm",employee.getYgxm().replace(" ",""));

        JSONObject json=new JSONObject();
        json.put("FormType","349");
        json.put("data",data);


        String FormConfigID= workFromService.CreateFormInstance(json.toJSONString());
        JSONObject resultJson = JSON.parseObject(FormConfigID);
        String result= workFromService.StartFormWorkflow(resultJson.getString("FormConfigID"));
        System.out.println(result);
        return "/jsp/app/addleave";
    }


    @RequestMapping(value = "/addcar", method = RequestMethod.GET)
    public String addcar(HttpServletRequest request,HttpServletResponse response) {

        return "/jsp/app/addcar";
    }

    @RequestMapping(value = "/addguestmeal", method = RequestMethod.GET)
    public String addguestmeal(HttpServletRequest request,HttpServletResponse response) {

        return "/jsp/app/addguestmeal";
    }

}
