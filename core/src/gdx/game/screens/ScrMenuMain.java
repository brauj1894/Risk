/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.objects.Button;

/**
 *
 * @author brauj1894
 */
public class ScrMenuMain implements Screen {
    SpriteBatch batch;
    Game game;
    Sprite sprBG;
    Texture txtBG;
    Button btnPlay, btnInst;
    public ScrMenuMain(Game _game) {
        game = _game;
        txtBG = new Texture ("bg3.jpg");
        batch = new SpriteBatch();
        sprBG = new Sprite(txtBG, 0 ,0, 1144,744);   
        btnPlay = new Button(200, 100, 200, 100, "play.png");
        btnInst = new Button(744, 100, 200, 100, "inst.png");
    }

    @Override
    public void render(float delta) {
        checkButtons();
        batch.begin();
        sprBG.draw(batch);
        btnPlay.draw(batch);
        btnInst.draw(batch);
        batch.end();
    }
    
    private void checkButtons(){
        if(Gdx.input.justTouched()){
            if(btnPlay.isMousedOver()){
                System.out.println("Starting game");
            }else if(btnInst.isMousedOver()){
                System.out.println("Instructions screen");
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
