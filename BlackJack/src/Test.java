import sun.lwawt.macosx.CSystemTray;

import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
//        Card.test();
//        Deck.test();

//        int[] array=new int[52];
//        for(int i=0;i<52;i++){
//            array[i]=i;
//        }
//
//        Random rand = new Random();
//        for(int i=0;i<100;i++){
////            System.out.println(rand.nextInt(52));
//            int index = rand.nextInt(52);
//            swap(array,0,index);
//        }
//
//
//        for (int i=0;i<52;i++){
//            System.out.print(array[i]+" ");
//        }
//
//        Random rand = new Random();
//        int a = 60+ rand.nextInt(40);
//
//        Deck d = new Deck();
//        d.shuffle();
//        System.out.println();
//        d.shuffle();

//        String[] a={"dfsf","sdad","asdsad"};
//        for(String i : a){
//            System.out.println(i);
//        }
//        Hand.test();

//        Panel p = new Panel();
//        System.out.println(p.operation1());
//        System.out.println(p.operation2());
        int a = 0;
        int b =2;
        char res = 'r';
        if(a==1&&b==2){
            res = 'a';
        }else if(a==1){
            res = 'b';
        }else if(b==2){
            res = 'c';
        }
        System.out.println(res);





    }

    private static void swap(int[] a,int i, int i1) {
        int temp = a[i];
        a[i] = a[i1];
        a[i1] = temp;
    }


}
