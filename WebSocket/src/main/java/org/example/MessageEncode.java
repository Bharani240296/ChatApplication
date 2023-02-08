package org.example;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncode implements Encoder.Text<Messages>{
    private static Gson gson=new Gson();
    @Override
    public String encode(Messages messages) throws EncodeException {
        return gson.toJson(messages);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
