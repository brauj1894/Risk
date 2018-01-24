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
public class ScrGam implements Screen{
    
    SpriteBatch batch;
    GamMain game;
    Texture txtBG, txtParch, txtPlayer1, txtPlayer2, txtAttack, txtDefend, txtNegOne, txtPosOne;
    Sprite sprParch, sprWater, sprPlayer1, sprPlayer2;
    OrthographicCamera camera;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tmr;
    Tile arTiles [][] = new Tile [3][3];
    Tile tileAttack, tileDefend, tileNegOne, tilePosOne;
    BitmapFont font;
    int nCount = 0, nMode = 0, nPlayerTurn = 2, nTroopLimitPlayer1 = 1, nTroopLimitPlayer2 = 1;
    CharSequence strTroopLimitPlayer1 = Integer.toString(nTroopLimitPlayer1), strTroopLimitPlayer2 = Integer.toString(nTroopLimitPlayer2);
    Button btnAttack, btnFortify, btnEndTurn, btnNextPhase;
    
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
        txtAttack = new Texture("button_attack.png");
        txtDefend = new Texture("button_defend.png");
        txtNegOne = new Texture("button_-1.png");
        txtPosOne = new Texture("button_+1.png");
        batch = new SpriteBatch();
        btnAttack = new Button(850, 510, 125, 48, "button_attack.png");
        btnFortify = new Button(850, 510, 125, 48, "button_fortify.png");
        btnEndTurn = new Button(850, 450, 128, 40, "button_end-turn.png");
        btnNextPhase = new Button(850, 450, 128, 40, "button_next-phase.png");

        // Creating Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        
        // Loading Tiled Map
        tiledMap = new TmxMapLoader().load("tiledMap2.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap);
        
        tileAttack = null;
        tileDefend = null;
        tileNegOne = null;
        tilePosOne = null;
        
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
        if(nMode == 0){
            // Player 1
            if(nPlayerTurn == 1){
                batch.begin();
                font.draw(batch, strTroopLimitPlayer1, 890, 580);
                batch.end();
            }
            // Player 2
            if(nPlayerTurn == 2){
                batch.begin();
                font.draw(batch, strTroopLimitPlayer2, 1000, 580);
                batch.end();
            }
        }
        
        // Draws the attack and defend tiles on the map
        if(nMode == 1){
            if(tileAttack != null){
                batch.begin();
                batch.draw(txtAttack, tileAttack.getX() * 256 + 65, (tileAttack.getY() * 256 + 40)*(-1)+Gdx.graphics.getHeight());
                batch.end();
            }
            if(tileDefend != null){
                batch.begin();
                batch.draw(txtDefend, tileDefend.getX() * 256 + 65, (tileDefend.getY() * 256 + 40)*(-1)+Gdx.graphics.getHeight());
                batch.end();
            }
        }
        
        // Draws the fortify tiles on the map
        if(nMode == 2){
            if(tileNegOne != null){
                batch.begin();
                batch.draw(txtNegOne, tileNegOne.getX() * 256 + 98, (tileNegOne.getY() * 256 + 40)*(-1)+Gdx.graphics.getHeight());
                batch.end();
            }
            if(tilePosOne != null){
                batch.begin();
                batch.draw(txtPosOne, tilePosOne.getX() * 256 + 96, (tilePosOne.getY() * 256 + 40)*(-1)+Gdx.graphics.getHeight());
                batch.end();
            }
        }
        
        // Draw button to attack
        if(nMode == 1){
            if(tileAttack != null && tileDefend != null){
                batch.begin();
                btnAttack.draw(batch);
                batch.end();
            }
        }
        
        // Draw button to fortify
        if(nMode == 2){
            if(tileNegOne != null && tilePosOne != null){
                batch.begin();
                btnFortify.draw(batch);
                batch.end();
            }
        }
        
        // Draw next phase button
        if(nMode == 1){
            batch.begin();
            btnNextPhase.draw(batch);
            batch.end();
        }
        
        // Draw end turn button
        if(nMode == 2){
            batch.begin();
            btnEndTurn.draw(batch);
            batch.end();
        }
        
        // Say the stage
        if(nMode == 0){
            batch.begin();
            font.draw(batch, "Reinforce", 850, 700);
            batch.end();
        } else if(nMode == 1){
            batch.begin();
            font.draw(batch, "Attack", 850, 700);
            batch.end();
        } else if(nMode == 2){
            batch.begin();
            font.draw(batch, "Fortify", 850, 700);
            batch.end();
        }
    }
    
