import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class Menu extends JPanel {
    JLabel order_label;//현재 바둑돌이 들어가는 label이다.
    JButton restart;//재시작하는 레이블이다.
    public Menu(){
        setSize(230,400);
        setBackground(new Color(255, 228, 121, 255));
        setBorder(new LineBorder(Color.black,3));
        setLayout(null);
        //순서를 보여주는 곳 만들기
        order();
        //재시작버튼
        restart_button();
        //시간초
        setVisible(true);
    }
    public void order(){
        JLabel label = new JLabel("순  서");
        label.setForeground(new Color(9, 9, 9));
        label.setFont(new Font("맑은 고딕",Font.BOLD,50));
        label.setBorder(new BasicBorders.MenuBarBorder(new Color(9, 9, 9),new Color(255, 255, 255)));
        add(label);
        label.setBounds(40,30,150,50);

    }
    public void restart_button(){
        restart = new JButton("재시작");
        restart.setFont(new Font("맑은 고딕",Font.BOLD,35));
        restart.setBackground(new Color(200,255,20));
        add(restart);
        restart.setBounds(35,250,150,50);
    }//재시작 버튼을 만드는 함수
}
