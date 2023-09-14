package com.juhai.business.mapper;

import java.util.List;
import com.juhai.business.domain.User;

/**
 * 用户列表Mapper接口
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
public interface UserMapper 
{
    /**
     * 查询用户列表
     * 
     * @param id 用户列表主键
     * @return 用户列表
     */
    public User selectUserById(Long id);

    /**
     * 查询用户列表列表
     * 
     * @param user 用户列表
     * @return 用户列表集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增用户列表
     * 
     * @param user 用户列表
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改用户列表
     * 
     * @param user 用户列表
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 删除用户列表
     * 
     * @param id 用户列表主键
     * @return 结果
     */
    public int deleteUserById(Long id);

    /**
     * 批量删除用户列表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserByIds(Long[] ids);
}
