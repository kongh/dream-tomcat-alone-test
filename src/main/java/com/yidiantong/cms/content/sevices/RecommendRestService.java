package com.yidiantong.cms.content.sevices;

import com.yidiantong.cms.content.dtos.RecommendContent;
import com.yidiantong.cms.content.dtos.RecommendSeatContent;
import com.yidiantong.cms.content.dtos.RecommendSeatSubject;
import com.yidiantong.cms.content.dtos.RecommendSubject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by konghang on 2017/8/16.
 */
public interface RecommendRestService {

    /**
     * 根据位置查找有效的推荐内容
     *
     * 默认按最近的推荐时间倒叙排列
     * @param seatId
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/content/recommends")
    List<RecommendContent> findEnabledContentsBySeatId(@RequestParam(value = "seatId") Long seatId, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size);

    /**
     * 根据位置查找有效的推荐内容
     *
     * 默认按最近的推荐时间倒叙排列
     * @param seatId
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/content/filter-recommends")
    List<RecommendContent> findEnabledContentsBySeatIdAndFilterId(@RequestParam(value = "seatId") Long seatId, @RequestParam(value = "filterId") Long filterId, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size);

    /**
     * 创建或修改推荐
     *
     * @param recommendSeatContent
     * @param sysUserId
     * @return
     */
    @PostMapping(value = "/content/recommend", consumes = MediaType.APPLICATION_JSON_VALUE)
    Long createOrUpdateRecommend(@RequestBody RecommendSeatContent recommendSeatContent, @RequestParam(value = "sysUserId") Long sysUserId);


    /**
     * 根据位置查找有效的推荐专题
     *
     * 默认按最近的推荐时间倒叙排列
     * @param seatId
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/subject/recommends")
    List<RecommendSubject> findEnabledSubjectsBySeatId(@RequestParam(value = "seatId") Long seatId, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size);

    /**
     * 创建或修改推荐
     *
     * @param recommendSeatSubject
     * @param sysUserId
     * @return
     */
    @PostMapping(value = "/subject/recommend", consumes = MediaType.APPLICATION_JSON_VALUE)
    Long createOrUpdateRecommend(@RequestBody RecommendSeatSubject recommendSeatSubject, @RequestParam(value = "sysUserId") Long sysUserId);
}
