
public class Card {
    enum Suit{
        heart,spade,club,diamond
    }
    private String[] dic = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    private String valueC;// string value
    private int valueN;// numeric value
    private int valueBJ;//blackjack value: A is -1；J,Q,K is 10
    private Suit suit;


    public Card(int value,char suit){// use number and suit to create a Card
        this.valueN = value;
        this.valueC = dic[value-1];
        switch (value){
            case 1:
                this.valueBJ = -1;
                break;
            case 11:
            case 12:
            case 13:
                this.valueBJ = 10;
                break;
            default:
                this.valueBJ = value;
        }
        switch (suit){
            case 'H':
            case 'h':
                this.suit = Suit.heart;
                break;
            case 'D':
            case 'd':
                this.suit = Suit.diamond;
                break;
            case 'C':
            case 'c':
                this.suit = Suit.club;
                break;
            case 'S':
            case 's':
                this.suit = Suit.spade;
                break;
            default:
                break;
        }
    }

    public int getValueBJ() {
        return valueBJ;
    }
    public int getValueN(){
        return  valueN;
    }
    public String getValueC(){
        return valueC;
    }
    public String getSuit(){
        return suit.toString();
    }

}
