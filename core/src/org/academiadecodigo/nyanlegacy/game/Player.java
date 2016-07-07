package org.academiadecodigo.nyanlegacy.game;

/**
 * Created by joaoc on 07/07/2016.
 */
public class Player implements Runnable {

    private PlayerLogic playerLogic;

    public Player() {

        playerLogic = PlayerLogic.getInstance();
    }

    @Override
    public void run() {

    }
}
