package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserControl {

    @Autowired
    private UserService userService;
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        try {
            PageInfo userByPage = userService.findAllUserByPage(userVo);
            ResponseResult result = new ResponseResult(true, 200, "用户查询成功！", userByPage);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){
        try {
            userService.updateUserStatus(id,status);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", status);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
    用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest  request) throws Exception {
        try {
            User user1 = userService.login(user);
            ResponseResult result=null;
            if(user1!=null){
                HttpSession session = request.getSession();
                String access_token= UUID.randomUUID().toString();
                session.setAttribute("access_token",access_token);
                session.setAttribute("user_id",user1.getId());
                Map<String, Object> map = new HashMap<>();
                map.put("access_token",access_token);
                map.put("user_id",user1.getId());
                result=new ResponseResult(true,1,"响应成功",map);
            }else{
                result=new ResponseResult(true,1,"用户名密码错误",null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    分配角色回显
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        try {
            List<Role> roleList = userService.findUserRelationRoleById(id);
            ResponseResult result = new ResponseResult(true, 200, "查询成功", roleList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    分配用户角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        try {
            userService.userContextRole(userVo);
            ResponseResult result = new ResponseResult(true, 200, "添加成功", null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        try {
            //获取请求头中token
            String token = request.getHeader("Authorization");
            //获取sessionh中的token
            HttpSession session = request.getSession();
            String access_token = (String) session.getAttribute("access_token");
            //判断
            if(token.equals(access_token)){
                Integer user_id = (Integer) session.getAttribute("user_id");
                ResponseResult result = userService.getUserPermissions(user_id);
                return result;
            }
            else{
                ResponseResult result = new ResponseResult(false, 400, "获取失败", null);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
