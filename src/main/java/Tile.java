import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * The definition of the tile.
 */
@AllArgsConstructor
@Getter
@Setter
public class Tile {

    private final int id;

    private final ImmutablePair<Integer, Integer> tileValues;

    private StatusForTiles status;

    private Optional<Player> gamer;

    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", \n tileValues=" + tileValues +
                ", \n status=" + status +
                '}';
    }
}
