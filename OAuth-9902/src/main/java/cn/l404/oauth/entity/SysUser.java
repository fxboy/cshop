package cn.l404.oauth.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * auth 鉴权模块实体类1
 * @author Fanxing
 * @since 2021-04-26 19:00:23
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -96110494608511139L;
    
    private Integer userId;
    /**
    * 用户名
    */
    private String userUsername;
    /**
    * 密码
    */
    private String userPassword;
    /**
    * 账号姓名
    */
    private String userName;
    /**
    * 手机号
    */
    private String userPhone;
    /**
    * 拥有的权限
    */
    private Integer userRole;
    private SysRole sysRole;
    /**
    * 头像
    */
    private String userAvatar;
    /**
    * 创建时间
    */
    private Date userCreate;
    /**
    * 修改时间
    */
    private Date userUpdate;
    /**
    * 0. 启用 1.禁用
    */
    private Integer userStatus;
    /**
    * 账号登录有效期，只要是2001年12月17过期的都是永远可以登录
    */
    private Date userValidity;

        
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
        
    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
        
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
        
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
        
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
        
    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
        
    public Date getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Date userCreate) {
        this.userCreate = userCreate;
    }
        
    public Date getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Date userUpdate) {
        this.userUpdate = userUpdate;
    }
        
    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
        
    public Date getUserValidity() {
        return userValidity;
    }

    public void setUserValidity(Date userValidity) {
        this.userValidity = userValidity;
    }

}