public class Round {
    private House house;
    private Player player;
    private Deck deck;
    private Panel panel;
    private char status; //g for going; b for blackjack; w for win; l for lose; d for draw
    private int bet;
    private int reward;
    private GUI gui;

    Round(House house, Player player, Deck deck,Panel panel,GUI gui){
        this.house = house;
        this.player = player;
        this.deck = deck;
        this.panel = panel;
        this.status = 'g';
        this.bet = 0;
        this.reward = 0;
        this.gui = gui;
    }

    public void init(){
//        System.out.println("--------------------new round--------------------");
        deck.shuffle();
        house.clearCards();
        player.clearCards();
        status = 'g';
        bet = 0;
        reward = 0;
        gui.reset(player.getMoney(),bet);
//        System.out.println("your wallet: "+player.getMoney());
    }

    public void betStage(){
        gui.changeBody(2);
        int tmp;
        while(true){
            tmp= panel.bet();
            if(player.hasBet(tmp)){
                break;
            }else{
                gui.showMessage("Wrong Bet","You can't afford your bet! Try again!");
//                System.out.println("You can't afford your bet! Try again!");
            }
        }
        bet = tmp;
        player.loseMoney(bet);
//        System.out.println("Your wallet: "+player.getMoney()+", your bet: "+bet);
        gui.setWallet(player.getMoney());
        gui.setBet(bet);
    }

    public void first2Stage() throws InterruptedException {
        gui.changeBody(3);
        if(deck.hasNext()){
            Card c = deck.nextCard();
            player.addCard(c);
            gui.setPlayerCard(0,c.getValueC());
            gui.setP_value(player.getHandValue());
        }
        if(deck.hasNext()){
            Card c = deck.nextCard();
            house.addCard(c);
            gui.setHouseCard(0,c.getValueC());
            gui.setH_value(house.getHandValue());
        }
        if(deck.hasNext()){
            Card c = deck.nextCard();
            player.addCard(c);
            gui.setPlayerCard(1, c.getValueC());
            gui.setP_value(player.getHandValue());
        }
        if(deck.hasNext()){
            Card c = deck.nextCard();
            house.addCard(c);
            gui.setHouseCard(1,c.getValueC());
        }

        if(player.isBJ() && house.isBJ()){
            status = 'd';
        }else if(player.isBJ()){
            status = 'b';
        }else if(house.isBJ()){
            status = 'l';
        }

//        System.out.println("House\t");//
//        house.getHand().showHouseHand();//
//        System.out.println("Your\t");//
//        player.getHand().showHand();//
    }

    public void playerStage() throws InterruptedException {
//        boolean first = true;
        int times = 0;
        boolean going = true;
        char op;
        while(status == 'g' && going && times<3){
            if(times==0){
                op = panel.operation1();
//                first = false;
            }else{
                op = panel.operation2();
            }
            switch (op){
                case 'd':
                    if(!player.hasBet(bet)){
//                        System.out.println("you don't have enough money!");
                        gui.showMessage("Wrong Bet","You don't have enough money!");
                        break;
                    }
                    player.loseMoney(bet);
                    bet+=bet;
                    gui.setBet(bet);
                    gui.setWallet(player.getMoney());
                    if(deck.hasNext()){
                        Card c = deck.nextCard();
                        player.addCard(c);
                        gui.setPlayerCard(player.getHand().getNum()-1,c.getValueC());
                        gui.setP_value(player.getHandValue());
//                        player.getHand().showHand();//
                    }
                    going = false;
                    break;
                case 's':
                    going = false;
                    break;
                case 'h':
                    if(deck.hasNext()){
                        Card c = deck.nextCard();
                        player.addCard(c);
                        gui.setPlayerCard(player.getHand().getNum()-1,c.getValueC());
                        gui.setP_value(player.getHandValue());
//                        player.getHand().showHand();//
                    }
                    break;
            }
            if(player.isLose()){
                status = 'l';
            }
            times++;
        }
    }

    public void houseStage() throws InterruptedException {
        int times=0;
        while(status=='g' && !house.isFinished()){
            if(deck.hasNext()){
                Card c= deck.nextCard();
                house.addCard(c);
                gui.setHouseCard(house.getHand().getNum()-1,c.getValueC());
//                house.getHand().showHouseHand();//
                if(house.isLose()){
                    status = 'w';
                }
            }
        }
    }

    public void finalStage() throws InterruptedException {
//        System.out.println("----------------------final----------------------");
//        System.out.println("House:");
        gui.showHouseCard();
        gui.setH_value(house.getHandValue());
//        house.getHand().showHand();
//        System.out.println("Player: ");
//        player.getHand().showHand();
        if(status == 'g'){
            if(player.getHandValue() > house.getHandValue()){
                status = 'w';
            }else if(player.getHandValue() < house.getHandValue()){
                status = 'l';
            }else{
                status = 'd';
            }
        }
        Thread.currentThread().sleep(500);
        gui.changeBody(4);
        switch (status){
            case 'b':
//                System.out.println("-------------Black Jack-----------");
                gui.setResult('b');
                reward = (int) (bet*1.5 + bet);
                break;
            case 'w':
//                System.out.println("-------------Win-----------");
                gui.setResult('w');
                reward = bet+ bet;
                break;
            case 'l':
//                System.out.println("-------------Lose-----------");
                gui.setResult('l');
                reward = 0;
                break;
            case 'd':
//                System.out.println("-------------Draw-----------");
                gui.setResult('d');
                reward = bet;
                break;
        }
        player.winMoney(reward);
        gui.setWallet(player.getMoney());
//        System.out.println("your wallet: "+player.getMoney());
        Thread.currentThread().sleep(5000);
    }

}
