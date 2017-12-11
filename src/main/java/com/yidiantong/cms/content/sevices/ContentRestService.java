package com.yidiantong.cms.content.sevices;

import com.yidiantong.cms.content.dtos.Content;
import com.yidiantong.cms.content.dtos.SimpleContent;
import com.yidiantong.core.dtos.ResultsTotalDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by konghang on 2017/8/20.
 */
public interface ContentRestService {


    /**
     * 查找
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/contents/simples")
    List<SimpleContent> findSimpleByIds(@RequestParam(value = "ids") List<Long> ids);

    /**
     * 查找
     *
     * @param moduleId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/contents")
    List<Content> findEnabledSimplesByModuleId(@RequestParam(value = "moduleId") Long moduleId,
                                               @RequestParam(value = "categoryIds", required = false) List<Long> categoryIds,
                                               @RequestParam(value = "dataType", required = false) String dataType,
                                               @RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "size") Integer size);

    /**
     * 查找
     *
     * @param moduleId
     * @param categoryIds
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/contents/page")
    ResultsTotalDTO<SimpleContent> findEnabledSimplesByModuleIdAndCategoryIds(@RequestParam(value = "moduleId") Long moduleId,
                                                                              @RequestParam(value = "categoryIds", required = false) List<Long> categoryIds,
                                                                              @RequestParam(value = "page") Integer page,
                                                                              @RequestParam(value = "size") Integer size);

    /**
     * 查找
     *
     * @param contentId
     * @return
     */
    @GetMapping(value = "/content/categoryIds")
    List<Long> findCategoryIdsByContentId(@RequestParam(value = "contentId") Long contentId);
}
