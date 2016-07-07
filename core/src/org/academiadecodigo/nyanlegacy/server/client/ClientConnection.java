package org.academiadecodigo.nyanlegacy.server.client;

import org.academiadecodigo.nyanlegacy.server.Server;
import org.academiadecodigo.nyanlegacy.server.ServerLogic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by joaoc on 07/07/2016.
 */
public class ClientConnection implements Runnable {

    private Server server;
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    private byte[] sendData;
    private byte[] receiveData;

    private Position position;
    private Position pixelPosition;
    private boolean isDead;

    public ClientConnection(Server server, DatagramSocket socket, InetAddress address, int port) {
        this.server = server;
        this.socket = socket;
        this.address = address;
        this.port = port;
        sendData = new byte[1024];
        receiveData = new byte[1024];
    }

    @Override
    public void run() {

        while (true) {
            try {
                if (server.isGameStarted()) {

                    if(!isDead) {

                        DatagramPacket receiveMessage = new DatagramPacket(receiveData, receiveData.length);
                        socket.receive(receiveMessage);

                        String message = new String(receiveData, 0, receiveMessage.getLength());

                        move(message);

                        server.sendToAll(toString());
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Movement logic
     *
     * @param move the respective movement to make.
     */
    private void move(String move){
        if(move.equals("up") || position.getRow() > 0){
            isDead = ServerLogic.getInstance().collision(position.getCol(),position.getRow());
        }
        if(move.equals("down") || position.getRow() < ServerLogic.getInstance().CELLSIZE - 1){
            isDead = ServerLogic.getInstance().collision(position.getCol(),position.getRow());
        }
        if(move.equals("left") || position.getCol() > 0){
            isDead = ServerLogic.getInstance().collision(position.getCol(),position.getRow());
        }
        if(move.equals("right") || position.getCol() < ServerLogic.getInstance().CELLSIZE - 1){
            isDead = ServerLogic.getInstance().collision(position.getCol(),position.getRow());
        }
    }

    /**
     * generates a String with the respective attributes of the client
     * @return a String
     */
    public String toString() {
        return "" + address + ":" + pixelPosition.getCol() + ":" + pixelPosition.getRow() + ":" + isDead + "";
    }

    /**
     * Send a mensage to the client connected
     *
     * @param message to send
     */
    public void send(String message) {
        sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GETTERS AND SETTERS

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Position getPixelPosition() {
        return pixelPosition;
    }

    public void setPixelPosition(Position pixelPosition) {
        this.pixelPosition = pixelPosition;
    }
}
