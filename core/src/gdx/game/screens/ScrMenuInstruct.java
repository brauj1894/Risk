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
public class ScrMenuInstruct implements Screen {

    SpriteBatch batch;
    Game game;
    Sprite sprBG;
    Texture txtBG;
    Button btnExit;

    public ScrMenuInstruct(Game _game) {

        game = _game;
        txtBG = new Texture("bg3.jpg");
        batch = new SpriteBatch();
        sprBG = new Sprite(txtBG, 0, 0, 1144, 744);
        btnExit = new Button(400, 80, 400, 100, "exit.png");
    }

    @Override
    public void render(float delta) {
        checkButtons();
        batch.begin();
        sprBG.draw(batch);
        btnExit.draw(batch);
        batch.end();
    }

    private void checkButtons() {
        if (Gdx.input.justTouched()) {
            if (btnExit.isMousedOver()) {
                System.out.println("Exiting to main menu");
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
