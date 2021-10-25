import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    private JFrame frame;
    private JPanel panel;//panel由top和main组成
    private JPanel top,body;//main由p1,p2,p3
    private CardLayout cardLayout;
    private JPanel p1,p2,p3,p4;//p1，开始界面;p2,下注页面;p3,玩家操作页面
    private JButton btn_play,btn_deal,btn_stand,btn_double,btn_hit;
    private JTextField tf_bet;
    private Label wallet,bet,h_value,p_value,result,result_h,result_p;
    private JLabel[] hCards,pCards;
    private Font font1,font2;//font1是通用字体,font2是游戏名字

    volatile private char op='z';
    private String input_bet;

    GUI(){
        init();
    }

    public void init(){
        font1 = new Font("Arial",Font.PLAIN,26);//通用字体
        font2 = new Font("Arial",Font.BOLD,64);//游戏名称

        frame = new JFrame("BlackJack");
        frame.setBounds(200,200,800,600);
        panel = new JPanel();
        BoxLayout bly = new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(bly);
        top();
        body();
        panel.add(top);
        panel.add(body);
        //add panel to panels
        frame.add(panel);

        myEvent();

        cardLayout.show(body,"p1");//不一定在这里调用
//        frame.setVisible(true);//不一定在这里调用
    }
    public void top(){//顶部深灰栏
        top = new JPanel(new FlowLayout(5));
        top.setBackground(Color.LIGHT_GRAY);

        Label l1 = new Label("Wallet:");
        l1.setFont(font1);
        wallet = new Label("      0");
        wallet.setFont(font1);
//        wallet.setText("  1000");//与后端交互
        wallet.setAlignment(Label.RIGHT);

        Label l2 = new Label("Bet: ");
        l2.setFont(font1);
        bet = new Label("      0");//修改label的值需要提前占位，否则当label值位数增多会出现..
        bet.setFont(font1);
//        bet.setText("     0");//与后端交互
        bet.setAlignment(Label.RIGHT);

        top.add(Box.createHorizontalStrut(10));
        top.add(l1);
        top.add(wallet);
        top.add(Box.createHorizontalStrut(470));
        top.add(l2);
        top.add(bet);
    }
    public void body(){//主体页面，包括p1，p2，p3
        cardLayout = new CardLayout();
        body = new JPanel(cardLayout);

        p1();
        p2();
        p3();
        p4();

        body.add(p1,"p1");
        body.add(p2,"p2");
        body.add(p3,"p3");
        body.add(p4,"p4");
    }
    public void p1(){//开始页面
        p1 = new JPanel();
//        p1.setBackground(Color.YELLOW);
        Box b1 = Box.createVerticalBox();
        Label game = new Label("BLACK JACK");
        game.setFont(font2);
//        game.setSize(new Dimension(400,400));
        game.setAlignment(Label.CENTER);

        btn_play = new JButton("PLAY");
        btn_play.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_play.setFont(font1);
        b1.add(Box.createVerticalStrut(150));
        b1.add(game);
        b1.add(Box.createVerticalStrut(100));
        b1.add(btn_play);
        p1.add(b1);
    }
    public void p2(){//下注页面
        p2 = new JPanel();
//        p2.setBackground(Color.RED);
        Box b2 = Box.createVerticalBox();
//        b2.setAlignmentX(Component.CENTER_ALIGNMENT);

        Label bet_info1 = new Label("Please Enter Your Bet");
        bet_info1.setFont(new Font("Arial",Font.PLAIN,32));
        bet_info1.setAlignment(Label.CENTER);
        Label bet_info2 = new Label("It must be a multiple of 10 and greater than 0.");
//        bet_info2.setFont(new Font("Arial",Font.PLAIN,16));
        bet_info2.setAlignment(Label.CENTER);
        tf_bet = new JTextField(10);
        tf_bet.setFont(font1);

        btn_deal = new JButton("DEAL");
        btn_deal.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_deal.setFont(font1);
        b2.add(Box.createVerticalStrut(150));
        b2.add(bet_info1);
        b2.add(bet_info2);
        b2.add(Box.createVerticalStrut(50));
        b2.add(tf_bet);
        b2.add(Box.createVerticalStrut(50));
        b2.add(btn_deal);

        p2.add(b2);
    }
    public void p3(){
        p3 = new JPanel();
//        p3.setBackground(Color.cyan);

        BoxLayout bly = new BoxLayout(p3,BoxLayout.Y_AXIS);
        p3.setLayout(bly);

        JPanel box3_1 = new JPanel(new FlowLayout(FlowLayout.LEADING,20,30));
//        box3_1.setBackground(Color.yellow);
        JPanel box3_2 = new JPanel(new FlowLayout(FlowLayout.LEADING,20,0));
//        box3_2.setBackground(Color.green);
        Box box3_3 = Box.createHorizontalBox();

        Box hv = Box.createVerticalBox();
        Label l3 = new Label("House Value");
        h_value = new Label(" 0");
        h_value.setAlignment(Label.CENTER);
        hv.add(l3);
        hv.add(Box.createVerticalStrut(10));
        hv.add(h_value);
        box3_1.add(hv);

        hCards = new JLabel[5];
        for(int i=0;i<5;i++){
            hCards[i] = new JLabel("   ");
            hCards[i].setPreferredSize(new Dimension(100,150));
            hCards[i].setHorizontalAlignment(SwingConstants.CENTER);
            hCards[i].setForeground(Color.BLACK);
            hCards[i].setBackground(Color.GRAY);
            hCards[i].setOpaque(true);
            hCards[i].setVisible(false);
            box3_1.add(hCards[i]);
        }

        Box pv = Box.createVerticalBox();
        Label l4 = new Label("Player Value");
        p_value = new Label(" 0");
        p_value.setAlignment(Label.CENTER);
        pv.add(l4);
        pv.add(Box.createVerticalStrut(10));
        pv.add(p_value);
        box3_2.add(pv);

        pCards = new JLabel[5];
        for(int i=0;i<5;i++){
            pCards[i] = new JLabel("   ");
            pCards[i].setPreferredSize(new Dimension(100,150));
            pCards[i].setHorizontalAlignment(SwingConstants.CENTER);
            pCards[i].setForeground(Color.BLACK);
            pCards[i].setBackground(Color.GRAY);
            pCards[i].setOpaque(true);
            pCards[i].setVisible(false);
            box3_2.add(pCards[i]);
        }


        btn_stand = new JButton("STAND");
        btn_stand.setFont(font1);
        btn_double = new JButton("DOUBLE");
//        btn_double.setSize(100,40);
        btn_double.setFont(font1);
        btn_hit = new JButton("   HIT   ");
//        btn_hit.setSize(100,40);
        btn_hit.setFont(font1);
        box3_3.add(btn_stand);
        box3_3.add(Box.createHorizontalStrut(100));
        box3_3.add(btn_double);
        box3_3.add(Box.createHorizontalStrut(100));
        box3_3.add(btn_hit);

        p3.add(box3_1);
        p3.add(box3_2);
        p3.add(box3_3);
    }
    public void p4(){//结束页面
        p4 = new JPanel();
//        p1.setBackground(Color.YELLOW);
        Box b1 = Box.createVerticalBox();
        result = new Label("            ");
        result.setFont(font2);
//        game.setSize(new Dimension(400,400));
        result.setAlignment(Label.CENTER);

        result_h = new Label("                            ");
        result_p = new Label("                            ");
//        setP4Label();

        b1.add(Box.createVerticalStrut(150));
        b1.add(result);
        b1.add(Box.createVerticalStrut(100));
        b1.add(result_h);
        b1.add(Box.createVerticalStrut(40));
        b1.add(result_p);
        p4.add(b1);
    }

    public void myEvent(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn_play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("p");
                op = 'p';
//                cardLayout.show(body,"p2");//不一定在这里调用
//                changeBody(2);//不一定在这里调用
            }
        });
        btn_deal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op = 'd';
                input_bet = tf_bet.getText();
