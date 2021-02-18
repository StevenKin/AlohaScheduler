package com.github.stevenkin.alohascheduler.common;

public interface MessageFactoryContext {

    /**
     * 通过MessageType获取MessageFactory
     * @param messageType
     * @return
     */
    MessageFactory getMessageFactory(MessageType messageType);
}
