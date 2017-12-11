package com.yidiantong.cms.content.daos;

import com.yidiantong.cms.content.models.ContentTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by konghang on 2017/6/16.
 */
public interface ContentTagRepository extends JpaRepository<ContentTagEntity, Long>, JpaSpecificationExecutor<ContentTagEntity> {

    /**
     * 查找
     *
     * @param contentId
     * @param isDeleted
     * @return
     */
    ContentTagEntity findByContentIdAndIsDeleted(Long contentId, Boolean isDeleted);

    /**
     * 查找
     *
     * @param contentIds
     * @param isDeleted
     * @return
     */
    List<ContentTagEntity> findByContentIdInAndIsDeleted(List<Long> contentIds, Boolean isDeleted);
}
