import lombok.Getter;
import lombok.Setter;

/**
 * This class will represent a tree with the root and two leaf.
 */
@Getter
@Setter
public class GameChain {
    /**
     * The root tile.
     */
    private Tile root;
    /**
     * The left tile of the chain.
     */
    private Tile leftLeaf;

    /**
     * The right tile of the game.
     */
    private Tile rightLeaf;

    /**
     * Constructor for the game chain.
     *
     * @param root the root tile.
     */
    public GameChain(final Tile root) {
        this.root = root;
        this.leftLeaf = root;
        this.rightLeaf = root;
    }

    /**
     * Return the value of the left in the chain.
     *
     * @return the value of the left in the chain.
     */
    public int getLeftValue() {
        return leftLeaf.getLeft();
    }

    /**
     * Return the value of the right in the chain.
     *
     * @return the value of the right in the chain.
     */
    public int getRightValue() {
        return rightLeaf.getRight();
    }
}
