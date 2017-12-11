package com.yidiantong.cms.tag.services.impl;

import com.google.common.collect.Lists;
import com.yidiantong.auth.sysuser.dtos.SimpleSysUser;
import com.yidiantong.cms.tag.daos.CmsTagRepository;
import com.yidiantong.cms.tag.dtos.CmsTag;
import com.yidiantong.cms.tag.dtos.SimpleCmsTag;
import com.yidiantong.cms.tag.dtos.TagForCreateReq;
import com.yidiantong.cms.tag.models.CmsTagEntity;
import com.yidiantong.cms.tag.services.TagService;
import com.yidiantong.core.dtos.ResultsTotalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.collection.CollectionUtil;
import org.springside.modules.utils.mapper.BeanMapper;
import org.springside.modules.utils.time.DateFormatUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by SUN at 2017/8/18
 */
@Service
public class  TagServiceImpl implements TagService {
    @Autowired
    CmsTagRepository contentTagsRepository;


    @Override
    public Boolean createTag(TagForCreateReq tagForCreateReq, Long sysUserId) {
        if(Objects.isNull(tagForCreateReq)){
            return  false;
        }
        CmsTagEntity entity=new CmsTagEntity();
        entity.setCreatedAt(new Date());
        entity.setCreatorId(sysUserId);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setName(tagForCreateReq.getName());
        contentTagsRepository.save(entity);
        return true;
    }

    @Override
    public Boolean deleteTags(List<Long> ids, Long sysUserId) {
        if(CollectionUtil.isNotEmpty(ids)){
            ids.stream().forEach(item->{
                CmsTagEntity contentTagsEntity=contentTagsRepository.findOne(item);
                contentTagsEntity.setIsDeleted(Boolean.TRUE);
                contentTagsEntity.setModifiedAt(new Date());
                contentTagsEntity.setModifierId(sysUserId);
                contentTagsRepository.save(contentTagsEntity);
            });
        }
        return true;
    }

    @Override
    public ResultsTotalDTO<CmsTag> findTagsPageable(Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        PageRequest request = new PageRequest(page, size, sort);
        Page<CmsTagEntity> tagPage =contentTagsRepository.findByIsDeletedIsFalse(request);

        //用户
        Set<Long> creatorIds = tagPage.getContent().stream().filter(item -> Objects.nonNull(item.getCreatorId())).map(CmsTagEntity::getCreatorId).collect(Collectors.toSet());
        Set<Long> modiferIds = tagPage.getContent().stream().filter(item -> Objects.nonNull(item.getModifierId())).map(CmsTagEntity::getModifierId).collect(Collectors.toSet());
        creatorIds.addAll(modiferIds);
        List<SimpleSysUser> sysUsers =  Lists.newArrayList() ;
        Map<Long, SimpleSysUser> sysUserMap = sysUsers.stream().collect(Collectors.toMap(SimpleSysUser::getId, item -> item));

        List<CmsTag> contentTagList= BeanMapper.mapList(tagPage.getContent(),CmsTagEntity.class,CmsTag.class);
        contentTagList.stream().map(item->{
            SimpleSysUser creator = sysUserMap.get(item.getCreatorId());
            SimpleSysUser modifier = sysUserMap.get(item.getModifierId());
            item.setCreatorName(creator.getName());
            item.setCreatedAtStr(Objects.isNull(item.getCreatedAt()) ?"" : DateFormatUtil.formatDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND,item.getCreatedAt()));
            item.setModifiedAtStr(Objects.isNull(item.getModifiedAt()) ?"" : DateFormatUtil.formatDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND,item.getModifiedAt()));
            item.setModifierUserName(Objects.nonNull(modifier) ? modifier.getName() : "");
            return item;
        }).collect(Collectors.toList());
        return ResultsTotalDTO.build(contentTagList,tagPage.getTotalElements());
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public SimpleCmsTag findOneSimpleById(Long id) {
        CmsTagEntity entity = contentTagsRepository.findOne(id);
        return BeanMapper.map(entity, SimpleCmsTag.class);
    }

    /**
     * 查找
     *
     * @param ids
     * @return
     */
    @Override
    public List<SimpleCmsTag> findSimplesByIds(List<Long> ids) {
        List<CmsTagEntity> entities = contentTagsRepository.findAll(ids);
        return BeanMapper.mapList(entities, CmsTagEntity.class, SimpleCmsTag.class);
    }
}
