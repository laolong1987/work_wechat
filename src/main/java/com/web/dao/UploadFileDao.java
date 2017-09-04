package com.web.dao;

import com.common.BaseDao;
import com.web.entity.Uploadfile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyang on 2016/10/30.
 */
@Repository
public class UploadFileDao extends BaseDao {

    public List<Uploadfile> findUploadfileByUUID(String uuid){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from uploadfile where uuid=:uuid and type=0 ");
        Map map = new HashMap();
        map.put("uuid",uuid);
        List<Uploadfile> list = super.findObjects(sql.toString(), map, Uploadfile.class);
        return list;
    }

    public List<Uploadfile> findUploadfileByIDS(List<String> ids){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from uploadfile where 1=1 and id in(:ids) ");
        Map map = new HashMap();
        map.put("ids",ids);
        List<Uploadfile> list = super.findObjects(sql.toString(), map, Uploadfile.class);
        return list;
    }


    public List<Uploadfile> findUploadfileBySource(String source_id, String source_entity){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from uploadfile where source_id=:source_id and type=1 and source_entity=:source_entity ");
        Map map = new HashMap();
        map.put("source_id",source_id);
        map.put("source_entity",source_entity);
        List<Uploadfile> list = super.findObjects(sql.toString(), map, Uploadfile.class);
        return list;
    }

    public List<Uploadfile> findUploadfileBySource(String source_id){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from uploadfile where source_id=:source_id ");
        Map map = new HashMap();
        map.put("source_id",source_id);
        List<Uploadfile> list = super.findObjects(sql.toString(), map, Uploadfile.class);
        return list;
    }

}
