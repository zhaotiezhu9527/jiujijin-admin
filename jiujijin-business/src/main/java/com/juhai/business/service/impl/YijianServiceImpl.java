package com.juhai.business.service.impl;

import java.util.List;
import com.juhai.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.juhai.business.mapper.YijianMapper;
import com.juhai.business.domain.Yijian;
import com.juhai.business.service.IYijianService;

/**
 * 意见箱Service业务层处理
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
@Service
public class YijianServiceImpl implements IYijianService 
{
    @Autowired
    private YijianMapper yijianMapper;

    /**
     * 查询意见箱
     * 
     * @param id 意见箱主键
     * @return 意见箱
     */
    @Override
    public Yijian selectYijianById(Long id)
    {
        return yijianMapper.selectYijianById(id);
    }

    /**
     * 查询意见箱列表
     * 
     * @param yijian 意见箱
     * @return 意见箱
     */
    @Override
    public List<Yijian> selectYijianList(Yijian yijian)
    {
        return yijianMapper.selectYijianList(yijian);
    }

    /**
     * 新增意见箱
     * 
     * @param yijian 意见箱
     * @return 结果
     */
    @Override
    public int insertYijian(Yijian yijian)
    {
        yijian.setCreateTime(DateUtils.getNowDate());
        return yijianMapper.insertYijian(yijian);
    }

    /**
     * 修改意见箱
     * 
     * @param yijian 意见箱
     * @return 结果
     */
    @Override
    public int updateYijian(Yijian yijian)
    {
        return yijianMapper.updateYijian(yijian);
    }

    /**
     * 批量删除意见箱
     * 
     * @param ids 需要删除的意见箱主键
     * @return 结果
     */
    @Override
    public int deleteYijianByIds(Long[] ids)
    {
        return yijianMapper.deleteYijianByIds(ids);
    }

    /**
     * 删除意见箱信息
     * 
     * @param id 意见箱主键
     * @return 结果
     */
    @Override
    public int deleteYijianById(Long id)
    {
        return yijianMapper.deleteYijianById(id);
    }
}
