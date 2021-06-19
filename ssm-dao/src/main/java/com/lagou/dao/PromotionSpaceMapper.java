package com.lagou.dao;

import com.lagou.domain.PromotionSpace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionSpaceMapper {

    /*
    广告列表查询
     */
    List<PromotionSpace> findAllPromotionSpace();

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
