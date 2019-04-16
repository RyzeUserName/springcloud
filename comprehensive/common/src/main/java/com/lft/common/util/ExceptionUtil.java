package com.lft.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * 描述
 * @author Ryze
 * @date 2019/4/16 21:38
 */
public class ExceptionUtil {
    /**
     * 异常枚举转类型换为英文code
     * @param error 异常枚举
     * @return 大驼峰编码
     */
    public static String errorToCodeEN(Enum<?> error) {
        String errorName = error.name().toLowerCase();
        String[] sp = errorName.split("_");
        StringBuffer code = new StringBuffer();
        for (String s : sp) {
            code.append(StringUtils.capitalize(s));
        }
        return code.toString();
    }
}
