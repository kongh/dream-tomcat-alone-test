package com.yidiantong.cms.content.daos;

import com.yidiantong.cms.content.models.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by konghang on 2017/6/16.
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {

    /**
     * 查找
     *
     * @param moduleId
     * @param type
     * @param status
     * @return
     */
    List<CategoryEntity> findByModuleIdAndTypeAndStatus(Long moduleId, String type, String status, Pageable pageable);

    /**
     * 查找
     *
     * @param type
     * @param code
     * @param status
     * @param pageable
     * @return
     */
    List<CategoryEntity> findByTypeAndCodeStartsWithAndStatus(String type, String code, String status, Pageable pageable);

    /**
     * 查找
     *
     * @param type
     * @param code
     * @param status
     * @return
     */
    List<CategoryEntity> findByTypeAndCodeStartsWithAndStatus(String type, String code, String status);

    /**
     * 查找模块下所有的分类
     *
     * @param code
     * @param status
     * @return
     */
    List<CategoryEntity> findByCodeStartsWithAndStatus(String code, String status);


    /**
     * 查找
     *
     * @param type
     * @param status
     * @return
     */
    List<CategoryEntity> findByTypeAndStatus(String type, String status);
}
