package cn.l404.oauth.entity;

import java.io.Serializable;
import java.util.List;

/**
 * auth 鉴权模块实体类1
 * @author Fanxing
 * @since 2021-04-26 20:00:59
 */
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 938408669901309848L;
    
    private Integer menuId;
    
    private String menuName;
    
    private String menuIcon;
    
    private String menuUrl;

    private Integer menuTop;

    private List<SysMenu> sysMenus;
    /**
    * 是否启用
    */
    private Integer menuStatus;

    public List<SysMenu> getSysMenus() {
        return sysMenus;
    }

    public void setSysMenus(List<SysMenu> sysMenus) {
        this.sysMenus = sysMenus;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
        
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
        
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
        
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
        
    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public Integer getMenuTop() {
        return menuTop;
    }

    public void setMenuTop(Integer menuTop) {
        this.menuTop = menuTop;
    }
}