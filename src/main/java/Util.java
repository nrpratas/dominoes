import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.Optional;

public class Util {

    public static ArrayList<Tile> createSet() {

        //it is the best structure?
        final ArrayList<Tile> allTiles = new ArrayList<>();

        int idOfTile = 0;
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < i + 1; j++) {
                final Tile tile = new Tile(idOfTile, new ImmutablePair<>(i, j), StatusForTiles.IN_STOCK, Optional.empty());
                idOfTile++;
                allTiles.add(tile);
            }

        }
        return allTiles;
    }


}
