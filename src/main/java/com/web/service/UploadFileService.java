package com.web.service;

import com.utils.ConvertUtil;
import com.web.dao.UploadFileDao;
import com.web.entity.Uploadfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by gaoyang on 2016/10/30.
 */
@Service("uploadFileService")
public class UploadFileService {

    @Autowired
    UploadFileDao uploadFileDao;

    public void saveUploadFile(Uploadfile uploadfile){
        uploadFileDao.save(uploadfile);
    }


    public List<Uploadfile> findUploadfileByUUID(String uuid){
        return uploadFileDao.findUploadfileByUUID(uuid);
    }
    public List<Uploadfile> findUploadfileBySource(String uuid , String type){
        return uploadFileDao.findUploadfileBySource(uuid,type);
    }

    public List<Uploadfile> findUploadfileByIDS(List<String> ids){
        return uploadFileDao.findUploadfileByIDS(ids);
    }

    public Uploadfile getUploadfile(String id){
            return (Uploadfile) uploadFileDao.getObjectById(id,Uploadfile.class);
    }

    public void removeUploadfile(Uploadfile uploadfile){
        uploadFileDao.remove(uploadfile);
    }

    public void savefiles(String fileids,String entityname,String entityid){
        //保存关联附件
        for (String fs:fileids.split(",")) {
            if(!"".equals(fs)){
                Uploadfile file= getUploadfile(fs);
                if(null!=file){
                    file.setSource_entity(entityname);
                    file.setSource_id(entityid);
                    file.setUpdate_time(new Date());
                    file.setType(1);
                    saveUploadFile(file);
                }
            }
        }
    }

    public void savefilesnewfile(String fileids,String entityname,String entityid){
        //保存关联附件
        for (String fs:fileids.split(",")) {
            if(!"".equals(fs)){
                Uploadfile file= getUploadfile(fs);
                if(null!=file){
                    Uploadfile newfile=new Uploadfile();
                    newfile.setSource_entity(entityname);
                    newfile.setSource_id(entityid);
                    newfile.setUpdate_time(new Date());
                    newfile.setType(1);
                    newfile.setFilename(file.getFilename());
                    newfile.setMimetype(file.getMimetype());
                    newfile.setUuid(file.getUuid());
                    newfile.setFilepath(file.getFilepath());
                    newfile.setFile_type(file.getFile_type());
                    newfile.setFile_size(file.getFile_size());
                    newfile.setCreate_time(file.getCreate_time());
                    newfile.setUpdate_time(file.getUpdate_time());
                    saveUploadFile(newfile);
                }
            }
        }
    }


    /**
     * 判断总大小是否超过300M
     * @return
     */
    public boolean findFileAllSize(long filesize,String entityid,String entityname){
        boolean result=true;
        List<Uploadfile> list= findUploadfileBySource(entityid,entityname);
        long allsize=0;
        for (Uploadfile file :list  ) {
            long size= ConvertUtil.safeToInteger(file.getFile_size(),0);
            allsize+=size;
        }
        if(314572800 < (allsize+filesize)){
            result=false;
        }
        return result;
    }

    /**
     * 判断总大小是否超过300M
     * @return
     */
    public boolean findFileAllSize(long filesize,String entityid,String entityname,String uuid){
        boolean result=true;
        long allsize=0;

        if(!"".equals(entityid) && !"".equals(entityname)){
            List<Uploadfile> list= findUploadfileBySource(entityid,entityname);
            for (Uploadfile file :list  ) {
                long size= ConvertUtil.safeToInteger(file.getFile_size(),0);
                allsize+=size;
            }
        }

        List<Uploadfile> list2= uploadFileDao.findUploadfileBySource(uuid);
        for (Uploadfile file :list2  ) {
            long size= ConvertUtil.safeToInteger(file.getFile_size(),0);
            allsize+=size;
        }
        if(314572800 < (allsize+filesize)){
            result=false;
        }
        return result;
    }
}
