package com.lagou.dao;

import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdMapper {
    /*
    分页显示广告信息
     */
    List<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);


    /*
    新建广告信息
     */
    void savePromotionAd(PromotionAd promotionAd);
    /*
    修改广告信息
     */
    void updatePromotionAd(PromotionAd promotionAd);

    /*
    回显广告信息
     */
    PromotionAd findPromotionAdById(Integer id);

    /*
    修改广告状态
     */
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
