/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.nijenhuis.gdxgame.Character;
import static de.nijenhuis.gdxgame.Constants.HEIGHT;
import static de.nijenhuis.gdxgame.Constants.WIDTH;
import de.nijenhuis.gdxgame.Entity;
import de.nijenhuis.gdxgame.GDxGame;
import de.nijenhuis.gdxgame.Inventory;
import de.nijenhuis.gdxgame.Item;
import de.nijenhuis.gdxgame.PlayerInputProcessor;
import de.nijenhuis.gdxgame.Player;
import de.nijenhuis.gdxgame.SaveMachine;
import de.nijenhuis.gdxgame.TextureMachine;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author max
 */
public class GameScreen implements Screen {

    final GDxGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Vector2 cameraPosition;
    
    private Stage stage;

    private Array<Entity> entities;
    private Player player;
    private Texture playerTexture;

    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    public GameScreen(final GDxGame game) {
        this.game = game;

        // Setting up camera and rendering
        camera = new OrthographicCamera(30, 30 * (HEIGHT / WIDTH));
        camera.setToOrtho(false, WIDTH, HEIGHT);
        batch = new SpriteBatch();
        cameraPosition = Vector2.Zero;
        
        stage = new Stage(new ScreenViewport());

        // Loading Tilemap
        tiledMap = new TmxMapLoader().load("untitled.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        
        try {
            // Init SaveMechine
            SaveMachine.init();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
        TextureMachine.init();

        // Loading player
        stage.addActor(new Player(this));

        // Initialize entities
        entities = new Array<>();
        stage.addActor(new Character(
                1,
                new Vector2(200, 300)
        ));

        // Connect inputProcessor for user input to player
        PlayerInputProcessor inputProcessor = new PlayerInputProcessor(player);
        Gdx.input.setInputProcessor(inputProcessor);
        
        Gdx.input.setInputProcessor(stage);

    }
    
    public Array<Entity> getEntities() {
        return entities;
    }

    @Override
    public void render(float delta) {

        // Clear the screen and set up rendering
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act(delta);
        stage.draw();

        // Rendering Map
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        // Rendering entities, player, and HUD
        /*batch.begin();
        renderEntities();
        renderPlayer();
        renderHUD();
        batch.end();*/

        // Move camera to player
        //moveCamera();

        // Move player and entities
        /*player.update(delta);
        player.move(delta);
        updateEntities(delta);
        moveEntities(delta);*/
    }

    private void moveCamera() {
        camera.translate(
                player.getX() - cameraPosition.x,
                player.getY() - cameraPosition.y
        );
        cameraPosition = new Vector2(
                player.getX(),
                player.getY()
        );
    }

    /*private void renderEntities() {
        Vector2 offset = new Vector2(
                (WIDTH/2) - player.getX(),
                (HEIGHT/2) - player.getY()
        );

        for (Entity e : entities) {
            renderEntity(e, offset);
            if(e.getClass() == Character.class) {
                Character c = (Character) e;
                c.doAttack(player);
            }
        }
    }

    private void renderEntity(Entity e, Vector2 offset) {
        e.draw(batch, offset);
    }

    private void renderPlayer() {
        player.draw(batch, new Vector2(WIDTH/2, HEIGHT/2));
    }

    private void moveEntities(float delta) {
        for (Entity e : entities) {
            e.move(delta);
        }
    }
    
    private void updateEntities(float delta) {
        for (Entity e : entities) {
            if(e.getClass() == Character.class) {
                Character c = (Character) e;
                c.update(delta);
            }
        }
    }*/

    private void renderHUD() {
        renderHotbar();
    }

    private void renderHotbar() {
        /* OLD
        Inventory hotbar = player.getHotbar();
        Texture slotTexture = hotbar.getSlotTexture();
        for (int i = 0; i < hotbar.getSize(); i++) {
            batch.draw(slotTexture, i * slotTexture.getWidth(), 0);
            if (hotbar.getItem(i) != null) {
                batch.draw(hotbar.getItemTexture(i), i * slotTexture.getWidth() + 32, 32, 64, 64);
            }
        }*/
        player.getHotbar().draw(batch, new Vector2(16, 16));
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}
