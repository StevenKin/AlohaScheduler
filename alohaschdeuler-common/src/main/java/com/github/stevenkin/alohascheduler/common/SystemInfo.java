package com.github.stevenkin.alohascheduler.common;

public class SystemInfo {
    public static final String ip = NetUtils.getLocalHost();

    public static final Integer pid = SystemUtils.getProcessID();

    public static final Long timestamp = System.currentTimeMillis();

    public static final String id = ip + "-" + pid + "-" + timestamp;
}
