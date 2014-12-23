package com.youyou.uucar.core3.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangyi on 12/23/14.
 */
public class MessageManager {

    public static MessageManager instance;

    private MessageManager() {
    }

    public static MessageManager init() {
        if (instance != null) {
            return instance;
        }
        instance = new MessageManager();
        return instance;
    }

    private class MessageContainer {
        List<CoreMessage> msgs;

        public MessageContainer(List<CoreMessage> msgs) {
            this.msgs = msgs;
        }

        void whisperToMsgs(int ret, Object... others) {
            for (CoreMessage msg : msgs) {
                msg.handleWhisper(new CoreMessage.Whisper(ret, others));
            }
        }
    }

    private Map<String, MessageContainer> mcs = new HashMap<String, MessageContainer>();

    public String listenToMe(List<CoreMessage> msgs) {
        String key = UUID.randomUUID().toString();
        mcs.put(key, new MessageContainer(msgs));
        return key;
    }

    public void leaveFromMe(String key) {
        mcs.remove(key);
    }

    public void whisperToMsgs(int ret, Object... others) {
        for (Map.Entry<String, MessageContainer> entry : mcs.entrySet()) {
            entry.getValue().whisperToMsgs(ret, others);
        }

    }

}
