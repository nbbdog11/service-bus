package com.johnscottwagner.bus.registry;

import com.johnscottwagner.bus.components.MessageHandler;
import com.johnscottwagner.bus.components.RequestHandler;

import java.util.List;

public interface BusRegistry {

    void registerMessageHandler(final MessageHandler messageHandler);
    List<MessageHandler> getMessageHandlersForType(final Class aClass);
    void registerRequestHandler(final RequestHandler requestHandler);
    List<RequestHandler> getRequestHandlersForRequestAndResponseType(final Class requestClass,
                                                                     final Class responseClass);
}
