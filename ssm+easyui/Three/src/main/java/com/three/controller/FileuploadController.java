package com.three.controller;

import com.three.entity.CommAttachment;
import com.three.service.CommAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 */
@Controller
public class FileuploadController {

    @Autowired
    private CommAttachmentService service;


    /**
     * 文件上传
     * @param req
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/upload")
    public Map<String,Object> testUpload(HttpServletRequest req, @RequestParam("file") MultipartFile file)
            throws IOException{
        Map<String,Object> map = new HashMap<String, Object>();
        if(file != null && !file.getOriginalFilename().equals("") && file.getOriginalFilename() != null){
            FileOutputStream fileOutput;
            String fileName = file.getOriginalFilename().substring(0,file.getOriginalFilename()
                    .lastIndexOf("."));
            String fileSize = formetFileSize(file.getSize());
            String extension = null;
            try{
                //获取后缀名
                extension = file.getOriginalFilename().substring(file.getOriginalFilename()
                        .lastIndexOf(".")+1,file.getOriginalFilename().length());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //设置文件名称
            String upFileName = "" + System.currentTimeMillis() + "." +extension;
            //设置路径
            String path =getRealPath(req) + System.getProperty("file.separator") + upFileName;
            try {
                //文件上传
                fileOutput = new FileOutputStream(path);
                fileOutput.write(file.getBytes());
                fileOutput.flush();
                fileOutput.close();
                System.out.println("上传文件成功："+path);
            }catch (FileNotFoundException e) {
                System.out.println("testUpload:"+e.getMessage());
            } catch (IOException e) {
                System.out.println("testUpload:"+e.getMessage());
            }


            CommAttachment comm = new CommAttachment();

            comm.setTitle(fileName);
            comm.setFileUrl(upFileName);
            comm.setFileSize(fileSize);
            comm.setFileType(extension);
            int num = service.insert(comm);
            if(num > 0){
                map.put("success",true);
                map.put("url",upFileName);
            }
        }else{
            map.put("success", false);

        }
        return map;
    }


    /**
     * 文件下载
     * @param response
     */
    @RequestMapping(value = "/download")
    public void testDownload(HttpServletRequest req,HttpServletResponse response, @RequestParam("fileName") String fileName){
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try{
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(getRealPath(req)+File.separator+fileName)));
            int i = bis.read(buff);
            while(i != -1){
                os.write(buff,0,buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(bis != null){
                try{
                    bis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
        System.out.println("Download  success");
    }

    @ResponseBody
    @RequestMapping(value = "/fileDelete")
    public  Map<String,Object> delete(HttpServletRequest req,HttpServletResponse response,@RequestParam("id") int id){
        Map<String,Object> map = new HashMap<String, Object>();
        String realPath = getRealPath(req);
        CommAttachment comm = service.getById(id);
        File file = new File(realPath + File.separator + comm.getFileUrl());
        if(file.exists()){
            file.delete();
            System.out.println("file delete success");
        }
        int num = service.delete(id);
        map.put("num",num);
        return map;
    }


    /**
     * 获取项目的tempUpload 路径
     * @param request
     * @return
     */
    private static String getRealPath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("tempUpload");
    }

    private static String formetFileSize(long fileS){
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeStr = "";
        if (fileS < 1024) {
            fileSizeStr = df.format((double) fileS) + "B";
        } else if (fileS < 1024*1024) {
            fileSizeStr = df.format((double) fileS / 1024) + "K";
        } else if (fileS <  1024*1024*1024) {
            fileSizeStr = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeStr = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeStr;


    }

}
