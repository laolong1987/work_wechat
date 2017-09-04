package com.web.service;

import com.common.SearchTemplate;
import com.web.dao.NewsDao;
import com.web.dao.UserDao;
import com.web.entity.News;
import com.web.entity.Newsflag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("newsService")
public class NewsService {

    @Autowired
    NewsDao newsDao;

    /**
     * 查询
     * @param map
     * @return
     */
    public SearchTemplate searchNews(Map map){
        return newsDao.searchNews(map);
    }


    public News getNews(Integer id){
        return (News) newsDao.getObjectById(id,News.class);
    }

    public void saveNews(News news){
        newsDao.save(news);
    }


    public List<Newsflag> findNewsidByNewsId(String newsid){
        return newsDao.findNewsidByNewsId(newsid);
    }
}
