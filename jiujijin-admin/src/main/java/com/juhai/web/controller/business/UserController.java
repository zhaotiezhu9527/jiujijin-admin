package com.juhai.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.SecureUtil;
import com.juhai.common.utils.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.juhai.common.annotation.Log;
import com.juhai.common.core.controller.BaseController;
import com.juhai.common.core.domain.AjaxResult;
import com.juhai.common.enums.BusinessType;
import com.juhai.business.domain.User;
import com.juhai.business.service.IUserService;
import com.juhai.common.utils.poi.ExcelUtil;
import com.juhai.common.core.page.TableDataInfo;

/**
 * 用户列表Controller
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
@RestController
@RequestMapping("/business/user")
public class UserController extends BaseController
{
    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询用户列表列表
     */
    @PreAuthorize("@ss.hasPermi('business:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户列表列表
     */
    @PreAuthorize("@ss.hasPermi('business:user:export')")
    @Log(title = "用户列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, User user)
    {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        util.exportExcel(response, list, "用户列表数据");
    }

    /**
     * 获取用户列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userService.selectUserById(id));
    }

    /**
     * 新增用户列表
     */
    @PreAuthorize("@ss.hasPermi('business:user:add')")
    @Log(title = "用户列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody User user)
    {
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户列表
     */
    @PreAuthorize("@ss.hasPermi('business:user:edit')")
    @Log(title = "用户列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody User user)
    {
        if (StringUtils.isNotBlank(user.getLoginPwd())) {
            boolean matchLoginPwd = ReUtil.isMatch("^[a-zA-Z0-9]{6,12}$", user.getLoginPwd());
            if (!matchLoginPwd) {
                return AjaxResult.error("请输入6-12位登录密码");
            }
            user.setLoginPwd(SecureUtil.md5(user.getLoginPwd()));
        } else {
            user.setLoginPwd(null);
        }
        User redisUser = userService.selectUserById(user.getId());
        // 删除密码限制
        String incKey = RedisKeyUtil.PayPwdErrorKey(redisUser.getUserName());
        redisTemplate.delete(incKey);
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户列表
     */
    @PreAuthorize("@ss.hasPermi('business:user:remove')")
    @Log(title = "用户列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userService.deleteUserByIds(ids));
    }
}
