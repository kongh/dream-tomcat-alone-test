package com.yidiantong.cms.content.services.impls;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yidiantong.cms.CmsConstants;
import com.yidiantong.cms.content.daos.ContentCategoryRepository;
import com.yidiantong.cms.content.daos.ContentRepository;
import com.yidiantong.cms.content.dtos.Content;
import com.yidiantong.cms.content.dtos.ContentQuery;
import com.yidiantong.cms.content.dtos.ContentTag;
import com.yidiantong.cms.content.dtos.SimpleContent;
import com.yidiantong.cms.content.models.ContentCategoryEntity;
import com.yidiantong.cms.content.models.ContentEntity;
import com.yidiantong.cms.content.services.ContentService;
import com.yidiantong.cms.content.services.ContentTagService;
import com.yidiantong.core.dtos.ResultsTotalDTO;
import com.yidiantong.spring.jpa.SpecificationBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.collection.CollectionUtil;
import org.springside.modules.utils.mapper.BeanMapper;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by konghang on 2017/8/20.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentCategoryRepository contentCategoryRepository;

    @Autowired
    private ContentTagService contentTagService;

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public SimpleContent findOneSimpleById(Long id) {
        return BeanMapper.map(contentRepository.findOne(id), SimpleContent.class);
    }

    /**
     * 查找
     *
     * @param ids
     * @return
     */
    @Override
    public List<SimpleContent> findSimpleByIds(List<Long> ids) {
        List<ContentEntity> entities = contentRepository.findAll(ids);
        return BeanMapper.mapList(entities, ContentEntity.class, SimpleContent.class);
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
    public List<Content> findEnabledSimplesByModuleIdAndCategoryId(Long moduleId, List<Long> categoryIds, String dataType, Integer page, Integer size) {
        Set<Long> contentIds = Sets.newHashSet();
        if(CollectionUtil.isNotEmpty(categoryIds)){
            List<ContentCategoryEntity> entities = contentCategoryRepository.findByModuleIdAndCategoryIdInAndIsDeleted(moduleId, categoryIds, Boolean.FALSE);
            if(CollectionUtil.isEmpty(entities)){
                return Lists.newArrayList();
            }
            contentIds = entities.stream().map(ContentCategoryEntity::getContentId).collect(Collectors.toSet());
        }

        final List<Long> searchIds = Lists.newArrayList(contentIds);
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        PageRequest request = new PageRequest(page, size, sort);
        Specification<ContentEntity> specification = SpecificationBuilder.builder(ContentEntity.class)
                .when(CollectionUtil.isEmpty(contentIds), builder -> builder.eq("moduleId", moduleId))
                .when(CollectionUtil.isNotEmpty(contentIds), builder -> builder.in("id", searchIds))
                .eq("dataType", dataType)
                .eq("status", CmsConstants.ContentStatus.ONLINED.getCode())
                .eq("isDeleted", Boolean.FALSE)
                .and();
        Page<ContentEntity> pageContent = contentRepository.findAll(specification, request);
        List<Long> ids = pageContent.getContent().stream().map(ContentEntity::getId).collect(Collectors.toList());
        List<ContentTag> contentTags = contentTagService.findByContentIds(ids);
        Map<Long, ContentTag> contentTagMap = contentTags.stream().collect(Collectors.toMap(ContentTag::getContentId, item -> item));
        List<Content> contents = BeanMapper.mapList(pageContent.getContent(), ContentEntity.class, Content.class);
        contents.stream().forEach(item -> item.setContentTag(contentTagMap.get(item.getId())));
        return contents;
    }

    /**
     * 查找
     *
     * @param moduleId
     * @param categoryIds
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResultsTotalDTO<SimpleContent> findEnabledSimplesByModuleIdAndCategoryIds(Long moduleId, List<Long> categoryIds, Integer page, Integer size) {
        if(CollectionUtil.isEmpty(categoryIds)){
            return ResultsTotalDTO.build(Lists.newArrayList(), 0L);
        }
        Set<Long> contentIds = Sets.newHashSet();
        if(CollectionUtil.isNotEmpty(categoryIds)){
            List<ContentCategoryEntity> entities = contentCategoryRepository.findByModuleIdAndCategoryIdInAndIsDeleted(moduleId, categoryIds, Boolean.FALSE);
            if(CollectionUtil.isEmpty(entities)){
                return ResultsTotalDTO.build(Lists.newArrayList(), 0L);
            }
            contentIds = entities.stream().map(ContentCategoryEntity::getContentId).collect(Collectors.toSet());
        }
        PageRequest request = new PageRequest(page, size);
        Page<ContentEntity> entityPage = contentRepository.findByIdInAndIsDeleted(Lists.newArrayList(contentIds), Boolean.FALSE, request);
        List<SimpleContent> contents = BeanMapper.mapList(entityPage.getContent(), ContentEntity.class, SimpleContent.class);
        return ResultsTotalDTO.build(contents, entityPage.getTotalElements());
    }

    /**
     * 查找
     *
     * @param query
     * @return
     */
    @Override
    public ResultsTotalDTO<SimpleContent> findSimplesByQuery(ContentQuery query) {
        Specification<ContentEntity> specification = SpecificationBuilder.builder(ContentEntity.class)
                .eq("moduleId", query.getModuleId())
                .when(StringUtils.isNoneBlank(query.getTitle()), builder -> builder.like("title", query.getTitle()))
                .when(StringUtils.isNoneBlank(query.getDataType()), builder -> builder.eq("dataType", query.getDataType()))
                .in("id", query.getContentIds())
                .eq("copyrightId", query.getCopyrightId())
                .eq("isDeleted", Boolean.FALSE)
                .when(StringUtils.isNoneBlank(query.getStatus()), builder -> builder.eq("status", query.getStatus()))
                .and();
        PageRequest request = new PageRequest(query.getPage(), query.getSize());
        Page<ContentEntity> contentPage = contentRepository.findAll(specification, request);
        if(CollectionUtil.isEmpty(contentPage.getContent())){
            return ResultsTotalDTO.build(Lists.newArrayList(), 0L);
        }
        List<SimpleContent> contents = BeanMapper.mapList(contentPage.getContent(), ContentEntity.class, SimpleContent.class);
        return ResultsTotalDTO.build(contents, contentPage.getTotalElements());
    }

    /**
     * 查找
     *
     * @param contentId
     * @return
     */
    @Override
    public List<Long> findCategoryIdsByContentId(Long contentId) {
        List<ContentCategoryEntity> contentCategoryEntities = contentCategoryRepository.findByContentIdInAndIsDeleted(Lists.newArrayList(contentId), Boolean.FALSE);
        Set<Long> categoryIds = contentCategoryEntities.stream().map(ContentCategoryEntity::getCategoryId).collect(Collectors.toSet());
        return Lists.newArrayList(categoryIds);
    }
}
