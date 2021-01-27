import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@NoArgsConstructor
@Setter
@Getter
public class Game {
    private Player player1;
    private Player player2;
    private ArrayList<Tile> gameChain = new ArrayList<>();
    private ArrayList<Tile> stock;
    private Player winner;
    private Player activePlayer;


    /**
     * Shuffle the tiles.
     */
    public void prepareForGame() {
        // Create Players
        this.player1 = new Player(1, "Alice");
        this.player2 = new Player(2, "Bob");
        this.activePlayer = this.player1;
        // define the tiles for the players and stock
        tilesForPlayers();
    }

    public void testOnly() {
        startGame();
        Tile tile = checkAvailabilityOfTile(gameChain.get(0));
        System.out.println(tile);
    }

    /**
     * Split 7 tiles for each player and put the rest on the stock.
     */
    private void tilesForPlayers() {
        //each player get 7 tiles
        this.stock = Util.createSet();

        for (int i = 0; i < 7; i++) {
            final Tile tilePlayer1 = randomTile();
            tilePlayer1.setStatus(StatusForTiles.IN_PLAYER);
            this.player1.getListTiles().add(tilePlayer1);

            final Tile tilePlayer2 = randomTile();
            tilePlayer2.setStatus(StatusForTiles.IN_PLAYER);
            this.player2.getListTiles().add(tilePlayer2);
        }
    }

    /**
     * Choose a random tile from the stock.
     *
     * @return the tile randomly chosen.
     */
    private Tile randomTile() {
        int randomIndexPlayer1 = new Random()
                .nextInt(this.stock.size());

        final Tile tile = stock.get(randomIndexPlayer1);

        this.stock.remove(randomIndexPlayer1);

        return tile;
    }

    /**
     * Choose the tile to start and put it on the game chain.
     */
    private void startGame() {
        final Tile tileToStart = randomTile();

        //set the status to in game
        tileToStart.setStatus(StatusForTiles.IN_GAME);

        this.gameChain.add(tileToStart);

        System.out.printf("Game starting with first tile: %s%n", tileToStart.getTileValues());
    }

    public Tile checkAvailabilityOfTile(final Tile tileFromChain) {

        return activePlayer.getListTiles().stream()
                .filter(tile -> tile.equals(tileFromChain))
                .findFirst().orElse(null);
    }

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
                ", winner=" + winner +
                '}';
    }
}
