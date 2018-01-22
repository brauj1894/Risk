/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.screens;

import com.badlogic.gdx.Game;
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
import gdx.game.objects.Tile;

/**
 *
 * @author brauj1894
 */
public class ScrGam implements Screen{
    
    SpriteBatch batch;
    GamMain game;
    Texture txtBG, txtParch, txtPlayer1, txtPlayer2;
    Sprite sprParch, sprWater, sprPlayer1, sprPlayer2;
    OrthographicCamera camera;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tmr;
    Tile arTiles [][] = new Tile [3][3];
    Tile tile1, tile2;
    BitmapFont font;
    int nCount = 0, nMode = 0, nPlayerTurn = 1, nTroopLimitPlayer1 = 9, nTroopLimitPlayer2 = 9;
    CharSequence strTroopLimitPlayer1, strTroopLimitPlayer2;
    
    public ScrGam(GamMain _game) {
        game = _game;
        txtParch = new Texture("paper-background.jpg");
        sprParch = new Sprite(txtParch,0,0,1400,1008);
        sprParch.setX(768);
        txtPlayer1 = new Texture("button_player1.png");
        sprPlayer1 = new Sprite(txtPlayer1,110, 45);
        sprPlayer1.setX(850);
        sprPlayer1.setY(600);
        txtPlayer2 = new Texture("button_player2.png");
        sprPlayer2 = new Sprite(txtPlayer2,110, 45);
        sprPlayer2.setX(960);
        sprPlayer2.setY(600);
        batch = new SpriteBatch();

        // Creating Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        
        // Loading Tiled Map
        tiledMap = new TmxMapLoader().load("tiledMap2.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap);
        
        tile1 = null;
        tile2 = null;
        
        // Load Text
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font2.fnt"));
        font.setColor(Color.BLACK);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(32/256f, 64/256f, 256/256f, 1);
        
        checkInput(); // Checks for input and handles any input
        update(); // Updates some game variables once every 10 frames
        graphics(); // Renders graphics
    }
    
    private void graphics(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(32/256f, 64/256f, 256/256f, 1);
        
        // Draw Tiled Map
        camera.update();
        tmr.setView(camera);
        tmr.render();
        
        // Draw Tiles
        graphicsTiles();
        
        batch.begin();
        sprParch.draw(batch);
        batch.end();
        
        // Draw the player picture
        if(nPlayerTurn == 1){
            batch.begin();
            sprPlayer1.draw(batch);
            batch.end();
        } else if(nPlayerTurn == 2){
            batch.begin();
            sprPlayer2.draw(batch);
            batch.end();
        }
        
        // Say troops left
        if(nMode == 1){
            // Player 1
            batch.begin();
            font.draw(batch, strTroopLimitPlayer1, 890, 580);
            batch.end();
            // Player 2
            batch.begin();
            font.draw(batch, strTroopLimitPlayer2, 1000, 580);
            batch.end();
        }
    }
    
    private void checkInput(){
        if(Gdx.input.justTouched()){
            Vector2 vTemp = getMouseLocationOnMap();
            Tile tempTile = null;
            
            // Tests adding troops to tiles
            if(vTemp.x < 3 && vTemp.y < 3){
                tempTile = arTiles[(int)vTemp.x][(int)vTemp.y];
            }
            
            // Claims the tile if it's free
            if(nMode == 0 && tempTile != null){
                if(tempTile.getPlayer() == 0){
                    tempTile.setTroopCount(1);
                    if(nPlayerTurn == 1){
                        tempTile.setPlayer(1);
                        nPlayerTurn = 2;
                    } else if(nPlayerTurn == 2){
                        tempTile.setPlayer(2);
                        nPlayerTurn = 1;
                    }
                }
            }
            
            // Adds a troop to the tile
            if(nMode == 1 && tempTile != null){
                if(nPlayerTurn == tempTile.getPlayer()){
                    addTroops(tempTile, 1);
                    if(nPlayerTurn == 1){
                        nTroopLimitPlayer1--;
                        nPlayerTurn = 2;
                    } else {
                        nTroopLimitPlayer2--;
                        nPlayerTurn = 1;
                    }
                }
            }
        }
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
            
            // Update TroopLimits
            strTroopLimitPlayer1 = Integer.toString(nTroopLimitPlayer1);
            strTroopLimitPlayer2 = Integer.toString(nTroopLimitPlayer2);
            
            // Checks if all the tiles are claimed by a player
            if(nMode == 0){
                if(isAllTilesClaimed()){
                    nMode = 1;
                    nPlayerTurn = 1;
                }
            }
            // Checks if players have placed all their blocks
            if(nMode == 1 && nTroopLimitPlayer2 == 0){
                nMode = 2;
                nPlayerTurn = 1;
            }
                    
        } else {
            nCount++;
        }
    }
    
    private void graphicsTiles(){
        Tile tempTile;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tempTile = arTiles[i][j];
                int nAdd = (tempTile.getStr().length() == 1) ? 10: 0;
                
                // Draw Player Icon
                if(tempTile.getPlayer() == 1){
                    batch.begin();
                    batch.draw(txtPlayer1, tempTile.getX() * 256 + 66, (tempTile.getY() * 256 + 86)*(-1)+Gdx.graphics.getHeight());
                    batch.end();
                } else if(tempTile.getPlayer() == 2){
                    batch.begin();
                    batch.draw(txtPlayer2, tempTile.getX() * 256 + 66, (tempTile.getY() * 256 + 86)*(-1)+Gdx.graphics.getHeight());
                    batch.end();
                }
                
                // Draw Troop Count
                batch.begin();
                font.draw(batch, tempTile.getStr(), i * 256 + 105 + nAdd, (j * 256 + 120)*(-1)+Gdx.graphics.getHeight());
                batch.end();
            }
        }
    }
    
    private void addTroops(Tile tempTile, int nAdd){
        tempTile.setTroopCount(tempTile.getTroopCount()+nAdd);
    }
    
    private boolean isAllTilesClaimed(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(arTiles[i][j].getPlayer() == 0){
                    return false;
                }
            }
        }
        return true;
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
    
    public void setTileArray(Tile[][] _arTiles){
        arTiles = _arTiles;
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
