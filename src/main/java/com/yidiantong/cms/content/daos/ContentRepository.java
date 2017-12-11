package com.yidiantong.cms.content.daos;

import com.yidiantong.cms.content.models.ContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by konghang on 2017/6/16.
 */
public interface ContentRepository extends JpaRepository<ContentEntity, Long>, JpaSpecificationExecutor<ContentEntity> {

    /**
     * 查找
     *
     * @param moduleId
     * @param status
     * @param pageable
     * @return
     */
    List<ContentEntity> findByModuleIdAndStatus(Long moduleId, String status, Pageable pageable);

    /**
     * 查找
     *
     * @param ids
     * @param isDeleted
     * @param pageable
     * @return
     */
    Page<ContentEntity> findByIdInAndIsDeleted(List<Long> ids, Boolean isDeleted, Pageable pageable);
}
