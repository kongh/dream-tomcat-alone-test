package com.yidiantong.cms.tag.services;

import com.yidiantong.cms.tag.dtos.CmsTag;
import com.yidiantong.cms.tag.dtos.SimpleCmsTag;
import com.yidiantong.cms.tag.dtos.TagForCreateReq;
import com.yidiantong.core.dtos.ResultsTotalDTO;

import java.util.List;

/**
 * Created by SUN at 2017/8/18
 */
public interface TagService {
    /*
    *
    * 新建标签
    *
    * */
    Boolean createTag(TagForCreateReq tagForCreateReq, Long sysUserId);
    /*
    * 删除标签
    * */
    Boolean deleteTags(List<Long> ids, Long sysUserId);

    /*
    * 分页查询
    * */
    ResultsTotalDTO<CmsTag> findTagsPageable(Integer page, Integer size);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    SimpleCmsTag findOneSimpleById(Long id);

    /**
     * 查找
     *
     * @param ids
     * @return
     */
    List<SimpleCmsTag> findSimplesByIds(List<Long> ids);
}
