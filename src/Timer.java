import javax.swing.*;
import java.awt.*;

public class Timer extends JLabel implements Runnable{
    int second ;
    public Timer(int second) {
        setOpaque(true);
        setBounds(200, 550, 75, 75);
        setForeground(Color.BLUE);
        setText(second + "");
        setFont(new Font("맑은고딕", Font.PLAIN, 50));
        setHorizontalAlignment(JLabel.CENTER);

        this.second = second;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);	// 1초
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (second > 0) {
                second -= 1;		// 1초씩 줄어듦
                setText(second + "");
            } else {
                JOptionPane.showMessageDialog(null,"시간이 종료되어 게임이 끝났습니다.");
                if(Board.turn==1)
                    new Victory(Screen.user2);
                if(Board.turn!=1)
                    new Victory(Screen.user1);
                Screen.end_check=true;
                break;
            }
        }
    }
}
