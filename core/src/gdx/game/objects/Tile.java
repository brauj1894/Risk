package gdx.game.objects;

import gdx.game.GamMain;
import com.badlogic.gdx.math.Vector2;

public class Tile {
    
    GamMain game;
    Vector2 vLocation;
    public int nTroopCount;
    
    public Tile(GamMain _game, int nX, int nY){
        game = _game;
        vLocation = new Vector2(nX, nY);
    }
    
    public float getX(){
        return vLocation.x;
    }
    
    public float getY(){
        return vLocation.y;
    }
}
