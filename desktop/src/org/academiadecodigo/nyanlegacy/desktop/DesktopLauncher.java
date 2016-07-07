package org.academiadecodigo.nyanlegacy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.nyanlegacy.game.ClientScreen;
import org.academiadecodigo.nyanlegacy.game.GameManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameManager.WIDTH;
		config.height = GameManager.HEIGHT;
		config.title = "Nyan Legacy";
		new LwjglApplication(new ClientScreen(), config);
	}
}
