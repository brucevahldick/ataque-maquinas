package model;

/**
 *
 * @author brucevahldick
 */
public class Mapa {
    
    private Tile[][] tiles;
    
    public Mapa(){
        tiles = new Tile[8][8];
    }
    
    public void posicionarTile(int x, int y, Tile tile){
        tiles[x][y] = tile;
    }
    
    public Tile getTile(int x, int y){
        return tiles[x][y];
    }
}
