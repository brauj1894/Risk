/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.GamMain;
import gdx.game.objects.Button;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author siria3007
 */
public class ScratchGamDiceRoller implements Screen {

    SpriteBatch batch;
    GamMain game;
    Sprite sprBG;
    Texture txtBG, txtAttackerDiceRoll, txtDefenderDiceRoll;
    Button btnBattle;
    int arnDiceRanAtt[] = new int[3];
    int arnDiceRanDef[] = new int[2];
    Random ranGen = new Random();

    public ScratchGamDiceRoller(GamMain _game) {
        game = _game;
        txtBG = new Texture("bg3.jpg");
        sprBG = new Sprite(txtBG, 0, 0, 1144, 744);
        txtAttackerDiceRoll = new Texture("button_attacker-dice-roll.png");
        txtDefenderDiceRoll = new Texture("button_defender-dice-roll.png");
        btnBattle = new Button(500, 650, 90, 40, "button_battle.png");
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        batch.begin();
        sprBG.draw(batch);
        batch.draw(txtAttackerDiceRoll, 20, 400);
        batch.draw(txtDefenderDiceRoll, 700, 400);
        btnBattle.draw(batch);
        batch.end();
        checkButtons();
        SpriteBatch spriteBatch;
        BitmapFont font;
        CharSequence sDA1 = Integer.toString(arnDiceRanAtt[0]);
        CharSequence sDA2 = Integer.toString(arnDiceRanAtt[1]);
        CharSequence sDA3 = Integer.toString(arnDiceRanAtt[2]);
        CharSequence sDD1 = Integer.toString(arnDiceRanDef[0]);
        CharSequence sDD2 = Integer.toString(arnDiceRanDef[1]);
        spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        font = new BitmapFont();
        font.draw(spriteBatch, sDA1, 350, 425);
        font.draw(spriteBatch, sDA2, 380, 425);
        font.draw(spriteBatch, sDA3, 410, 425);
        font.draw(spriteBatch, sDD1, 1020, 425);
        font.draw(spriteBatch, sDD2, 1050, 425);
        spriteBatch.end();
    }

    private void checkButtons() {
        if (Gdx.input.justTouched()) {
            if (btnBattle.isMousedOver()) {
                diceMechanics();
            }
        }
    }
    int nTroopsA =5, nTroopsD =5;
    private void diceMechanics() {
        for (int i = 0; i < 3; i++) {
            arnDiceRanAtt[i] = ranGen.nextInt(6) + 1;
        }
        for (int j = 0; j < 2; j++) {
            arnDiceRanDef[j] = ranGen.nextInt(6) + 1;
        }
        Arrays.sort(arnDiceRanAtt);
        Arrays.sort(arnDiceRanDef);
        if (arnDiceRanAtt[2] <= arnDiceRanDef[1]) {
            nTroopsA -=1;
        } else {
            nTroopsD -=1;
        }
        if (arnDiceRanAtt[1] <= arnDiceRanDef[0]) {
            nTroopsA -=1;
        } else {
            nTroopsD -=1;
        }
        System.out.println(arnDiceRanAtt[1]);
        System.out.println(arnDiceRanAtt[2]);
        System.out.println(arnDiceRanDef[0]);
        System.out.println(arnDiceRanDef[1]);
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
