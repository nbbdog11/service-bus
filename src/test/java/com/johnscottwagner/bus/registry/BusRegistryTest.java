package com.johnscottwagner.bus.registry;

import com.johnscottwagner.bus.BusRegistry;
import com.johnscottwagner.bus.components.Message;
import com.johnscottwagner.bus.components.MessageHandler;
import static org.assertj.core.api.Assertions.*;

import com.johnscottwagner.bus.components.Request;
import com.johnscottwagner.bus.components.RequestHandler;
import com.johnscottwagner.bus.components.Response;
import org.junit.Test;

import java.util.List;

public class BusRegistryTest {

    @Test
    public void RegisterMessageHandler_Registers_Handler_Appropriately() {
        final Class<Message> messageClass = Message.class;
        final TestMessageHandler<Message> testMessageHandler = new TestMessageHandler<Message>(messageClass);
        final BusRegistry busRegistry = new BusRegistry();

        busRegistry.registerMessageHandler(testMessageHandler);
        final List<MessageHandler> registeredMessageHandlers = busRegistry.getMessageHandlersForType(messageClass);

        assertThat(registeredMessageHandlers).isNotNull();
        assertThat(registeredMessageHandlers).contains(testMessageHandler);
        assertThat(registeredMessageHandlers).hasSize(1);
    }

    @Test
    public void GetMessageHandlers_Returns_Empty_List_And_Not_Null() {
        final BusRegistry busRegistry = new BusRegistry();

        final List<MessageHandler> registeredMessageHandlers = busRegistry.getMessageHandlersForType(Message.class);

        assertThat(registeredMessageHandlers).isNotNull();
        assertThat(registeredMessageHandlers).hasSize(0);
    }

    @Test
    public void RegisterRequestHandler_Registers_Handler_Appropriately() throws Exception {
        final Class<Request> requestClass = Request.class;
        final Class<Response> responseClass = Response.class;
        final TestRequestHandler<Request, Response> testRequestHandler = new TestRequestHandler<Request, Response>(requestClass, responseClass);
        final BusRegistry busRegistry = new BusRegistry();

        busRegistry.registerRequestHandler(testRequestHandler);
        final List<RequestHandler> registeredRequestHandlers = busRegistry.getRequestHandlersForRequestAndResponseType(requestClass,
                                                                                                                       responseClass);

        assertThat(registeredRequestHandlers).isNotNull();
        assertThat(registeredRequestHandlers).contains(testRequestHandler);
        assertThat(registeredRequestHandlers).hasSize(1);
    }

    @Test
    public void GetRequestHandlers_Returns_Empty_List_And_Not_Null() {
        final BusRegistry busRegistry = new BusRegistry();

        final List<RequestHandler> registeredRequestHandlers =
                busRegistry.getRequestHandlersForRequestAndResponseType(Request.class,
                                                                        Response.class);

        assertThat(registeredRequestHandlers).isNotNull();
        assertThat(registeredRequestHandlers).hasSize(0);
    }

    private static class TestMessageHandler<T> implements MessageHandler<T> {

        private final Class<T> messageClass;

        public TestMessageHandler(Class<T> messageClass) {
            this.messageClass = messageClass;
        }

        public Class<T> getMessageType() {
            return messageClass;
        }
    }

    private static class TestRequestHandler<I extends Request,O extends Response> implements RequestHandler<I,O> {

        private final Class<I> requestType;
        private final Class<O> responseType;

        public TestRequestHandler(final Class<I> requestType,
                                  final Class<O> responseType) {
            this.requestType = requestType;
            this.responseType = responseType;
        }

        public Class<I> getRequestType() {
            return requestType;
        }

        public Class<O> getResponseType() {
            return responseType;
        }
    }
}
