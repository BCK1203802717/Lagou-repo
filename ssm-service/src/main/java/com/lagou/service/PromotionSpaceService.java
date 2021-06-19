package com.lagou.service;

import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService{
    /*
    广告列表查询
     */
    public List<PromotionSpace> findAllPromotionSpace();
    /*
    添加广告位
     */
    void savePromotionSpace(PromotionSpace promotionSpace);

    /*
    修改广告位
     */
    void updatePromotionSpace(PromotionSpace promotionSpace);

    /*
    回显广告名称
     */
    PromotionSpace findPromotionSpaceById(Integer id);



}
