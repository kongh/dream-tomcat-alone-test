package com.yidiantong.cms.content.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by konghang on 2017/8/20.
 */
public class SimpleContent implements Serializable {

    private Long id;
    private Long moduleId;
    private String title;
    private String dataType;
    private Long coverId;
    private String coverUri;
    private Long copyrightId;
    private Date copyrightBeginAt;
    private Date copyrightEndAt;
    private String link;
    private String summary;
    private Boolean isDeleted;
    private String status;
    private Date createdAt;
    private Long creatorId;
    private Date modifiedAt;
    private Long modifierId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
