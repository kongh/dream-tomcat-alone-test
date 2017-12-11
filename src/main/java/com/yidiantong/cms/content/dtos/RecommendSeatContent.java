package com.yidiantong.cms.content.dtos;

import java.io.Serializable;

/**
 * 推荐位置内容
 *
 * Created by konghang on 2017/8/23.
 */
public class RecommendSeatContent implements Serializable{
    private Long id;
    private Long moduleId;
    private Long seatId;
    private Long filterId;
    private Long contentId;
    private Long resourceId;
    private String resourceUri;
    private String beginAt;
    private String endAt;
    private Boolean isDeleted;

    private String seatName;
    private String allowedFormat;
    private String allowedDisplaySize;
    private Integer limitedFileSize;
    private String thumbnailUri;


    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
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

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getAllowedFormat() {
        return allowedFormat;
    }

    public void setAllowedFormat(String allowedFormat) {
        this.allowedFormat = allowedFormat;
    }

    public String getAllowedDisplaySize() {
        return allowedDisplaySize;
    }

    public void setAllowedDisplaySize(String allowedDisplaySize) {
        this.allowedDisplaySize = allowedDisplaySize;
    }

    public Integer getLimitedFileSize() {
        return limitedFileSize;
    }

    public void setLimitedFileSize(Integer limitedFileSize) {
        this.limitedFileSize = limitedFileSize;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }

    public Long getFilterId() {
        return filterId;
    }

    public void setFilterId(Long filterId) {
        this.filterId = filterId;
    }
}
