package org.academiadecodigo.nyanlegacy.server;

/**
 * Created by codecadet on 07/07/16.
 */
public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(8080);
        server.startServer();
    }
}
