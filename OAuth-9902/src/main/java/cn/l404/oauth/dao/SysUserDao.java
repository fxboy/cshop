package cn.l404.oauth.dao;

import cn.l404.oauth.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * auth 鉴权模块dao接口
 * @author Fanxing
 * @since 2021-04-26 19:00:23
 */
@Mapper
public interface SysUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    public SysUser findById(Integer userId);
    
    

    /**
     * 查询所有auth 鉴权模块数据 不分页
     * @return 对象列表
     */
   public List<SysUser> list();


    /**
     * 查询所有auth 鉴权模块
     * @return
     */
    public List<SysUser> listAll(@Param("keyword") String keyword);

    public SysUser login(@Param("username") String username,@Param("password")String password);


    /**
     * 保存auth 鉴权模块的方法
     * @param sysUser
     * @return
     */
    public Integer save(SysUser sysUser);


    /**
     * 更新auth 鉴权模块的方法
     * @param sysUser
     * @return
     */
    public Integer update(SysUser sysUser);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUser 实例对象
     * @return 对象列表
     */
    List<SysUser> queryAll(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

}