package com.yidiantong.cms;

import com.yidiantong.spring.boot.feign.YdtErrorDecoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Created by konghang on 2017/7/22.
 */
@SpringBootApplication
public class CmsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CmsApplication.class).web(Boolean.TRUE).run(args);
    }

    @Bean
    public YdtErrorDecoder createYdtErrorDecoder(){
        return new YdtErrorDecoder();
    }
}
