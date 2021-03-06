/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.Game;
import gdx.game.screens.ScrGam;
import gdx.game.screens.ScrGamAttack;
import gdx.game.screens.ScrGamOver;
import gdx.game.screens.ScrGamSetup;
import gdx.game.screens.ScrMenuInstruct;
import gdx.game.screens.ScrMenuMain;
import gdx.game.scratches.ScratchGamAttack;
import gdx.game.scratches.ScratchGamTiled;
import gdx.game.scratches.ScratchGamDiceRoller;

/**
 * @author brauj1894
 * @author siria3007
 */
public class GamMain extends Game {

    ScrMenuMain scrMenuMain;
    ScrMenuInstruct scrMenuInstruct;
    ScrGamSetup scrGamSetup;
    public ScrGam scrGam;
    public ScrGamAttack scrGamAttack;
    ScrGamOver scrGamOver;
    ScratchGamAttack ScratchGamAttack;
    ScratchGamTiled ScratchGamTiled;
    ScratchGamDiceRoller ScratchGamDiceRoller;

    @Override
    public void create() {
        scrMenuMain = new gdx.game.screens.ScrMenuMain(this);
        scrMenuInstruct = new gdx.game.screens.ScrMenuInstruct(this);
        scrGamSetup = new gdx.game.screens.ScrGamSetup(this);
        scrGam = new gdx.game.screens.ScrGam(this);
        scrGamAttack = new gdx.game.screens.ScrGamAttack(this);
        scrGamOver = new gdx.game.screens.ScrGamOver(this);
        ScratchGamAttack = new gdx.game.scratches.ScratchGamAttack(this);
        ScratchGamTiled = new gdx.game.scratches.ScratchGamTiled(this);
        ScratchGamDiceRoller = new gdx.game.scratches.ScratchGamDiceRoller(this);
        changeScreen(0);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }

    public void changeScreen(int nScreen) {
        switch (nScreen) {
            case 0:
                // Main Menu
                setScreen(scrMenuMain);
                break;
            case 1:
                // Instructions
                setScreen(scrMenuInstruct);
                break;
            case 2:
                // Game Setup
                setScreen(scrGamSetup);
                break;
            case 3:
                // Game
                setScreen(scrGam);
                break;
            case 4:
                // Attack/Defense
                setScreen(scrGamAttack);
                break;
            case 5:
                // Game Over
                setScreen(scrGamOver);
                break;
            case 6:
                //Scratch for attack screen
                setScreen(ScratchGamAttack);
                break;
            case 7:
                //Scratch for the tiled map
                setScreen(ScratchGamTiled);
                break;
            case 8:
                //Scratch for the dice rolling function
                setScreen(ScratchGamDiceRoller);
                break;
            default:
                break;
        }
    }
}
