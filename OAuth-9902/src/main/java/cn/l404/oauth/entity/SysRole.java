package cn.l404.oauth.entity;

import java.io.Serializable;

/**
 * auth 鉴权模块实体类1
 * @author Fanxing
 * @since 2021-04-26 19:00:16
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = 488276235226469687L;
    
    private Integer roleId;
    
    private String roleName;
    /**
    * 是否启用
    */
    private Integer roleStatus;

        
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
        
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
        
    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

}