package org.academiadecodigo.nyanlegacy.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Cadavre Exquis on 07-07-2016.
 */
public class GameManager extends Game {

    //Game norms and constants that are used by various classes

    public static final int WIDTH = 1050;
    public static final int HEIGHT = WIDTH;
    //bits equivalent to layers in tiled
    public static final short GRID_BIT = 1;
    public static final short NYAN_BIT = 2;
    public static final short PINK_NYAN_BIT = 4;
    public static final short CLOUD_BIT = 8;

    public SpriteBatch spriteBatch;

    private AssetManager manager;  //<----- use to add soundtrack

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        manager = new AssetManager();
        //manager.load("path", Music.class);
        //manager.finishLoading();

        setScreen(new ClientScreen(this, manager));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        spriteBatch.dispose();
    }
}
