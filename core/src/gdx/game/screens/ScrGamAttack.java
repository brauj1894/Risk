/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.screens;

import com.badlogic.gdx.Game;
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
 * @author brauj1894
 */
public class ScrGamAttack implements Screen {

    Random ranGen = new Random();
    int nRandAttack, nRandDefend;
    SpriteBatch batch;
    GamMain game;
    Sprite sprBG;
    Texture txtBG, txtAttack, txtDefend, txtWhite1, txtWhite2;
    Button btnEndBattle, btnBattle;
    Texture txtAttackerDiceRoll, txtDefenderDiceRoll;
    int arnDiceRanAtt[] = new int[3];
    int arnDiceRanDef[] = new int[2];

    public ScrGamAttack(GamMain _game) {
        game = _game;
        txtBG = new Texture("bg3.jpg");
        txtAttack = new Texture("button_attack.png");
        txtDefend = new Texture("button_defend.png");
        txtWhite1 = new Texture("button.png");
        txtWhite2 = new Texture("button.png");
        sprBG = new Sprite(txtBG, 0, 0, 1144, 744);
        btnBattle = new Button(500, 650, 90, 40, "button_battle.png");
        btnEndBattle = new Button(500, 100, 167, 48, "button_end-battle.png");
        txtAttackerDiceRoll = new Texture("button_attacker-dice-roll.png");
        txtDefenderDiceRoll = new Texture("button_defender-dice-roll.png");
        btnBattle = new Button(500, 650, 90, 40, "button_battle.png");
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        checkButtons();
        batch.begin();
        sprBG.draw(batch);
        batch.draw(txtAttack, 200, 650);
        batch.draw(txtWhite1, 325, 650);
        batch.draw(txtDefend, 800, 650);
        batch.draw(txtWhite2, 933, 650);
        batch.draw(txtAttackerDiceRoll, 20, 400);
        batch.draw(txtDefenderDiceRoll, 700, 400);
        btnBattle.draw(batch);
        btnEndBattle.draw(batch);

        batch.end();
        SpriteBatch spriteBatch;
        BitmapFont font;
        CharSequence sNumberAttackers = Integer.toString(nTroopsA);
        CharSequence sNumberDefenders = Integer.toString(nTroopsD);
        spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        font = new BitmapFont();
        font.draw(spriteBatch, sNumberAttackers, 341, 670);
        font.draw(spriteBatch, sNumberDefenders, 950, 670);
        CharSequence sDA1 = Integer.toString(arnDiceRanAtt[0]);
        CharSequence sDA2 = Integer.toString(arnDiceRanAtt[1]);
        CharSequence sDA3 = Integer.toString(arnDiceRanAtt[2]);
        CharSequence sDD1 = Integer.toString(arnDiceRanDef[0]);
        CharSequence sDD2 = Integer.toString(arnDiceRanDef[1]);
        font.draw(spriteBatch, sDA1, 350, 425);
        font.draw(spriteBatch, sDA2, 380, 425);
        font.draw(spriteBatch, sDA3, 410, 425);
        font.draw(spriteBatch, sDD1, 1020, 425);
        font.draw(spriteBatch, sDD2, 1050, 425);
        spriteBatch.end();
    }
    boolean isBattle = false;
    boolean isTroopsA = true, isTroopsD = true;
    int nTroopsA = 5, nTroopsD = 5;

    private void checkButtons() {
        if (Gdx.input.justTouched()) {
            if (btnEndBattle.isMousedOver()) {
                game.changeScreen(0);
            }
            if (isTroopsA && isTroopsD) {
                if (btnBattle.isMousedOver()) {
                    diceMechanics();
                }
                
            }else{
                game.changeScreen(0);
            }
            if(nTroopsA <= 0){
                isTroopsA = false;
            }
            if(nTroopsD <= 0){
                isTroopsD = false;
            }
        }
    }
    private void diceMechanics() {
        for (int i = 0; i < 3; i++) {
            arnDiceRanAtt[i] = ranGen.nextInt(6) + 1;
        }
        for (int j = 0; j < 2; j++) {
            arnDiceRanDef[j] = ranGen.nextInt(6) + 1;
        }
        Arrays.sort(arnDiceRanAtt);
        Arrays.sort(arnDiceRanDef);
        System.out.println(arnDiceRanAtt);
        if (arnDiceRanAtt[2] <= arnDiceRanDef[1]) {
            nTroopsA -=1;
            System.out.println(nTroopsA);
        } else {
            nTroopsD -=1;
            System.out.println(nTroopsD);
        }
        if (arnDiceRanAtt[1] <= arnDiceRanDef[0]) {
            nTroopsA -=1;
            System.out.println(nTroopsA);
        } else {
            nTroopsD -=1;
            System.out.println(nTroopsD);
        }
        System.out.println(arnDiceRanAtt[1]);
        System.out.println(arnDiceRanAtt[2]);
        System.out.println(arnDiceRanDef[0]);
        System.out.println(arnDiceRanDef[1]);
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
