import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The definition of the tile.
 */
@AllArgsConstructor
@Getter
@Setter
public class Tile {
    /**
     * Value of the left.
     */
    private int left;

    /**
     * Value of the right.
     */
    private int right;

    /**
     * Check if the tile is compatible with the one on the game chain.
     *
     * @return true if possible to play, false if not.
     */
    public boolean isPlayable(int leftOfChain, int rightOfChain ) {

        if (leftOfChain == this.left
                || rightOfChain == this.right) {
            // rotation
            applyRotation();
            return true;
        }

        return rightOfChain == this.left || leftOfChain == this.right;
    }

    /**
     * Rotate the tile to match the game chain position.
     */
    public void applyRotation() {
        int newRight = this.left;
        this.left = this.right;
        this.right = newRight;
    }

    @Override
    public String toString() {
        return "<" + left + ":" + right + ">";
    }
}
