package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    /*
    用户分页&查询
     */
    List<User> findAllUserByPage(UserVo userVo);

    /*
    用户状态设置
     */
    void updateUserStatus(User user);
    /*
    用户登陆
     */
    User login(User user);

    /*
    角色回显
     */
    List<Role> findUserRelationRoleById(Integer id);
    /*
    根据用户ID清空中间表
    */
    void deleteUserContextRole(Integer userId);
    /*
    分配角色
    */
    void userContextRole(User_Role_relation user_role_relation);
    /*
    根据id查询顶级菜单
     */
    List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
    根据id查询子级菜单
     */
    List<Menu> findSubMenuByPid(int pid);

    /*
    获取用户拥有的资源权限信息
     */
    List<Resource> findResourceByRoleId(List<Integer> ids);


}
