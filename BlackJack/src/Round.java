public class Round {
    private House house;
    private Player player;
    private Deck deck;
    private Panel panel;
    private char status; //g for going; b for blackjack; w for win; l for lose; d for draw
    private int bet;
    private int reward;

    Round(House house, Player player, Deck deck,Panel panel){
        this.house = house;
        this.player = player;
        this.deck = deck;
        this.panel = panel;
        this.status = 'g';
        this.bet = 0;
        this.reward = 0;
    }

    public void init(){
        System.out.println("--------------------new round--------------------");
        deck.shuffle();
        house.clearCards();
        player.clearCards();
        status = 'g';
        bet = 0;
        reward = 0;
        System.out.println("your wallet: "+player.getMoney());
    }

    public void betStage(){
        int tmp;
        while(true){
            tmp= panel.bet();
            if(player.hasBet(tmp)){
                break;
            }else{
                System.out.println("You can't afford your bet! Try again!");
            }
        }
        bet = tmp;
        player.loseMoney(bet);
        System.out.println("Your wallet: "+player.getMoney()+", your bet: "+bet);
    }

    public void first2Stage(){
        if(deck.hasNext()){
            player.addCard(deck.nextCard());
        }
        if(deck.hasNext()){
            house.addCard(deck.nextCard());
        }
        if(deck.hasNext()){
            player.addCard(deck.nextCard());
        }
        if(deck.hasNext()){
            house.addCard(deck.nextCard());
        }

        if(player.isBJ() && house.isBJ()){
            status = 'd';
        }else if(player.isBJ()){
            status = 'b';
        }else if(house.isBJ()){
            status = 'l';
        }

        System.out.println("House\t");//
        house.getHand().showHouseHand();//
        System.out.println("Your\t");//
        player.getHand().showHand();//
    }

    public void playerStage(){
        boolean first = true;
        boolean going = true;
        char op;
        while(status == 'g' && going){
            if(first){
                op = panel.operation1();
                first = false;
            }else{
                op = panel.operation2();
            }
            switch (op){
                case 'd':
                    if(!player.hasBet(bet)){
                        System.out.println("you don't have enough money!");
                        break;
                    }
                    player.loseMoney(bet);
                    bet+=bet;
                    if(deck.hasNext()){
                        player.addCard(deck.nextCard());
                        player.getHand().showHand();//
                    }
                    going = false;
                    break;
                case 's':
                    going = false;
                    break;
                case 'h':
                    if(deck.hasNext()){
                        player.addCard(deck.nextCard());
                        player.getHand().showHand();//
                    }
                    break;
            }
            if(player.isLose()){
                status = 'l';
            }
        }
    }

    public void houseStage(){
        while(status=='g' && !house.isFinished()){
            if(deck.hasNext()){
                house.addCard(deck.nextCard());
                house.getHand().showHouseHand();//
                if(house.isLose()){
                    status = 'w';
                }
            }
        }
    }

    public void finalStage(){
        System.out.println("----------------------final----------------------");
        System.out.println("House:");
        house.getHand().showHand();
        System.out.println("Player: ");
        player.getHand().showHand();
        if(status == 'g'){
            if(player.getHandValue() > house.getHandValue()){
                status = 'w';
            }else if(player.getHandValue() < house.getHandValue()){
                status = 'l';
            }else{
                status = 'd';
            }
        }
        switch (status){
            case 'b':
                System.out.println("-------------Black Jack-----------");
                reward = (int) (bet*1.5 + bet);
                break;
            case 'w':
                System.out.println("-------------Win-----------");
                reward = bet+ bet;
                break;
            case 'l':
                System.out.println("-------------Lose-----------");
                reward = 0;
                break;
            case 'd':
                System.out.println("-------------Draw-----------");
                reward = bet;
                break;
        }
        player.winMoney(reward);
        System.out.println("your wallet: "+player.getMoney());
    }
}
