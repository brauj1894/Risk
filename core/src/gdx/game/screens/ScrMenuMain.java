/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.screens;

import com.badlogic.gdx.Gdx;
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
public class ScrMenuMain implements Screen {
    SpriteBatch batch;
    GamMain game;
    Sprite sprBG;
    Texture txtBG;
    Button btnPlay;
    public ScrMenuMain(GamMain _game) {
        game = _game;
        txtBG = new Texture ("bg3.jpg");
        batch = new SpriteBatch();
        sprBG = new Sprite(txtBG, 0 ,0, 1144,744);   
        btnPlay = new Button(100, 100, 200, 200, "badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        checkButtons();
        batch.begin();
        sprBG.draw(batch);
        btnPlay.draw(batch);
        batch.end();
    }
    
    private void checkButtons(){
        if(Gdx.input.justTouched()){
            if(btnPlay.isMousedOver()){
                game.changeScreen(1);
            }
        }
    }
    
    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
