package org.academiadecodigo.nyanlegacy.server;

/**
 * Created by joaoc on 07/07/2016.
 */
public final class ServerLogic {

    /*
    ServerLogic singleton definition.
    */
    private static ServerLogic serverLogic = null;

    private ServerLogic() {
    }

    public synchronized static ServerLogic getInstance() {

        if (serverLogic == null) {
            serverLogic = new ServerLogic();
        }

        return serverLogic;
    }

    /*
    properties and methods
     */
    private boolean[][] matrix;

    static {

        //create matrix

    }

    private boolean collision() {

        return false;
    }

    private void libgdxConverter() {

    }

    private void setPositionState() {

    }
}
