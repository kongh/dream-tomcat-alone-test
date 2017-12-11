package com.yidiantong.cms.content.dtos;

import java.io.Serializable;

/**
 * Created by konghang on 2017/9/12.
 */
public class Content extends SimpleContent implements Serializable {

    private ContentTag contentTag;

    public ContentTag getContentTag() {
        return contentTag;
    }

    public void setContentTag(ContentTag contentTag) {
        this.contentTag = contentTag;
    }
}
