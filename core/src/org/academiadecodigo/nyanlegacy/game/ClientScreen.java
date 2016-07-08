package org.academiadecodigo.nyanlegacy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.nyanlegacy.game.game_objects.GameObject;
import org.academiadecodigo.nyanlegacy.game.game_objects.NyanCat;
import org.academiadecodigo.nyanlegacy.game.game_objects.PinkNyanCat;
import org.academiadecodigo.nyanlegacy.game.tools.B2WorldCreator;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

    private GameObject[][] gameObjects;

    private GameObject player1;

    private String json;



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

    private void addGameObject(GameObject gameObject, int x, int y) {
        creator.addGameObject(gameObject, x, y);
    }

    private void handleInput(String sentence) throws UnknownHostException {
        //1 substring to remove /
        //array that splits string
        //then split : ---> resulting array:
        ///verify if ip == local ip --> atribuir player
        //get position
        //booblean is dead

        if(json.length() > 10) {
            String result = sentence.substring(1);

            String[] resultArray = result.split(":");

            String ip = resultArray[0];
            int x = Integer.parseInt(resultArray[1])+1;
            int y = Integer.parseInt(resultArray[2])+1;
            boolean isDead = Boolean.parseBoolean(resultArray[3]);

            if (InetAddress.getLocalHost().getHostAddress().equals(ip)) {
                System.out.println("MINE");
                player1 = new NyanCat(this, new Rectangle().setPosition(x,y));
                player1.setPosition(x, y);
                gameObjects[x][y] = player1;

            } else {
                System.out.println("YOURS");
                gameObjects[x][y] = player1;
                player1 = new PinkNyanCat(this, new Rectangle().setPosition(x,y));
                System.out.println("END?????");
                player1.setPosition(x, y);
            }

            System.out.println("LOOP");
        }

    }



    public void update(float dt) {

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
        renderer.render(starsBackground);
        renderer.render(starsForeground);

        try {
            handleInput(json);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        game.spriteBatch.setProjectionMatrix(gameCam.combined);

        if (!gameObjectsIsEmpty()) {
            game.spriteBatch.begin();

            //draw bidimensional array
            for (int row = 1; row < gameObjects.length; row++) {
                for (int col = 1; col < gameObjects.length; col++) {
                    if (gameObjects[row][col] != null) {
                        game.spriteBatch.draw(gameObjects[row][col].getTexture(), row * 50 / GameManager.PPM, col * 50 / GameManager.PPM);
                        System.out.println("DRAW");
                    }
                }
            }
            game.spriteBatch.end();

        }

        /*TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(5);
        TiledMapTileLayer.Cell cell =  layer.getCell(5, 5);
        TiledMapTile tile = cell.getTile();
        Texture texture = tile.getProperties().get();
        texture.draw(Gdx.files.);*/

        //texture.draw(Gdx.files.getFileHandle("nyancat_S_Main.1_50.png",  ).internal("nyancat_S_Main.1_50.png"),50,50 );

    }

    private boolean gameObjectsIsEmpty() {

        for (int i = 0; i < gameObjects.length; i++) {
            for (int j = 0; j < gameObjects.length; j++) {
                if (gameObjects[i][j] != null) {
                    System.out.println("break????");
                    return false;

                }

            }

        }
        return true;
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

    public void setGameObjects(GameObject[][] gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
