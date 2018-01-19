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
    Button btnPlay, btnInst,btnAttackScratch, btnTiledScratch, btnDiceScratch, btnScrAttack;
    public ScrMenuMain(GamMain _game) {
        game = _game;
        txtBG = new Texture ("bg3.jpg");
        batch = new SpriteBatch();
        sprBG = new Sprite(txtBG, 0 ,0, 1144,744);   
        btnPlay = new Button(200, 100, 200, 100, "play.png");
        btnInst = new Button(744, 100, 200, 100, "inst.png");
        btnAttackScratch = new Button(200,500,182,40,"button_attack-scratch.png");
        btnTiledScratch = new Button(744,500,166,40,"button_tiled-scratch.png");
        btnDiceScratch = new Button(500,500,203,40,"button_dice-roll-scratch.png");
        btnScrAttack = new Button(500,400,176,40,"button_attack-screen.png");
    }

    @Override
    public void render(float delta) {
        checkButtons();
        batch.begin();
        sprBG.draw(batch);
        btnPlay.draw(batch);
        btnInst.draw(batch);
        btnAttackScratch.draw(batch);
        btnTiledScratch.draw(batch);
        btnDiceScratch.draw(batch);
        btnScrAttack.draw(batch);
        batch.end();
    }
    
    private void checkButtons(){
        if(Gdx.input.justTouched()){
            if(btnPlay.isMousedOver()){
                game.changeScreen(2);
            }
            if(btnInst.isMousedOver()){
                game.changeScreen(1);
            }
            if(btnAttackScratch.isMousedOver()){
                game.changeScreen(6);
            }
            if(btnTiledScratch.isMousedOver()){
                game.changeScreen(7);
            }
            if(btnDiceScratch.isMousedOver()){
                game.changeScreen(8);
            }
            if(btnScrAttack.isMousedOver()){
                game.changeScreen(4);
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
