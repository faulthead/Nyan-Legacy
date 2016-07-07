package org.academiadecodigo.nyanlegacy.game;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by joaoc on 07/07/2016.
 */
public final class PlayerLogic implements InputProcessor {


    //Singleton stuff

    private static PlayerLogic playerLogic;

    private PlayerLogic(){
    }

    public synchronized static PlayerLogic getInstance(){

        if (playerLogic == null) {
            playerLogic = new PlayerLogic();
        }

        return playerLogic;
    }

    //Input Processor stuff

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
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
