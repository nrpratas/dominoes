import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@NoArgsConstructor
@Setter
@Getter
public class Game {
    private Player player1;
    private Player player2;
    private ArrayList<Tile> gameChain;
    private ArrayList<Tile> stock;
    private Player winner;


    /**
     * Shuffle the tiles.
     */
    public void prepareForGame() {
        // Create Player
        this.player1 = new Player(1, "Alice");
        this.player2 = new Player(2, "Bob");

        tilesForPlayers();
    }

    private void tilesForPlayers() {
        //each player get 7 tiles
        final ArrayList<Tile> tiles = Util.createSet();

        for (int i = 0; i < 7; i++) {
            int randomIndexPlayer1 = new Random()
                    .nextInt(tiles.size());

            Tile tilePlayer1 = tiles.get(randomIndexPlayer1);
            tilePlayer1.setGamer(Optional.of(player1));
            tilePlayer1.setStatus(StatusForTiles.IN_PLAYER);
            this.player1.getListTiles().add(tilePlayer1);
            tiles.remove(randomIndexPlayer1);

            int randomIndexPlayer2 = new Random()
                    .nextInt(tiles.size());
            Tile tilePlayer2 = tiles.get(randomIndexPlayer2);
            tilePlayer2.setGamer(Optional.of(player2));
            tilePlayer2.setStatus(StatusForTiles.IN_PLAYER);
            this.player2.getListTiles().add(tilePlayer2);
            tiles.remove(randomIndexPlayer2);
        }

        //the tiles remaining go to the stock
        this.stock = tiles;
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
