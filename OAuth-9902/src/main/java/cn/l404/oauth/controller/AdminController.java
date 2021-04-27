package cn.l404.oauth.controller;

import cn.l404.common.pojo.ResultVO;
import cn.l404.oauth.annotation.HasRoleMapping;
import cn.l404.oauth.annotation.Renewal;
import cn.l404.oauth.entity.SysMenu;
import cn.l404.oauth.entity.SysRm;
import cn.l404.oauth.entity.SysRole;
import cn.l404.oauth.entity.SysUser;
import cn.l404.oauth.service.AdminService;
import cn.l404.oauth.util.RequstUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther Fanxing
 * 管理员操作
 * @Renewal 本类下操作全部执行续签操作
 */


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    RequstUtils requstUtils;
    @Autowired
    AdminService adminService;

    @Renewal
    @HasRoleMapping(value = "/addNewUser",role = "admin")
    public ResultVO addNewUser(@RequestBody SysUser sysUser){
        return adminService.addNewUser(requstUtils.getToken(),sysUser);
    }

    @Renewal
    @HasRoleMapping(value = "/getRoleList",role = "admin")
    public ResultVO getRoleList(){
        return adminService.getRoleList(requstUtils.getToken());
    }

    @Renewal
    @HasRoleMapping(value = "/getMenu",role = "qq",allow = false)
    public ResultVO getMenu() throws Exception {
        return adminService.getMenu(requstUtils.getToken());
    }

    @Renewal
    @HasRoleMapping(value = "/updateUserPassword",role = "admin")
    public ResultVO updateUserPassword(Integer uid,String oldPassWord,String newPassWord){
        return adminService.updateUserPassword(requstUtils.getToken(),uid,oldPassWord,newPassWord);
    }

    @Renewal
    @HasRoleMapping(value = "/addNewRole",role = "admin")
    public ResultVO addNewRole(@RequestBody SysRole sysRole) throws Exception {
        return adminService.addNewRole(requstUtils.getToken(),sysRole);
    }

    @Renewal
    @HasRoleMapping(value = "/addNewMenu",role = "admin")
    public ResultVO addNewMenu(@RequestBody SysMenu sysMenu) throws Exception {
        return adminService.addNewMenu(requstUtils.getToken(),sysMenu);
    }

    @Renewal
    @HasRoleMapping(value = "/updateUserInfo",role = "admin")
    public ResultVO updateUserInfo(@RequestBody SysUser sysUser) throws Exception{
        return adminService.updateUserInfo(requstUtils.getToken(),sysUser);
    }

    @Renewal
    @HasRoleMapping(value = "/updateMyInfo",role = "qq",allow = false)
    public ResultVO updateMyInfo(@RequestBody SysUser sysUser) throws Exception{
        return adminService.updateMyInfo(requstUtils.getToken(),sysUser);
    }

    @Renewal
    @HasRoleMapping(value = "/roleAddNewMenu",role = "admin")
    public ResultVO roleAddNewMenu(@RequestBody SysRm sysRm) throws Exception{
        return adminService.roleAddNewMenu(requstUtils.getToken(),sysRm);
    }

    @Renewal
    @HasRoleMapping(value = "/updateRoleInfo",role = "admin")
    public ResultVO updateRoleInfo(@RequestBody SysRole sysRole) throws Exception{
        return adminService.updateRoleInfo(requstUtils.getToken(), sysRole);
    }

    @Renewal
    @HasRoleMapping(value = "/updateMenuInfo",role = "admin")
    public ResultVO updateMenuInfo(@RequestBody SysMenu sysMenu) throws Exception{
        return adminService.updateMenuInfo(requstUtils.getToken(), sysMenu);
    }

    @Renewal
    @HasRoleMapping(value = "/deleteRmInfo",role = "admin")
    public ResultVO deleteRmInfo(@RequestBody SysRm sysRm) throws Exception{
        return adminService.deleteRmInfo(requstUtils.getToken(),sysRm);
    }
}
