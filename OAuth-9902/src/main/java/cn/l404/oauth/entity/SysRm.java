package cn.l404.oauth.entity;
import java.io.Serializable;

/**
 * auth 鉴权模块实体类1
 * @author Fanxing
 * @since 2021-04-27 10:12:21
 */
public class SysRm implements Serializable {
    private static final long serialVersionUID = -19280430630616620L;

    private Integer rmId;

    private Integer roleId;

    private Integer menuId;


    public Integer getRmId() {
        return rmId;
    }

    public void setRmId(Integer rmId) {
        this.rmId = rmId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

}