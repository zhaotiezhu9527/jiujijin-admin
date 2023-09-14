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
import com.juhai.business.domain.Opinion;
import com.juhai.business.service.IOpinionService;
import com.juhai.common.utils.poi.ExcelUtil;
import com.juhai.common.core.page.TableDataInfo;

/**
 * 救济金申请Controller
 * 
 * @author zhaotiezhu
 * @date 2023-09-14
 */
@RestController
@RequestMapping("/business/opinion")
public class OpinionController extends BaseController
{
    @Autowired
    private IOpinionService opinionService;

    /**
     * 查询救济金申请列表
     */
    @PreAuthorize("@ss.hasPermi('business:opinion:list')")
    @GetMapping("/list")
    public TableDataInfo list(Opinion opinion)
    {
        startPage();
        List<Opinion> list = opinionService.selectOpinionList(opinion);
        return getDataTable(list);
    }

    /**
     * 导出救济金申请列表
     */
    @PreAuthorize("@ss.hasPermi('business:opinion:export')")
    @Log(title = "救济金申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Opinion opinion)
    {
        List<Opinion> list = opinionService.selectOpinionList(opinion);
        ExcelUtil<Opinion> util = new ExcelUtil<Opinion>(Opinion.class);
        util.exportExcel(response, list, "救济金申请数据");
    }

    /**
     * 获取救济金申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:opinion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(opinionService.selectOpinionById(id));
    }

    /**
     * 新增救济金申请
     */
    @PreAuthorize("@ss.hasPermi('business:opinion:add')")
    @Log(title = "救济金申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Opinion opinion)
    {
        return toAjax(opinionService.insertOpinion(opinion));
    }

    /**
     * 修改救济金申请
     */
    @PreAuthorize("@ss.hasPermi('business:opinion:edit')")
    @Log(title = "救济金申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Opinion opinion)
    {
        return toAjax(opinionService.updateOpinion(opinion));
    }

    /**
     * 删除救济金申请
     */
    @PreAuthorize("@ss.hasPermi('business:opinion:remove')")
    @Log(title = "救济金申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(opinionService.deleteOpinionByIds(ids));
    }
}
