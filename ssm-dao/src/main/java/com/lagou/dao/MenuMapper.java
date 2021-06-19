package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*
    查询全部父子菜单信息
     */
    List<Menu> findSubMenuListByPid(Integer id);

    /*
    根据角色id查询关联菜单ID
     */
    List<String> findMenuByRoleId(Integer id);

    /*
    查询菜单列表
     */
    List<Menu> findAllMenu();
    /*
    回显菜单信息
     */
    List<Menu> findMenuInfoById(Integer id);

    /*
    查询菜单信息
     */
    Menu findMenuById(Integer id);

    /*
    添加菜单
     */
    void saveMenu(Menu menu);
    /*
    修改菜单
     */
    void updateMenu(Menu menu);
}
