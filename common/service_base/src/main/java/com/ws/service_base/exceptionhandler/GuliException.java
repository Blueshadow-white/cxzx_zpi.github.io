package com.ws.service_base.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //生成有参构造器
@NoArgsConstructor  //生成无参构造器
public class GuliException extends RuntimeException{

    private Integer code;//状态码

    private String msg;//异常的信息


}
