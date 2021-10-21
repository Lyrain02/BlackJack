import java.util.Random;

public class Deck {
    private int totalNum;
    private int current; //the index of sequence mark which should be sent now
    private Card[] poker; //store 52 cards
    private int[] sequence; //a sequence of cards


    // create a poker of 52 cards, and a sequence of {0,1,2,...,51}
    // sequence is used to determine the sending card sequence
    Deck(){
        totalNum = 0;
        current = 0;
        poker = new Card[52];
        for(int i=1;i<=13;i++){
            Card c1 = new Card(i,'H');
            poker[totalNum++]=c1;
            Card c2 = new Card(i,'D');
            poker[totalNum++]=c2;
            Card c3 = new Card(i,'S');
            poker[totalNum++]=c3;
            Card c4 = new Card(i,'C');
            poker[totalNum++]=c4;
        }
        sequence = new int[52];
        for (int i=0;i<52;i++){
            sequence[i]=i;
        }
    }

    // shuffle sequence instead of poker
    public void shuffle(){
        current = 0;
        Random rand = new Random();
        int times = 60 + rand.nextInt(40);
        for(int i=0;i<times;i++){
            int index = rand.nextInt(52);
            swap(sequence,0,index);
        }
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public Boolean hasNext(){
        if(current>=52)
            return false;
        return true;
    }

    // send a random card using sequence
    public Card nextCard(){
        return poker[sequence[current++]];
    }


    //only for test
    public static void test(){
        Deck d = new Deck();
        d.shuffle();
        for(int i : d.sequence){
            System.out.print(i+" ");
        }
        System.out.println();
        Card c1 = d.nextCard();
        System.out.println("current: "+d.current+"value: "+c1.getValueC()+",suit: "+c1.getSuit());
        Card c2 = d.nextCard();
        System.out.println("current: "+d.current+"value: "+c2.getValueC()+",suit: "+c2.getSuit());
        d.shuffle();
        for(int i : d.sequence){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("current: "+d.current);
    }


}
