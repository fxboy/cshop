package cn.l404.oauth.dao;

import cn.l404.oauth.entity.SysRm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * auth 鉴权模块dao接口
 * @author Fanxing
 * @since 2021-04-27 10:12:22
 */
@Mapper
public interface SysRmDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rmId 主键
     * @return 实例对象
     */
    public SysRm findById(Integer rmId);
    
    

    /**
     * 查询所有auth 鉴权模块数据 不分页
     * @return 对象列表
     */
   public List<SysRm> list();


    /**
     * 查询所有auth 鉴权模块
     * @return
     */
    public List<SysRm> listAll(@Param("keyword") String keyword);


    /**
     * 保存auth 鉴权模块的方法
     * @param sysRm
     * @return
     */
    public Integer save(SysRm sysRm);


    /**
     * 更新auth 鉴权模块的方法
     * @param sysRm
     * @return
     */
    public Integer update(SysRm sysRm);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRm 实例对象
     * @return 对象列表
     */
    List<SysRm> queryAll(SysRm sysRm);

    /**
     * 通过主键删除数据
     *
     * @param rmId 主键
     * @return 影响行数
     */
    int deleteById(Integer rmId);

}