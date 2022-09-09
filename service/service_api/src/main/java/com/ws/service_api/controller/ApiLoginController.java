package com.ws.service_api.controller;

import com.ws.common_utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(tags = "登录")
@RestController
@RequestMapping("/api/user")
@CrossOrigin    //解决跨域
public class ApiLoginController {

    //login
    @PostMapping("login")
    public Result login(){
        return Result.ok().data("token","admin_token");
    }

    //info
    @GetMapping("info")
    public Result info(){
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
    }
}
