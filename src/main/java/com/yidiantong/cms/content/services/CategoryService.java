package com.yidiantong.cms.content.services;

import com.yidiantong.cms.content.dtos.SimpleCategory;

import java.util.List;

/**
 * Created by konghang on 2017/8/18.
 */
public interface CategoryService {

    /**
     * 查找指定板块、指定类型的分类条目
     *
     * @param moduleId
     * @param type
     * @return
     */
    List<SimpleCategory> findEnabledSimpleByModuleIdAndType(Long moduleId, String type, Integer page, Integer size);

    /**
     * 查找
     *
     * @param type
     * @param module
     * @param page
     * @param size
     * @return
     */
    List<SimpleCategory> findEnabledSimpleByTypeAndModule(String type, String module, Integer page, Integer size);

    /**
     * 查找
     *
     * @param type
     * @param module
     * @return
     */
    List<SimpleCategory> findEnabledSimpleByTypeAndModule(String type, String module);

    /**
     * 查找
     *
     * @param module
     * @return
     */
    List<SimpleCategory> findEnabledSimpleByModule(String module);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    SimpleCategory findOneSimpleById(Long id);

    /**
     * 查找
     *
     * @param ids
     * @return
     */
    List<SimpleCategory> findSimpleByIds(List<Long> ids);

    /**
     * 查找
     *
     * @param type
     * @return
     */
    List<SimpleCategory> findSimpleByType(String type);
}
