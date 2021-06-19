package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {
    /*
    角色列表查询
     */
    List<Role> findAllRole(Role role);

    /*
    添加角色信息
     */
    void saveRole(Role role);

    /*
    删除
     */
    void updateRole(Role role);
    /*
    分配角色菜单
     */
    void RoleContextMenu(RoleMenuVo roleMenuVo);
    /*
    删除角色
     */
    void deleteRole(Integer id);

    /*
    获取当前角色所拥有的资源信息
     */
    List<ResourceCategory> findResourceListByRoleId(Integer id);

    /*
    分配角色菜单
     */
    void RoleContextResource(RoleResourceVo roleResourceVo);
}
