package org.academiadecodigo.nyanlegacy.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.nyanlegacy.game.ClientScreen;
import org.academiadecodigo.nyanlegacy.game.GameManager;

/**
 * Created by Cadavre Exquis on 07-07-2016.
 */
public class B2WorldCreator {

    public B2WorldCreator(ClientScreen clientScreen) {

        init(clientScreen);
    }

    private void init(ClientScreen clientScreen) {

        World world = clientScreen.getWorld();
        TiledMap map = clientScreen.getMap();

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();

        Body body;

        //creating grid ---> layer 1 has tile borders
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;

            //seting tile positions according to grid created on Tiled
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2), (rectangle.getY() + rectangle.getHeight() / 2));

            //creating tile body
            body = world.createBody(bodyDef);

            //creating tile hitbox
            shape.setAsBox(rectangle.getWidth()/2, rectangle.getHeight()/2);

            fixtureDef.shape = shape;

            //connect tiled file layer with corresponding bit
            fixtureDef.filter.categoryBits = GameManager.GRID_BIT;
            body.createFixture(fixtureDef);
        }
    }
}
