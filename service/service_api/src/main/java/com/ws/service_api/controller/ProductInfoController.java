package com.ws.service_api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.common_utils.Result;
import com.ws.service_api.entity.ProductInfo;
import com.ws.service_api.entity.vo.ProductQuery;
import com.ws.service_api.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author 王帅
 * @since 2022-09-09
 */
@RestController
@RequestMapping("/api/product")
@Api(tags = "产品管理")
@CrossOrigin
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;

    //1. 获取所有产品
    @GetMapping("getAllProduct")
    @ApiOperation(value = "查询所有产品")
    public Result getAllProduct(){
        List<ProductInfo> list = productInfoService.list(null);
        return Result.ok().data("data",list);
    }

    //2. 逻辑删除产品
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除产品")
    public Result removeProduct(@PathVariable String id){
        boolean remove = productInfoService.removeById(id);
        if (remove){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //3. 分页查询产品
    @GetMapping("pageProduct/{current}/{limit}")
    @ApiOperation(value = "分页查询产品")
    public Result pageProduct(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page<ProductInfo> newsPage = new Page<>(current,limit);
        //调用方法实现分页
        //调用方法时，底层把所有数据封装到newsPage对象中
        productInfoService.page(newsPage,null);
        long total = newsPage.getTotal();//总记录数
        List<ProductInfo> records = newsPage.getRecords();//每页数据
        return Result.ok().data("total",total).data("data",records);
    }

    //4. 分页条件查询
    @PostMapping("pageProductCondition/{current}/{limit}")
    @ApiOperation(value = "分页条件查询")
    public Result pageProductCondition (@PathVariable long current,
                                        @PathVariable long limit,
                                        @RequestBody(required = false) ProductQuery productQuery){
        //床架page对象
        Page<ProductInfo> productInfoPage = new Page<>(current, limit);
        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();

        //多条件组合查询
        String name = productQuery.getName();
        String sort = productQuery.getSort();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }if(!StringUtils.isEmpty(sort)){
            queryWrapper.like("sort",sort);
        }

        //对数据按照名称进行排序
        queryWrapper.orderByDesc("name");

        //调用方法，实现条件查询
        IPage<ProductInfo> page = productInfoService.page(productInfoPage, queryWrapper);
        long total = productInfoPage.getTotal();
        List<ProductInfo> data = productInfoPage.getRecords();
        return Result.ok().data("total",total).data("data",data);
    }

    //5. 添加产品
    @PostMapping("addProduct")
    @ApiOperation(value = "添加产品")
    public Result addProduct(@RequestBody ProductInfo productInfo){
        boolean save = productInfoService.save(productInfo);
        if (save){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //6. 修改产品
    //根据id查找产品
    @GetMapping("getProduct/{id}")
    @ApiOperation(value = "根据id查询产品")
    public Result getProduct(@PathVariable String id){
        ProductInfo productInfo = productInfoService.getById(id);
        return Result.ok().data("data",productInfo);
    }
    //修改产品
    @PostMapping("updateProduct")
    @ApiOperation(value = "修改产品")
    public Result updateProduce(@RequestBody ProductInfo productInfo){
        boolean update = productInfoService.updateById(productInfo);
        if (update){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

