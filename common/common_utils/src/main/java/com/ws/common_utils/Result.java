package com.ws.common_utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果
 */
@Data
public class Result {
    @ApiModelProperty(value = "是否成功")
    private boolean success;//响应是否成功

    @ApiModelProperty(value = "响应码")
    private Integer code;//响应码

    @ApiModelProperty(value = "消息")
    private String message;//返回消息

    @ApiModelProperty(value = "数据")
    private Map data = new HashMap<String,Object>();//返回数据，放在键值对里面

    //构造方法私有化
    private Result (){

    }

    //静态方法
    public static Result ok(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    public static Result error(){
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }


    //链式编程
    //设置响应码
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    //设置消息
    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    //
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    //
    public Result data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}
