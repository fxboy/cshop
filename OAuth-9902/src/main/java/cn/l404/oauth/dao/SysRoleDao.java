package cn.l404.oauth.dao;

import cn.l404.oauth.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * auth 鉴权模块dao接口
 * @author Fanxing
 * @since 2021-04-26 19:00:19
 */
@Mapper
public interface SysRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    public SysRole findById(Integer roleId);
    
    

    /**
     * 查询所有auth 鉴权模块数据 不分页
     * @return 对象列表
     */
   public List<SysRole> list();


    /**
     * 查询所有auth 鉴权模块
     * @return
     */
    public List<SysRole> listAll(@Param("keyword") String keyword);


    /**
     * 保存auth 鉴权模块的方法
     * @param sysRole
     * @return
     */
    public Integer save(SysRole sysRole);


    /**
     * 更新auth 鉴权模块的方法
     * @param sysRole
     * @return
     */
    public Integer update(SysRole sysRole);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRole 实例对象
     * @return 对象列表
     */
    List<SysRole> queryAll(SysRole sysRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Integer roleId);

}