package model;

/**
 *
 * @author brucevahldick
 */
public class Terreno {

    private int bonusTerreno;
    private String color;
    private int idRef;

    public Terreno(TerrenoBuilder tb) {
        this.bonusTerreno = tb.bonusTerreno;
        this.color = tb.color;
        this.idRef = tb.idRef;
    }

    public int getIdRef() {
        return idRef;
    }

    public void setIdRef(int idRef) {
        this.idRef = idRef;
    }

    public void setBonusTerreno(int bonusTerreno) {
        this.bonusTerreno = bonusTerreno;
    }
    

    public int getBonusTerreno() {
        return bonusTerreno;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static class TerrenoBuilder {

        private String color;
        private int bonusTerreno;
        private int idRef;

        public void reset() {
            this.color = null;
            this.bonusTerreno = 0;
        }

        public TerrenoBuilder color(String color) {
            this.color = color;
            return this;
        }

        public TerrenoBuilder bonusTerreno(int bonusTerreno) {
            this.bonusTerreno = bonusTerreno;
            return this;
        }
        
        public TerrenoBuilder idRef(int idRef){
            this.idRef = idRef;
            return this;
        }

        public Terreno build() {
            return new Terreno(this);
        }
    }
}
