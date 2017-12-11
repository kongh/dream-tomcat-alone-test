package com.yidiantong.cms.content.daos;

import com.yidiantong.cms.content.models.RecommendContentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

/**
 * Created by konghang on 2017/6/16.
 */
public interface RecommendContentRepository extends JpaRepository<RecommendContentEntity, Long>, JpaSpecificationExecutor<RecommendContentEntity> {

    /**
     * 查找
     *
     * @param seatId
     * @param status
     * @param isDeleted
     * @param beginAt
     * @param endAt
     * @param pageable
     * @return
     */
    List<RecommendContentEntity> findBySeatIdAndStatusAndIsDeletedAndBeginAtLessThanEqualAndEndAtGreaterThan(Long seatId, String status, Boolean isDeleted, Date beginAt, Date endAt, Pageable pageable);

    /**
     * 查找
     *
     * @param seatId
     * @param status
     * @param isDeleted
     * @param beginAt
     * @param endAt
     * @param pageable
     * @return
     */
    List<RecommendContentEntity> findByFilterIdAndSeatIdAndStatusAndIsDeletedAndBeginAtLessThanEqualAndEndAtGreaterThan(Long filterId, Long seatId, String status, Boolean isDeleted, Date beginAt, Date endAt, Pageable pageable);

    /**
     * 查找
     *
     * @param contentIds
     * @param isDeleted
     * @return
     */
    List<RecommendContentEntity> findByContentIdInAndIsDeleted(List<Long> contentIds, Boolean isDeleted);

    /**
     * 查找
     *
     * @param contentIds
     * @param status
     * @param isDeleted
     * @return
     */
    List<RecommendContentEntity> findByContentIdInAndStatusAndIsDeleted(List<Long> contentIds, String status, Boolean isDeleted);

    /**
     * 查找
     *
     * @param contentId
     * @param seatIds
     * @return
     */
    List<RecommendContentEntity> findByContentIdAndSeatIdIn(Long contentId, List<Long> seatIds);

    /**
     * 查找
     *
     * @param contentId
     * @param filterId
     * @param seatIds
     * @return
     */
    List<RecommendContentEntity> findByContentIdAndFilterIdAndSeatIdIn(Long contentId, Long filterId, List<Long> seatIds);
}
