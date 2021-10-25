import java.util.Scanner;

public class Panel {
    private GUI gui;

    Panel(GUI gui){
        this.gui = gui;
    }

    public boolean play(){
//        Scanner scan = new Scanner(System.in);
        while(gui.getUserOp()!='p'){
//            System.out.println("start game, enter p: ");
        }
        gui.clearUserOp();
        return true;
    }

    public int bet(){
        int bet=0;

        while(true){
//            System.out.println("please enter your bet: (it must be a multiple of 10 and greater than 0)");
            while(gui.getUserOp()!='d') {
            }
            gui.clearUserOp();
            String str = gui.getUserInput();
            if(str.equals("")){
                gui.showMessage("Wrong Bet","You must input something!");
                continue;
            }
            try{
                bet = Integer.parseInt(str);
            }catch (Exception e){
                gui.showMessage("Wrong Bet","Please enter a number!");
                continue;
            }
            if(bet>0 &&  bet % 10 == 0){
                break;
            }else{
                gui.showMessage("Wrong Bet","It must be a multiple of 10 and greater than 0!");
            }
        }
        return bet;
    }

    public char operation1(){
        char op;
        while(true){
//            System.out.println("please enter your operation: h for hit, s for stand, d for double: ");
            op = gui.getUserOp();
            if(op=='h'||op=='s'||op=='d'){
                gui.clearUserOp();
                break;
            }
        }
        return op;
    }

    public char operation2(){
        char op;
        while(true){
//            System.out.println("please enter your operation: h for hit, s for stand: ");
            op = gui.getUserOp();
            if(op=='h'||op=='s'){
                gui.clearUserOp();
                break;
            }
        }
        return op;
    }

}