//                cardLayout.show(body,"p3");//不一定在这里调用
//                changeBody(3);//不一定在这里调用
//                btn_double.setVisible(true);//不在这里调用
            }
        });
        btn_stand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op = 's';
                btn_double.setVisible(false);
//                cardLayout.show(body,"p1");
//                changeBody(1);//不一定在这里调用
            }
        });
        btn_double.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op = 'd';
                btn_double.setVisible(false);
            }
        });
        btn_hit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op = 'h';
                btn_double.setVisible(false);
//                cardLayout.show(body,"p1");
//                changeBody(1);//不一定在这里调用
            }
        });
    }

    //一些设置
    public void setP4Label(){
        String house = "house   "+h_value.getText()+", cards:     ";
        for(JLabel c:hCards){
            house+=c.getText()+" ";
        }
        result_h.setText(house);

        String player = "player   "+p_value.getText()+", cards:     ";
        for(JLabel c:pCards){
            player+=c.getText()+" ";
        }
        result_p.setText(player);
    }
    public void reset(int wallet, int bet){
        //top
        String str1="";
        int len1 = String.valueOf(wallet).length();
        if(len1<6){
            for(int i=0;i<7-len1;i++)
                str1+=" ";
        }
        str1+=String.valueOf(wallet);
        this.wallet.setText(String.valueOf(wallet));

        String str2="";
        int len2 = String.valueOf(bet).length();
        if(len2<6){
            for(int i=0;i<7-len2;i++)
                str2+=" ";
        }
        str2+=String.valueOf(bet);
        this.wallet.setText(str1);
        this.bet.setText(str2);
        //p2
        tf_bet.setText("");
        //p3
        h_value.setText(" 0");
        p_value.setText(" 0");
        for(JLabel c: hCards){
            c.setText(" ");
            c.setVisible(false);
            c.setBackground(Color.GRAY);
        }
        for(JLabel c: pCards){
            c.setText(" ");
            c.setVisible(false);
        }
        btn_double.setVisible(true);
        //p4
        result.setText("");
        result_h.setText("");
        result_p.setText("");
    }


    //对外调用
    public void showGUI(){
        frame.setVisible(true);
    }
    public void setWallet(int money){
        wallet.setText(String.valueOf(money));
    }
    public void setBet(int money){
        bet.setText(String.valueOf(money));
    }
    public void changeBody(int idx){
        switch (idx){
            case 1:
                cardLayout.show(body,"p1");
                break;
            case 2:
                cardLayout.show(body,"p2");
                break;
            case 3:
//                btn_double.setVisible(true);
                cardLayout.show(body,"p3");
                break;
            case 4:
                cardLayout.show(body,"p4");
                break;
        }
    }
    public char getUserOp(){
        return op;
    }
    public String getUserInput(){
        return input_bet;
    }
    public void clearUserOp(){
        tf_bet.setText("");
        op = 'z';
    }
    public void setH_value(int value){
        h_value.setText(String.valueOf(value));
    }
    public void setP_value(int value){
        p_value.setText(String.valueOf(value));
    }
    public void setHouseCard(int idx, String value) throws InterruptedException {//show=false,值为***
        Thread.currentThread().sleep(400);
        hCards[idx].setText(value);
        if(idx!=0)
            hCards[idx].setBackground(Color.BLACK);

        hCards[idx].setVisible(true);
    }
    public void showHouseCard() throws InterruptedException {
        Thread.currentThread().sleep(500);
        for (JLabel c: hCards)
            c.setBackground(Color.GRAY);
//        hCards[idx].setText(value);
    }
    public void setPlayerCard(int idx, String value) throws InterruptedException {
        Thread.currentThread().sleep(400);
        pCards[idx].setText(value);
        pCards[idx].setVisible(true);
    }
    public void setResult(char status){
        setP4Label();
        switch (status){
            case 'w':
                result.setText("Win");
                break;
            case 'l':
                result.setText("Lose");
                break;
            case 'd':
                result.setText("Draw");
                break;
            case 'b':
                result.setText("BlackJack");
                break;
        }
    }
    public void showMessage(String title, String msg){
        JOptionPane.showMessageDialog(frame, msg, title,JOptionPane.WARNING_MESSAGE);
    }
    public void showExitMessage(String title,String msg){
        JOptionPane.showMessageDialog(frame, msg, title,JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }





//    public static void main(String[] args) throws InterruptedException {
//
//
//        GUI gui = new GUI();
//        gui.setWallet(1000);
//        gui.showGUI();
//        Thread.currentThread().sleep(1000);
//        gui.changeBody(2);
//        Thread.currentThread().sleep(1000);
//        gui.setBet(50);
//        gui.setWallet(950);
//        gui.changeBody(3);
//        gui.setPlayerCard(0,"A");
//        gui.setP_value(11);
//        gui.setHouseCard(0,"J");
//        gui.setH_value(10);
//        gui.setPlayerCard(1,"2");
//        gui.setP_value(13);
//        gui.setHouseCard(1,"5");
//
//        gui.showHouseCard();
//        gui.setH_value(15);
//
//        Thread.currentThread().sleep(1000);
//        gui.setResult('w');
//        gui.changeBody(4);
//
//        Thread.currentThread().sleep(1000);
//        gui.reset(1000,0);
//        gui.changeBody(2);
//
//    }
}
