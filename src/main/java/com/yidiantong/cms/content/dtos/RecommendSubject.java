package com.yidiantong.cms.content.dtos;

/**
 * 推荐专题
 *
 * Created by konghang on 2017/8/21.
 */
public class RecommendSubject extends SimpleRecommendSubject{

    private String module;
    private String subjectLink;
    private String dataType;
    private String name;
    private String subName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubjectLink() {
        return subjectLink;
    }

    public void setSubjectLink(String subjectLink) {
        this.subjectLink = subjectLink;
    }
}
