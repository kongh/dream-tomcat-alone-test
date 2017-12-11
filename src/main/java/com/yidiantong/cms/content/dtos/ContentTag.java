package com.yidiantong.cms.content.dtos;

import java.util.Objects;

/**
 * Created by konghang on 2017/9/10.
 */
public class ContentTag extends SimpleContentTag {

    private String tagName;
    private String beginAtStr;
    private String endAtStr;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getBeginAtStr() {
        return beginAtStr;
    }

    public void setBeginAtStr(String beginAtStr) {
        this.beginAtStr = beginAtStr;
    }

    public String getEndAtStr() {
        return endAtStr;
    }

    public void setEndAtStr(String endAtStr) {
        this.endAtStr = endAtStr;
    }

    public static Boolean isValid(ContentTag contentTag){
        if(Objects.isNull(contentTag)){
            return Boolean.FALSE;
        }
        long current = System.currentTimeMillis();
        return current >= contentTag.getBeginAt().getTime() && current < contentTag.getEndAt().getTime();
    }
}
