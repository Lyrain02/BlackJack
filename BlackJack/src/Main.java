public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.init();
        while(game.isGameOn()){
            game.play();
        }
    }
}
