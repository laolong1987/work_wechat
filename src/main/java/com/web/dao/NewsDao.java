package com.web.dao;


import com.common.BaseDao;
import com.common.SearchTemplate;
import com.utils.ConvertUtil;
import com.utils.DateUtil;
import com.web.entity.Newsflag;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
        sql.append(" select t.*, CONVERT(varchar(100), t.puttime, 120) as puttime1 from news t where 1=1 ");
        sql.append(" and t.status!=2 ");
        if (map.containsKey("title") && !"".equals(ConvertUtil.safeToString(map.get("title"),""))){
            sql.append(" and t.title like :title ");
            map.put("title", "%" + map.get("title") + "%");
        }
        sql.append(" order by t.createtime desc");
        return super.search(sql.toString(),map);
    }


    public List<Newsflag> findNewsidByNewsId(String newsid){
        StringBuffer sql = new StringBuffer();
        sql.append("select  * from newsflag where newsid=:newsid ");
        Map map = new HashMap();
        map.put("newsid",newsid);
        List<Newsflag> list = super.findObjects(sql.toString(), map, Newsflag.class);
        return list;
    }

    public List<Map> searchNewsByEmpid(Map map) {
        StringBuffer sql = new StringBuffer("");
        sql.append("select * from news where status=1 ");
        if (map.containsKey("title") &&  !"".equals(map.get("title"))){
            sql.append(" and title like :title ");
            map.put("title", "%" + ConvertUtil.safeToString(map.get("title"), "") + "%");
        }
        sql.append(" order by puttime desc ");
        return super.findResult(sql.toString(), map);
    }

    public List<Newsflag> findNewsidByreadid(String readid){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from newsflag where readid=:readid ");
        Map map = new HashMap();
        map.put("readid",readid);
        List<Newsflag> list = super.findObjects(sql.toString(), map, Newsflag.class);
        return list;
    }

    public List<Newsflag> findNewsidByreadid(String readid,String newsid){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from newsflag where readid=:readid and newsid=:newsid ");
        Map map = new HashMap();
        map.put("readid",readid);
        map.put("newsid",newsid);
        List<Newsflag> list = super.findObjects(sql.toString(), map, Newsflag.class);
        return list;
    }
}
