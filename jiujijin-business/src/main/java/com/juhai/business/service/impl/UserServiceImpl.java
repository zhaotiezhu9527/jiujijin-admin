package com.juhai.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.juhai.business.mapper.UserMapper;
import com.juhai.business.domain.User;
import com.juhai.business.service.IUserService;

/**
 * 用户列表Service业务层处理
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
@Service
public class UserServiceImpl implements IUserService 
{
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户列表
     * 
     * @param id 用户列表主键
     * @return 用户列表
     */
    @Override
    public User selectUserById(Long id)
    {
        return userMapper.selectUserById(id);
    }

    /**
     * 查询用户列表列表
     * 
     * @param user 用户列表
     * @return 用户列表
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户列表
     * 
     * @param user 用户列表
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        return userMapper.insertUser(user);
    }

    /**
     * 修改用户列表
     * 
     * @param user 用户列表
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除用户列表
     * 
     * @param ids 需要删除的用户列表主键
     * @return 结果
     */
    @Override
    public int deleteUserByIds(Long[] ids)
    {
        return userMapper.deleteUserByIds(ids);
    }

    /**
     * 删除用户列表信息
     * 
     * @param id 用户列表主键
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id)
    {
        return userMapper.deleteUserById(id);
    }
}
