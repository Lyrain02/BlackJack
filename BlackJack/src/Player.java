import java.util.ArrayList;

public class Player {
    private int id;
    private int wallet;
    private int bet;
    private Hand hand;

    Player(int id, int wallet){
        this.id = id;
        this.wallet = wallet;
        bet = 0;
        hand = new Hand();
    }

    public Boolean hasMoney(){
        return (wallet > 0);
    }

    public Boolean hasBet(int bet){
        return (wallet >=bet);
    }

    public int winMoney(int money){
        this.wallet+=money;
        return wallet;
    }

    public int loseMoney(int money){
        this.wallet-=money;
        return wallet;
    }

    public Boolean setBet(int money){
        if(wallet >= money){
            wallet-=money;
            return true;
        }else{
            return false;
        }
    }

    public int addCard(Card c){
        return hand.addCard(c);
    }

    public void clearCards(){
        hand.clearCards();
    }

    public int getHandValue(){
        return hand.getValue();
    }

    public boolean isBJ(){
        return hand.isBlackJack();
    }

    public boolean is21(){
        return hand.is21Points();
    }

    public boolean isLose(){
        return hand.isLose();
    }

    public int getMoney(){
        return wallet;
    }

    public int getBet(){
        return bet;
    }

    public Hand getHand(){
        return hand;
    }





}
