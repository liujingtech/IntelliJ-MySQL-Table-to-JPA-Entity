package org.liujing.generator.convert.java;

import org.liujing.generator.TypeMaping;
import org.liujing.generator.Util;
import org.liujing.generator.object.java.AnnotationObject;
import org.liujing.generator.object.java.MemberObject;
import org.liujing.generator.object.sql.ColumnObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberConvert {
    public static MemberObject convertMemberObject(ColumnObject columnObject) {
        MemberObject memberObject = new MemberObject();
        memberObject.setComment(getComment(columnObject));
        memberObject.setTypeName(getTypeName(columnObject));
        memberObject.setName(getMenberName(columnObject));
        memberObject.setAnnotationList(getAnnotationList(columnObject));
        memberObject.setDefaultValue(getDefaultValue(columnObject));
        return memberObject;
    }

    private static String getComment(ColumnObject columnObject) {
        return columnObject.getComment();
    }

    static String getTypeName(ColumnObject columnObject) {
        return TypeMaping.getJavaTypeByDDLType(columnObject.getType());
    }

    static String getMenberName(ColumnObject columnObject) {
        return Util.toLowCamelCase(columnObject.getName());
    }


    private static List<AnnotationObject> getAnnotationList(ColumnObject columnObject) {
        ArrayList<AnnotationObject> list = new ArrayList<>();

        AnnotationObject columnAnnotaionObject = new AnnotationObject();
        columnAnnotaionObject.setFullName("javax.persistence.Column");
        columnAnnotaionObject.setName("Column");
        HashMap<String, Object> columnValue = new HashMap<>();
        columnValue.put("name", columnObject.getName());
        columnAnnotaionObject.setValue(columnValue);
        list.add(columnAnnotaionObject);

        Boolean identity = columnObject.getIdentity();
        if (identity != null && identity) {
            AnnotationObject idAnnotaionObject = new AnnotationObject();
            idAnnotaionObject.setFullName("javax.persistence.Id");
            idAnnotaionObject.setName("Id");
            list.add(idAnnotaionObject);
        }
        Boolean autoIncrement = columnObject.getAutoIncrement();
        if (autoIncrement != null && autoIncrement) {
            AnnotationObject generatedValueAnnotaionObject = new AnnotationObject();
            generatedValueAnnotaionObject.setFullName("javax.persistence.GeneratedValue");
            generatedValueAnnotaionObject.setName("GeneratedValue");
            HashMap<String, Object> generatedValue = new HashMap<>();
            generatedValue.put("strategy", javax.persistence.GenerationType.AUTO);
            generatedValueAnnotaionObject.setValue(generatedValue);
            list.add(generatedValueAnnotaionObject);
        }

        return list;
    }

    private static String getDefaultValue(ColumnObject columnObject) {
        String result = null;
        String defaultStr = columnObject.getDefaultStr();
        if ("CURRENT_TIMESTAMP".equalsIgnoreCase(defaultStr)) {
//            result = "new java.util.Date()";
            result = "new Date()";
        } else if ("''".equalsIgnoreCase(defaultStr)) {
            result = "\"\"";
        } else {
            String javaType = TypeMaping.getJavaTypeByDDLType(columnObject.getType());
            if ("Long".equalsIgnoreCase(javaType) || "Integer".equalsIgnoreCase(javaType)) {
                if (defaultStr != null && defaultStr.length() >= 2) {
                    defaultStr = defaultStr.substring(1, defaultStr.length() - 1);
                    if ("Long".equalsIgnoreCase(javaType)) {
                        defaultStr += "L";
                    }
                }
            }
            result = defaultStr;
        }
        return result;
    }
}
