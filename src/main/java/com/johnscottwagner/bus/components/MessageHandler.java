package com.johnscottwagner.bus.components;

public interface MessageHandler<T> {

    Class<T> getMessageType();
}
