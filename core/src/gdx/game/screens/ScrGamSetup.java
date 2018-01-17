/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import gdx.game.GamMain;
import gdx.game.objects.Button;
import gdx.game.objects.Tile;

/**
 *
 * @author brauj1894
 */
public class ScrGamSetup implements Screen{
    
    SpriteBatch batch;
    GamMain game;
    Texture txtBG, txtParch;
    Button btnNextPhase, btnEndTurn;
    Sprite sprParch, sprWater;
    OrthographicCamera camera;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tmr;
    int nCount;
    Tile arTiles [][] = new Tile [3][3];
    Tile tile1, tile2;
    BitmapFont font;
    
    public ScrGamSetup(GamMain _game) {
        game = _game;
        txtParch = new Texture("paper-background.jpg");
        sprParch = new Sprite(txtParch,0,0,1400,1008);
        sprParch.setX(768);
        batch = new SpriteBatch();
        
        // Creating Buttons
        btnNextPhase = new Button(100,200,151,40,"button_next-phase.png");
        btnEndTurn = new Button(100,100,128,40,"button_end-turn.png");
        
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
                arTiles[i][j] = new Tile(game, i, j);
            }
        }
        tile1 = null;
        tile2 = null;
        
        // Load Text
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font2.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(1.5f, 1.5f);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(32/256f, 64/256f, 256/256f, 1);
        
        // Main Methods
        checkInputs();
        update();
        graphics();
    }
    
    private void graphics(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(32/256f, 64/256f, 256/256f, 1);
        
        // Draw Tiled Map
        camera.update();
        tmr.setView(camera);
        tmr.render();
        
        // Draw Troop Count
        graphicsTiles();
        
        // Draw Right Menu
        batch.begin();
        sprParch.draw(batch);
        batch.end();
    }
    
    private void update(){
        if(nCount == 10){
            nCount = 0;
            
            // Update TroopCount
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    arTiles[i][j].updateStr();
                }
            }
        } else {
            nCount++;
        }
    }
    
    private void graphicsTiles(){
        Tile tile;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tile = arTiles[i][j];
                
                // Troop Count
                int nAdd = 0;
                if(tile.getStr().length() == 1){
                    nAdd = 10;
                }
                batch.begin();
                font.draw(batch, tile.getStr(), i * 256 + 100 + nAdd, (j * 256 + 130)*(-1)+Gdx.graphics.getHeight());
                batch.end();
            }
        }
    }
    
    private Vector2 getMouseLocationOnMap(){
        Vector2 vMapLocation = new Vector2(0, 0);
        vMapLocation.x = Gdx.input.getX() / 256; // 256 is the tile size
        vMapLocation.y = Gdx.input.getY() / 256; // 256 is the tile size
        return vMapLocation;
    }
    
    private boolean isAdjacent(Tile tile1, Tile tile2){
        if(tile1.getX() == tile2.getX()){
            if(tile1.getY() == tile2.getY() + 1){
                return true;
            } else if(tile1.getY() == tile2.getY() - 1){
                return true;
            }
        }
        if(tile1.getY() == tile2.getY()){
            if(tile1.getX() == tile2.getX() + 1){
                return true;
            } else if(tile1.getX() == tile2.getX() - 1){
                return true;
            }
        }
        return false;
    }
    
    private void checkInputs() {
        if (Gdx.input.justTouched()) {
            Vector2 vTemp = getMouseLocationOnMap();
            
            // Tests adding troops to tiles
            arTiles[(int)vTemp.x][(int)vTemp.y].setTroopCount(arTiles[(int)vTemp.x][(int)vTemp.y].getTroopCount()+1);
            System.out.println("X: "+vTemp.x+" Y: "+vTemp.y+" Troops: "+arTiles[(int)vTemp.x][(int)vTemp.y].getTroopCount());
            
            if (btnEndTurn.isMousedOver()) {
                
            }else if(btnNextPhase.isMousedOver()){
                
            }
    }
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
