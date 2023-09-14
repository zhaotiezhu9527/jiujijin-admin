package com.juhai.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.juhai.common.annotation.Excel;
import com.juhai.common.core.domain.BaseEntity;

/**
 * 救济金申请对象 t_opinion
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
public class Opinion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 任务 */
    @Excel(name = "任务")
    private Long rwId;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setRwId(Long rwId) 
    {
        this.rwId = rwId;
    }

    public Long getRwId() 
    {
        return rwId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("rwId", getRwId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .append("remark", getRemark())
            .toString();
    }
}
