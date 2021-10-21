public class Game {
    private House house;
    private Player player;
    private Deck deck;
    private Panel panel;
    private Round round;
    private boolean gameStatus;

    Game(){
        house = new House();
        player = new Player(1,1000);
        deck = new Deck();
        gameStatus = false;
        panel = new Panel();
        round = new Round(house,player,deck,panel);
    }

    public void init(){
        gameStatus = panel.play();
    }

    public boolean play(){
        if(player.hasMoney()==false){
            gameStatus = false;
            System.out.println("You do not have enough money!");
            return false;
        }
        round.init();
        round.betStage();
        round.first2Stage();
        round.playerStage();
        round.houseStage();
        round.finalStage();
//        System.out.println("Game is on!");
//        player.loseMoney(500);
        return true;
    }

    public boolean isGameOn(){
        return gameStatus;
    }


}
