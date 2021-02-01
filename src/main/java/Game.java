import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
        this.player1 = new Player("Alice");
        this.player2 = new Player("Bob");
        this.activePlayer = this.player1;
        // define the tiles for the players and stock
        tilesForPlayers();
    }

    public void logicGame() {
        prepareForGame();
        startGame();

        int checkForDraw = 0;
        while (!this.stock.isEmpty()) {

            //compare the tiles of the active player with the left and right values of the game chain
            final List<Tile> listTiles = activePlayer.getListTiles();

            for (int i = 0; i < listTiles.size(); i++) {
                final Tile tileToCheck = listTiles.get(i);
                if (tileToCheck.isPlayable(gameChain.getLeftValue(), gameChain.getRightValue())) {
                    // there is a tile compatible with the game chain
                    leftOrRight(tileToCheck);
                    activePlayer.getListTiles().remove(tileToCheck);
                    checkForDraw = 0;
                    break;
                }

                if (i == listTiles.size() - 1) {
                    // go to stock
                    boolean tileFound = false;
                    while (!tileFound) {

                        if (this.stock.isEmpty()) {
                            checkForDraw++;
                            tileFound = true;
                        }
                        final Tile tileFromStock = this.stock.pop();
                        if (tileFromStock.isPlayable(gameChain.getLeftValue(), gameChain.getRightValue())) {
                            leftOrRight(tileFromStock);
                            tileFound = true;
                            checkForDraw = 0;
                        } else {
                            activePlayer.getListTiles().add(tileFromStock);
                        }
                    }
                }
            }

            if (checkForDraw > 1) {
                //draw
                System.out.println("Draw!");
                return;
            }
            //The game ends if one of the players does not have more tiles
            if (!activePlayer.haveTiles()) {
                System.out.printf("Player %s has won!%n", activePlayer.getName());
                return;
            }
            changeActivePlayer();
        }
    }

    /**
     * Determine if the tile goes to the left or to the right side of the chain.
     *
     * @param tileToCheck the tile that you already know that goes to the game chain to check.
     */
    private void leftOrRight(final Tile tileToCheck) {
        if (tileToCheck.getLeft() == this.gameChain.getRightValue()) {
            System.out.printf("%s plays %s to connect to tile %s on the board %n",
                    activePlayer.getName(),
                    tileToCheck.toString(),
                    this.gameChain.getRightLeaf().toString());
            this.gameChainString = this.gameChainString.concat(tileToCheck.toString());
            this.gameChain.setRightLeaf(tileToCheck);
        } else {
            System.out.printf("%s plays %s to connect to tile %s on the board %n",
                    activePlayer.getName(),
                    tileToCheck.toString(),
                    this.gameChain.getLeftLeaf().toString());
            this.gameChainString = tileToCheck.toString().concat(this.gameChainString);
            this.gameChain.setLeftLeaf(tileToCheck);

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

    /**
     * Change the active player in game.
     */
    private void changeActivePlayer() {
        if (activePlayer.equals(player1)) {
            activePlayer = player2;
        } else {
            activePlayer = player1;
        }
    }
}
