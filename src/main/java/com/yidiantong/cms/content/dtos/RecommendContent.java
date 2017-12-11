package com.yidiantong.cms.content.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 * 推荐内容
 *
 * Created by konghang on 2017/8/16.
 */
public class RecommendContent extends SimpleRecommendContent implements Serializable{

    private ContentTag contentTag;
    private String module;
    private String dataType;
    private String title;
    private Long coverId;
    private String coverUri;
    private Long copyrightId;
    private Date copyrightBeginAt;
    private Date copyrightEndAt;
    private String summary;
    private String link;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCoverId() {
        return coverId;
    }

    public void setCoverId(Long coverId) {
        this.coverId = coverId;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }

    public Long getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(Long copyrightId) {
        this.copyrightId = copyrightId;
    }

    public Date getCopyrightBeginAt() {
        return copyrightBeginAt;
    }

    public void setCopyrightBeginAt(Date copyrightBeginAt) {
        this.copyrightBeginAt = copyrightBeginAt;
    }

    public Date getCopyrightEndAt() {
        return copyrightEndAt;
    }

    public void setCopyrightEndAt(Date copyrightEndAt) {
        this.copyrightEndAt = copyrightEndAt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ContentTag getContentTag() {
        return contentTag;
    }

    public void setContentTag(ContentTag contentTag) {
        this.contentTag = contentTag;
    }
}
