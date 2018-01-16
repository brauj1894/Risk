/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.scratches;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.GamMain;

/**
 *
 * @author siria3007
 */
public class ScratchGamDiceRoller implements Screen {
    SpriteBatch batch;
    GamMain game;
    Sprite sprBG;
    Texture txtBG,txtAttackerDiceRoll, txtDefenderDiceRoll;
    public ScratchGamDiceRoller(GamMain _game) {
        game = _game;
        txtBG = new Texture("bg3.jpg");
        sprBG = new Sprite(txtBG, 0, 0, 1144, 744);
        txtAttackerDiceRoll = new Texture("button_attacker-dice-roll.png");
        txtDefenderDiceRoll = new Texture("button_defender-dice-roll.png");
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(txtAttackerDiceRoll, 200,200);
        batch.draw(txtDefenderDiceRoll, 400,400);
        batch.end();
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
