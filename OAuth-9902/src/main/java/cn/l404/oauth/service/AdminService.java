package cn.l404.oauth.service;

import cn.l404.common.pojo.ResultVO;
import cn.l404.oauth.entity.SysMenu;
import cn.l404.oauth.entity.SysRm;
import cn.l404.oauth.entity.SysRole;
import cn.l404.oauth.entity.SysUser;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;

@Service
public interface AdminService {
    // 添加新的用户（id为1的权限）
    ResultVO addNewUser(String access_token,SysUser sysUser);

    //获取权限列表，需要最终管理员（id为1的权限名称）
    ResultVO getRoleList(String access_token);

    // 获取当前登录后台用户的菜单（除了不是前台用户的qq权限，其他都可以）
    ResultVO getMenu(String access_token) throws Exception;

    // 修改指定后台用户的密码（需要id为1的权限）,可以做为重置密码
    ResultVO updateUserPassword(String access_token,Integer uid,String oldpassWord,String newPassword);

    // 添加新的权限 （需要id为1的权限）
    ResultVO addNewRole(String access_token, SysRole sysRole) throws Exception;

    // 添加新菜单 (需要id为1的权限)
    ResultVO addNewMenu(String access_token, SysMenu sysMenu) throws Exception;

    // 修改后台用户的信息（需要id为1的权限）
    ResultVO updateUserInfo(String access_token,SysUser sysUser) throws Exception;

    // 修改我的个人信息 需要获取token中的用户id，包括我的登录密码
    ResultVO updateMyInfo(String access_token,SysUser sysUser) throws Exception;


    //将menu菜单添加给某个权限
    ResultVO roleAddNewMenu(String access_token, SysRm sysRm) throws Exception;
    // 修改权限信息
    ResultVO updateRoleInfo(String access_token,SysRole sysRole) throws Exception;
    // 修改菜单信息
    ResultVO updateMenuInfo(String access_token,SysMenu sysMenu) throws Exception;
    //删除一个权限菜单映射
    ResultVO deleteRmInfo(String access_token,SysRm sysRm) throws Exception;
}
