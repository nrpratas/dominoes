import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Player {
    @Getter
    private int id;
    private String name;
    @Getter
    @Setter
    private ArrayList<Tile> listTiles;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.listTiles = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listTiles=" + listTiles +
                '}';
    }
}
