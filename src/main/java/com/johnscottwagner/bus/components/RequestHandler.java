package com.johnscottwagner.bus.components;

public interface RequestHandler<I extends Request, O extends Response> {

    Class<I> getRequestType();
    Class<O> getResponseType();
}
