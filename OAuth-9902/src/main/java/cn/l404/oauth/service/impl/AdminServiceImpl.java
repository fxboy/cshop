package cn.l404.oauth.service.impl;

import cn.l404.common.pojo.ResultVO;
import cn.l404.common.util.PasswordUtils;
import cn.l404.oauth.dao.SysRoleDao;
import cn.l404.oauth.dao.SysUserDao;
import cn.l404.oauth.entity.SysMenu;
import cn.l404.oauth.entity.SysRole;
import cn.l404.oauth.entity.SysUser;
import cn.l404.oauth.service.AdminService;
import cn.l404.oauth.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

/**
 * @auther Fanxing
 * 后台敏感操作接口实现Service层
 */
@Service("admin")
public class AdminServiceImpl implements AdminService {
    @Value("${roles.name}")
    String adminRole;

    @Autowired
    SysUserDao sysUserDao;
    @Autowired
    SysRoleDao sysRoleDao;

    @Override
    public ResultVO addNewUser(String access_token, SysUser sysUser) {
        // 校验权限
        ResultVO<Boolean> result = JWTUtils.Vercheck(access_token,adminRole);
        if(!result.getData()){
            // 权限校验未通过
            return result;
        }
        sysUser.setUserPassword(PasswordUtils.encypt(sysUser.getUserPassword()));
        Integer res = sysUserDao.save(sysUser);
        if(res == 0){
            return new ResultVO(5000,"error[0]:Integer is 0");
        }
        sysUser.setUserStatus(1);
        return new ResultVO(2000,"ok",sysUser);
    }

    @Override
    public ResultVO getRoleList(String access_token) {
        ResultVO<Boolean> result = JWTUtils.Vercheck(access_token,adminRole);
        if(!result.getData()){
            // 权限校验未通过
            return result;
        }
        return new ResultVO(2000,"ok",sysRoleDao.list());
    }

    @Override
    public ResultVO getMenu(String access_token) {
        return null;
    }

    @Override
    public ResultVO updateUserPassword(String access_token, Integer uid, String oldpassWord, String newPassword) {
        oldpassWord = PasswordUtils.encypt(oldpassWord);
        newPassword = PasswordUtils.encypt(newPassword);
        ResultVO<Boolean> result = JWTUtils.Vercheck(access_token,adminRole);
        if(!result.getData()){
            // 权限校验未通过
            return result;
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserId(uid);
        sysUser.setUserPassword(oldpassWord);
        //判断sysUser是否存在
        List<SysUser> userList = sysUserDao.queryAll(sysUser);
        if(userList.size() ==  0){
            return new ResultVO(5000,"旧密码错误");
        }
        sysUser.setUserPassword(newPassword);
        Integer res = sysUserDao.update(sysUser);
        if(res == 0){
            return new ResultVO(5000,"error[0]:Integer is 0");
        }
        return new ResultVO(2000,"ok");
    }

    @Override
    public ResultVO addNewRole(String access_token, SysRole sysRole) {
        ResultVO<Boolean> result = JWTUtils.Vercheck(access_token,adminRole);
        if(!result.getData()){
            // 权限校验未通过
            return result;
        }
        Integer res = sysRoleDao.save(sysRole);
        if(res == 0){
            return new ResultVO(5000,"error[0]:Integer is 0");
        }
        sysRole.setRoleStatus(1);
        return new ResultVO(2000,"ok",sysRole);
    }

    // 暂时不写添加路由
    @Override
    public ResultVO addNewMenu(String access_token, SysMenu sysMenu) {
        return null;
    }
}
