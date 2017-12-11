package com.yidiantong.cms.content.services;

import com.yidiantong.cms.content.dtos.ContentTag;
import com.yidiantong.cms.content.dtos.ContentTagCreateOrUpdate;
import com.yidiantong.cms.content.dtos.SimpleContentTag;

import java.util.List;

/**
 * 内容标签
 *
 * Created by konghang on 2017/9/10.
 */
public interface ContentTagService {

    /**
     * 查找
     *
     * @param contentId
     * @return
     */
    SimpleContentTag findOneSimpleByContentId(Long contentId);

    /**
     * 查找
     *
     * @param contentId
     * @return
     */
    ContentTag findOneByContentId(Long contentId);

    /**
     * 查找
     *
     * @param contentIds
     * @return
     */
    List<ContentTag> findByContentIds(List<Long> contentIds);

    /**
     * 创建内容标签
     *
     * @param tag
     * @return
     */
    Boolean createContentTag(ContentTagCreateOrUpdate tag, Long sysUserId);
}
