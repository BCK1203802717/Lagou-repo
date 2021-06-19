package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

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
    删除角色关联菜单
     */

    void deleteRoleContextMenu(Integer id);

    /*
    分配角色菜单
     */
    void RoleContextMenu(Role_menu_relation role_menu_relation);

    /*
    删除角色
     */
    void deleteRole(Integer id);

    /*
    查询角色所拥有的资源信息
     */
    List<Resource> findRoleResource(Integer id);
    /*
    角色拥有资源分类信息
     */
    List<ResourceCategory> findRoleResourceCategory(List<Integer> ids);

    /*
    删除角色资源
     */
    void deleteRoleContextResource(Integer id);
    /*
    添加角色资源
     */
    void RoleContextResource(Role_resource_relation role_resource_relation);
}
