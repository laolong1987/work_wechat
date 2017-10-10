package com.web.controller;


import com.utils.ConvertUtil;
import com.utils.DateUtil;
import com.utils.StringUtil;
import com.web.entity.News;
import com.web.entity.Newsflag;
import com.web.service.NewsService;
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


    @RequestMapping(value = "/addleave", method = RequestMethod.GET)
    public String addleave(HttpServletRequest request,HttpServletResponse response) {

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
