package com.yidiantong.cms.content.sevices;

import com.yidiantong.cms.content.dtos.SimpleCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by konghang on 2017/8/18.
 */
public interface CategoryRestService {

    /**
     * 查找
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/simples")
    List<SimpleCategory> findSimpleByIds(@RequestParam(value = "ids") List<Long> ids);

    /**
     * 查找指定板块、指定类型的分类条目
     *
     * @param moduleId
     * @param type
     * @return
     */
    @GetMapping(value = "/category/simples")
    List<SimpleCategory> findEnabledSimpleByModuleId(@RequestParam(value = "moduleId") Long moduleId,
                                                     @RequestParam(value = "type") String type,
                                                     @RequestParam(value = "page") Integer page,
                                                     @RequestParam(value = "size") Integer size);

    /**
     * 根据指定板块代号、指定类型查找分类条目
     *
     * @param type
     * @param module
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/category/{module}/simples")
    List<SimpleCategory> findEnabledSimpleByTypeAndModule(@RequestParam(value = "type") String type,
                                                          @PathVariable(value = "module") String module,
                                                          @RequestParam(value = "page") Integer page,
                                                          @RequestParam(value = "size") Integer size);

    /**
     * 查找指定模块的所有分类
     *
     * @param module
     * @return
     */
    @GetMapping(value = "/category/{module}/all")
    List<SimpleCategory> findEnabledSimpleByModule(@PathVariable(value = "module") String module);

    /**
     * 查找指定类型的所有分类
     *
     * @param type
     * @return
     */
    @GetMapping(value = "/category/simples/type")
    List<SimpleCategory> findEnabledSimpleByType(@RequestParam(value = "type") String type);

    /**
     * 查找
     *
     * @param module
     * @param type
     * @return
     */
    @GetMapping(value = "/category/simples/module/type")
    List<SimpleCategory> findEnabledSimpleByModuleAndType(@RequestParam(value = "module") String module, @RequestParam(value = "type") String type);
}
