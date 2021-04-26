package cn.l404.oauth.controller;

import cn.l404.common.pojo.ResultVO;
import cn.l404.oauth.entity.SysMenu;
import cn.l404.oauth.entity.SysRole;
import cn.l404.oauth.entity.SysUser;
import cn.l404.oauth.service.AdminService;
import cn.l404.oauth.util.RequstUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther Fanxing
 * 管理员操作
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    RequstUtils requstUtils;
    @Autowired
    AdminService adminService;

    // 添加新的用户（id为1的权限）
    @RequestMapping("/addNewUser")
    public ResultVO addNewUser(SysUser sysUser){
        return adminService.addNewUser(requstUtils.getToken(),sysUser);
    }

    //获取权限列表，需要最终管理员（id为1的权限名称）
    @RequestMapping("/getRoleList")
    public ResultVO getRoleList(){
        return adminService.getRoleList(requstUtils.getToken());
    }

    // 获取当前登录后台用户的菜单（除了不是前台用户的qq权限，其他都可以）
    @RequestMapping("/getMenu")
    public ResultVO getMenu(){
        return adminService.getMenu(requstUtils.getToken());
    }

    // 修改指定后台用户的密码（需要id为1的权限）
    @RequestMapping("/updateUserPassword")
    public ResultVO updateUserPassword(String access_token,Integer uid,String oldpassWord,String newPassword){
        return adminService.updateUserPassword(requstUtils.getToken(),uid,oldpassWord,newPassword);
    }

    // 添加新的权限 （需要id为1的权限）
    @RequestMapping("/addNewRole")
    public ResultVO addNewRole(String access_token, SysRole sysRole){
        return adminService.addNewRole(requstUtils.getToken(),sysRole);
    }

    // 添加新菜单 (需要id为1的权限)
    @RequestMapping("/addNewMenu")
    public ResultVO addNewMenu(String access_token, SysMenu sysMenu){
        return adminService.addNewMenu(requstUtils.getToken(),sysMenu);
    }
}
