package com.yidiantong.cms.tag.controller;

import com.yidiantong.cms.tag.dtos.CmsTag;
import com.yidiantong.cms.tag.dtos.TagForCreateReq;
import com.yidiantong.cms.tag.services.TagRestService;
import com.yidiantong.cms.tag.services.TagService;
import com.yidiantong.core.dtos.ResultsTotalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by SUN at 2017/8/18
 */
@RestController
public class TagController implements TagRestService {
    @Autowired
    TagService tagService;

    @Override
    public ResultsTotalDTO<CmsTag> findTagsPageable(@RequestParam Integer page, @RequestParam Integer size) {
        return tagService.findTagsPageable(page, size);
    }

    @Override
    public Boolean createTag(@RequestBody TagForCreateReq tagForCreateReq, @RequestParam Long sysUserId) {
        return tagService.createTag(tagForCreateReq,sysUserId);
    }

    @Override
    public Boolean deleteTags(@RequestBody List<Long> ids,@RequestParam Long sysUserId) {
        return tagService.deleteTags(ids,sysUserId);
    }
}
