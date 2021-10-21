import java.util.Scanner;

public class Panel {

    public boolean play(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("start game, enter p: ");
            String op = scan.next();
            if(op.equals("p"))
                break;
        }
        return true;
    }

    public int bet(){
        int bet=0;
        while(true){
            System.out.println("please enter your bet: (it must be a multiple of 10 and greater than 0)");
            try{
                Scanner scanner = new Scanner(System.in);
                bet = scanner.nextInt();
            }catch (Exception e){
                System.out.println("please enter a number!");
            }
            if(bet>0 &&  bet % 10 == 0)
                break;
        }
        return bet;
    }

    public char operation1(){
        Scanner scan = new Scanner(System.in);
        String op;
        while(true){
            System.out.println("please enter your operation: h for hit, s for stand, d for double: ");
            op = scan.next();
            if(op.equals("h")||op.equals("s")||op.equals("d"))
                break;

        }
        return op.charAt(0);
    }

    public char operation2(){
        Scanner scan = new Scanner(System.in);
        String op;
        while(true){
            System.out.println("please enter your operation: h for hit, s for stand: ");
            op = scan.next();
            if(op.equals("h")||op.equals("s"))
                break;

        }
        return op.charAt(0);
    }

}
