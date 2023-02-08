package org.example;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecode implements Decoder.Text<Messages> {
    private static Gson gson = new Gson();

    @Override
    public Messages decode(String s) throws DecodeException {
        return gson.fromJson(s, Messages.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
