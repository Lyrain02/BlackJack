import java.util.ArrayList;

public class Hand {
    private int num;
    private int value;
    private ArrayList<Card> hand;
    private int numA;

    Hand(){
        this.num = 0;
        this.value = 0;
        this.hand = new ArrayList<Card>();
        this.numA = 0;
    }

    // add a card to hand, return valueBJ of all cards
    public int addCard(Card c){
        num++;
        hand.add(c);
        int valueBJ = c.getValueBJ();
        if(valueBJ==-1){
            numA++;
            value+=11;
        }else{
            value+=valueBJ;
        }
        if(value>21 && numA>0){
            value-=10;
            numA-=1;
        }
        return value;
    }

    public void clearCards(){
        hand.clear();
        num = 0;
        value = 0;
        numA = 0;
    }

    public int getValue(){
        return value;
    }

    public int getNum(){
        return num;
    }

    public Card getCardAt(int index){
        return hand.get(index);
    }

    public boolean isBlackJack(){
        return (num==2 && value==21);
    }

    public boolean is21Points(){
        return (value==21);
    }

    public boolean isLose()
    {
        return (value>21);
    }

    public void showHand(){
//        System.out.print("BJ value: "+getValue()+"\t");

        System.out.print("hand: ");
        for(Card t : hand){
            System.out.print(t.getValueC()+" ");
        }
        System.out.println();
    }

    public void showHouseHand(){
        int tmp = hand.get(0).getValueBJ();
        if(tmp == -1)
            tmp = 11;
        System.out.print("BJ value: "+tmp+"\t");
        System.out.print("hand: ");
        for(int i=0;i<num;i++) {
            if(i==0)
                System.out.print(hand.get(0).getValueC()+" ");
            else
                System.out.print("*"+" ");
        }
        System.out.println();
    }


}
