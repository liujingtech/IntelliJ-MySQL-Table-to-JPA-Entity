package org.liujing.generator.convert.sql;

import org.liujing.generator.object.sql.ColumnObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ColumnConverter {

    static ColumnObject convertToColumnObject(String line) {
        ColumnObject columnObject = new ColumnObject();
        columnObject.setName(getColumnName(line));
        columnObject.setType(getColumnType(line));
        columnObject.setAllowNull(getColumnAllowNull(line));
        columnObject.setAutoIncrement(getAutoIncrement(line));
        columnObject.setDefaultStr(getColumnDefault(line));
        columnObject.setComment(getColumnComment(line));
        return columnObject;
    }

    private static String getColumnName(String str) {
        String pattern = "^`(\\S*)` (\\S*)? ";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1).trim();
        } else {
            throw new IllegalArgumentException("无法找到字段");
        }
    }

    private static String getColumnType(String str) {
        String pattern = "^`\\S*` (\\S*)? ";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1).trim();
        } else {
            throw new IllegalArgumentException("无法找到类型");
        }
    }

    private static Boolean getColumnAllowNull(String str) {
        String pattern = "NOT NULL";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.find();
    }

    private static Boolean getAutoIncrement(String str) {
        String pattern = "NOT NULL AUTO_INCREMENT";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.find();
    }

    private static String getColumnDefault(String str) {
        String pattern = "DEFAULT ('[\\S\\s]*?'|CURRENT_TIMESTAMP) ";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1).trim();
        }
        return null;
    }

    private static String getColumnComment(String str) {
        String pattern = "COMMENT '([\\S\\s]*)?'";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1).trim();
        }
        return null;
    }
}
