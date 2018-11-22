package org.liujing.generator;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.liujing.generator.object.java.AnnotationObject;
import org.liujing.generator.object.java.ClassObject;
import org.liujing.generator.object.java.MemberObject;

import java.util.*;

public class Generator {

    public String gen(ClassObject classObject) {

        return String.format(
                "%s\n"
                        + "\n/**"
                        + "\n * %s"
                        + "\n */"
                        + "%s\n"
                        + "public class %s {\n%s}"
                , genImport(classObject)
                , classObject.getComment()
                , genClassAnnotation(classObject.getAnnotationList())
                , classObject.getName()
                , genBody(classObject)
        );
    }

    @NotNull
    private String genImport(ClassObject classObject) {
        //通过 Set 来排除相同元素
        Set<String> importSet = new HashSet<>();

        //声明在 class 上的 Annotation
        List<AnnotationObject> classAnnotationList = classObject.getAnnotationList();
        // 设置 class 的 Annotation
        setImportSetByAnnotationList(importSet, classAnnotationList);
        //Member 的包名
        classObject.getMemberObjectList().forEach(memberObject -> {
            //如果 Member 的类型需要引入包名
            String typeName = memberObject.getTypeName();
            if (!typeName.contains(".")) {
                return;
            }
            importSet.add(typeName);

            //如果 Member 声明的 Annotation 需要引入包名
            List<AnnotationObject> menberAnnotationList = memberObject.getAnnotationList();
            // 设置 member 的 Annotation
            setImportSetByAnnotationList(importSet, menberAnnotationList);
        });

        //排序
        List<String> sortedList = new ArrayList<>(importSet);
        Collections.sort(sortedList);

        StringBuilder sb = new StringBuilder();
        sortedList.forEach(importStr -> {
            sb.append(String.format("\nimport %s;"
                    , importStr
                    )
            );
        });
//        //如果大于一个import 换行
//        if (sb.length() > 0) {
//            sb.append("\n");
//        }
        return sb.toString();
    }

    private void setImportSetByAnnotationList(Set<String> importSet, List<AnnotationObject> classAnnotationList) {
        if (classAnnotationList != null) {
            classAnnotationList.forEach(annotationObject -> {
                String fullName = annotationObject.getFullName();
                if (!fullName.contains(".")) {
                    return;
                }
                importSet.add(fullName);
            });
        }
    }

    /**
     * 和 genMemberAnnotation 的唯一区别在于为了格式化有个缩进
     */
    @NotNull
    private StringBuilder genClassAnnotation(List<AnnotationObject> annotationObjectList) {
        StringBuilder stringBuilder = new StringBuilder();
        annotationObjectList.forEach(annotation -> {
            stringBuilder.append("\n@").append(annotation.getName());//@javax.persistence.EntityListeners
            genAnnotationCore(stringBuilder, annotation);
        });
        return stringBuilder;
    }

    /**
     * 和 genClassAnnotation 的唯一区别在于为了格式化有个缩进
     */
    @NotNull
    private StringBuilder genMemberAnnotation(List<AnnotationObject> annotationObjectList) {
        StringBuilder stringBuilder = new StringBuilder();
        annotationObjectList.forEach(annotation -> {
            stringBuilder.append("\n\t@").append(annotation.getName());//@javax.persistence.EntityListeners
            genAnnotationCore(stringBuilder, annotation);
        });
        return stringBuilder;
    }

    private void genAnnotationCore(StringBuilder stringBuilder, AnnotationObject annotation) {
        Map<String, Object> valueMap = annotation.getValue();
        if (valueMap == null)
            return;
        stringBuilder.append("(");//@javax.persistence.EntityListeners(
        valueMap.forEach((key, value) -> {
            if (StringUtil.isNotEmpty(key)) {
                stringBuilder.append(key).append(" = ");//@javax.persistence.EntityListeners(name =
            }

            String valueString;
            if (value instanceof String) {
                valueString = "\"" + value.toString() + "\"";
            } else {
                valueString = value.getClass().getName() + "." + value;
            }
            stringBuilder.append(valueString);//@javax.persistence.EntityListeners(name = "t_zibo_building"

        });
        stringBuilder.append(")");//@javax.persistence.EntityListeners(name = "t_zibo_building")
    }

