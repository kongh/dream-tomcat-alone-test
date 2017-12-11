package com.yidiantong.cms.content.services;

import com.yidiantong.cms.content.dtos.RecommendContent;
import com.yidiantong.cms.content.dtos.RecommendSubject;

import java.util.List;

/**
 * 推荐服务
 *
 * Created by konghang on 2017/8/16.
 */
public interface RecommendService {

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
     * 查找指定页码的推荐专题
     *
     * @param seatId
     * @param page
     * @param size
     * @return
     */
    List<RecommendSubject> findEnabledSubjectsBySeatId(Long seatId, Integer page, Integer size);
}
