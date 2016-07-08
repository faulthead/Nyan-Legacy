package org.academiadecodigo.nyanlegacy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.nyanlegacy.game.tools.B2WorldCreator;

public class ClientScreen implements Screen {

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

    private int[] background = new int[]{2};
    private int[] starsBackground = new int[]{3};
    private int[] starsForeground = new int[]{10};



    public ClientScreen(GameManager game, AssetManager manager) {
        this.game = game;
        this.manager = manager;
        init();

    }

    private void init() {

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(GameManager.WIDTH / GameManager.PPM, GameManager.HEIGHT / GameManager.PPM, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tilemap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GameManager.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);

        creator = new B2WorldCreator(this);


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
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(gameCam);

        renderer.render(background);
        renderer.render(starsForeground);

        renderer.render(starsBackground);

        /*TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(5);
        TiledMapTileLayer.Cell cell =  layer.getCell(5, 5);
        TiledMapTile tile = cell.getTile();
        Texture texture = tile.getProperties().get();
        texture.draw(Gdx.files.);*/

        game.spriteBatch.setProjectionMatrix(gameCam.combined);
        game.spriteBatch.begin();
        //texture.draw(Gdx.files.getFileHandle("nyancat_S_Main.1_50.png",  ).internal("nyancat_S_Main.1_50.png"),50,50 );

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }
}
