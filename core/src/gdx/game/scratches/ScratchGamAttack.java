/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import java.util.Random;

/**
 *
 * @author Alex
 */
public class ScratchGamAttack implements Screen {

    Random ranGen = new Random();
    int nRandAttack, nRandDefend;
    SpriteBatch batch;
    GamMain game;
    Sprite sprBG, sprTroopsA, sprTroopsD;
    Texture txtBG, txtTroopsA, txtTroopsD;
    Button btnAttack, btnDefend, btnEndBattle;

    public ScratchGamAttack(GamMain _game) {
        txtBG = new Texture("bg3.jpg");
        txtTroopsA = new Texture("button_troops.png");
        txtTroopsD = new Texture("button_troops.png");
        sprBG = new Sprite(txtBG, 0, 0, 1144, 744);
        sprTroopsA = new Sprite(txtTroopsA,200,500,100,100);
        sprTroopsD = new Sprite(txtTroopsD, 800,500,100,100);
        btnAttack = new Button(200, 650, 100, 100, "button_attack.png");
        btnDefend = new Button(800, 650, 100, 100, "button_defend.png");
        btnEndBattle = new Button(500, 100, 100, 100, "button_end-battle.png");
        batch = new SpriteBatch();
        
    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float delta) {
        checkButtons();
        batch.begin();
        sprBG.draw(batch);
        btnAttack.draw(batch);
        btnDefend.draw(batch);
        sprTroopsA.draw(batch);
        sprTroopsD.draw(batch);
        btnEndBattle.draw(batch);
        batch.end();
        SpriteBatch spriteBatch;
        BitmapFont font;
        CharSequence sNumberAttackers = Integer.toString(nTroopsA);
        CharSequence sNumberDefenders = Integer.toString(nTroopsD);
        spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        font = new BitmapFont();
        font.draw(spriteBatch, sNumberAttackers, 200, 650);
        font.draw(spriteBatch, sNumberDefenders, 800, 650);
        spriteBatch.end();
    }
    boolean isAttack = false, isDefend = false;
    boolean isTroopsA = true, isTroopsD = true;
    int nTroopsA = 5, nTroopsD = 3;

    private void checkButtons() {
        if (Gdx.input.justTouched()) {
            if (btnEndBattle.isMousedOver()) {
                game.changeScreen(0);
            }
            if (isTroopsA && isTroopsD) {
                if (btnAttack.isMousedOver()) {
                    nRandAttack = ranGen.nextInt(10);
                    System.out.println(nRandAttack);
                    isAttack = true;
                } else if (btnDefend.isMousedOver()) {
                    nRandDefend = ranGen.nextInt(10);
                    System.out.println(nRandDefend);
                    isDefend = true;
                }

                if (isAttack && isDefend) {
                    if (nRandAttack > nRandDefend) {
                        System.out.println("Defenders lost one");
                        nTroopsD -= 1;
                        System.out.println(nTroopsD);
                        if (nTroopsD == 0) {
                            isTroopsD = false;
                        }
                    } else if (nRandAttack < nRandDefend) {
                        System.out.println("Attackers lost one");
                        nTroopsA -= 1;
                        System.out.println(nTroopsA);
                        if (nTroopsA == 0) {
                            isTroopsA = false;
                        }
                    } else if (nRandAttack == nRandDefend) {
                        System.out.println("Nobody dies this night");
                    }
                }
            }
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
