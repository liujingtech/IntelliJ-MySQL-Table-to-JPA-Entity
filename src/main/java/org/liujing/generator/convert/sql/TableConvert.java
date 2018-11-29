package org.liujing.generator.convert.sql;

import org.liujing.generator.object.sql.ColumnObject;
import org.liujing.generator.object.sql.TableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TableConvert {

    public static TableObject convertToTableObject(String ddlString) {
        //获取表内结构
        String allColumnStr = org.liujing.generator.convert.sql.TableConvert.getAllColumnStr(ddlString);
        List<String> fullList = org.liujing.generator.convert.sql.TableConvert.getSingleColumn(allColumnStr);
        //获取字段结构，除了主外键声明
        ArrayList<String> lineWithOutKeyList = new ArrayList<>(fullList);
        //从列表中移除字段中主外键
        lineWithOutKeyList.removeIf(line -> getColumnKey(line) != null && Objects.requireNonNull(getColumnKey(line)).length() > 0);

        //从字段结构转换成字段对象
        List<ColumnObject> columnObjectList = new ArrayList<>();
        lineWithOutKeyList.forEach(line -> {
            ColumnObject columnObject = ColumnConverter.convertToColumnObject(line);
            columnObjectList.add(columnObject);
        });

        //获取主键字段
        Map<String, ColumnObject> map = columnObjectList.stream().collect(Collectors.toMap(ColumnObject::getName, Function.identity()));
        String key = getPrimaryKey(ddlString);
        if (key != null && Objects.requireNonNull(key).length() > 0) {
            ColumnObject columnObject = map.get(key);
            columnObject.setIdentity(true);
        }

        //获取表对象
        TableObject tableObject = new TableObject();
        tableObject.setName(org.liujing.generator.convert.sql.TableConvert.getTableName(ddlString));
        tableObject.setComment(org.liujing.generator.convert.sql.TableConvert.getTableComment(ddlString));
        tableObject.setColumnObjectList(columnObjectList);
        return tableObject;
    }

    private static String getTableName(String str) {
        String pattern = "(?i)create table `(\\S*)`";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1);
        } else {
            throw new IllegalArgumentException("无法找到表名");
        }
    }


    private static String getTableComment(String str) {
        String pattern = "CREATE TABLE [\\s\\S]*\\)[\\s\\S]*COMMENT='([\\S\\s]*)'";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1);
        } else {
            throw new IllegalArgumentException("无法找到表注释");
        }
    }

    private static String getAllColumnStr(String str) {
        String pattern = "\\(([\\s\\S]*)\\)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1).trim();
        } else {
            throw new IllegalArgumentException("无法找到表");
        }
    }


    private static List<String> getSingleColumn(String str) {
        List<String> lineList = new ArrayList<>();
        String pattern = "([\\s\\S]*?),\\n";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        while (m.find()) {
            String trim = m.group(1).trim();
            lineList.add(trim);
        }
        return lineList;
    }

    private static String getColumnKey(String str) {
        String pattern = "^(PRIMARY KEY|UNIQUE KEY|KEY)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1).trim();
        }
        return null;
    }

    private static String getPrimaryKey(String str) {
        String pattern = ",\\n *PRIMARY KEY \\(`(\\S*)`\\)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1).trim();
        }
        return null;
    }
}
