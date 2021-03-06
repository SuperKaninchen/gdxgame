package de.nijenhuis.gdxgame;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import static de.nijenhuis.gdxgame.Constants.HEIGHT;
import static de.nijenhuis.gdxgame.Constants.WIDTH;
import de.nijenhuis.gdxgame.GDxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("GDxGame");
        config.setWindowedMode(WIDTH, HEIGHT);
        config.useVsync(true);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new GDxGame(), config);
    }
}
