package org.liujing.generator.object.java;

import java.util.List;

public class ClassObject {
    private String comment;
    private List<AnnotationObject> annotationList;
    private String name;
    private List<MemberObject> memberObjectList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MemberObject> getMemberObjectList() {
        return memberObjectList;
    }

    public void setMemberObjectList(List<MemberObject> memberObjectList) {
        this.memberObjectList = memberObjectList;
    }
}
