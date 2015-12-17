package com.johnscottwagner.bus.registry;

import com.johnscottwagner.bus.test.components.TestMessageHandler;
import com.johnscottwagner.bus.components.Message;
import com.johnscottwagner.bus.components.MessageHandler;
import static org.assertj.core.api.Assertions.*;

import com.johnscottwagner.bus.components.Request;
import com.johnscottwagner.bus.components.RequestHandler;
import com.johnscottwagner.bus.components.Response;
import com.johnscottwagner.bus.test.components.TestRequestHandler;
import org.junit.Test;

import java.util.List;

public class SimpleBusRegistryTest {

    @Test
    public void RegisterMessageHandler_Registers_Handler_Appropriately() {
        final Class<Message> messageClass = Message.class;
        final TestMessageHandler<Message> testMessageHandler = new TestMessageHandler<>(messageClass);
        final BusRegistry busRegistry = new SimpleBusRegistry();

        busRegistry.registerMessageHandler(testMessageHandler);
        final List<MessageHandler> registeredMessageHandlers = busRegistry.getMessageHandlersForType(messageClass);

        assertThat(registeredMessageHandlers).isNotNull();
        assertThat(registeredMessageHandlers).contains(testMessageHandler);
        assertThat(registeredMessageHandlers).hasSize(1);
    }

    @Test
    public void GetMessageHandlers_Returns_Empty_List_And_Not_Null() {
        final BusRegistry busRegistry = new SimpleBusRegistry();

        final List<MessageHandler> registeredMessageHandlers = busRegistry.getMessageHandlersForType(Message.class);

        assertThat(registeredMessageHandlers).isNotNull();
        assertThat(registeredMessageHandlers).hasSize(0);
    }

    @Test
    public void RegisterRequestHandler_Registers_Handler_Appropriately() throws Exception {
        final Class<Request> requestClass = Request.class;
        final Class<Response> responseClass = Response.class;
        final TestRequestHandler<Request, Response> testRequestHandler = new TestRequestHandler<>(requestClass,
                                                                                                  responseClass);
        final BusRegistry busRegistry = new SimpleBusRegistry();

        busRegistry.registerRequestHandler(testRequestHandler);
        final List<RequestHandler> registeredRequestHandlers = busRegistry.getRequestHandlersForRequestAndResponseType(requestClass,
                                                                                                                       responseClass);

        assertThat(registeredRequestHandlers).isNotNull();
        assertThat(registeredRequestHandlers).contains(testRequestHandler);
        assertThat(registeredRequestHandlers).hasSize(1);
    }

    @Test
    public void GetRequestHandlers_Returns_Empty_List_And_Not_Null() {
        final BusRegistry busRegistry = new SimpleBusRegistry();

        final List<RequestHandler> registeredRequestHandlers =
                busRegistry.getRequestHandlersForRequestAndResponseType(Request.class,
                                                                        Response.class);

        assertThat(registeredRequestHandlers).isNotNull();
        assertThat(registeredRequestHandlers).hasSize(0);
    }

}
