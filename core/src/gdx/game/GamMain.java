/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author brauj1894
 */
public class GamMain extends Game {

    SpriteBatch batch;
    Texture img;

    @Override
    public void create() {
        setScreen(new gdx.game.screens.ScrMenuMain(this));
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
