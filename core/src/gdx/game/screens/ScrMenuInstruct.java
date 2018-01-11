/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.GamMain;
import gdx.game.objects.Button;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author brauj1894
 */
public class ScrMenuInstruct implements Screen {

    SpriteBatch batch;
    SpriteBatch spriteBatch;
    BitmapFont font;
    GamMain game;
    Sprite sprBG;
    Texture txtBG;
    Button btnExit;
    CharSequence str;

    public ScrMenuInstruct(GamMain _game){
        game = _game;
        txtBG = new Texture("paper-background.jpg");
        batch = new SpriteBatch();
        sprBG = new Sprite(txtBG, 0, 0, 1400, 1008);
        btnExit = new Button(400, 80, 400, 100, "exit.png");
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        getText();
    }
    
    private void getText(){
        try{
            Scanner fin = new Scanner(new FileReader("Instructions.txt"));
            str = "";
            for(int i = 0; i < 20; i++){
                str += fin.nextLine() + "\n";
            }
        }
        catch(IOException ex){
        System.out.println (ex.toString());
        System.out.println("Could not find file");
        }
        
    }

    @Override
    public void render(float delta) {
        checkButtons();
        batch.begin();
        sprBG.draw(batch);
        btnExit.draw(batch);
        batch.end();
        spriteBatch.begin();
        font.draw(spriteBatch, str, 10, 700);
        spriteBatch.end();
    }
    
    private void checkButtons() {
        if (Gdx.input.justTouched()) {
            if (btnExit.isMousedOver()) {
                game.changeScreen(0);
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
