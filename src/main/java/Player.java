import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    @Getter
    private String name;
    @Getter
    @Setter
    private List<Tile> listTiles;

    public Player(String name) {
        this.name = name;
        this.listTiles = new ArrayList<>();
    }

    /**
     * Check if active player have tiles.
     *
     * @return true if he have.
     */
    public boolean haveTiles() {
        return !this.listTiles.isEmpty();
    }
}
