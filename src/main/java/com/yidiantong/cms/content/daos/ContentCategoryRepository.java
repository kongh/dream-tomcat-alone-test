package com.yidiantong.cms.content.daos;

import com.yidiantong.cms.content.models.ContentCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by konghang on 2017/6/16.
 */
public interface ContentCategoryRepository extends JpaRepository<ContentCategoryEntity, Long>, JpaSpecificationExecutor<ContentCategoryEntity> {

    /**
     * 查找
     *
     * @param moduleId
     * @param categoryIds
     * @param isDeleted
     * @return
     */
    List<ContentCategoryEntity> findByModuleIdAndCategoryIdInAndIsDeleted(Long moduleId, List<Long> categoryIds, Boolean isDeleted);

    /**
     * 查找
     *
     * @param categoryId
     * @param isDeleted
     * @return
     */
    List<ContentCategoryEntity> findByCategoryIdAndIsDeleted(Long categoryId, Boolean isDeleted);

    /**
     * 查找
     *
     * @param contentId
     * @param isDeleted
     * @return
     */
    List<ContentCategoryEntity> findByContentIdAndIsDeleted(Long contentId, Boolean isDeleted);

    /**
     * 查找
     *
     * @param contentIds
     * @param isDeleted
     * @return
     */
    List<ContentCategoryEntity> findByContentIdInAndIsDeleted(List<Long> contentIds, Boolean isDeleted);
}
