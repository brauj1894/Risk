/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.GamMain;
import gdx.game.objects.Button;

/**
 *
 * @author brauj1894
 */
public class ScrMenuInstruct implements Screen {

    SpriteBatch batch;
    GamMain game;
    Sprite sprBG;
    Texture txtBG;
    Button btnExit;

    public ScrMenuInstruct(GamMain _game) {
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
        SpriteBatch spriteBatch;
        BitmapFont font;
        CharSequence str = "Welcome to Risk, Joalandia conquest\n" +
"Pregame:\n" +
"When you click the play button, you will be taken to a pregame setup screen where you will choose your provinces and territories.\n" +
"\n" +
"Game:\n" +
"After you have done this, the game will commence. While it is your turn you will have 4 options to choose from, Attack, Move troops, Place troops, end turn.\n" +
"In order to attack the enemy you will need to select the province in which you are attacking and the province you will attack with.\n" +
"At any point of your turn you may move the troops you already have between the provinces you own.\n" +
"You will receive troops at the beginning of each turn to place down on your territories. The amount of troops you recieve at these points will be determined by your owned territories\n" +
"When it is not your turn the other player may attack you and take your land.\n" +
"\n" +
"Attacking and Defending:\n" +
"While you are in a battle, You will have the option to attack or to end the battle if you want to stop.\n" +
"When you attack, the attacker will hit the attack button and the defender will hit the defend button. A random number will be chosen for each player for each attack.\n" +
"The person with the highest number will win that attacking round. The loser of the attacking round will lose a troop.\n" +
"At the end of the battle, the player with no troops left in their territory will lost that territory and the battle.\n" +
"If the battle ends with no side losing all their troops, it will be a mutual end no winners no losers.\n" +
"\n" +
"How to Win:\n" +
"In order to win, you need to control all of the territories in the country.";
        spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        font = new BitmapFont();
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
