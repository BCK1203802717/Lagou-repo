package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> findSubMenuListByPid(Integer id) {
        List<Menu> menuList= menuMapper.findSubMenuListByPid(id);
        return menuList;
    }

    @Override
    public List<String> findMenuByRoleId(Integer id) {
        List<String> list = menuMapper.findMenuByRoleId(id);
        return list;
    }

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> menuList = menuMapper.findAllMenu();
        return menuList;
    }

    @Override
    public List<Menu> findMenuInfoById(Integer id) {
        List<Menu> menuList = menuMapper.findMenuInfoById(id);
        return menuList;
    }

    @Override
    public Menu findMenuById(Integer id) {
        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }

    @Override
    public void saveMenu(Menu menu) {
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedBy("system");
        menu.setUpdatedTime(new Date());
        menuMapper.updateMenu(menu);
    }
}
