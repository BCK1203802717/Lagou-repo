package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVo);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }

    @Override
    public void updateUserStatus(Integer id,String status) {

        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {
        User userLogin = userMapper.login(user);
        if(userLogin!=null && Md5.verify(user.getPassword(),"lagou",userLogin.getPassword())){
            return userLogin;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        return roleList;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //根据用户id清空用户角色
        userMapper.deleteUserContextRole(userVo.getUserId());
        for(Integer roleId:userVo.getRoleIdList()){
            User_Role_relation user_role_relation=new User_Role_relation();
            user_role_relation.setRoleId(roleId);
            user_role_relation.setUserId(userVo.getUserId());
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setUpdatedby("system");
            user_role_relation.setCreatedBy("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        //获取当前用户所有角色
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        List<Integer> list = new ArrayList<>();
        //将用户保存到list中
        for(Role role:roleList){
            list.add(role.getId());
        }
        //根据id查询父菜单
        List<Menu> parentList = userMapper.findParentMenuByRoleId(list);

        //封装父菜单中的子菜单
        for(Menu menu:parentList){
            List<Menu> menuList = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(menuList);
        }
        //获取资源权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentList);
        map.put("resourceList",resourceList);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }


}
