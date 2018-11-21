package org.liujing.generator.object.java;

import java.util.List;

public class MemberObject {
    private String comment;
    private List<AnnotationObject> annotationList;
    private String typeName;
    private String name;
    private String defaultValue;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<AnnotationObject> getAnnotationList() {
        return annotationList;
    }

    public void setAnnotationList(List<AnnotationObject> annotationList) {
        this.annotationList = annotationList;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}