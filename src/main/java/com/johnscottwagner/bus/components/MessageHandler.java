package com.johnscottwagner.bus.components;

public interface MessageHandler<T> {

    Class<T> getMessageType();
    void handleMessage(Message message);
}
