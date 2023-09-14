package com.juhai.business.mapper;

import java.util.List;
import com.juhai.business.domain.Opinion;

/**
 * 救济金申请Mapper接口
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
public interface OpinionMapper 
{
    /**
     * 查询救济金申请
     * 
     * @param id 救济金申请主键
     * @return 救济金申请
     */
    public Opinion selectOpinionById(Long id);

    /**
     * 查询救济金申请列表
     * 
     * @param opinion 救济金申请
     * @return 救济金申请集合
     */
    public List<Opinion> selectOpinionList(Opinion opinion);

    /**
     * 新增救济金申请
     * 
     * @param opinion 救济金申请
     * @return 结果
     */
    public int insertOpinion(Opinion opinion);

    /**
     * 修改救济金申请
     * 
     * @param opinion 救济金申请
     * @return 结果
     */
    public int updateOpinion(Opinion opinion);

    /**
     * 删除救济金申请
     * 
     * @param id 救济金申请主键
     * @return 结果
     */
    public int deleteOpinionById(Long id);

    /**
     * 批量删除救济金申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOpinionByIds(Long[] ids);
}
