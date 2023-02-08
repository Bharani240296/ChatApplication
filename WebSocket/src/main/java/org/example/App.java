package org.example;


import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;

public class App {
    public static void main(String[] args) {
        Server server;
        server = new Server("localhost", 8080, "/folder", ChatApplications.class);
        try {
            server.start();
            System.out.println("server run");
            while (true) {
            }
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}