    @NotNull
    private StringBuilder genBody(ClassObject classObject) {
        StringBuilder stringBuilder = new StringBuilder();
        //Member
        classObject.getMemberObjectList().forEach(memberObject -> {
            stringBuilder.append(genMenber(memberObject));
        });
        //Getter And Settter
        classObject.getMemberObjectList().forEach(memberObject -> {
            stringBuilder.append(genGetterAndSetter(memberObject));
        });
        //default constructor
        stringBuilder.append(genConstructor(classObject));
        return stringBuilder;
    }

    @NotNull
    private String genMenber(MemberObject memberObject) {
        return String.format(
                "\n\t/**" +
                        "\n\t * %s" +
                        "\n\t */" +
                        "%s" +
                        "\n\tprivate %s %s;" +
                        "\n"
                , memberObject.getComment()
                , genMemberAnnotation(memberObject.getAnnotationList())
                , getType(memberObject)
                , memberObject.getName()
        );
    }

    @NotNull
    private String genGetterAndSetter(MemberObject memberObject) {
        return String.format(
                "\n\tpublic %s get%s() {" +
                        "\n\t\treturn %s;" +
                        "\n\t}\n"
                , getType(memberObject)
                , getMethodName(memberObject)
                , memberObject.getName()
        ) +
                String.format("\n\tpublic void set%s(%s %s) {\n\t\tthis.%s = %s;\n\t}\n"
                        , getMethodName(memberObject)
                        , getType(memberObject)
                        , getParmName(memberObject)
                        , memberObject.getName()
                        , getParmName(memberObject)
                );
    }

    @NotNull
    private String genConstructor(ClassObject classObject) {
        return String.format(
                "\n\tpublic %s() {" +
                        "%s" +
                        "\n\t}\n", classObject.getName(), getDefaultMember(classObject)
        );
    }

    @NotNull
    private String getDefaultMember(ClassObject classObject) {
        StringBuilder sb = new StringBuilder();
        classObject.getMemberObjectList().forEach(memberObject -> {
            sb.append(String.format("\n\t\tthis.%s = %s;", memberObject.getName(), memberObject.getDefaultValue()));
        });
        return sb.toString();
    }

    @NotNull
    private String getType(MemberObject memberObject) {
        //如果 Member 的类型需要引入包名
        String typeName = memberObject.getTypeName();
        if (!typeName.contains(".")) {
            return typeName;
        }
        return typeName.substring(typeName.lastIndexOf(".") + 1);
    }

    /**
     * 统一参数名生成方式
     */
    @NotNull
    private String getParmName(MemberObject memberObject) {
        return Util.toLowCamelCase(memberObject.getName());
    }

    /**
     * 根据 JavaBean 的规范
     * According to the JavaBeans specification section 8.3.2:
     * <p>
     * Boolean properties
     * In addition, for boolean properties, we allow a getter method to match the pattern:
     * <p>
     * public boolean is<PropertyName>();
     * <p>
     * This "isPropertyName" method may be provided instead of a "get<PropertyName>" method, or it may be provided in addition to a "get<PropertyName>" method. In either case, if the is<PropertyName> method is present for a boolean property then we will use the "is<PropertyName>" method to read the property value. An example boolean property might be:
     * <p>
     * public boolean isMarsupial(); public void setMarsupial(boolean m);
     */
    @NotNull
    private String getMethodName(MemberObject memberObject) {
        String methodName = Util.toUpperCamelCase(memberObject.getName());
        String typeName = memberObject.getTypeName();
        // 如果类型是 Boolean，属性名是 is 开头
        if ("Boolean".equals(typeName)
                && methodName.substring(0, 2).equalsIgnoreCase("is")
                && Character.isUpperCase(methodName.charAt(2))) {
            //去掉 is 作为方法名
            methodName = methodName.substring(2);
        }
        return methodName;
    }
}