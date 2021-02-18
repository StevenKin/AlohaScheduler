package com.github.stevenkin.alohascheduler.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    /**
     * 消息的序列号
     */
    private Long serialNo;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 发件人
     */
    private String sender;

    /**
     * 消息类型
     */
    private MessageType messageType;

    /**
     * 负载的信息, T attachment，T = Map<String, (原始类型 | T)>
     */
    private Map<String, Object> attachment;
}
