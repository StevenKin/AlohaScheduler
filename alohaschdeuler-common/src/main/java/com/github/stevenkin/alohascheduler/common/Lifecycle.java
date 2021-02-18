package com.github.stevenkin.alohascheduler.common;

import java.util.Map;

public interface Lifecycle {
    void init(Map<String, Object> params);

    void destroy();
}
