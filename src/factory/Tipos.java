package factory;

/**
 *
 * @author brucevahldick
 */
public enum Tipos {
    Pasto(3, "#92f09d"), 
    Pantano(2, "#5567a6"), 
    Montanha(6, "#f0e2e1"), 
    Floresta(4, "#35b03b"), 
    Colina(5, "#827170"), 
    Abismo(1, "#402c2a");

    int id;
    String cor;

    Tipos(int id, String cor) {
        this.id = id;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public String getCor() {
        return cor;
    }
}
