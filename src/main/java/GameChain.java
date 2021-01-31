import lombok.Getter;
import lombok.Setter;

/**
 * This class will represent a tree with the root and two leaf.
 */
@Getter
@Setter
public class GameChain {
    private Tile root;
    private Tile leftLeaf;
    private Tile rightLeaf;

    public GameChain(Tile root) {
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
        return leftLeaf.getLeft();
    }


}
