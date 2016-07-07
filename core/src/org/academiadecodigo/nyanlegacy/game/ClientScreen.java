package org.academiadecodigo.nyanlegacy.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.nyanlegacy.game.tools.B2WorldCreator;

import static com.badlogic.gdx.Input.Keys.*;

public class ClientScreen extends ApplicationAdapter implements Screen, InputProcessor {

    private GameManager game;
    private AssetManager manager;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private B2WorldCreator creator;

    //tiled stuff
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    //Input processor stuff.
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingDown = false;
    private boolean movingUp = false;

    public ClientScreen() {
    }

    public ClientScreen(GameManager game, AssetManager manager) {
        this.game = game;
        this.manager = manager;
        init();

    }

    private void init() {

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(GameManager.WIDTH, GameManager.HEIGHT, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tilemap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);

        creator = new B2WorldCreator(this);

        Gdx.input.setInputProcessor(this);
    }


    private void handleInput(float dt) {

        //merge?????
    }

    public void update(float dt) {

        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        renderer.setView(gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        /*
        map.dispose();
        renderer.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
        */
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == LEFT) {
            movingLeft = true;
        }
        if (keycode == RIGHT) {
            movingLeft = true;
        }
        if (keycode == UP) {
            movingLeft = true;
        }
        if (keycode == DOWN) {
            movingLeft = true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == LEFT) {
            movingLeft = false;
        }
        if (keycode == RIGHT) {
            movingLeft = false;
        }
        if (keycode == UP) {
            movingLeft = false;
        }
        if (keycode == DOWN) {
            movingLeft = false;
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
