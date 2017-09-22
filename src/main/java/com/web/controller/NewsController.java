package com.web.controller;


import com.utils.ConvertUtil;
import com.utils.DateUtil;
import com.web.entity.News;
import com.web.service.NewsService;
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
@RequestMapping("/admin/news")
public class NewsController {


    @Autowired
    NewsService newsService;

    @Autowired
    UploadFileService uploadFileService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/jsp/manage/listnews";
    }

    @RequestMapping(value = "/searchlist", method = RequestMethod.POST)
    @ResponseBody
    public Object searchlist(@RequestBody Map<String, String> param, HttpServletRequest request) {


        return newsService.searchNews(param).getResult();
    }

    @RequestMapping(value = "/showaddnews")
    public String showaddnews(HttpServletRequest request,HttpServletResponse response) {
        int id= ConvertUtil.safeToInteger(request.getParameter("id"),0);
        int type= ConvertUtil.safeToInteger(request.getParameter("type"),0);

        if(0!=id) {
            News news = newsService.getNews(id);
            request.setAttribute("news",news);
            request.setAttribute("puttime", DateUtil.SimpleDateUserFormat(news.getPuttime(),"yyyy-MM-dd HH:mm"));
        }
        request.setAttribute("type",type);
        return "/jsp/manage/addnews";
    }

    @RequestMapping(value = "/addnews")
    public String addnews(HttpServletRequest request,HttpServletResponse response) {
        int id= ConvertUtil.safeToInteger(request.getParameter("id"),0);
        int status= ConvertUtil.safeToInteger(request.getParameter("status"),0);
        String title=ConvertUtil.safeToString(request.getParameter("title"),"");
        String source=ConvertUtil.safeToString(request.getParameter("source"),"");
        String content=ConvertUtil.safeToString(request.getParameter("content"),"");
        String fileids = ConvertUtil.safeToString(request.getParameter("fileid"), "");

        Date puttime=ConvertUtil.safeToDate(request.getParameter("puttime")+":00","yyyy-MM-dd HH:mm:ss",new Date());

        News news=new News();
        if(0!=id){
            news=newsService.getNews(id);
            news.setUpdateid(ConvertUtil.safeToString(request.getSession().getAttribute("usercode"), "0"));
            news.setUpdatetime(new Date());
        }else {
            news.setCreatetime(new Date());
            news.setCreateid(ConvertUtil.safeToString(request.getSession().getAttribute("usercode"), "0"));
        }
        news.setContent(content);
        news.setTitle(title);
        news.setSource(source);
        news.setPuttime(puttime);
        news.setTopimg(fileids);
        news.setStatus(status);
        newsService.saveNews(news);

        return "redirect:/admin/news/showaddnews?type=1";
    }


    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    @ResponseBody
    public Object removeEnum(@RequestBody List<Map> params){
        for (Map map : params) {
            News news=newsService.getNews(ConvertUtil.safeToInteger(map.get("id"),0));
            if(null!=news){
                news.setStatus(2);
                newsService.saveNews(news);
            }
        }
        return "success";
    }
}
