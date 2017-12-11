package com.yidiantong.cms.content.controllers;

import com.yidiantong.cms.content.dtos.RecommendContent;
import com.yidiantong.cms.content.dtos.RecommendSeatContent;
import com.yidiantong.cms.content.dtos.RecommendSeatSubject;
import com.yidiantong.cms.content.dtos.RecommendSubject;
import com.yidiantong.cms.content.services.RecommendContentService;
import com.yidiantong.cms.content.sevices.RecommendRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by konghang on 2017/8/16.
 */
@RestController
public class RecommendController implements RecommendRestService{

    @Autowired
    private RecommendContentService recommendContentService;

    /**
     * 根据位置查找有效的推荐内容
     * <p>
     * 默认按最近的推荐时间倒叙排列
     *
     * @param seatId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<RecommendContent> findEnabledContentsBySeatId(@RequestParam(value = "seatId") Long seatId,
                                                              @RequestParam(value = "page") Integer page,
                                                              @RequestParam(value = "size") Integer size) {
        return recommendContentService.findEnabledContentsBySeatId(seatId, page, size);
    }

    /**
     * 根据位置查找有效的推荐内容
     * <p>
     * 默认按最近的推荐时间倒叙排列
     *
     * @param seatId
     * @param filterId
     * @param page
     * @param size     @return
     */
    @Override
    public List<RecommendContent> findEnabledContentsBySeatIdAndFilterId(@RequestParam(value = "seatId") Long seatId,
                                                                         @RequestParam(value = "filterId") Long filterId,
                                                                         @RequestParam(value = "page") Integer page,
                                                                         @RequestParam(value = "size") Integer size) {
        return recommendContentService.findEnabledContentsBySeatIdAndFilterId(seatId, filterId, page, size);
    }

    /**
     * 根据位置查找有效的推荐专题
     * <p>
     * 默认按最近的推荐时间倒叙排列
     *
     * @param seatId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<RecommendSubject> findEnabledSubjectsBySeatId(Long seatId, Integer page, Integer size) {
        return null;
    }

    /**
     * 创建或修改推荐
     *
     * @param recommendSeatContent
     * @param sysUserId
     * @return
     */
    @Override
    public Long createOrUpdateRecommend(@RequestBody RecommendSeatContent recommendSeatContent, @RequestParam(value = "sysUserId") Long sysUserId) {
        return recommendContentService.create(recommendSeatContent, sysUserId);
    }

    /**
     * 创建或修改推荐
     *
     * @param recommendSeatSubject
     * @param sysUserId
     * @return
     */
    @Override
    public Long createOrUpdateRecommend(@RequestBody RecommendSeatSubject recommendSeatSubject, @RequestParam(value = "sysUserId") Long sysUserId) {
        return null;
    }
}
