package com.ws.service_oss.controller;

import com.ws.common_utils.Result;
import com.ws.service_oss.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
@Api(tags = "OSS文件上传")
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像
    @PostMapping
    public Result uploadOssFile(MultipartFile  file){
        //获取文件MultipartFile
        String url = ossService.uploadFileAvatar(file);
        return Result.ok().data("url",url);
    }
}
