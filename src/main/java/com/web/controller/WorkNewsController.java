package com.web.controller;


import com.utils.ConvertUtil;
import com.utils.DateUtil;
import com.utils.StringUtil;
import com.web.entity.Employee;
import com.web.entity.News;
import com.web.entity.Newsflag;
import com.web.service.NewsService;
import com.web.service.OrgService;
import com.web.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyang on 16/3/1.
 */

@Controller
@RequestMapping("/news")
public class WorkNewsController {


    @Autowired
    NewsService newsService;

    @Autowired
    OrgService orgService;


    @RequestMapping(value = "/detailnews", method = RequestMethod.GET)
    public String detailnews(HttpServletRequest request,HttpServletResponse response) {
        int id= ConvertUtil.safeToInteger(request.getParameter("id"),0);
        if(0!=id) {
            News news = newsService.getNews(id);
            request.setAttribute("news",news);
            request.setAttribute("puttime", DateUtil.SimpleDateUserFormat(news.getPuttime(),"yyyy-MM-dd HH:mm"));

            //记录阅读人
            String userid=ConvertUtil.safeToString(request.getSession().getAttribute("newsUserId"),"");
            if(!"".equals(userid)){
                Employee employee= orgService.findEmployee(userid);
                if(null!=employee){
                    if (newsService.findNewsidByreadid(userid,""+id)){
                        Newsflag newsflag=new Newsflag();
                        newsflag.setCreatetime(new Date());
                        newsflag.setNewsid(id);
                        newsflag.setUpdatetime(new Date());
                        newsflag.setReadid(userid);
                        newsflag.setReadname(employee.getYgxm());
                        newsService.saveNewsflag(newsflag);
                    }
                }
            }

            List<Newsflag> newsflags=newsService.findNewsidByNewsId(String.valueOf(id));
            String names="";
            for (Newsflag nf:newsflags  ) {
                if(names.length()>0){
                    names+=",";
                }
                names+=nf.getReadname();
            }
            request.setAttribute("flagsize",newsflags.size());
            request.setAttribute("names",names);
        }

        return "/jsp/app/detailnews";
    }


    @RequestMapping(value = "/listnews")
    public String listnews(HttpServletRequest request,HttpServletResponse response) {
        String title = ConvertUtil.safeToString(request.getParameter("title"),"");
        Map p=new HashMap<>();
        p.put("title",title);
        List<Map> list=newsService.searchNewsByEmpid(p);

        //记录阅读人
        Map<String,String> readmap=new HashMap<>();
        String userid=ConvertUtil.safeToString(request.getSession().getAttribute("newsUserId"),"");
        if(!"".equals(userid)){
            List<Newsflag> newsflags=newsService.findNewsidByreadid(userid);
            for (Newsflag flag:newsflags) {
                readmap.put(""+flag.getNewsid(),""+flag.getNewsid());
            }
        }else{
            request.setAttribute("userid",userid);
        }

        for (Map map:list) {
            map.put("cont", StringUtil.Html2Text(ConvertUtil.safeToString(map.get("content"),"")));
            String newsid= ConvertUtil.safeToString(map.get("id"),"");
            String readflag="0";
            if(readmap.containsKey(newsid)){
                readflag="1";
            }
            map.put("readflag",readflag);
        }
        request.setAttribute("list",list);
        return "/jsp/app/listnews";
    }

}
