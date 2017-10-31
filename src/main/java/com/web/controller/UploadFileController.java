package com.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.utils.ConvertUtil;
import com.web.entity.Uploadfile;
import com.web.service.UploadFileService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gaoyang on 16/2/28.
 */
@Controller
@RequestMapping("/file/")
public class UploadFileController {

    @Autowired
    UploadFileService uploadFileService;

    @RequestMapping(value = "/uploadfile")
    @ResponseBody
    public String adduploadfile(HttpServletRequest request, HttpServletResponse response) throws IOException {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        int file_type = ConvertUtil.safeToInteger(request.getParameter("file_type"), 1);


        /**构建保存的目录**/
        String tmpPathDir = "/file";
        String tmpRealPathDir = request.getSession().getServletContext().getRealPath(tmpPathDir);
        /**根据真实路径创建目录**/
        File tmpSaveFile = new File(tmpRealPathDir);
        if (!tmpSaveFile.exists())
            tmpSaveFile.mkdirs();
        String fileuuid = UUID.randomUUID().toString();
        String fileName = tmpRealPathDir + File.separator + fileuuid;

        File transferred = new File(fileName);
        file.transferTo(transferred);

        String attachmentName = FilenameUtils.getName(file.getOriginalFilename());
        String attachmentContentType = file.getContentType();

        Uploadfile uploadfile = new Uploadfile();
        uploadfile.setUpdate_time(new Date());
        uploadfile.setCreate_time(new Date());
        uploadfile.setFilename(attachmentName);
        uploadfile.setMimetype(attachmentContentType);
        uploadfile.setFile_type(ConvertUtil.safeToInteger(file_type, 0));
        uploadfile.setFilepath(fileuuid);
        uploadfile.setType(0);
        uploadfile.setFile_size(file.getSize());
        uploadFileService.saveUploadFile(uploadfile);
//        System.out.println(uploadfile.getId());
        return uploadfile.getId();
    }


    private String getAsciiFilename(String filename) {
        return new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
    }

    private String getContentDispositionValue(String filename) {

        return String.format("attachment; filename=\"%s\"; filename*=UTF-8''%s",
                getAsciiFilename(filename),
                urlEncode(filename));
        //return String.format("attachment; filename=\"%s\"", getAsciiFilename(encodedFilename));
    }

    private String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException(uee);
        }
    }

    @RequestMapping(value = "/kindeditoruploadfile")
    public void kindeditoruploadfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
//文件保存目录路径
        String savePath = request.getSession().getServletContext().getRealPath("/") + "attached/";

//文件保存目录URL
        String saveUrl  = request.getContextPath() + "/attached/";

//定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

//最大文件大小
        long maxSize = 1000000;

        response.setContentType("text/html; charset=UTF-8");
        if(!ServletFileUpload.isMultipartContent(request)){
            response.getWriter().write(getError("请选择文件。"));
            return;
        }
//检查目录
        File uploadDir = new File(savePath);
        if(!uploadDir.isDirectory()){
            response.getWriter().write(getError("上传目录不存在。"));
            return;
        }
//检查目录写权限
        if(!uploadDir.canWrite()){
            response.getWriter().write(getError("上传目录没有写权限。"));
            return;
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if(!extMap.containsKey(dirName)){
            response.getWriter().write(getError("目录名不正确。"));
            return;
        }
//创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator itr = items.iterator();
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            String fileName = item.getName();
            long fileSize = item.getSize();
            if (!item.isFormField()) {
                //检查文件大小
                if(item.getSize() > maxSize){
                    response.getWriter().write(getError("上传文件大小超过限制。"));
                    return;
                }
                //检查扩展名
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
                    response.getWriter().write(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                    return;
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                try{
                    File uploadedFile = new File(savePath, newFileName);
                    item.write(uploadedFile);
                }catch(Exception e){
                    response.getWriter().write(getError("上传文件失败。"));
                    return;
                }

                JSONObject obj = new JSONObject();
                obj.put("error", 0);
                obj.put("url", saveUrl + newFileName);
                response.getWriter().write(obj.toJSONString());
            }
        }
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }


    @RequestMapping(value = "/kindeditoruploadfilemanager")
    public void kindeditoruploadfilemanager(HttpServletRequest request, HttpServletResponse response) throws IOException {
//根目录路径，可以指定绝对路径，比如 /var/www/attached/
        String rootPath = request.getSession().getServletContext().getRealPath("/") + "attached/";
//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
        String rootUrl  = request.getContextPath() + "/attached/";
//图片扩展名
        String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

        String dirName = request.getParameter("dir");
        if (dirName != null) {
            if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
                response.getWriter().write("Invalid Directory name.");
                return;
            }
            rootPath += dirName + "/";
            rootUrl += dirName + "/";
            File saveDirFile = new File(rootPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
        }
//根据path参数，设置各路径和URL
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

//排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

//不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            response.getWriter().write("Access is not allowed.");
            return;
        }
//最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            response.getWriter().write("Parameter is not valid.");
            return;
        }
//目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if(!currentPathFile.isDirectory()){
            response.getWriter().write("Directory does not exist.");
            return;
        }

//遍历目录取的文件信息
        List<Hashtable> fileList = new ArrayList<Hashtable>();
        if(currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<String, Object>();
                String fileName = file.getName();
                if(file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if(file.isFile()){
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }
        JSONObject result = new JSONObject();
        result.put("moveup_dir_path", moveupDirPath);
        result.put("current_dir_path", currentDirPath);
        result.put("current_url", currentUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(result.toJSONString());
    }
    public class NameComparator implements Comparator {
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable)a;
            Hashtable hashB = (Hashtable)b;
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
            }
        }
    }
    public class SizeComparator implements Comparator {
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable)a;
            Hashtable hashB = (Hashtable)b;
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
                return 1;
            } else {
                if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
                    return 1;
                } else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
    public class TypeComparator implements Comparator {
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable)a;
            Hashtable hashB = (Hashtable)b;
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
            }
        }
    }

    @RequestMapping(value = "/doDownload/{id}", method = RequestMethod.GET)
    public void doDownload(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Uploadfile uploadfile = uploadFileService.getUploadfile(id);

        if (null == uploadfile) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (StringUtils.isNotBlank(uploadfile.getMimetype()))
            response.setContentType(uploadfile.getMimetype());
        else
            response.setContentType("application/octet-stream");

        if (StringUtils.isNotBlank(uploadfile.getFilename())) {
            response.setHeader("Content-Disposition", getContentDispositionValue(uploadfile.getFilename()));
        }

        /**构建保存的目录**/
        String tmpPathDir = "/file";
        String tmpRealPathDir = request.getSession().getServletContext().getRealPath(tmpPathDir);
        String fileName = tmpRealPathDir + File.separator + uploadfile.getFilepath();
        /**根据真实路径创建目录**/
        File saveFile = new File(fileName);
        response.setContentLength((int) saveFile.length());
        OutputStream out = response.getOutputStream();
        try {
            FileUtils.copyFile(saveFile, out);
        }catch (Exception e){
            System.out.println("no imgfile");
        }
        finally {
            IOUtils.closeQuietly(out);
        }

    }

    @RequestMapping(value = "/removefile")
    @ResponseBody
    public void removefile(HttpServletRequest request, HttpServletResponse response) {
        String id = ConvertUtil.safeToString(request.getParameter("id"), "");
        Uploadfile file = uploadFileService.getUploadfile(id);
        uploadFileService.removeUploadfile(file);
    }
}
