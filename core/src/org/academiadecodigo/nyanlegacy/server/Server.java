package org.academiadecodigo.nyanlegacy.server;

import org.academiadecodigo.nyanlegacy.server.client.ClientConnection;
import org.academiadecodigo.nyanlegacy.server.client.Position;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by joaoc on 07/07/2016.
 */
public class Server {

    private DatagramSocket serverSocket;
    private byte[] receiveData;
    private int port;

    private List<ClientConnection> clients = Collections.synchronizedList(new ArrayList<ClientConnection>());
    private ExecutorService pool = Executors.newFixedThreadPool(2);

    private boolean gameStarted;

    /**
     * Server constructor
     *
     * @param port to be listening
     */
    public Server(int port) {
        this.port = port;
        System.out.println("### running ###");
    }

    /**
     * Start the server socket with the respective port
     * then wait for clientConnections
     * then set the positions of the clients
     * then start the games
     */
    public void startServer() {
        try {
            serverSocket = new DatagramSocket(port);
            System.out.println("SERVER SOCKET:" + serverSocket.getLocalPort());

            while (true) {

                System.out.println("### waiting ###");
                waitForConnections();

                System.out.println("### setting positions ###");
                setPositions();

                System.out.println("### start game ###");
                startGame();

            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will wait until the client list has two players
     * It will create a clientConnection thread then add it to the client list
     * and poll then send to that client a message
     */
    private void waitForConnections() {


        try {

            while (clients.size() < 2) {
                receiveData = new byte[1024];
                DatagramPacket receive = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receive);

                if (!clientExists(receive)) {

                    System.out.println("### User: " + receive.getAddress() + " has enter the room ###");
                    System.out.println("RECEIVED: " + receive.getPort() + " " + receive.getAddress());
                    ClientConnection client = new ClientConnection(this, receive.getAddress(), receive.getPort());

                    clients.add(client);
                    pool.submit(client);

                    client.send("### You Connected ###");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean clientExists(DatagramPacket receive) {
        for (ClientConnection client:clients) {
            if(client.getAddress().equals(receive.getAddress())){
                return true;
            }
        }
        return false;
    }

    /**
     * set the initial position for player one and two
     */
    private void setPositions() {
        clients.get(0).setPosition(new Position(0, 0));

        clients.get(1).setPosition(new Position(20, 20));
    }

    /**
     * Start the game by send it to all the players
     */
    private synchronized void startGame() {
        sendToAll("start");
        sendToAll(clients.get(0).toJSON());
        sendToAll(clients.get(1).toJSON());

        gameStarted = true;

        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * send a message to all clients
     *
     * @param message to send
     */
    public void sendToAll(String message) {
        for (ClientConnection client : clients) {
            client.send(message);
        }
    }

    /**
     * Getter for gameStarted
     *
     * @return gameStarted
     */
    public synchronized boolean isGameStarted() {
        return gameStarted;
    }
}
