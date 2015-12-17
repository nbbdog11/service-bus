package com.johnscottwagner.bus;

import com.johnscottwagner.bus.components.Message;
import com.johnscottwagner.bus.components.MessageHandler;
import com.johnscottwagner.bus.components.Request;
import com.johnscottwagner.bus.components.RequestHandler;
import com.johnscottwagner.bus.components.Response;
import com.johnscottwagner.bus.registry.BusRegistry;
import com.johnscottwagner.bus.test.components.TestMessageHandler;
import com.johnscottwagner.bus.test.components.TestRequestHandler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SimpleBusTest {

    @Test
    public void registerMessageHandlers_Delegates_To_BusRegistry_List() {
        final BusRegistry busRegistry = mock(BusRegistry.class);
        final Bus simpleBus = new SimpleBus(busRegistry);

        final List<MessageHandler> testMessageHandlers = buildTestMessageHandlers();
        simpleBus.registerMessageHandlers(testMessageHandlers);

        testMessageHandlers.forEach(m -> verify(busRegistry).registerMessageHandler(m));
    }

    @Test
    public void registerRequestHandlers_Delegates_To_BusRegistry_List() {
        final BusRegistry busRegistry = mock(BusRegistry.class);
        final Bus simpleBus = new SimpleBus(busRegistry);

        final List<RequestHandler> testRequestHandlers = buildTestRequestHandlers();
        simpleBus.registerRequestHandlers(testRequestHandlers);

        testRequestHandlers.forEach(r -> verify(busRegistry).registerRequestHandler(r));
    }

    private List<MessageHandler> buildTestMessageHandlers() {
        final List<MessageHandler> messageHandlers = new ArrayList<>(3);
        messageHandlers.add(new TestMessageHandler<>(Message.class));
        messageHandlers.add(new TestMessageHandler<>(Message.class));
        messageHandlers.add(new TestMessageHandler<>(Message.class));
        return messageHandlers;
    }

    private List<RequestHandler> buildTestRequestHandlers() {
        final List<RequestHandler> requestHandlers = new ArrayList<>(3);
        requestHandlers.add(new TestRequestHandler<>(Request.class,
                                                     Response.class));
        requestHandlers.add(new TestRequestHandler<>(Request.class,
                                                     Response.class));
        requestHandlers.add(new TestRequestHandler<>(Request.class,
                                                     Response.class));
        return requestHandlers;
    }
}
