package org.academiadecodigo.nyanlegacy.game;

import java.io.IOException;
import java.net.*;

/**
 * Created by joaoc on 07/07/2016.
 */
public class ClientConnector implements Runnable {

    //Connector class.

    private DatagramSocket clientSocket;
    private InetAddress IPAddress;
    private int port = 8080;
    private byte[] sendData;
    private byte[] receiveData;
    private String sentence = "";
    private String message = "";
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;

    public ClientConnector() {
        try {

            clientSocket = new DatagramSocket();

            IPAddress = InetAddress.getByName("192.168.1.27");

            receiveData = new byte[1024];

            sendData = new byte[1024];

        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {

                        sendData = message.getBytes();

                        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

                        clientSocket.send(sendPacket);


                    } catch (SocketException e) {
                        System.out.println(e.getMessage());
                    } catch (UnknownHostException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        };

        thread.start();
    }

    @Override
    public void run() {

        receivePacket = new DatagramPacket(receiveData, receiveData.length);

        try {
            clientSocket.receive(receivePacket);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        port = receivePacket.getPort();

        sentence = new String(receiveData, 0, receivePacket.getLength());

        System.out.println(sentence);

        while (true) {

            receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                clientSocket.receive(receivePacket);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            sentence = new String(receiveData, 0, receivePacket.getLength());

            System.out.println(sentence);
        }

    }

    public void send(String message) {
        this.message = message;
    }
}