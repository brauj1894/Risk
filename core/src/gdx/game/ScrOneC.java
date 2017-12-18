/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author brauj1894
 */
public class ScrOneC implements Screen {

    Game game;
    SpriteBatch batch;
    Texture txBad;
    Sprite sprBad;
    int nX, nY; // coordinates for the dude.
    boolean isClick;

    public ScrOneC(Game _game) {
        game = _game;
        batch = new SpriteBatch();
        txBad = new Texture("badlogic.jpg");
        sprBad = new Sprite(txBad);
        sprBad.setX(0);
        sprBad.setY(0);
        isClick = false;
    }

    public boolean handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            sprBad.setY(sprBad.getY() + 20);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            sprBad.setY(sprBad.getY() - 20);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprBad.setX(sprBad.getX() - 20);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprBad.setX(sprBad.getX() + 20);
        }
        if(Gdx.input.isTouched()){
            isClick = true;
        } else {
            isClick = false;
        }
        return true;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.begin();
        batch.draw(sprBad, sprBad.getX(), sprBad.getY());
        batch.end();
        if (isClick) {
            Gdx.gl.glClearColor(1, 0, 1, 1);
        } else {
            Gdx.gl.glClearColor(1, 0, 0, 1);
        }
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
