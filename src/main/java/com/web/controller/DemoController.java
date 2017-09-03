package com.web.controller;

import com.alibaba.fastjson.JSON;
import com.common.HttpHelper;
import com.utils.ConvertUtil;
import com.utils.StringUtil;
import com.web.entity.Demo;
import com.web.service.DemoService;
import com.web.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaoyang on 16/2/28.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    DemoService demoService;

    @Autowired
    OrgService orgService;

    @RequestMapping(value = "/show")
    public String show(HttpServletRequest request,
                                HttpServletResponse response) {

        request.setAttribute("list",demoService.searchList(new HashMap()));

        return "/jsp/demo";
    }

    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request,
                                         HttpServletResponse response) {

        String id= StringUtil.safeToString(request.getParameter("id"),"");
        String name= StringUtil.safeToString(request.getParameter("name"),"");

        Demo demo=new Demo();
        if(!"".equals(id)){
            demo=demoService.get(Integer.parseInt(id));
        }
        demo.setName(name);
        demoService.save(demo);
        return show(request,response);
    }

    @RequestMapping(value = "/del")
    public void del(HttpServletRequest request,
                      HttpServletResponse response) {

        String id= StringUtil.safeToString(request.getParameter("id"),"");

        if(!"".equals(id)){
            demoService.del(Integer.parseInt(id));
        }

    }

    @RequestMapping(value = "/httpclient")
    public String httpclient(HttpServletRequest request,
                       HttpServletResponse response) {

        orgService.updateEmp();

        String url= ConvertUtil.safeToString(request.getParameter("url"),"");
        String content= ConvertUtil.safeToString(request.getParameter("content"),"");
        if(!"".equals(content)){
            Map params= JSON.parseObject(content,Map.class);
            String ret = HttpHelper.fetchUTF8StringByPost(url, params, null, 0, 0);
            System.out.println(ret);
            request.setAttribute("ret",ret);
        }


        return "/jsp/httpclient";
    }
}
