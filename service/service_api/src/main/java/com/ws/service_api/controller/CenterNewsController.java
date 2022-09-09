package com.ws.service_api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.common_utils.Result;
import com.ws.service_api.entity.CenterNews;
import com.ws.service_api.entity.vo.NewsQuery;
import com.ws.service_api.service.CenterNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author 王帅
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/api/news")
@Api(tags = "新闻管理")
@CrossOrigin
public class CenterNewsController {
    @Autowired
    private CenterNewsService centerNewsService;

    //1. 获取所有中心新闻
    @GetMapping("getAllNews")
    @ApiOperation(value = "查询所有新闻")
    public Result getAllNews(){
        List<CenterNews> list = centerNewsService.list(null);
        return Result.ok().data("items",list);

    }

    //2. 逻辑删除中心新闻
    //@PathVariable用于获取路径中的值
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除新闻")
    public Result removeCenterNews(@PathVariable String id){
        boolean remove = centerNewsService.removeById(id);
        if (remove){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //3. 分页查询中心新闻
    /**
     *
     * @param current 当前页
     * @param limit 每页的记录数
     * @return
     */
    @GetMapping("pageNews/{current}/{limit}")
    @ApiOperation(value = "分页查询新闻")
    public Result pageGetNews(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page<CenterNews> newsPage = new Page<>(current,limit);
        //调用方法实现分页
        //调用方法时，底层把所有数据封装到newsPage对象中
        centerNewsService.page(newsPage,null);

        long total = newsPage.getTotal();//总记录数
        List<CenterNews> records = newsPage.getRecords();//每页数据

        return Result.ok().data("total",total).data("rows",records);
    }

    //4. 分页条件查询
    @PostMapping("pageNewsCondition/{current}/{limit}")
    @ApiOperation(value = "分页条件查询")
    public Result pageNewsCondition(@PathVariable Long current, @PathVariable Long limit,
                                    @RequestBody(required = false) NewsQuery newsQuery){
        //创建page对象
        Page<CenterNews> newsPage = new Page<>(current,limit);
        QueryWrapper<CenterNews> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //动态SQL
        //判断条件是否为空，如果不为空，则将条件进行拼接
        String title = newsQuery.getTitle();
        String begin = newsQuery.getBegin();
        String end = newsQuery.getEnd();

        if (!StringUtils.isEmpty(title)){
            //构建条件
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(begin)) {
            //构建条件
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)) {
            //构建条件
            wrapper.le("gmt_create",end);
        }
        //对数据进行按照时间排序
        wrapper.orderByDesc("gmt_create");

        //调用方法，实现条件查询
        IPage<CenterNews> page = centerNewsService.page(newsPage, wrapper);
        long total = newsPage.getTotal();//总记录数
        List<CenterNews> records = newsPage.getRecords();//每页数据
        return Result.ok().data("total",total).data("rows",records);
    }

    //5. 添加新闻
    @PostMapping("addNews")
    @ApiOperation(value = "添加新闻")
    public Result addNews(@RequestBody CenterNews centerNews){
        boolean save = centerNewsService.save(centerNews);
        if (save){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //6. 修改新闻
    //根据新闻id进行查询
    @GetMapping("getNews/{id}")
    @ApiOperation(value = "根据id查询新闻")
    public Result getNews(@PathVariable String id){
        CenterNews news = centerNewsService.getById(id);
        return Result.ok().data("news",news);
    }

    //7. 新闻修改
    @PostMapping("updateNews")
    @ApiOperation(value = "修改新闻")
    public Result updateNews(@RequestBody CenterNews centerNews){
        boolean update = centerNewsService.updateById(centerNews);
        if (update){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

