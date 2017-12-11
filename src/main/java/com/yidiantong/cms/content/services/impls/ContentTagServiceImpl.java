package com.yidiantong.cms.content.services.impls;

import com.google.common.collect.Lists;
import com.yidiantong.cms.content.daos.ContentTagRepository;
import com.yidiantong.cms.content.dtos.ContentTag;
import com.yidiantong.cms.content.dtos.ContentTagCreateOrUpdate;
import com.yidiantong.cms.content.dtos.SimpleContentTag;
import com.yidiantong.cms.content.models.ContentTagEntity;
import com.yidiantong.cms.content.services.ContentTagService;
import com.yidiantong.cms.tag.dtos.SimpleCmsTag;
import com.yidiantong.cms.tag.services.TagService;
import com.yidiantong.core.exceptions.CommonExceptionConstants;
import com.yidiantong.core.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.collection.CollectionUtil;
import org.springside.modules.utils.mapper.BeanMapper;
import org.springside.modules.utils.time.DateFormatUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by konghang on 2017/9/10.
 */
@Service
public class ContentTagServiceImpl implements ContentTagService {

    @Autowired
    private ContentTagRepository contentTagRepository;

    @Autowired
    private TagService tagService;

    /**
     * 查找
     *
     * @param contentId
     * @return
     */
    @Override
    public SimpleContentTag findOneSimpleByContentId(Long contentId) {
        ContentTagEntity entity = contentTagRepository.findByContentIdAndIsDeleted(contentId, Boolean.FALSE);
        return BeanMapper.map(entity, SimpleContentTag.class);
    }

    /**
     * 查找
     *
     * @param contentId
     * @return
     */
    @Override
    public ContentTag findOneByContentId(Long contentId) {
        ContentTagEntity entity = contentTagRepository.findByContentIdAndIsDeleted(contentId, Boolean.FALSE);
        if(Objects.isNull(entity)){
            return null;
        }
        Long tagId = entity.getTagId();
        SimpleCmsTag cmsTag = null;
        ContentTag tag = BeanMapper.map(entity, ContentTag.class);
        tag.setTagName(cmsTag.getName());
        tag.setBeginAtStr(DateFormatUtil.formatDate(DateFormatUtil.PATTERN_ISO_ON_DATE, tag.getBeginAt()));
        tag.setEndAtStr(DateFormatUtil.formatDate(DateFormatUtil.PATTERN_ISO_ON_DATE, tag.getEndAt()));
        return tag;
    }

    /**
     * 查找
     *
     * @param contentIds
     * @return
     */
    @Override
    public List<ContentTag> findByContentIds(List<Long> contentIds) {
        List<ContentTagEntity> entities = contentTagRepository.findByContentIdInAndIsDeleted(contentIds, Boolean.FALSE);
        if(CollectionUtil.isEmpty(entities)){
            return Lists.newArrayList();
        }
        List<Long> tagIds = entities.stream().map(ContentTagEntity::getTagId).collect(Collectors.toList());
        List<SimpleCmsTag> tags = tagService.findSimplesByIds(tagIds);
        Map<Long, SimpleCmsTag> tagMap = tags.stream().collect(Collectors.toMap(SimpleCmsTag::getId, item -> item));
        List<ContentTag> contentTags = BeanMapper.mapList(entities, ContentTagEntity.class, ContentTag.class);
        contentTags.stream().forEach(item -> {
            SimpleCmsTag tag = tagMap.get(item.getTagId());
            item.setTagName(tag.getName());
            item.setBeginAtStr(DateFormatUtil.formatDate(DateFormatUtil.PATTERN_ISO_ON_DATE, item.getBeginAt()));
            item.setEndAtStr(DateFormatUtil.formatDate(DateFormatUtil.PATTERN_ISO_ON_DATE, item.getEndAt()));
        });
        return contentTags;
    }

    /**
     * 创建内容标签
     *
     * @param tag
     * @return
     */
    @Override
    public Boolean createContentTag(ContentTagCreateOrUpdate tag, Long sysUserId) {
        ContentTagEntity entity = Objects.isNull(tag.getId()) ? null : contentTagRepository.findByContentIdAndIsDeleted(tag.getContentId(), Boolean.FALSE);
        if(Objects.isNull(tag.getId())){
            entity = new ContentTagEntity();
            entity.setContentId(tag.getContentId());
            entity.setTagId(tag.getTagId());
            entity.setIsDeleted(Boolean.FALSE);
            entity.setCreatorId(sysUserId);
            entity.setCreatedAt(new Date());
            try {
                entity.setBeginAt(DateFormatUtil.pareDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, tag.getBeginAt() + " 00:00:00"));
                entity.setEndAt(DateFormatUtil.pareDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, tag.getEndAt() + " 23:59:59"));
            } catch (ParseException e) {
                throw ServiceException.build(CommonExceptionConstants.not_available_data);
            }
        }else {
            entity.setTagId(tag.getTagId());
            entity.setIsDeleted(Boolean.FALSE);
            entity.setCreatorId(sysUserId);
            entity.setCreatedAt(new Date());
            try {
                entity.setBeginAt(DateFormatUtil.pareDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, tag.getBeginAt() + " 00:00:00"));
                entity.setEndAt(DateFormatUtil.pareDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, tag.getEndAt() + " 23:59:59"));
            } catch (ParseException e) {
                throw ServiceException.build(CommonExceptionConstants.not_available_data);
            }
        }
        contentTagRepository.save(entity);
        return Boolean.TRUE;
    }
}
