package com.johnscottwagner.bus;

import com.johnscottwagner.bus.components.Message;
import com.johnscottwagner.bus.components.MessageHandler;
import com.johnscottwagner.bus.components.Request;
import com.johnscottwagner.bus.components.RequestHandler;
import com.johnscottwagner.bus.components.Response;
import com.johnscottwagner.bus.registry.BusRegistry;

import java.util.List;

public class SimpleBus implements Bus {

    private final BusRegistry busRegistry;

    public SimpleBus(final BusRegistry busRegistry) {
        this.busRegistry = busRegistry;
    }

    public void sendMessage(final Message message) {
        busRegistry.getMessageHandlersForType(message.getClass())
                   .forEach(h -> h.handleMessage(message));
    }

    public Response sendRequest(final Request request) {
        return null;
    }

    public void registerMessageHandlers(final List<MessageHandler> messageHandlers) {
        messageHandlers.forEach(this::registerMessageHandler);
    }

    private void registerMessageHandler(final MessageHandler messageHandler) {
        busRegistry.registerMessageHandler(messageHandler);
    }

    public void registerRequestHandlers(final List<RequestHandler> requestHandlers) {
        requestHandlers.forEach(this::registerRequestHandler);
    }

    private void registerRequestHandler(final RequestHandler requestHandler) {
        busRegistry.registerRequestHandler(requestHandler);
    }
}
