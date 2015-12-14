package com.johnscottwagner.bus;

import com.johnscottwagner.bus.components.MessageHandler;
import com.johnscottwagner.bus.components.RequestHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BusRegistry {

    private final Map<Class, List<MessageHandler>> MESSAGE_HANDLER_MAP;
    private final Map<RequestAndResponseClasses, List<RequestHandler>> REQUEST_HANDLER_MAP;

    public BusRegistry() {
        MESSAGE_HANDLER_MAP = new ConcurrentHashMap<Class, List<MessageHandler>>();
        REQUEST_HANDLER_MAP = new ConcurrentHashMap<RequestAndResponseClasses, List<RequestHandler>>();
    }

    public void registerMessageHandler(final MessageHandler messageHandler) {
        final Class messageType = messageHandler.getMessageType();
        initializeMessageHandlerMapForType(messageType);
        addMessageHandlerToMap(messageHandler);
    }

    public List<MessageHandler> getMessageHandlersForType(final Class aClass) {
        return MESSAGE_HANDLER_MAP.getOrDefault(aClass,
                                                new ArrayList<MessageHandler>(0));
    }

    private List<MessageHandler> initializeMessageHandlerMapForType(final Class messageType) {
        return MESSAGE_HANDLER_MAP.putIfAbsent(messageType, new ArrayList<MessageHandler>());
    }

    private void addMessageHandlerToMap(final MessageHandler messageHandler) {
        MESSAGE_HANDLER_MAP.get(messageHandler.getMessageType()).add(messageHandler);
    }

    public void registerRequestHandler(final RequestHandler requestHandler) {
        final Class requestType = requestHandler.getRequestType();
        final Class responseType = requestHandler.getResponseType();
        final RequestAndResponseClasses requestAndResponseClasses = new RequestAndResponseClasses(requestType, responseType);
        initializeRequestHandlerMapForType(requestAndResponseClasses);
        addRequestHandlerToMap(requestAndResponseClasses,
                               requestHandler);
    }

    private void addRequestHandlerToMap(final RequestAndResponseClasses requestAndResponseClasses,
                                        final RequestHandler requestHandler) {
        REQUEST_HANDLER_MAP.get(requestAndResponseClasses).add(requestHandler);
    }

    public List<RequestHandler> getRequestHandlersForRequestAndResponseType(final Class requestClass,
                                                                            final Class responseClass) {
        return REQUEST_HANDLER_MAP.getOrDefault(new RequestAndResponseClasses(requestClass,
                                                                              responseClass),
                                                new ArrayList<RequestHandler>(0));
    }

    private void initializeRequestHandlerMapForType(final RequestAndResponseClasses requestAndResponseClasses) {
        REQUEST_HANDLER_MAP.putIfAbsent(requestAndResponseClasses, new ArrayList<RequestHandler>());
    }

    private static class RequestAndResponseClasses {

        private final Class requestClass;
        private final Class responseClass;

        RequestAndResponseClasses(Class requestClass,
                                  Class responseClass) {
            this.requestClass = requestClass;
            this.responseClass = responseClass;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final RequestAndResponseClasses that = (RequestAndResponseClasses) o;

            if (!requestClass.equals(that.requestClass)) {
                return false;
            }
            return responseClass.equals(that.responseClass);
        }

        @Override
        public int hashCode() {
            int result = requestClass.hashCode();
            result = 31 * result + responseClass.hashCode();
            return result;
        }
    }
}
