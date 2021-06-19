package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        try {
            List<Role> roleList = roleService.findAllRole(role);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", roleList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        try {
            ResponseResult result=null;
            if(role.getId()!=null){
                roleService.updateRole(role);
                result=new ResponseResult(true,200,"修改成功",null);
            }
            else{
                roleService.saveRole(role);
                result=new ResponseResult(true,200,"添加成功",null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        try {
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", menuList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        try {
            List<String> list = menuService.findMenuByRoleId(roleId);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        try {
            roleService.RoleContextMenu(roleMenuVo);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        try {
            roleService.deleteRole(id);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        try {
            List<ResourceCategory> resourceCategoryList = roleService.findResourceListByRoleId(roleId);
            ResponseResult result = new ResponseResult(true, 200, "查询成功", resourceCategoryList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){
        try {
            roleService.RoleContextResource(roleResourceVo);
            ResponseResult result = new ResponseResult(true, 200, "分配成功", null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
