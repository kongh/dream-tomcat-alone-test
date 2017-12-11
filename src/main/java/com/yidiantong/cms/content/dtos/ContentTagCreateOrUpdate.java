package com.yidiantong.cms.content.dtos;

import java.io.Serializable;

/**
 * Created by konghang on 2017/9/10.
 */
public class ContentTagCreateOrUpdate implements Serializable {

    private Long id;

    private Long contentId;

    private Long tagId;

    private String beginAt;

    private String endAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(String beginAt) {
        this.beginAt = beginAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }
}
