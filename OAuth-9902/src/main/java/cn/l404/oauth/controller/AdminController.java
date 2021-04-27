package cn.l404.oauth.controller;

import cn.l404.common.pojo.ResultVO;
import cn.l404.oauth.annotation.Renewal;
import cn.l404.oauth.entity.SysMenu;
import cn.l404.oauth.entity.SysRm;
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
@Renewal
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
    public ResultVO updateUserPassword(Integer uid,String oldpassWord,String newPassword){
        return adminService.updateUserPassword(requstUtils.getToken(),uid,oldpassWord,newPassword);
    }
    // 添加新的权限 （需要id为1的权限）
    @RequestMapping("/addNewRole")
    public ResultVO addNewRole(SysRole sysRole) throws Exception {
        return adminService.addNewRole(requstUtils.getToken(),sysRole);
    }
    // 添加新菜单 (需要id为1的权限)
    @RequestMapping("/addNewMenu")
    public ResultVO addNewMenu(SysMenu sysMenu) throws Exception {
        return adminService.addNewMenu(requstUtils.getToken(),sysMenu);
    }

    @RequestMapping("/updateUserInfo")
    // 修改后台用户的信息（需要id为1的权限）
    public ResultVO updateUserInfo(SysUser sysUser) throws Exception{
        return adminService.updateUserInfo(requstUtils.getToken(),sysUser);
    }
    @RequestMapping("/updateMyInfo")
    // 修改我的个人信息 需要获取token中的用户id，包括我的登录密码
    public ResultVO updateMyInfo(SysUser sysUser) throws Exception{
        return adminService.updateMyInfo(requstUtils.getToken(),sysUser);
    }
    @RequestMapping("/roleAddNewMenu")
    //将menu菜单添加给某个权限
    public ResultVO roleAddNewMenu(SysRm sysRm) throws Exception{
        return adminService.roleAddNewMenu(requstUtils.getToken(),sysRm);
    }
    @RequestMapping("/updateRoleInfo")
    // 修改权限信息
    public ResultVO updateRoleInfo(SysRole sysRole) throws Exception{
        return adminService.updateRoleInfo(requstUtils.getToken(), sysRole);
    }
    @RequestMapping("/updateMenuInfo")
    // 修改菜单信息
    public ResultVO updateMenuInfo(SysMenu sysMenu) throws Exception{
        return adminService.updateMenuInfo(requstUtils.getToken(), sysMenu);
    }
    @RequestMapping("/deleteRmInfo")
    //删除一个权限菜单映射
    public ResultVO deleteRmInfo(SysRm sysRm) throws Exception{
        return adminService.deleteRmInfo(requstUtils.getToken(),sysRm);
    }
}
