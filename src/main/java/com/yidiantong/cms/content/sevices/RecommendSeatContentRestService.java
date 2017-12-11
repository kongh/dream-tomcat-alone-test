package com.yidiantong.cms.content.sevices;//package com.yidiantong.cms.content.sevices;
//
//import com.yidiantong.cms.content.dtos.RecommendSeatContent;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
///**
// * Created by konghang on 2017/8/23.
// */
//public interface RecommendSeatContentRestService {
//
//    /**
//     * 根据指定module下所有推荐位置关联的内容
//     *
//     * @param platform
//     * @param module
//     * @param contentId
//     * @return
//     */
//    @GetMapping(value = "/recommend/seat/contents")
//    List<RecommendSeatContent> findRecommendSeatContents(@RequestParam(value = "platform") String platform,
//                                                         @RequestParam(value = "module") String module,
//                                                         @RequestParam(value = "contentId") Long contentId);
//}
