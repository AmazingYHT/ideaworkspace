package com.cnaidun.user.api.policeUser.common.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 判空
 *
 * @author 15091187
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class EmptyUtil {
    /**
     * 〈验证字符串是否为空〉
     * 
     * @param str str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 
     * 〈数组内容是否为空〉
     * 
     * @param str 数组
     * @return boolean 为null返回true;否则返回false
     */
    public static boolean isEmptyContent(String str[]) {
        boolean retn = true;
        if (null == str) {
            return retn;
        }
        if (str.length <= 0) {
            return retn;
        } else {
            for (String tmp : str) {
                if (!isEmpty(tmp)) {
                    retn = false;
                    break;
                }
            }
        }
        return retn;
    }

    /**
     * 〈〈验证字符串是否为空〉
     * 
     * @param list list
     * @return boolean
     */
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 〈〈验证字符串是否为空〉
     * 
     * @param list list
     * @return boolean
     */
    public static boolean isEmpty(Set<?> set) {
        return set == null || set.isEmpty();
    }

    /**
     * 〈验证字符串是否为空〉
     * 
     * @param map map
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 
     * 〈对象是否为空〉
     * 
     * @param obj 对象
     * @return boolean 为null返回true;否则返回false
     */
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     * 
     * 〈数组对象是否为空〉
     * 
     * @param obj 数组对象
     * @return boolean 为null返回true;否则返回false
     */
    public static boolean isEmpty(Object obj[]) {
        if (null == obj) {
            return true;
        }
        return obj.length <= 0;
    }
}
