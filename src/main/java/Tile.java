import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * The definition of the tile.
 */
@AllArgsConstructor
@Getter
@Setter
public class Tile {

    private final int id;

    private ImmutablePair<Integer, Integer> tileValues;

    private StatusForTiles status;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tile)) {
            return false;
        }
        final ImmutablePair<Integer, Integer> tileValueChain = ((Tile) obj).getTileValues();

        if (tileValueChain.getLeft().equals(tileValues.getLeft())
                || tileValueChain.getRight().equals(tileValues.getRight())) {
            // rotation
            applyRotation();
            return true;
        }

        return tileValueChain.getRight().equals(tileValues.getLeft())
                || tileValueChain.getLeft().equals(tileValues.getRight());
    }

    public void applyRotation() {
        int oldLeft = this.tileValues.getLeft();

        this.tileValues = new ImmutablePair<>(this.getTileValues().getRight(), oldLeft);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", \n tileValues=" + tileValues +
                ", \n status=" + status +
                '}';
    }
}
