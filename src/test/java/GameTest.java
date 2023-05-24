import junit.framework.TestCase;

public class GameTest extends TestCase {

    private final Game game = new Game();
    /*
    28 Tiles -7 for each player -1 stock
     */
    private static final int NUMBER_TILES_IN_STOCK_BEGINNING = 14;

    private static final int NUMBER_TILES_OF_EACH_PLAYER_BEGINNING = 7;

    /*
        - Verify that player1 and player2 are created with the correct names ("Alice" and "Bob" respectively).
        - Verify that the activePlayer is set to player1.
        - Verify that players have tiles and the stock is correctly created.
    */
    public void testPrepareForGame() {
        game.prepareForGame();

        assertNotNull(game.getPlayer1());
        assertNotNull(game.getPlayer2());
        assertEquals("Alice", game.getPlayer1().getName());
        assertEquals("Bob", game.getPlayer2().getName());

        assertNotNull(game.getActivePlayer());
        assertEquals(game.getActivePlayer(), game.getPlayer1());

        assertTrue(game.getPlayer1().haveTiles());

        assertTrue(game.getPlayer2().haveTiles());

        assertEquals(game.getStock().size(), NUMBER_TILES_IN_STOCK_BEGINNING);
        assertNotNull(game.getStock());
    }

    /*
        - Verify that stock is created and contains the correct number of tiles.
        - Verify that player1 and player2 each have 7 tiles.
        - Verify that the remaining tiles are in the stock.
    */
    public void testTilesForPlayers() {
        game.prepareForGame();
        game.startGame();

        assertNotNull(game.getStock());
        assertEquals(game.getStock().size(), NUMBER_TILES_IN_STOCK_BEGINNING);

        assertEquals(game.getPlayer1().getListTiles().size(),NUMBER_TILES_OF_EACH_PLAYER_BEGINNING);
        assertEquals(game.getPlayer2().getListTiles().size(),NUMBER_TILES_OF_EACH_PLAYER_BEGINNING);

        assertNotNull(game.getGameChain());
        assertNotNull(game.getGameChainString());

        assertEquals(game.getGameChain().getLeftValue(), game.getGameChain().getRightValue());
        assertTrue(game.getGameChainString().contains(game.getGameChain().getRoot().toString()));
    }
}
