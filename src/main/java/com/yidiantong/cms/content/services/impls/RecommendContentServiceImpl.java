package com.yidiantong.cms.content.services.impls;

import com.google.common.collect.Lists;
import com.yidiantong.cms.CmsConstants;
import com.yidiantong.cms.content.daos.ContentRepository;
import com.yidiantong.cms.content.daos.RecommendContentRepository;
import com.yidiantong.cms.content.dtos.ContentTag;
import com.yidiantong.cms.content.dtos.RecommendContent;
import com.yidiantong.cms.content.dtos.RecommendSeatContent;
import com.yidiantong.cms.content.models.ContentEntity;
import com.yidiantong.cms.content.models.RecommendContentEntity;
import com.yidiantong.cms.content.services.ContentTagService;
import com.yidiantong.cms.content.services.RecommendContentService;
import com.yidiantong.core.exceptions.CommonExceptionConstants;
import com.yidiantong.core.exceptions.ServiceException;
import com.yidiantong.resource.dtos.SimpleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.collection.CollectionUtil;
import org.springside.modules.utils.mapper.BeanMapper;
import org.springside.modules.utils.time.DateFormatUtil;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 推荐服务
 *
 * Created by konghang on 2017/8/16.
 */
@Service
public class RecommendContentServiceImpl implements RecommendContentService {

    @Autowired
    private RecommendContentRepository recommendContentRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentTagService contentTagService;

    /**
     * 查找指定页码的推荐内容
     *
     * @param seatId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<RecommendContent> findEnabledContentsBySeatId(Long seatId, Integer page, Integer size) {
        Date current = new Date();
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        PageRequest request = new PageRequest(page, size, sort);
        long start = System.currentTimeMillis();
        List<RecommendContentEntity> entities = recommendContentRepository.findBySeatIdAndStatusAndIsDeletedAndBeginAtLessThanEqualAndEndAtGreaterThan(seatId, CmsConstants.ContentStatus.ONLINED.getCode(), Boolean.FALSE, current, current, request);
        if(CollectionUtil.isEmpty(entities)){
            return Lists.newArrayList();
        }
//        System.out.println("recommendContentRepository \t" + String.valueOf(System.currentTimeMillis() - start));
        List<RecommendContent> recommendContents = getRecommendContents(entities);
//        System.out.println("total \t" + String.valueOf(System.currentTimeMillis() - start));
        return recommendContents;
    }

    private List<RecommendContent> getRecommendContents(List<RecommendContentEntity> entities) {
        Set<Long> contentIds = entities.stream().map(RecommendContentEntity::getContentId).collect(Collectors.toSet());
        long start = System.currentTimeMillis();
        List<ContentEntity> contentEntities = contentRepository.findAll(contentIds);
        System.out.println("contentRepository.findAll \t" + String.valueOf(System.currentTimeMillis() - start));

        Map<Long, ContentEntity> contentMap = contentEntities.stream().collect(Collectors.toMap(ContentEntity::getId, item -> item));
        Set<Long> moduleIds = contentEntities.stream().map(ContentEntity::getModuleId).collect(Collectors.toSet());
        start = System.currentTimeMillis();
//        List<SimpleModule> modules = moduleService.findSimpleByIds(Lists.newArrayList(moduleIds));
//        System.out.println("moduleService.findSimpleByIds \t" + String.valueOf(System.currentTimeMillis() - start));
//        Map<Long, SimpleModule> moduleMap = modules.stream().collect(Collectors.toMap(SimpleModule::getId, item -> item));
        start = System.currentTimeMillis();
        List<ContentTag> contentTags = contentTagService.findByContentIds(Lists.newArrayList(contentIds));
//        System.out.println("contentTagService.findByContentIds \t" + String.valueOf(System.currentTimeMillis() - start));
        Map<Long, ContentTag> contentTagMap = contentTags.stream().collect(Collectors.toMap(ContentTag::getContentId, item -> item));

        List<RecommendContent> contents = entities.stream().map(item -> {
            RecommendContent recommendContent = BeanMapper.map(item, RecommendContent.class);
            ContentEntity contentEntity = contentMap.get(item.getContentId());
//            SimpleModule module = moduleMap.get(item.getModuleId());
            ContentTag contentTag = contentTagMap.get(item.getContentId());
            if (Objects.nonNull(contentEntity)) {
                recommendContent.setModule("123");
                recommendContent.setDataType(contentEntity.getDataType());
                recommendContent.setTitle(contentEntity.getTitle());
                recommendContent.setCoverId(contentEntity.getCoverId());
                recommendContent.setCoverUri(contentEntity.getCoverUri());
                recommendContent.setCopyrightId(contentEntity.getCopyrightId());
                recommendContent.setCopyrightBeginAt(contentEntity.getCopyrightBeginAt());
                recommendContent.setCopyrightEndAt(contentEntity.getCopyrightEndAt());
                recommendContent.setSummary(contentEntity.getSummary());
                recommendContent.setContentTag(contentTag);
                recommendContent.setLink(contentEntity.getLink());
            }
            return recommendContent;
        }).collect(Collectors.toList());
        return contents;
    }

    /**
     * 查找指定页码的推荐内容
     *
     * @param seatId
     * @param filterId
     * @param page
     * @param size     @return
     */
    @Override
    public List<RecommendContent> findEnabledContentsBySeatIdAndFilterId(Long seatId, Long filterId, Integer page, Integer size) {
        Date current = new Date();
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        PageRequest request = new PageRequest(page, size, sort);

        List<RecommendContentEntity> entities = recommendContentRepository.findByFilterIdAndSeatIdAndStatusAndIsDeletedAndBeginAtLessThanEqualAndEndAtGreaterThan(filterId, seatId, CmsConstants.ContentStatus.ONLINED.getCode(), Boolean.FALSE, current, current, request);
        if(CollectionUtil.isEmpty(entities)){
            return Lists.newArrayList();
        }
        return getRecommendContents(entities);
    }

