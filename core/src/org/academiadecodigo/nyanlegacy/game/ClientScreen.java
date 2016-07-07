package org.academiadecodigo.nyanlegacy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.net.DatagramSocket;
import java.net.SocketException;

import static com.badlogic.gdx.Input.Keys.*;

public class ClientScreen extends ApplicationAdapter implements InputProcessor{

    private SpriteBatch batch;
    private Texture img;

    private DatagramSocket playerSocket;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    @Override
    public void create() {

        try {
            playerSocket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }

        batch = new SpriteBatch();
        img = new Texture("core/assets/background.png");

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {

        /**
         * Purposely empty to add socket implementation in a new branch.
         */

        if (movingLeft) {

        }
        if (movingRight) {

        }
        if (movingUp) {

        }
        if (movingDown) {

        }

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    /** {@link InputProcessor} implementation.
     *
     * We tried very, very hard to have this in a separate class but in the way Libgdx works it just isn't feasible
     * in such a short time. I hope to one day be able to separate this logic from the {@link ClientScreen} class.
     * Currently set up to work with flags to allow continuous movement while holding down the key.
     */

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == LEFT) {
            movingLeft = true;
        }
        if (keycode == RIGHT) {
            movingRight = true;
        }
        if (keycode == UP) {
            movingUp = true;
        }
        if (keycode == DOWN) {
            movingDown = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == LEFT) {
            movingLeft = false;
        }
        if (keycode == RIGHT) {
            movingRight = false;
        }
        if (keycode == UP) {
            movingUp = false;
        }
        if (keycode == DOWN) {
            movingDown = false;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
