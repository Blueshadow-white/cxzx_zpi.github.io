package com.ws.service_api.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductQuery {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "产品分类")
    private String sort;
}