    @Override
    public Long create(RecommendSeatContent recommendSeatContent, Long sysUserId) {
        Long id = recommendSeatContent.getId();
        RecommendContentEntity entity = Objects.isNull(id) ? null : recommendContentRepository.findOne(recommendSeatContent.getId());
        ContentEntity contentEntity = contentRepository.findOne(recommendSeatContent.getContentId());
        SimpleResource resource = null;
        if(Objects.isNull(entity)){
            entity = new RecommendContentEntity();
            entity.setModuleId(recommendSeatContent.getModuleId());
            entity.setSeatId(recommendSeatContent.getSeatId());
            entity.setFilterId(recommendSeatContent.getFilterId());
            entity.setContentId(recommendSeatContent.getContentId());
            entity.setStatus(contentEntity.getStatus());
            entity.setIsDeleted(recommendSeatContent.getIsDeleted());
            entity.setResourceId(resource.getId());
            entity.setResourceUri(resource.getUri());
            entity.setCreatorId(sysUserId);
            entity.setCreatedAt(new Date());
            try {
                entity.setBeginAt(DateFormatUtil.pareDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, recommendSeatContent.getBeginAt() + " 00:00:00"));
                entity.setEndAt(DateFormatUtil.pareDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, recommendSeatContent.getEndAt() + " 23:59:59"));
            } catch (ParseException e) {
                throw ServiceException.build(CommonExceptionConstants.not_available_data);
            }
            recommendContentRepository.save(entity);
        }else {
            entity.setIsDeleted(recommendSeatContent.getIsDeleted());
            entity.setResourceId(resource.getId());
            entity.setResourceUri(resource.getUri());
            entity.setModifierId(sysUserId);
            entity.setModifiedAt(new Date());
            try {
                entity.setBeginAt(DateFormatUtil.pareDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, recommendSeatContent.getBeginAt() + " 00:00:00"));
                entity.setEndAt(DateFormatUtil.pareDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, recommendSeatContent.getEndAt() + " 23:59:59"));
            } catch (ParseException e) {
                throw ServiceException.build(CommonExceptionConstants.not_available_data);
            }
            recommendContentRepository.save(entity);
        }
        return entity.getId();
    }

    /**
     * 删除
     *
     * @param contentIds
     * @param sysUserId
     * @return
     */
    @Override
    public Boolean delete(List<Long> contentIds, Long sysUserId) {
        List<RecommendContentEntity> entities = recommendContentRepository.findByContentIdInAndIsDeleted(contentIds, Boolean.FALSE);
        Date date = new Date();
        entities.stream().forEach(item -> {
            item.setIsDeleted(Boolean.TRUE);
            item.setModifiedAt(date);
            item.setModifierId(sysUserId);
            recommendContentRepository.save(item);
        });
        return Boolean.TRUE;
    }

    /**
     * 批量上架
     *
     * @param contentIds
     * @param sysUserId
     * @return
     */
    @Override
    public Boolean online(List<Long> contentIds, Long sysUserId) {
        List<RecommendContentEntity> entities = recommendContentRepository.findByContentIdInAndStatusAndIsDeleted(contentIds, CmsConstants.ContentStatus.UNONLINED.getCode(), Boolean.FALSE);
        Date date = new Date();
        entities.stream().forEach(item -> {
            item.setStatus(CmsConstants.ContentStatus.ONLINED.getCode());
            item.setModifiedAt(date);
            item.setModifierId(sysUserId);
            recommendContentRepository.save(item);
        });
        return Boolean.TRUE;
    }

    /**
     * 批量下架
     *
     * @param contentIds
     * @param sysUserId
     * @return
     */
    @Override
    public Boolean offline(List<Long> contentIds, Long sysUserId) {
        List<RecommendContentEntity> entities = recommendContentRepository.findByContentIdInAndStatusAndIsDeleted(contentIds, CmsConstants.ContentStatus.ONLINED.getCode(), Boolean.FALSE);
        Date date = new Date();
        entities.stream().forEach(item -> {
            item.setStatus(CmsConstants.ContentStatus.UNONLINED.getCode());
            item.setModifiedAt(date);
            item.setModifierId(sysUserId);
            recommendContentRepository.save(item);
        });
        return Boolean.TRUE;
    }
}
