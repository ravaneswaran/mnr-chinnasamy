package com.store.core.utils;

public class StringUtil {

    public static boolean isEmpty(String argument){
        return null != argument && !"".equals(argument.trim());
    }

}
