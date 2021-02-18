package com.github.stevenkin.alohascheduler.common;

import lombok.Getter;

public enum MessageType {
    SCHEDULE(0, "start schedule"),
    STOP_SCHEDULE(1, "stop schedule")
    ;
    @Getter
    private Integer code;
    @Getter
    private String msg;

    MessageType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static MessageType from(Integer code) {
        if (code == null) {
            throw new NullPointerException();
        }
        for (MessageType type : values()) {
            if (code.equals(type.code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("no such message type code");
    }
}
