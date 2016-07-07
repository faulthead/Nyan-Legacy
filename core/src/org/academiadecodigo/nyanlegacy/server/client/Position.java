package org.academiadecodigo.nyanlegacy.server.client;

/**
 * Created by joaoc on 07/07/2016.
 */
public class Position {

    private int col;
    private int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
