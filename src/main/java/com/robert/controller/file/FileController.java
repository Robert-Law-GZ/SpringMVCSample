package com.robert.controller.file;

import com.robert.model.ResponseData;
import com.robert.model.UploadFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping(value = "/file")
public class FileController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file, HttpSession session) throws Exception{

        ResponseData responseData=new ResponseData();

        if(!file.isEmpty()){
            //上传文件路径
            String path=session.getServletContext().getRealPath("/images/");
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));

            UploadFile uploadFile=new UploadFile();
            uploadFile.setName(filename);
            uploadFile.setBigUrl(path);
            uploadFile.setSmallUrl(path);
            responseData.setData(uploadFile);
        }

        return responseData;
    }

}
