package com.web.controller;


import com.utils.ConvertUtil;
import com.web.service.NewsService;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by gaoyang on 16/3/1.
 */

@Controller
@RequestMapping("/admin/news")
public class NewsController {


    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/jsp/manage/listnews";
    }

    @RequestMapping(value = "/searchlist", method = RequestMethod.POST)
    @ResponseBody
    public Object searchlist(@RequestBody Map<String, String> param, HttpServletRequest request) {


        return newsService.searchNews(param).getResult();
    }

    @RequestMapping(value = "/showaddnews", method = RequestMethod.GET)
    public String showaddnews(HttpServletRequest request,HttpServletResponse response) {
        int id= ConvertUtil.safeToInteger(request.getParameter(""),0);

        return "/jsp/manage/addnews";
    }

}
