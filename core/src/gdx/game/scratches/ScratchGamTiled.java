/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import gdx.game.GamMain;
import gdx.game.objects.Tile;

/**
 *
 * @author Alex
 */
public class ScratchGamTiled implements Screen {
    
    GamMain game;
    OrthographicCamera camera;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    Tile arTiles [][] = new Tile [3][3];
    
    public ScratchGamTiled(GamMain _game){
        game = _game;
        
        // Creating Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        
        // Loading Tiled Map
        tiledMap = new TmxMapLoader().load("tiledMap2.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap);
        
        // Create Tiles
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                arTiles[i][j] = new Tile(game);
            }
        }
    }

    

    @Override
    public void render(float delta) {
        checkInput();
        graphics();
    }
    
    private void graphics(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(32/256f, 64/256f, 256/256f, 1);
        // Draw Tiled Map
        camera.update();
        tmr.setView(camera);
        tmr.render();
    }
    
    private void checkInput(){
        if(Gdx.input.justTouched()){
            Vector2 vTemp = mouseLocationOnMap();
            arTiles[(int)vTemp.x][(int)vTemp.y].nTroopCount++;
            System.out.println("X: "+vTemp.x+" Y: "+vTemp.y+" Troops: "+arTiles[(int)vTemp.x][(int)vTemp.y].nTroopCount);
        }
    }
    
    private Vector2 mouseLocationOnMap(){
        Vector2 vMapLocation = new Vector2(0, 0);
        vMapLocation.x = Gdx.input.getX() / 256; // 256 is the tile size
        vMapLocation.y = Gdx.input.getY() / 256; // 256 is the tile size
        return vMapLocation;
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
