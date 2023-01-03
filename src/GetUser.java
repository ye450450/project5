import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetUser extends JFrame implements ActionListener {
    JTextField user1_text;//사용자1이름 설정
    JTextField user2_text;//사용자2이름 설정
    JRadioButton mode[];//게임모드를 설정함
    JRadioButton time_mode[];//시간모드를 설정함

    public GetUser(){
        setTitle("게임설정하기");
        setBounds(500,100,600,600);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 255, 255)); //배경색 흰색
        user();//사용자설정하는 부분
        gamemode();
        inputTime();
        submit();//제출하는 버튼

        setVisible(true);
    }

    public void user(){
        JLabel text= new JLabel("게임 설정하기");
        text.setFont(new Font("맑은 고딕",Font.BOLD,30));
        add(text);
        text.setBounds(200,30,250,100);

        JLabel user1= new JLabel("사용자1 이름설정:");
        user1.setFont(new Font("맑은 고딕",Font.BOLD,20));
        add(user1);
        user1.setBounds(100,130,200,50);
        JLabel user2= new JLabel("사용자2 이름설정:");
        user2.setFont(new Font("맑은 고딕",Font.BOLD,20));
        add(user2);
        user2.setBounds(100,180,250,50);

        user1_text= new JTextField();
        user1_text.setFont(new Font("맑은 고딕",Font.BOLD,20));
        add(user1_text);
        user1_text.setBounds(280,130,130,50);
        user2_text= new JTextField();
        user2_text.setFont(new Font("맑은 고딕",Font.BOLD,20));
        add(user2_text);
        user2_text.setBounds(280,180,130,50);
    }//사용자 이름을 설정하는 부분
    public void gamemode(){
        JLabel game_lable= new JLabel("게임 모드:");
        game_lable.setFont(new Font("맑은 고딕",Font.BOLD,20));
        add(game_lable);
        game_lable.setBounds(170,240,200,50);
        mode = new JRadioButton[2];
        String game_mode[] ={"쉬움", "보통"};
        ButtonGroup group = new ButtonGroup();
        for(int i=0; i<mode.length; i++){
            mode[i] = new JRadioButton(game_mode[i]);
            group.add(mode[i]);
            add(mode[i]);
            mode[i].setBackground(Color.white);
            mode[i].setFont(new Font("맑은 고딕",Font.BOLD,20));
        }
        mode[0].setSelected(true);
        mode[1].setSelected(false);
        mode[0].setBounds(280,250,80,30);
        mode[1].setBounds(360,250,80,30);
    }
    public void submit(){
        JButton confirm = new JButton("게임하기");
        confirm.setFont(new Font("맑은 고딕",Font.BOLD,15));
        confirm.setBackground(Color.yellow);
        add(confirm);
        confirm.setBounds(220,400,150,50);
        confirm.addActionListener(this);
    }
    public void inputTime(){
        JLabel time_lable= new JLabel("시간 설정:");
        time_lable.setFont(new Font("맑은 고딕",Font.BOLD,20));
        add(time_lable);
        time_lable.setBounds(170,280,200,50);
        time_mode = new JRadioButton[4];
        String game_mode[] ={"없음", "15","30","45"};
        ButtonGroup group = new ButtonGroup();
        for(int i=0; i<time_mode.length; i++){
            time_mode[i] = new JRadioButton(game_mode[i]);
            group.add(time_mode[i]);
            add(time_mode[i]);
            time_mode[i].setBackground(Color.white);
            time_mode[i].setFont(new Font("맑은 고딕",Font.BOLD,15));
        }
        time_mode[0].setSelected(true);
        time_mode[1].setSelected(false);
        time_mode[2].setSelected(false);
        time_mode[3].setSelected(false);
        time_mode[0].setBounds(280,290,70,30);
        time_mode[1].setBounds(350,290,50,30);
        time_mode[2].setBounds(400,290,50,30);
        time_mode[3].setBounds(450,290,50,30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("게임하기")){
            int type=0;
            int second=0;
            if(mode[0].isSelected()) type=1;
            else type=2;
            if(time_mode[1].isSelected()) second=15;
            else if(time_mode[2].isSelected()) second=30;
            else if(time_mode[3].isSelected()) second=45;
            Screen screen= new Screen(user1_text.getText(),user2_text.getText(),type,second);
            screen.user1=user1_text.getText();
            screen.user2=user2_text.getText();
        }
    }
}
