package com.yidiantong.cms.content.services;

import com.yidiantong.cms.content.dtos.RecommendContent;
import com.yidiantong.cms.content.dtos.RecommendSeatContent;

import java.util.List;

/**
 * 推荐服务
 *
 * Created by konghang on 2017/8/16.
 */
public interface RecommendContentService {

    /**
     * 查找指定页码的推荐内容
     *
     * @param seatId
     * @param page
     * @param size
     * @return
     */
    List<RecommendContent> findEnabledContentsBySeatId(Long seatId, Integer page, Integer size);

    /**
     * 查找指定页码的推荐内容
     *
     * @param seatId
     * @param page
     * @param size
     * @return
     */
    List<RecommendContent> findEnabledContentsBySeatIdAndFilterId(Long seatId, Long filterId, Integer page, Integer size);

    /**
     * 创建
     *
     * @param recommendSeatContent
     * @param sysUserId
     * @return
     */
    Long create(RecommendSeatContent recommendSeatContent, Long sysUserId);

    /**
     * 删除
     *
     * @param contentIds
     * @return
     */
    Boolean delete(List<Long> contentIds, Long sysUserId);

    /**
     * 批量上架
     *
     * @param contentIds
     * @return
     */
    Boolean online(List<Long> contentIds, Long sysUserId);

    /**
     * 批量下架
     *
     * @param contentIds
     * @return
     */
    Boolean offline(List<Long> contentIds, Long sysUserId);
}
