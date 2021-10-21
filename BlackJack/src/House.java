public class House {
    private Hand hand;

    House(){
        this.hand = new Hand();
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

    public boolean isFinished(){
        if(hand.getValue()<17 && hand.getNum()<5){
            return false;
        }else{
            return true;
        }
    }

    public Hand getHand(){
        return hand;
    }
}
