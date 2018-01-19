package gdx.game.objects;

import gdx.game.GamMain;
import com.badlogic.gdx.math.Vector2;

public class Tile {
    
    GamMain game;
    Vector2 vLocation;
    int nTroopCount, nPlayer = 0;
    CharSequence str;
    
    public Tile(GamMain _game, int nX, int nY){
        game = _game;
        vLocation = new Vector2(nX, nY);
        str = "0";
    }
    
    public void updateStr(){
        str = Integer.toString(nTroopCount);
    }
    
    public float getX(){
        return vLocation.x;
    }
    
    public float getY(){
        return vLocation.y;
    }
    
    public int getTroopCount(){
        return nTroopCount;
    }
    
    public void setTroopCount(int _nTroopCount){
        nTroopCount = _nTroopCount;
    }
    
    public void setPlayer(int _nPlayer){
        nPlayer = _nPlayer;
    }
    
    public CharSequence getStr(){
        return str;
    }
    
    public int getPlayer(){
        return nPlayer;
    }
}
