package com.github.stevenkin.alohascheduler.common;

public interface MessageFactory {
    /**
     * 处理message
     * @param message
     */
    void processMessage(Message message);

    /**
     * 构建message
     * @param args
     * @return
     */
    Message buildMessage(Object... args);
}
