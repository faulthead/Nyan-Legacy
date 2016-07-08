package org.academiadecodigo.nyanlegacy.server.client;

import org.academiadecodigo.nyanlegacy.server.Server;
import org.academiadecodigo.nyanlegacy.server.ServerLogic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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

    public ClientConnection(Server server, InetAddress address, int port) throws SocketException {

        this.server = server;
        this.address = address;
        this.port = port;
        sendData = new byte[1024];
        receiveData = new byte[1024];

        socket = new DatagramSocket();
        System.out.println("NEW CLIENT CONNECTION PORT: " + socket.getLocalPort());
    }

    @Override
    public void run() {

        while (true) {
            try {
                if (server.isGameStarted()) {

                    if(!isDead) {

                        System.out.println("here");

                        DatagramPacket receiveMessage = new DatagramPacket(receiveData, receiveData.length);
                        socket.receive(receiveMessage);

                        String message = new String(receiveData, 0, receiveMessage.getLength());

                        System.out.println(message);

                        move(message);

                        server.sendToAll(toJSON());
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
     * @param move the respective movement to make
     */
    private void move(String move){
        if(move.equals("up") || position.getRow() > 0){
            ServerLogic.getInstance().setPositionState(position.getCol(),position.getRow());
            position.setRow(position.getRow()-1);
            pixelPosition.setRow(ServerLogic.getInstance().libgdxConverter(position.getRow()));
            isDead = ServerLogic.getInstance().collision(position.getCol(),position.getRow());
        }
        if(move.equals("down") || position.getRow() < ServerLogic.getInstance().TILESIZE - 1){
            ServerLogic.getInstance().setPositionState(position.getCol(),position.getRow());
            position.setRow(position.getRow()+1);
            pixelPosition.setRow(ServerLogic.getInstance().libgdxConverter(position.getRow()));
            isDead = ServerLogic.getInstance().collision(position.getCol(),position.getRow());
        }
        if(move.equals("left") || position.getCol() > 0){
            ServerLogic.getInstance().setPositionState(position.getCol(),position.getRow());
            position.setCol(position.getCol()-1);
            pixelPosition.setCol(ServerLogic.getInstance().libgdxConverter(position.getCol()));
            isDead = ServerLogic.getInstance().collision(position.getCol(),position.getRow());
        }
        if(move.equals("right") || position.getCol() < ServerLogic.getInstance().TILESIZE - 1){
            ServerLogic.getInstance().setPositionState(position.getCol(),position.getRow());
            position.setCol(position.getCol()+1);
            pixelPosition.setCol(ServerLogic.getInstance().libgdxConverter(position.getCol()));
            isDead = ServerLogic.getInstance().collision(position.getCol(),position.getRow());
        }
    }

    /**
     * generates a String with the respective attributes of the client.
     * @return a String
     */
    public String toJSON() {
        return "" + address + ":" + pixelPosition.getCol() + ":" + pixelPosition.getRow() + ":" + isDead + "";
    }

    /**
     * Send a mensage to the client connected
     *
     * @param message to send.
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

    public InetAddress getAddress() {
        return address;
    }
}
