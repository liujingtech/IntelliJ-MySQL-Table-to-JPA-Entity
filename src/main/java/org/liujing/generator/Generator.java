package org.liujing.generator;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.liujing.generator.object.java.AnnotationObject;
import org.liujing.generator.object.java.ClassObject;
import org.liujing.generator.object.java.MemberObject;

import java.util.*;

/**
 * 生成器
 * <p>
 * 根据 Annotation Object 和 Class Object 和 Member Object 类型的值，生成一个 POJO 对象
 */
public class Generator {

    /**
     * 格式化 POJO 字符串
     */
    public String formatPOJOString(ClassObject classObject) {

        return String.format(
                "%s\n"
                        + "\n/**"
                        + "\n * %s"
                        + "\n */"
                        + "%s\n"
                        + "public class %s {\n%s}"
                , formatImportPackage(classObject)
                , classObject.getComment()
                , formatClassAnnotation(classObject.getAnnotationList())
                , classObject.getName()
                , formatBody(classObject)
        );
    }

    /**
     * 获取 导入包 模块的字符串
     */
    @NotNull
    private String formatImportPackage(ClassObject classObject) {
        //通过 Set 来排除相同元素
        Set<String> importSet = new HashSet<>();

        // 设置 class 的 Annotation
        setImportSetByAnnotationList(importSet, classObject.getAnnotationList());
        //Member 的包名
        setImportSetByMemberType(importSet, classObject);

        setImportSetByMemberAnnotationList(importSet, classObject.getMemberObjectList());

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
        return sb.toString();
    }

    /**
     * 生成类的注解
     * 和 formatMemberAnnotation 的唯一区别在于为了格式化没有缩进
     */
    @NotNull
    private StringBuilder formatClassAnnotation(List<AnnotationObject> annotationObjectList) {
        StringBuilder stringBuilder = new StringBuilder();
        annotationObjectList.forEach(annotation -> {
            stringBuilder.append("\n@").append(annotation.getName());//@javax.persistence.EntityListeners
            formatAnnotationCore(stringBuilder, annotation);
        });
        return stringBuilder;
    }

    /**
     * 生成成员变量的注解
     * 和 formatClassAnnotation 的唯一区别在于为了格式化有个缩进
     */
    @NotNull
    private StringBuilder formatMemberAnnotation(List<AnnotationObject> annotationObjectList) {
        StringBuilder stringBuilder = new StringBuilder();
        annotationObjectList.forEach(annotation -> {
            stringBuilder.append("\n\t@").append(annotation.getName());//@javax.persistence.EntityListeners
            formatAnnotationCore(stringBuilder, annotation);
        });
        return stringBuilder;
    }

    /**
     * 生成
     */
    private void formatAnnotationCore(StringBuilder stringBuilder, AnnotationObject annotation) {
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
    private StringBuilder formatBody(ClassObject classObject) {
        StringBuilder stringBuilder = new StringBuilder();
        //Member
        classObject.getMemberObjectList().forEach(memberObject -> {
            stringBuilder.append(formatMenber(memberObject));
        });
        //Getter And Settter
        classObject.getMemberObjectList().forEach(memberObject -> {
            stringBuilder.append(formatGetterAndSetter(memberObject));
        });
        //default constructor
        stringBuilder.append(formatConstructor(classObject));
        return stringBuilder;
    }

    @NotNull
    private String formatMenber(MemberObject memberObject) {
        return String.format(
                "\n\t/**" +
                        "\n\t * %s" +
                        "\n\t */" +
                        "%s" +
                        "\n\tprivate %s %s;" +
                        "\n"
                , memberObject.getComment()
                , formatMemberAnnotation(memberObject.getAnnotationList())
                , getMemberType(memberObject)
                , memberObject.getName()
        );
    }

    @NotNull
    private String formatGetterAndSetter(MemberObject memberObject) {
        return String.format(
                "\n\tpublic %s get%s() {" +
                        "\n\t\treturn %s;" +
                        "\n\t}\n"
                , getMemberType(memberObject)
                , getMethodName(memberObject)
                , memberObject.getName()
        ) +
                String.format("\n\tpublic void set%s(%s %s) {\n\t\tthis.%s = %s;\n\t}\n"
                        , getMethodName(memberObject)
                        , getMemberType(memberObject)
                        , getMethodParmName(memberObject)
                        , memberObject.getName()
                        , getMethodParmName(memberObject)
                );
    }

    @NotNull
    private String formatConstructor(ClassObject classObject) {
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
    private String getMemberType(MemberObject memberObject) {
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
    private String getMethodParmName(MemberObject memberObject) {
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

    /**
     * 设置 ImportSet
     * 从成员变量的注解的全名，获取包名，准备设置到导入包中
     */
    private void setImportSetByMemberAnnotationList(Set<String> importSet, List<MemberObject> memberList) {
        if (memberList == null) {
            return;
        }
        memberList.forEach(memberObject -> {
            //如果 Member 声明的 Annotation 需要引入包名
            List<AnnotationObject> menberAnnotationList = memberObject.getAnnotationList();
            // 设置 member 的 Annotation
            setImportSetByAnnotationList(importSet, menberAnnotationList);
        });
    }

    /**
     * 设置 ImportSet
     * 从成员变量的类型，获取包名，准备设置到导入包中
     */
    private void setImportSetByMemberType(Set<String> importSet, ClassObject classObject) {
        if (classObject == null) {
            return;
        }
        classObject.getMemberObjectList().forEach(memberObject -> {
            //如果 Member 的类型需要引入包名
            String typeName = memberObject.getTypeName();
            if (!typeName.contains(".")) {
                return;
            }
            importSet.add(typeName);
        });
    }

    /**
     * 设置 ImportSet
     * 从成员变量的注解的全名，获取包名，准备设置到导入包中
     */
    private void setImportSetByAnnotationList(Set<String> importSet, List<AnnotationObject> anotationList) {
        if (anotationList == null) {
            return;
        }
        anotationList.forEach(annotationObject -> {
            String fullName = annotationObject.getFullName();
            if (!fullName.contains(".")) {
                return;
            }
            importSet.add(fullName);
        });
    }

}