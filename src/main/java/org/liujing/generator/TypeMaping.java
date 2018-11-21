package org.liujing.generator;

import java.util.HashMap;
import java.util.Map;

public class TypeMaping {
    private static Map<String, String> typeMap;

    /**
     * MySQL type to Java type
     * org.liujing.generator.convert map by https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-type-conversions.html
     */
    static {
        typeMap = new HashMap<>();
        typeMap.put("CHAR", "String");
        typeMap.put("VARCHAR", "String");
        typeMap.put("LONGVARCHAR", "String");
        typeMap.put("TINYTEXT", "String");
        typeMap.put("TEXT", "String");
        typeMap.put("MEDIUMTEXT", "String");
        typeMap.put("LONGTEXT", "String");
        typeMap.put("TINYINT", "Integer");
        typeMap.put("BOOL", "Boolean");
        typeMap.put("BOOLEAN", "Boolean");
        typeMap.put("SMALLINT", "Integer");
        typeMap.put("MEDIUMINT", "Integer");
        typeMap.put("INT", "Integer");
        typeMap.put("INTEGER", "Integer");
        typeMap.put("BIGINT", "Long");
        typeMap.put("FLOAT", "Float");
        typeMap.put("DOUBLE", "Double");
        typeMap.put("DECIMAL", "java.math.BigDecimal");
        typeMap.put("DATE", "java.sql.Date");
        typeMap.put("DATETIME", "java.sql.Timestamp");
        typeMap.put("TIMESTAMP", "java.util.Date");
        typeMap.put("TIME", "java.sql.Time");
        typeMap.put("YEAR[(2|4)]", "java.sql.Date");
        typeMap.put("BINARY", "byte[]");
        typeMap.put("VARBINARY", "byte[]");
        typeMap.put("TINYBLOB", "byte[]");
        typeMap.put("BLOB", "byte[]");
        typeMap.put("MEDIUMBLOB", "byte[]");
        typeMap.put("LONGBLOB", "byte[]");
    }

    public static String getJavaTypeByDDLType(String ddlType) {
        String type = ddlType.replaceAll("\\(\\d+\\)", "").toUpperCase();
        String javaType = typeMap.get(type);
        return javaType == null || javaType.isEmpty() ? ddlType + "//FIXME not support type" : javaType;
    }
}