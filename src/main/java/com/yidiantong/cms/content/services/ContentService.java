package com.yidiantong.cms.content.services;

import com.yidiantong.cms.content.dtos.Content;
import com.yidiantong.cms.content.dtos.ContentQuery;
import com.yidiantong.cms.content.dtos.SimpleContent;
import com.yidiantong.core.dtos.ResultsTotalDTO;

import java.util.List;

/**
 * Created by konghang on 2017/8/20.
 */
public interface ContentService {

    /**
     * 查找
     *
     * @param id
     * @return
     */
    SimpleContent findOneSimpleById(Long id);

    /**
     * 查找
     *
     * @param ids
     * @return
     */
    List<SimpleContent> findSimpleByIds(List<Long> ids);

    /**
     * 查找
     *
     * @param moduleId
     * @param page
     * @param size
     * @return
     */
    List<Content> findEnabledSimplesByModuleIdAndCategoryId(Long moduleId, List<Long> categoryIds, String dataType, Integer page, Integer size);

    /**
     * 查找
     *
     * @param moduleId
     * @param page
     * @param size
     * @return
     */
    ResultsTotalDTO<SimpleContent> findEnabledSimplesByModuleIdAndCategoryIds(Long moduleId, List<Long> categoryIds, Integer page, Integer size);

    /**
     * 查找
     *
     * @param query
     * @return
     */
    ResultsTotalDTO<SimpleContent> findSimplesByQuery(ContentQuery query);

    /**
     * 查找
     *
     * @param contentId
     * @return
     */
    List<Long> findCategoryIdsByContentId(Long contentId);
}