    private void checkInput(){
        if(Gdx.input.justTouched()){
            Vector2 vTemp = getMouseLocationOnMap();
            Tile tempTile = null;
            
            // Checks if the click was on the map
            if(vTemp.x < 3 && vTemp.y < 3){
                tempTile = arTiles[(int)vTemp.x][(int)vTemp.y];
            }
            
            // Adds Reinforcements
            if(nMode == 0 && tempTile != null){
                if(tempTile.getPlayer() == nPlayerTurn){
                    addTroops(tempTile, 1);
                    if(nPlayerTurn == 1){
                        nTroopLimitPlayer1--;
                    } else {
                        nTroopLimitPlayer2--;
                    }
                }
            }
            
            // Select tiles to attack
            if(nMode == 1 && tempTile != null){
                // Select Attack Tile
                if(tempTile.getPlayer() == nPlayerTurn && tempTile.getTroopCount() >= 2){
                    tileAttack = tempTile;
                    tileDefend = null;
                }
                // Select Defend Tile
                if(tempTile.getPlayer() != nPlayerTurn && tileAttack != null){
                    if(isAdjacent(tileAttack, tempTile)){
                        tileDefend = tempTile;
                    }
                }
            }
            
            // Select tiles to fortify
            if(nMode == 2 && tempTile != null){
                if(tileNegOne != null && tilePosOne != null){
                    tileNegOne = null;
                    tilePosOne = null;
                }
                // Select PosOne Tile
                if(tempTile.getPlayer() == nPlayerTurn && tileNegOne != null){
                    tilePosOne = tempTile;
                }
                // Select NegOne Tile
                if(tempTile.getPlayer() == nPlayerTurn && tempTile.getTroopCount() >= 2 && tileNegOne == null){
                    tileNegOne = tempTile;
                }
            }
            
            // Checks if the attack button is clicked
            if(nMode == 1){
                if(tileAttack != null && tileDefend != null){
                    if(btnAttack.isMousedOver()){
                        game.scrGamAttack.setTroops(tileAttack.getTroopCount(), tileDefend.getTroopCount());
                        game.scrGamAttack.setup();
                        game.changeScreen(4);
                    }
                }
            }
            
            // Checks if the fortify button is clicked
            if(nMode == 2){
                if(tileNegOne != null && tilePosOne != null){
                    if(btnFortify.isMousedOver()){
                        if(tileNegOne.getTroopCount() >= 2){
                            tileNegOne.setTroopCount(tileNegOne.getTroopCount()-1);
                            tilePosOne.setTroopCount(tilePosOne.getTroopCount()+1);
                        }
                    }
                }
            }
            
            // Checks if the end turn button is clicked
            if(nMode == 2){
                if(btnEndTurn.isMousedOver()){
                    if(nPlayerTurn == 1){
                        nPlayerTurn = 2;
                        calTroopLimitPlayer2();
                        nMode = 0;
                    } else {
                        nPlayerTurn = 1;
                        calTroopLimitPlayer1();
                        nMode = 0;
                    }
                }
            }
            
            // Checks if the next phase button is clicked
            if(nMode == 1){
                if(btnNextPhase.isMousedOver()){
                    nMode = 2;
                    tileNegOne = null;
                    tilePosOne = null;
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
                    
            // Checks if all reinforcements have been placed
            if(nMode == 0){
                if(nPlayerTurn == 1 && nTroopLimitPlayer1 <= 0){
                    nMode = 1;
                }
                if(nPlayerTurn == 2 && nTroopLimitPlayer2 <= 0){
                    nMode = 1;
                }
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
    
    private int getNumOfOwnedTiles(int nPlayer){
        int nCount = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(arTiles[i][j].getPlayer() == nPlayer){
                    nCount++;
                }
            }
        }
        return nCount;
    }
    
    private void calTroopLimitPlayer1(){
        int nTilesOwned = getNumOfOwnedTiles(1);
        nTroopLimitPlayer1 = 3 + (nTilesOwned / 2);
    }
    
    private void calTroopLimitPlayer2(){
        int nTilesOwned = getNumOfOwnedTiles(2);
        nTroopLimitPlayer2 = 3 + (nTilesOwned / 2);
    }
    
    public void setTileArray(Tile[][] _arTiles){
        arTiles = _arTiles;
        calTroopLimitPlayer2();
    }
    
    public void battleFinished(int nTroopsAttack, int nTroopsDefend, boolean bTroopsDefend){
        if(bTroopsDefend){
            tileAttack.setTroopCount(nTroopsAttack);
            tileDefend.setTroopCount(nTroopsDefend);
        } else {
            switchPlayer(tileDefend.getPlayer(), tileDefend);
            tileAttack.setTroopCount(nTroopsAttack / 2);
            tileDefend.setTroopCount(nTroopsAttack / 2 + (nTroopsAttack % 2));
        }
        tileAttack = null;
        tileDefend = null;
    }
    
    private void switchPlayer(int nPlayer, Tile tile){
        if(nPlayer == 1){
            tile.setPlayer(2);
        } else {
            tile.setPlayer(1);
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
