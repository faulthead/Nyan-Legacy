package org.academiadecodigo.nyanlegacy.game.game_objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.nyanlegacy.game.ClientScreen;
import org.academiadecodigo.nyanlegacy.game.GameManager;

/**
 * Created by Cadavre Exquis on 07-07-2016.
 */
public class Cloud {


    private Body body;
    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    private ClientScreen clientScreen;
    private Fixture fixture;
    private MapObject object;

    private TiledMapTileSet tileSet;

    public Cloud(MapObject object, ClientScreen clientScreen) {
        this.object = object;
        this.clientScreen = clientScreen;

        defineCloud();
    }

    private void defineCloud() {

        world = clientScreen.getWorld();
        map = clientScreen.getMap();
        bounds = ((RectangleMapObject) object).getRectangle();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2), (bounds.getY() + bounds.getHeight()));

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2, bounds.getHeight() / 2);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

        fixture.setUserData(this);
        Filter filter = new Filter();
        filter.categoryBits = GameManager.CLOUD_BIT;
        fixture.getFilterData();
    }

    private void show(){
        //show image
    }

    public void onSelect(){
        show();
        //do stuff when tile is selected
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(5);

        return layer.getCell((int) (body.getPosition().x), (int) (body.getPosition().y));
    }
}
