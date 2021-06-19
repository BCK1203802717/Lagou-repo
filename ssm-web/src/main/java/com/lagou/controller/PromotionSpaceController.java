package com.lagou.controller;


import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {
    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /*
    查询广告位信息
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        try {
            List<PromotionSpace> promotionSpaceList = promotionSpaceService.findAllPromotionSpace();
            ResponseResult result=null;
            if(promotionSpaceList!=null){
                result=new ResponseResult(true,200,"响应成功！",promotionSpaceList);
            }
            else{
                result=new ResponseResult(true,200,"响应失败！",null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    添加或者修改广告位信息
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        try {
            ResponseResult result=null;
            if(promotionSpace.getId()!=null){
                promotionSpaceService.updatePromotionSpace(promotionSpace);
                result=new ResponseResult(true,200,"修改成功",null);
            }
            else{
                promotionSpaceService.savePromotionSpace(promotionSpace);
                result=new ResponseResult(true,200,"保存成功",null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    回显广告位名称
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam Integer id){
        try {
            PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
            ResponseResult result=new ResponseResult(true,200,"响应成功",promotionSpace);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
