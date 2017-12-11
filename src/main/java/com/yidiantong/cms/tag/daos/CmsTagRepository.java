package com.yidiantong.cms.tag.daos;

import com.yidiantong.cms.tag.models.CmsTagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by SUN at 2017/8/18
 */
public interface CmsTagRepository extends JpaRepository<CmsTagEntity, Long>, JpaSpecificationExecutor<CmsTagEntity> {
    Page<CmsTagEntity> findByIsDeletedIsFalse(Pageable pageable);
}
