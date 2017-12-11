package com.yidiantong.cms.content.controllers;

import com.yidiantong.cms.content.dtos.SimpleCategory;
import com.yidiantong.cms.content.services.CategoryService;
import com.yidiantong.cms.content.sevices.CategoryRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by konghang on 2017/8/18.
 */
@RestController
public class CategoryController implements CategoryRestService{

    @Autowired
    private CategoryService categoryService;

    /**
     * 查找
     *
     * @param ids
     * @return
     */
    @Override
    public List<SimpleCategory> findSimpleByIds(@RequestParam(value = "ids") List<Long> ids) {
        return categoryService.findSimpleByIds(ids);
    }

    /**
     * 查找指定板块、指定类型的分类条目
     *
     * @param moduleId
     * @param type
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<SimpleCategory> findEnabledSimpleByModuleId(@RequestParam(value = "moduleId")Long moduleId,
                                                            @RequestParam(value = "type") String type,
                                                            @RequestParam(value = "page")Integer page,
                                                            @RequestParam(value = "size")Integer size) {
        return categoryService.findEnabledSimpleByModuleIdAndType(moduleId, type, page, size);
    }

    /**
     * 根据指定板块代号、指定类型查找分类条目
     *
     * @param type
     * @param module
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<SimpleCategory> findEnabledSimpleByTypeAndModule(@RequestParam(value = "type") String type,
                                                                 @PathVariable(value = "module") String module,
                                                                 @RequestParam(value = "page") Integer page,
                                                                 @RequestParam(value = "size") Integer size) {
        return categoryService.findEnabledSimpleByTypeAndModule(type, module, page, size);
    }

    /**
     * 查找指定模块的所有分类
     *
     * @param module
     * @return
     */
    @Override
    public List<SimpleCategory> findEnabledSimpleByModule(@PathVariable(value = "module") String module) {
        return categoryService.findEnabledSimpleByModule(module);
    }

    /**
     * 查找指定类型的所有分类
     *
     * @param type
     * @return
     */
    @Override
    public List<SimpleCategory> findEnabledSimpleByType(@RequestParam(value = "type") String type) {
        return categoryService.findSimpleByType(type);
    }

    /**
     * 查找
     *
     * @param module
     * @param type
     * @return
     */
    @Override
    public List<SimpleCategory> findEnabledSimpleByModuleAndType(@RequestParam(value = "module") String module, @RequestParam(value = "type") String type) {
        return categoryService.findEnabledSimpleByTypeAndModule(type, module);
    }
}
