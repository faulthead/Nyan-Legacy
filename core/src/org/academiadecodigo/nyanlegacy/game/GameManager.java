package org.academiadecodigo.nyanlegacy.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Cadavre Exquis on 07-07-2016.
 */
public class GameManager extends Game implements InputProcessor{

    //Game norms and constants that are used by various classes

    public static final int WIDTH = 1050;
    public static final int HEIGHT = WIDTH;
    public static final float PPM = 100;

    //bits equivalent to layers in tiled
    public static final short GRID_BIT = 1;
    public static final short NYAN_BIT = 2;
    public static final short PINK_NYAN_BIT = 4;
    public static final short CLOUD_BIT = 8;


    //Input processor stuff.
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    public SpriteBatch spriteBatch;

    private AssetManager manager;  //<----- use to add soundtrack

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        manager = new AssetManager();
        //manager.load("path", Music.class);
        //manager.finishLoading();

        Gdx.input.setInputProcessor(this);

        setScreen(new ClientScreen(this, manager));
    }

    @Override
    public void render() {
        super.render();
        if (movingLeft == true) {
            System.out.println("left");
        }
        if (movingRight == true) {
            System.out.println("right");
        }
        if (movingDown == true) {
            System.out.println("down");
        }
        if (movingUp == true) {
            System.out.println("up");
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        spriteBatch.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.LEFT) {
            movingLeft = true;
        }
        if (keycode == Input.Keys.RIGHT) {
            movingRight = true;
        }
        if (keycode == Input.Keys.UP) {
            movingUp = true;
        }
        if (keycode == Input.Keys.DOWN) {
            movingDown = true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.LEFT) {
            movingLeft = false;
        }
        if (keycode == Input.Keys.RIGHT) {
            movingRight = false;
        }
        if (keycode == Input.Keys.UP) {
            movingUp = false;
        }
        if (keycode == Input.Keys.DOWN) {
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
