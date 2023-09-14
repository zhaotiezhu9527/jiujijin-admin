package com.juhai.business.service;

import java.util.List;
import com.juhai.business.domain.Yijian;

/**
 * 意见箱Service接口
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
public interface IYijianService 
{
    /**
     * 查询意见箱
     * 
     * @param id 意见箱主键
     * @return 意见箱
     */
    public Yijian selectYijianById(Long id);

    /**
     * 查询意见箱列表
     * 
     * @param yijian 意见箱
     * @return 意见箱集合
     */
    public List<Yijian> selectYijianList(Yijian yijian);

    /**
     * 新增意见箱
     * 
     * @param yijian 意见箱
     * @return 结果
     */
    public int insertYijian(Yijian yijian);

    /**
     * 修改意见箱
     * 
     * @param yijian 意见箱
     * @return 结果
     */
    public int updateYijian(Yijian yijian);

    /**
     * 批量删除意见箱
     * 
     * @param ids 需要删除的意见箱主键集合
     * @return 结果
     */
    public int deleteYijianByIds(Long[] ids);

    /**
     * 删除意见箱信息
     * 
     * @param id 意见箱主键
     * @return 结果
     */
    public int deleteYijianById(Long id);
}
