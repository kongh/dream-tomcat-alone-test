package com.yidiantong.cms.content.controllers;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by konghang on 2017/8/16.
 */
@RestController
public class TestController  {

    @RequestMapping(value = "/test")
    public List<String> test() {
        return Lists.newArrayList("123","321");
    }
}


