package cn.l404.oauth.dao;

import cn.l404.oauth.entity.SysMenu;
import cn.l404.oauth.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * auth 鉴权模块dao接口
 * @author Fanxing
 * @since 2021-04-26 20:00:59
 */
@Mapper
public interface SysMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    public SysMenu findById(Integer menuId);
    
    

    /**
     * 查询所有auth 鉴权模块数据 不分页
     * @return 对象列表
     */
   public List<SysMenu> list();


    /**
     * 查询所有auth 鉴权模块
     * @return
     */
    public List<SysMenu> listAll(@Param("keyword") String keyword);


    /**
     * 保存auth 鉴权模块的方法
     * @param sysMenu
     * @return
     */
    public Integer save(SysMenu sysMenu);


    /**
     * 更新auth 鉴权模块的方法
     * @param sysMenu
     * @return
     */
    public Integer update(SysMenu sysMenu);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysMenu 实例对象
     * @return 对象列表
     */
    List<SysMenu> queryAll(SysMenu sysMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Integer menuId);

    // 根据权限查出来所有的子菜单
    List<SysMenu> selectMenuByRoles(@Param("roleId") Integer roleId);

}