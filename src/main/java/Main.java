public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        game.prepareForGame();
        System.out.println(game.getPlayer1().toString());
    }
}
