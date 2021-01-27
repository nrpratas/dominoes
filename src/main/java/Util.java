import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class Util {

    public static Stack<Tile> createStock() {

        final Stack<Tile> tiles = new Stack<>();

        int idOfTile = 0;
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < i + 1; j++) {
                final Tile tile = new Tile(idOfTile, new ImmutablePair<>(i, j), StatusForTiles.IN_STOCK);
                idOfTile++;
                tiles.add(tile);
            }

        }
        Collections.shuffle(tiles);
        return tiles;
    }


}
