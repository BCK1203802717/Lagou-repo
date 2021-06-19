package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /*
    资源信息分页希纳是&条件吗查询
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO){
        try {
            PageInfo<Resource> allResource = resourceService.findAllResource(resourceVO);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", allResource);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
    添加和修改资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        try {
            if(resource.getId()!=null){
                resourceService.updateResource(resource);
                ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
                return result;
            }
            else {
                resourceService.saveResource(resource);
                ResponseResult result = new ResponseResult(true, 200, "保存成功", null);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    删除资源信息
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(@RequestParam Integer id){
        try {
            resourceService.deleteResource(id);
            ResponseResult result = new ResponseResult(true, 200, "删除成功", null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
