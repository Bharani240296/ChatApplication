package org.example;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecode.class,
        encoders = MessageEncode.class)
public class ChatApplications {
    private Session session;
    private static Set<ChatApplications> chatApp = new CopyOnWriteArraySet<>();
    private static Map<String, String> chatmessage = new HashMap<>();

    @OnOpen
    public void OnOpen(Session session, @PathParam("username") String username) {
        this.session = session;
        chatApp.add(this);
        chatmessage.put(session.getId(), username);

        Messages message = new Messages();
        message.setFrom(username);
        message.setContext("Connected");
        broadcast(message);
    }

    @OnClose
    public void OnClose(Session session) {
        chatApp.remove(this);

        Messages messages = new Messages();
        messages.setFrom(chatmessage.get(session.getId()));
        messages.setContext("DisConnected");
        broadcast(messages);
    }

    @OnMessage
    public void OnMessage(Session session, Messages messages) {
        messages.setFrom(chatmessage.get(session.getId()));
        broadcast(messages);
    }


    private void broadcast(Messages message) {
        for (ChatApplications chatApplications : chatApp) {
            synchronized (chatApplications) {
                try {
                    chatApplications.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
