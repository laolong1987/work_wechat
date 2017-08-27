package com.web.service;

import com.common.SearchTemplate;
import com.web.dao.NewsDao;
import com.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
