package cn.l404.oauth.service.impl;

import cn.l404.common.pojo.ResultVO;
import cn.l404.common.util.PasswordUtils;
import cn.l404.oauth.dao.SysMenuDao;
import cn.l404.oauth.dao.SysRmDao;
import cn.l404.oauth.dao.SysRoleDao;
import cn.l404.oauth.dao.SysUserDao;
import cn.l404.oauth.entity.SysMenu;
import cn.l404.oauth.entity.SysRm;
import cn.l404.oauth.entity.SysRole;
import cn.l404.oauth.entity.SysUser;
import cn.l404.oauth.service.AdminService;
import cn.l404.oauth.util.JWTUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
    @Autowired
    SysMenuDao sysMenuDao;
    @Autowired
    SysRmDao sysRmDao;

    @Autowired
    JWTUtils jwtUtils;

    @Override
    public ResultVO addNewUser(String access_token, SysUser sysUser) {
        // 校验权限
//        ResultVO<Boolean> result = jwtUtils.Vercheck(access_token,adminRole);
//        if(!result.getData()){
//            // 权限校验未通过
//            return result;
//        }
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
//        ResultVO<Boolean> result = jwtUtils.Vercheck(access_token,adminRole);
//        if(!result.getData()){
//            // 权限校验未通过
//            return result;
//        }
        return new ResultVO(2000,"ok",sysRoleDao.list());
    }



    // 获取用户菜单
    @Override
    public ResultVO getMenu(String access_token) throws Exception {
       // selectMenuByRoles
        ResultVO res = jwtUtils.getTokenInfo(access_token);
        System.out.println(res.getData());
        if (res.getCode() != 2000){
            throw new Exception("access_token失效，请重新登录");
        }
        Integer roleId = JSON.parseObject(res.getData().toString()).getInteger("userRole");
        return new ResultVO(2000,"ok",sysMenuDao.selectMenuByRoles(roleId));
    }

    @Override
    public ResultVO updateUserPassword(String access_token, Integer uid, String oldpassWord, String newPassword) {
        oldpassWord = PasswordUtils.encypt(oldpassWord);
        newPassword = PasswordUtils.encypt(newPassword);
//
        SysUser sysUser = new SysUser();
        sysUser.setUserId(uid);
        sysUser.setUserPassword(oldpassWord);
        System.out.println(sysUser.toString());
        //判断sysUser是否存在
        List<SysUser> userList = sysUserDao.queryAll(sysUser);
        System.out.println("这里是修改指定密码的：" + userList.toString());
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
    public ResultVO addNewRole(String access_token, SysRole sysRole) throws Exception {
        Integer res = sysRoleDao.save(sysRole);
        if(res == 0){
           throw new Exception("添加失败，请重新尝试");
        }
        sysRole.setRoleStatus(1);
        return new ResultVO(2000,"ok",sysRole);
    }

    // 添加路由 （权限id为1）
    @Override
    public ResultVO addNewMenu(String access_token, SysMenu sysMenu) throws Exception {
        Integer res = sysMenuDao.save(sysMenu);
        if(res == 0){
            throw new Exception("添加失败，请重新尝试");
        }

        sysMenu.setMenuStatus(1);
        return new ResultVO(2000,"ok",sysMenu);

    }

    // 修改用户的信息
    @Override
    public ResultVO updateUserInfo(String access_token, SysUser sysUser) throws Exception {
        if(updateinfo(sysUser)){
            return new ResultVO(2000,"ok");
        }else{
            throw new Exception("修改用户信息服务异常");
        }
    }

    @Override
    public ResultVO updateMyInfo(String access_token, SysUser sysUser) throws Exception {
        ResultVO res = jwtUtils.getTokenInfo(access_token);
        System.out.println(res.getData());
        if (res.getCode() != 2000){
            throw new Exception("access_token失效，请重新登录");
        }
        Integer id = JSON.parseObject(res.getData().toString()).getInteger("userId");
        // 修改成自己的id
        sysUser.setUserId(id);
        if(updateinfo(sysUser)){
            return new ResultVO(2000,"ok");
        }else{
           throw new Exception("用户信息服务异常，请稍后重试");
        }
    }

    @Override
    public ResultVO roleAddNewMenu(String access_token, SysRm sysRm) throws Exception {
        Integer res = sysRmDao.save(sysRm);
        if(res > 0){
            return new ResultVO(2000,"ok");
        }else{
            throw new Exception("用户信息服务异常，请稍后重试");
        }
    }

    @Override
    public ResultVO updateRoleInfo(String access_token,SysRole sysRole) throws Exception {
        Integer res = sysRoleDao.update(sysRole);
        if(res == 0){
            throw new Exception("角色信息未执行，请稍后重试");
        }
        return new ResultVO(2000,"ok");
    }

    @Override
    public ResultVO updateMenuInfo(String access_token, SysMenu sysMenu) throws Exception {

        Integer res = sysMenuDao.update(sysMenu);
        if(res == 0){
            throw new Exception("修改菜单信息未执行，请稍后重试");
        }
        return new ResultVO(2000,"ok");
    }

    @Override
    public ResultVO deleteRmInfo(String access_token, SysRm sysRm) throws Exception {
        System.out.println("删除映射id："+ sysRm.getRmId());
        Integer res = sysRmDao.deleteById(sysRm.getRmId());
        if(res == 0){
            throw new Exception("删除角色菜单映射关系未执行，请稍后重试");
        }
        return new ResultVO(2000,"ok");
    }

    // 修改用户信息。。。
    public boolean updateinfo(SysUser sysUser){
        System.out.println("修改用户信息：" + sysUser);
        Integer res = sysUserDao.update(sysUser);
        if(res == 0){
            return false;
        }
        return true;
    }
}
