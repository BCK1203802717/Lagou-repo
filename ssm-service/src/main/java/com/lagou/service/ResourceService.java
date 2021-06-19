package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceService {
    /*
    条件查询资源信息
     */
    PageInfo<Resource> findAllResource(ResourceVO resourceVO);
    /*
    添加资源
     */
    void saveResource(Resource resource);
    /*
    修改资源
     */
    void updateResource(Resource resource);
    /*
    删除资源
     */
    void deleteResource(Integer id);
}
