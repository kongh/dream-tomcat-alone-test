package com.yidiantong.cms.content.controllers;

import com.yidiantong.cms.content.dtos.Content;
import com.yidiantong.cms.content.dtos.SimpleContent;
import com.yidiantong.cms.content.services.ContentService;
import com.yidiantong.cms.content.sevices.ContentRestService;
import com.yidiantong.core.dtos.ResultsTotalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by konghang on 2017/8/20.
 */
@RestController
public class ContentController implements ContentRestService{

    @Autowired
    private ContentService contentService;

    /**
     * 查找
     *
     * @param ids
     * @return
     */
    @Override
    public List<SimpleContent> findSimpleByIds(@RequestParam(value = "ids") List<Long> ids) {
        return contentService.findSimpleByIds(ids);
    }

    /**
     * 查找
     *
     * @param moduleId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Content> findEnabledSimplesByModuleId(@RequestParam(value = "moduleId") Long moduleId,
                                                      @RequestParam(value = "categoryIds", required = false) List<Long> categoryIds,
                                                      @RequestParam(value = "dataType", required = false) String dataType,
                                                      @RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "size") Integer size) {
        return contentService.findEnabledSimplesByModuleIdAndCategoryId(moduleId, categoryIds, dataType, page, size);
    }

    /**
     * 查找
     *
     * @param moduleId
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResultsTotalDTO<SimpleContent> findEnabledSimplesByModuleIdAndCategoryIds(@RequestParam(value = "moduleId") Long moduleId,
                                                                       @RequestParam(value = "categoryIds") List<Long> categoryIds,
                                                                       @RequestParam(value = "page") Integer page,
                                                                       @RequestParam(value = "size") Integer size) {
        return contentService.findEnabledSimplesByModuleIdAndCategoryIds(moduleId, categoryIds, page, size);
    }

    /**
     * 查找
     *
     * @param contentId
     * @return
     */
    @Override
    public List<Long> findCategoryIdsByContentId(@RequestParam(value = "contentId") Long contentId) {
        return contentService.findCategoryIdsByContentId(contentId);
    }
}
