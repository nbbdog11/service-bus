package com.johnscottwagner.bus;

import com.johnscottwagner.bus.components.Message;
import com.johnscottwagner.bus.components.MessageHandler;
import com.johnscottwagner.bus.components.Request;
import com.johnscottwagner.bus.components.RequestHandler;
import com.johnscottwagner.bus.components.Response;

import java.util.List;

public interface Bus {

    void sendMessage(final Message message);
    Response sendRequest(final Request request);
    void registerMessageHandlers(final List<MessageHandler> messageHandlers);
    void registerRequestHandlers(final List<RequestHandler> requestHandlers);
}
