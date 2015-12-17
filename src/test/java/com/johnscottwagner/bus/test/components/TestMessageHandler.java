package com.johnscottwagner.bus.test.components;

import com.johnscottwagner.bus.components.Message;
import com.johnscottwagner.bus.components.MessageHandler;

public class TestMessageHandler<T> implements MessageHandler<T> {

    private final Class<T> messageClass;

    public TestMessageHandler(Class<T> messageClass) {
        this.messageClass = messageClass;
    }

    public Class<T> getMessageType() {
        return messageClass;
    }

    @Override
    public void handleMessage(final Message message) {

    }
}
