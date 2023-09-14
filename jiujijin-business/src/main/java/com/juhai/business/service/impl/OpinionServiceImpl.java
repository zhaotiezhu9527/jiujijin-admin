package com.juhai.business.service.impl;

import java.util.List;
import com.juhai.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.juhai.business.mapper.OpinionMapper;
import com.juhai.business.domain.Opinion;
import com.juhai.business.service.IOpinionService;

/**
 * 救济金申请Service业务层处理
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
@Service
public class OpinionServiceImpl implements IOpinionService 
{
    @Autowired
    private OpinionMapper opinionMapper;

    /**
     * 查询救济金申请
     * 
     * @param id 救济金申请主键
     * @return 救济金申请
     */
    @Override
    public Opinion selectOpinionById(Long id)
    {
        return opinionMapper.selectOpinionById(id);
    }

    /**
     * 查询救济金申请列表
     * 
     * @param opinion 救济金申请
     * @return 救济金申请
     */
    @Override
    public List<Opinion> selectOpinionList(Opinion opinion)
    {
        return opinionMapper.selectOpinionList(opinion);
    }

    /**
     * 新增救济金申请
     * 
     * @param opinion 救济金申请
     * @return 结果
     */
    @Override
    public int insertOpinion(Opinion opinion)
    {
        opinion.setCreateTime(DateUtils.getNowDate());
        return opinionMapper.insertOpinion(opinion);
    }

    /**
     * 修改救济金申请
     * 
     * @param opinion 救济金申请
     * @return 结果
     */
    @Override
    public int updateOpinion(Opinion opinion)
    {
        return opinionMapper.updateOpinion(opinion);
    }

    /**
     * 批量删除救济金申请
     * 
     * @param ids 需要删除的救济金申请主键
     * @return 结果
     */
    @Override
    public int deleteOpinionByIds(Long[] ids)
    {
        return opinionMapper.deleteOpinionByIds(ids);
    }

    /**
     * 删除救济金申请信息
     * 
     * @param id 救济金申请主键
     * @return 结果
     */
    @Override
    public int deleteOpinionById(Long id)
    {
        return opinionMapper.deleteOpinionById(id);
    }
}
