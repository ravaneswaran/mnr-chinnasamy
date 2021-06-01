package com.store.core.utils;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    public static boolean isEmpty(String argument){
        return null != argument && !"".equals(argument.trim());
    }

}
