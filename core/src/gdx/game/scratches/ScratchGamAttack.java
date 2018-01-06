/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Alex
 */
public class ScratchGamAttack implements Screen {

    Random ranGen = new Random();
    int nRandAttack, nRandDefend;
    SpriteBatch batch;
    GamMain game;
    Sprite sprBG;
    Texture txtBG;
    Button btnAttack, btnDefend, btnEndBattle;

    public ScratchGamAttack(GamMain _game) {
        txtBG = new Texture("bg3.jpg");
        sprBG = new Sprite(txtBG, 0, 0, 1144, 744);
        btnAttack = new Button(100, 100, 100, 100, "button_attack.png");
        btnDefend = new Button(200, 200, 100, 100, "button_defend.png");
        btnEndBattle = new Button(300, 300, 100, 100, "button_end-battle.png");
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
        btnEndBattle.draw(batch);
        batch.end();
    }
    boolean isAttack = false, isDefend = false;
    private void checkButtons() {
        if (Gdx.input.justTouched()) {
            if (btnEndBattle.isMousedOver()) {
                game.changeScreen(3);
            } else if (btnAttack.isMousedOver()) {
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
                } else if (nRandAttack < nRandDefend) {
                    System.out.println("Attackers lost one");
                } else if (nRandAttack == nRandDefend) {
                    System.out.println("Nobody dies this night");
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
