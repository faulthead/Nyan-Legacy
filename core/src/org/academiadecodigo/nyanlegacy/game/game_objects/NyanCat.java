package org.academiadecodigo.nyanlegacy.game.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.nyanlegacy.game.ClientScreen;
import org.academiadecodigo.nyanlegacy.game.GameManager;

/**
 * Created by Cadavre Exquis on 07-07-2016.
 */
public class NyanCat extends GameObject {

    private final int TILE_SIZE = 50;

    private Texture texture;
    private Vector2 position;

    private Rectangle bounds;

    private Body body;
    private World world;
    private TiledMap map;
    private TiledMapTile tile;

    private ClientScreen clientScreen;
    private Fixture fixture;
    private MapObject object;


    private TiledMapTileSet tileSet;

    public NyanCat(ClientScreen clientScreen, Rectangle object) {

        bounds = object;
        this.clientScreen = clientScreen;

        defineNyanCat();
    }

    private void defineNyanCat() {

        texture = new Texture("nyancat_S_Main_1_50.png");

        world = clientScreen.getWorld();
        map = clientScreen.getMap();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / GameManager.PPM, (bounds.getY() + bounds.getHeight() / 2) / GameManager.PPM);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / GameManager.PPM, bounds.getHeight() / 2 / GameManager.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

        fixture.setUserData(this);
        Filter filter = new Filter();
        filter.categoryBits = GameManager.NYAN_BIT;
        fixture.setFilterData(filter);
    }

    private void show() {
        //show image
    }

    public void onSelect() {
        show();
        //do stuff when tile is selected
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(5);

        return layer.getCell((int) (body.getPosition().x * GameManager.PPM / TILE_SIZE), (int) (body.getPosition().y * GameManager.PPM / TILE_SIZE));
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        bounds = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());

    }


}