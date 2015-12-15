package com.johnscottwagner.bus.test.components;

import com.johnscottwagner.bus.components.Request;
import com.johnscottwagner.bus.components.RequestHandler;
import com.johnscottwagner.bus.components.Response;

public class TestRequestHandler<I extends Request,O extends Response> implements RequestHandler<I,O> {

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
