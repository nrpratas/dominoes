import java.util.Collections;
import java.util.Stack;

public class Util {

    public static Stack<Tile> createStock() {

        final Stack<Tile> tiles = new Stack<>();

        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < i + 1; j++) {
                final Tile tile = new Tile(i, j);
                tiles.add(tile);
            }
        }
        Collections.shuffle(tiles);
        return tiles;
    }


}
