package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
    查询所有菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        try {
            List<Menu> menuList = menuService.findAllMenu();
            ResponseResult result = new ResponseResult(true, 200, "响应成功", menuList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    查询菜单信息回显
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam Integer id){
        if(id==-1){
            //添加操作，回显不需要查询menu信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(id);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", subMenuListByPid);
            return result;
        }
        else{
            Menu menu = menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(id);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        }
    }

    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        try {
            if(menu.getId()!=null){
                menuService.updateMenu(menu);
                ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
                return result;
            }
            else{
                menuService.saveMenu(menu);
                ResponseResult result = new ResponseResult(true, 200, "添加成功", null);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
