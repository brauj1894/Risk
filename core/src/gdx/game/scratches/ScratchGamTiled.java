/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import gdx.game.GamMain;

/**
 *
 * @author Alex
 */
public class ScratchGamTiled implements Screen {
    
    GamMain game;
    OrthographicCamera camera;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    
    public ScratchGamTiled(GamMain _game){
        game = _game;
        
        // Loading Tiled Map
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        tiledMap = new TmxMapLoader().load("tiledMap2.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap);
    }

    

    @Override
    public void render(float delta) {
        // Draw Tiled Map
        camera.update();
        tmr.setView(camera);
        tmr.render();
    }
    
    @Override
    public void show() {
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
    }
}
