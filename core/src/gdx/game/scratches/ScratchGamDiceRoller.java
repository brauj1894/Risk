/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.GamMain;
import gdx.game.objects.Button;
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
    int nRA1, nRA2, nRA3;//Dice for attackers
    int nRD1, nRD2;//Dice for defenders
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
    }

    private void checkButtons() {
        if (Gdx.input.justTouched()) {
            if (btnBattle.isMousedOver()) {

                nRA1 = ranGen.nextInt(6) + 1;
                nRA2 = ranGen.nextInt(6) + 1;
                nRA3 = ranGen.nextInt(6) + 1;
                nRD1 = ranGen.nextInt(6) + 1;
                nRD2 = ranGen.nextInt(6) + 1;
                System.out.println(nRA1);
                System.out.println(nRA2);
                System.out.println(nRA3);
                System.out.println(nRD1);
                System.out.println(nRD2);
                diceMechanics();
            }
        }
    }
    
    private void diceMechanics() {
        if(nRA1 < nRA2 && nRA1 < nRA3){
            System.out.println("First dice is the lowest");
            if(nRD1>nRD2){
                
            }else if(nRD2>nRD1){
                
            }
        }else if(nRA2 < nRA1 && nRA2 < nRA3){
            System.out.println("Second dice is the lowest");
            if(nRD1>nRD2){
                
            }else if(nRD2>nRD1){
                
            }
        }else if(nRA3 < nRA2 && nRA3 < nRA1){
            System.out.println("Third dice is the lowest");
            if(nRD1>nRD2){
                
            }else if(nRD2>nRD1){
                
            }
        }
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
