package cn.l404.oauth.service;

import cn.l404.common.pojo.ResultVO;
import cn.l404.oauth.entity.SysRole;
import cn.l404.oauth.entity.SysUser;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    // 添加新的用户（id为1的权限）
    ResultVO addNewUser(String access_token,SysUser sysUser);

    //获取权限列表，需要最终管理员（id为1的权限名称）
    ResultVO getRoleList(String access_token);

    // 获取当前登录后台用户的菜单（除了不是前台用户的qq权限，其他都可以）
    ResultVO getMenu(String access_token);

    // 修改指定后台用户的密码（需要id为1的权限）
    ResultVO updateUserPassword(String access_token,Integer uid,String oldpassWord,String newPassword);

    // 添加新的权限 （需要id为1的权限）
    ResultVO addNewRole(String access_token, SysRole sysRole);

    // 添加新菜单
    //ResultVO addNewMenu(String access_token,)
}
