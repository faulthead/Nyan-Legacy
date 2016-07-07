package org.academiadecodigo.nyanlegacy.server.client;

import org.academiadecodigo.nyanlegacy.server.Server;
import org.academiadecodigo.nyanlegacy.server.ServerLogic;

/**
 * Created by joaoc on 07/07/2016.
 */
public class ClientConnection implements Runnable {

    private Position position;
    private boolean isDead;

    public ClientConnection(Server server, ServerLogic serverLogic) {
    }

    @Override
    public void run() {

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
