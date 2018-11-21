package org.liujing.generator.object.sql;

public class ColumnObject {
    private String name;
    private String type;
    private Boolean allowNull;
    private Boolean identity;
    private Boolean autoIncrement;
    private String defaultStr;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAllowNull() {
        return allowNull;
    }

    public void setAllowNull(Boolean allowNull) {
        this.allowNull = allowNull;
    }

    public Boolean getIdentity() {
        return identity;
    }

    public void setIdentity(Boolean identity) {
        this.identity = identity;
    }

    public Boolean getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getDefaultStr() {
        return defaultStr;
    }

    public void setDefaultStr(String defaultStr) {
        this.defaultStr = defaultStr;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ColumnObject{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", allowNull=" + allowNull +
                ", identity=" + identity +
                ", autoIncrement=" + autoIncrement +
                ", defaultStr='" + defaultStr + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
