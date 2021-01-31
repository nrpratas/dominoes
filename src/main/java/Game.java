import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.Stack;

@NoArgsConstructor
@Setter
@Getter
public class Game {
    private Player player1;
    private Player player2;
    private GameChain gameChain;
    private String gameChainString;
    private Stack<Tile> stock;
    private Player activePlayer;


    /**
     * Shuffle the tiles.
     */
    private void prepareForGame() {
        // Create Players
        this.player1 = new Player(1, "Alice");
        this.player2 = new Player(2, "Bob");
        this.activePlayer = this.player1;
        // define the tiles for the players and stock
        tilesForPlayers();
    }

    public void logicGame() {
        prepareForGame();
        startGame();

        while (!this.stock.isEmpty()) {

            //compare the tiles of the active player with the left and right values of the game chain
            for (Tile tileToCheck : activePlayer.getListTiles()) {
                if (tileToCheck.isPlayable(gameChain.getLeftValue(), gameChain.getRightValue())) {
                    // there is a tile compatible with the game chain
                    checkLeftOrRight(tileToCheck);
                    activePlayer.getListTiles().remove(tileToCheck);
                    return;
                } else {
                    // go to stock
                    boolean success = false;
                    while (!success) {
                        Tile tileFromStock = this.stock.pop();
                        if (tileFromStock.isPlayable(gameChain.getLeftValue(), gameChain.getRightValue())) {
                            checkLeftOrRight(tileFromStock);
                            success = true;
                            activePlayer.getListTiles().remove(tileToCheck);
                        } else {
                            activePlayer.getListTiles().add(tileFromStock);
                        }
                        if (this.stock.isEmpty()) {
                            return;
                        }
                    }
                }

            }
            //The game ends if one of the players does not have more tiles
            if (!activePlayer.haveTiles()) {
                System.out.printf("Player %s has won!%n", activePlayer.getName());
                return;
            }

            changeActivePlayer();
        }
    }

    private void checkLeftOrRight(final Tile tileToCheck) {
        if (tileToCheck.getLeft() == this.gameChain.getRightValue()) {
            System.out.printf("%s plays %s to connect to tile %s on the board %n",
                    activePlayer.getName(),
                    tileToCheck.toString(),
                    this.gameChain.getRightLeaf().toString());
            this.gameChainString = this.gameChainString.concat(tileToCheck.toString());
        } else {
            System.out.printf("%s plays %s to connect to tile %s on the board %n",
                    activePlayer.getName(),
                    tileToCheck.toString(),
                    this.gameChain.getLeftLeaf().toString());
            this.gameChainString = tileToCheck.toString().concat(this.gameChainString);
        }
        System.out.printf("Board is now: %s %n", this.gameChainString);
    }

    public boolean isEnd() {
        if (player1.getListTiles().size() == 0 || player2.getListTiles().size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Split 7 tiles for each player and put the rest on the stock.
     */
    private void tilesForPlayers() {
        //each player get 7 tiles
        this.stock = Util.createStock();

        for (int i = 0; i < 7; i++) {
            final Tile tilePlayer1 = this.stock.pop();
            this.player1.getListTiles().add(tilePlayer1);

            final Tile tilePlayer2 = this.stock.pop();
            this.player2.getListTiles().add(tilePlayer2);
        }
    }


    /**
     * Choose the tile to start and put it on the game chain.
     */
    private void startGame() {
        final Tile tileToStart = stock.pop();
        this.gameChain = new GameChain(tileToStart);
        this.gameChainString = tileToStart.toString();
        System.out.printf("Game starting with first tile: %s%n", this.gameChainString);
    }

    //public Optional<Tile> checkAvailabilityOfTile(final Tile tileFromChain) {
//
    //    return activePlayer.getListTiles()
    //            .stream()
    //            .filter(tile -> tile.isPlayable(tileFromChain))
    //            .findAny();
    //}

    private void changeActivePlayer() {
        if (activePlayer.equals(player1)) {
            activePlayer = player2;
        } else {
            activePlayer = player1;
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", gameChain=" + gameChain +
                ", stock=" + stock +
                '}';
    }
}
