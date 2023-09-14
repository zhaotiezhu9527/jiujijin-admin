package com.juhai.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.juhai.business.domain.Yijian;
import com.juhai.business.service.IYijianService;
import com.juhai.common.utils.poi.ExcelUtil;
import com.juhai.common.core.page.TableDataInfo;

/**
 * 意见箱Controller
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
@RestController
@RequestMapping("/business/yijian")
public class YijianController extends BaseController
{
    @Autowired
    private IYijianService yijianService;

    /**
     * 查询意见箱列表
     */
    @PreAuthorize("@ss.hasPermi('business:yijian:list')")
    @GetMapping("/list")
    public TableDataInfo list(Yijian yijian)
    {
        startPage();
        List<Yijian> list = yijianService.selectYijianList(yijian);
        return getDataTable(list);
    }

    /**
     * 导出意见箱列表
     */
    @PreAuthorize("@ss.hasPermi('business:yijian:export')")
    @Log(title = "意见箱", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Yijian yijian)
    {
        List<Yijian> list = yijianService.selectYijianList(yijian);
        ExcelUtil<Yijian> util = new ExcelUtil<Yijian>(Yijian.class);
        util.exportExcel(response, list, "意见箱数据");
    }

    /**
     * 获取意见箱详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:yijian:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(yijianService.selectYijianById(id));
    }

    /**
     * 新增意见箱
     */
    @PreAuthorize("@ss.hasPermi('business:yijian:add')")
    @Log(title = "意见箱", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Yijian yijian)
    {
        return toAjax(yijianService.insertYijian(yijian));
    }

    /**
     * 修改意见箱
     */
    @PreAuthorize("@ss.hasPermi('business:yijian:edit')")
    @Log(title = "意见箱", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Yijian yijian)
    {
        return toAjax(yijianService.updateYijian(yijian));
    }

    /**
     * 删除意见箱
     */
    @PreAuthorize("@ss.hasPermi('business:yijian:remove')")
    @Log(title = "意见箱", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yijianService.deleteYijianByIds(ids));
    }
}
