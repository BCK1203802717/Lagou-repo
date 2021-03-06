package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roleList = roleMapper.findAllRole(role);

        return roleList;
    }

    @Override
    public void saveRole(Role role) {
        Date date = new Date();
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        roleMapper.saveRole(role);
    }



    @Override
    public void updateRole(Role role) {
        Date date = new Date();
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        role.setUpdatedTime(date);
        roleMapper.updateRole(role);
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, timeout = -1, readOnly = false)
    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            roleMapper.RoleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRoleContextMenu(id);
        roleMapper.deleteRole(id);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer id) {
        //??????????????????????????????
        List<Resource> resourceList = roleMapper.findRoleResource(id);

        List<ResourceCategory> resourceCategoryList=null;

        //??????????????????
        if(resourceList!=null && resourceList.size()>0) {
            //????????????ID
            List<Integer> ids = new ArrayList<Integer>();
            for (Resource resource : resourceList) {
                ids.add(resource.getCategoryId());
            }


            resourceCategoryList = roleMapper.findRoleResourceCategory(ids);
            //?????????????????????????????????????????????
            for (ResourceCategory resourceCategory : resourceCategoryList) {
                List<Resource> resourceListNew = new ArrayList<Resource>();
                for (Resource resource : resourceList) {
                    if (resource.getCategoryId() == resourceCategory.getId()) {
                        resourceListNew.add(resource);
                    }
                }
                resourceCategory.setResourceList(resourceListNew);
            }
        }
        return resourceCategoryList;
    }
    /*
    ????????????????????????

    REQUIRED???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    Isolation.REPEATABLE_READ???????????????
    read-only???????????????????????????????????????????????????
    timeout?????????????????????????????????-1???????????????????????????????????????????????????????????????
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, timeout = -1, readOnly = false)
    @Override
    public void RoleContextResource(RoleResourceVo roleResourceVo) {
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());

        for (Integer id:roleResourceVo.getResourceIdList()){
            Role_resource_relation role_resource_relation = new Role_resource_relation();
            role_resource_relation.setResourceId(id);
            role_resource_relation.setRoleId(roleResourceVo.getRoleId());
            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedBy("system");
            role_resource_relation.setCreatedTime(new Date());
            role_resource_relation.setUpdatedTime(new Date());
            roleMapper.RoleContextResource(role_resource_relation);
        }
    }
}
