import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {
    JLabel score1_lable;
    JLabel score2_lable;
    JLabel player1_label;
    JLabel player2_label;
    int player1_score= 2;

    int  player2_score=2;
    public Score(){
        setBackground(new Color(24, 47, 119, 229));
        setSize(1100,100);
        setLayout(null);
        //player와 점수를 추가
        player1_label= player("");
        add(player1_label);
        player1_label.setBounds(150,10,300,80);
        score1_lable = score0fPlayer(String.valueOf(player1_score));//player1의 점수를 추가
        add(score1_lable);
        score1_lable.setBounds(400,10,150,80);
        player2_label= player("");
        add(player2_label);
        player2_label.setBounds(700,10,300,80);
        score2_lable = score0fPlayer(String.valueOf(player2_score));//player2의 점수를 추가
        add(score2_lable);
        score2_lable.setBounds(950,10,150,80);
        repaint();
    }//점수판을 만드는 패널
    public void paint(Graphics g){
        super.paint(g);
    //바둑알이 들어가는 사각형을 그린다.
        g.setColor(new Color(255, 255, 255, 255));
        g.fillRect(50,10,80,80);
        g.fillRect(595,10,80,80);
        //사용자의 바둑알을 그린다.
        g.setColor(new Color(255, 0, 0, 255));
        g.fillOval(55,13,70,70);
        g.setColor(new Color(0, 0, 255, 255));
        g.fillOval(600,13,70,70);

    }
    public JLabel player(String player){
        JLabel label_play= new JLabel(player);
        label_play.setSize(200,100);
        label_play.setForeground(new Color(255, 255, 255));
        label_play.setFont(new Font("맑은 고딕",Font.BOLD,60));
        return label_play;
    }//플레이어를 나타냄
    public JLabel score0fPlayer(String score){
        JLabel score_label= new JLabel(score);
        score_label.setSize(200,100);
        score_label.setForeground(new Color(255, 255, 255));
        score_label.setFont(new Font("맑은 고딕",Font.BOLD,60));
        return score_label;
    }//점수를 띄워줌
}
