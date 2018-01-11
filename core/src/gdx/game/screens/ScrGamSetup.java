/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.GamMain;
import gdx.game.objects.Button;

/**
 *
 * @author brauj1894
 */
public class ScrGamSetup implements Screen{
    SpriteBatch batch;
    GamMain game;
    Texture txtBG, txtParch;
    Button btnEndBattle, btnBattle;
    Sprite sprParch, sprWater;
    public ScrGamSetup(GamMain _game) {
        game = _game;
        txtParch = new Texture("paper-background.jpg");
        sprParch = new Sprite(txtParch,768,0,1400,1008);
        batch = new SpriteBatch();
    }
    
    @Override
    public void render(float delta) {
        checkButtons();
        batch.begin();
        sprParch.draw(batch);

        batch.end();
    }
    private void checkButtons() {
    
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
