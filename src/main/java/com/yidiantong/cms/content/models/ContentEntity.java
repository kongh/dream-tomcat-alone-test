package com.yidiantong.cms.content.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by konghang on 2017/8/16.
 */
@Entity
@Table(name = "contents")
public class ContentEntity {
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "module_id")
    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "cover_id")
    public Long getCoverId() {
        return coverId;
    }

    public void setCoverId(Long coverId) {
        this.coverId = coverId;
    }

    @Basic
    @Column(name = "cover_uri")
    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }

    @Basic
    @Column(name = "copyright_id")
    public Long getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(Long copyrightId) {
        this.copyrightId = copyrightId;
    }

    @Basic
    @Column(name = "copyright_begin_at")
    public Date getCopyrightBeginAt() {
        return copyrightBeginAt;
    }

    public void setCopyrightBeginAt(Date copyrightBeginAt) {
        this.copyrightBeginAt = copyrightBeginAt;
    }

    @Basic
    @Column(name = "copyright_end_at")
    public Date getCopyrightEndAt() {
        return copyrightEndAt;
    }

    public void setCopyrightEndAt(Date copyrightEndAt) {
        this.copyrightEndAt = copyrightEndAt;
    }

    @Basic
    @Column(name = "is_deleted")
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "creator_id")
    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "modified_at")
    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Basic
    @Column(name = "modifier_id")
    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    @Basic
    @Column(name = "data_type")
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentEntity that = (ContentEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
