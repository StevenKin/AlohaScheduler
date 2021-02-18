package com.github.stevenkin.alohascheduler.common;

import org.apache.commons.lang3.StringUtils;

import static com.github.stevenkin.alohascheduler.common.Constant.*;

public class Utils {
    public static String buildKey(String domainPrefix, String... args) {
        String key = ROOT + ":" + domainPrefix;
        if (args == null)
            return key;
        for (String arg : args) {
            if (StringUtils.isNotEmpty(arg)) {
                key = key + ":" + arg;
            }
        }
        return key;
    }
}
