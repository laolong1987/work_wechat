package com.web.dao;


import com.common.BaseDao;
import com.common.SearchTemplate;
import com.utils.ConvertUtil;
import com.web.entity.Demo;
import com.web.entity.News;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyang on 16/2/29.
 */
@Repository
public class NewsDao extends BaseDao{

    /**
     * 查询
     * @param map
     * @return
     */
    public SearchTemplate searchNews(Map map){
        StringBuffer sql =new StringBuffer();
        Map p=new HashMap();
        sql.append("select  * from news ");
        return super.search(sql.toString(),map);
    }
}
