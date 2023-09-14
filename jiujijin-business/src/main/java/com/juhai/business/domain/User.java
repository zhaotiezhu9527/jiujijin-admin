package com.juhai.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.juhai.common.annotation.Excel;
import com.juhai.common.core.domain.BaseEntity;

/**
 * 用户列表对象 t_user
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 姓名 */
    @Excel(name = "姓名")
    private String realName;

    /** 登录密码 */
    private String loginPwd;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 性别 */
    @Excel(name = "性别")
    private Long gender;

    /** 国籍 */
    @Excel(name = "国籍")
    private String nationality;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birth;

    /** 身份证/护照 */
    @Excel(name = "身份证/护照")
    private String idCard;

    /** 工作 */
    @Excel(name = "工作")
    private String work;

    /** 省/市 */
    @Excel(name = "省/市")
    private String city;

    /** 地区 */
    @Excel(name = "地区")
    private String region;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankName;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String bankCard;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    /** 注册Ip */
    @Excel(name = "注册Ip")
    private String registerIp;

    /** 上次登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上次登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

    /** 上次登录IP */
    @Excel(name = "上次登录IP")
    private String lastIp;

    /** 最后修改时间 */
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
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setLoginPwd(String loginPwd) 
    {
        this.loginPwd = loginPwd;
    }

    public String getLoginPwd() 
    {
        return loginPwd;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setGender(Long gender) 
    {
        this.gender = gender;
    }

    public Long getGender() 
    {
        return gender;
    }
    public void setNationality(String nationality) 
    {
        this.nationality = nationality;
    }

    public String getNationality() 
    {
        return nationality;
    }
    public void setBirth(Date birth) 
    {
        this.birth = birth;
    }

    public Date getBirth() 
    {
        return birth;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setWork(String work) 
    {
        this.work = work;
    }

    public String getWork() 
    {
        return work;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setRegion(String region) 
    {
        this.region = region;
    }

    public String getRegion() 
    {
        return region;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setBankCard(String bankCard) 
    {
        this.bankCard = bankCard;
    }

    public String getBankCard() 
    {
        return bankCard;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setRegisterTime(Date registerTime) 
    {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime() 
    {
        return registerTime;
    }
    public void setRegisterIp(String registerIp) 
    {
        this.registerIp = registerIp;
    }

    public String getRegisterIp() 
    {
        return registerIp;
    }
    public void setLastTime(Date lastTime) 
    {
        this.lastTime = lastTime;
    }

    public Date getLastTime() 
    {
        return lastTime;
    }
    public void setLastIp(String lastIp) 
    {
        this.lastIp = lastIp;
    }

    public String getLastIp() 
    {
        return lastIp;
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
            .append("realName", getRealName())
            .append("loginPwd", getLoginPwd())
            .append("email", getEmail())
            .append("gender", getGender())
            .append("nationality", getNationality())
            .append("birth", getBirth())
            .append("idCard", getIdCard())
            .append("work", getWork())
            .append("city", getCity())
            .append("region", getRegion())
            .append("address", getAddress())
            .append("bankName", getBankName())
            .append("bankCard", getBankCard())
            .append("status", getStatus())
            .append("registerTime", getRegisterTime())
            .append("registerIp", getRegisterIp())
            .append("lastTime", getLastTime())
            .append("lastIp", getLastIp())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
