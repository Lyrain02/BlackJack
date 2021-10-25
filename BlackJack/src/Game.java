public class Game {
    private House house;
    private Player player;
    private Deck deck;
    private Panel panel;
    private Round round;
    private boolean gameStatus;
    private GUI gui;
    private static final int initWallet = 1000;

    Game(){
        gui = new GUI();
        house = new House();
        player = new Player(1,initWallet);
        deck = new Deck();
        gameStatus = false;
        panel = new Panel(gui);
        round = new Round(house,player,deck,panel,gui);
    }

    public void init(){
        gui.setWallet(initWallet);
        gui.showGUI();
        gameStatus = panel.play();

    }

    public boolean play() throws InterruptedException {
        if(player.hasMoney()==false){
            gameStatus = false;
//            System.out.println("You do not have enough money!");
            gui.showExitMessage("Game Over","You lose all your money, bye！");
            return false;
        }
        round.init();
        round.betStage();
        round.first2Stage();
        round.playerStage();
        round.houseStage();
        round.finalStage();
        return true;
    }

    public boolean isGameOn(){
        return gameStatus;
    }


}
