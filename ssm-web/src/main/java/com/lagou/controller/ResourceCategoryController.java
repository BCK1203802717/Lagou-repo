package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        try {
            List<ResourceCategory> categoryList = resourceCategoryService.findAllResourceCategory();
            ResponseResult result = new ResponseResult(true, 200, "查询成功", categoryList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory)
    {
        try {
            ResponseResult result=null;
            if(resourceCategory.getId()!=null){
                resourceCategoryService.updateResourceCategory(resourceCategory);
                result= new ResponseResult(true,200,"资源分类修改成功",null);
            }
            else{
                resourceCategoryService.saveResourceCategory(resourceCategory);
                result=new ResponseResult(true,200,"资源分类添加成功",null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        try {
            resourceCategoryService.deleteResourceCategory(id);
            ResponseResult result = new ResponseResult(true, 200, "删除成功", null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
