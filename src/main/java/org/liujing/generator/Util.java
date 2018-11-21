package org.liujing.generator;

import com.google.common.base.CaseFormat;

public class Util {

    public static String toLowCamelCase(String str) {
        if (str == null) {
            return null;
        }
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }

    public static String toUpperCamelCase(String str) {
        if (str == null) {
            return null;
        }
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str);
    }
    public static String toLowerUnderscore(String str) {
        if (str == null) {
            return null;
        }
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }
}