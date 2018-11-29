package org.liujing.generator.object.sql;

import java.util.List;

public class TableObject {
    private String name;
    private List<ColumnObject> columnObjectList;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ColumnObject> getColumnObjectList() {
        return columnObjectList;
    }

    public void setColumnObjectList(List<ColumnObject> columnObjectList) {
        this.columnObjectList = columnObjectList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}