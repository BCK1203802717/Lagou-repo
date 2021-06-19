package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {
    /*
    用户分页&查询
     */
    PageInfo findAllUserByPage(UserVo userVo);

    /*
    用户状态设置
     */
    void updateUserStatus(Integer id,String status);
    /*
    用户登陆
     */
    User login(User user) throws Exception;
    /*
    角色回显
     */
    List<Role> findUserRelationRoleById(Integer id);
    /*
    用户关联角色
    */
    void userContextRole(UserVo userVo);
    /*
     获取用户权限
    */
    ResponseResult getUserPermissions(Integer id);
}
