import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class Screen extends JFrame implements MouseListener {
    int player1_num=0;//player1의 돌의 수
    int player2_num=0;//player2의 돌의 수
    Score score_panel= new Score();
    Board board = new Board();
    public static String user1;//사용자1의 이름을 저장
    public static String user2;//사용자2의 이름을 저장
    Timer timer;//타이머 클래스를 사용
    int second;//시간을 표시
    Vector <Integer> turn_check;//turn이 바뀌는지를 확인(타이머를 초기화하기 위해)
    int sum;
    public static boolean end_check = false;
    public Screen(){
        setTitle("오델로게임");
        setBounds(400,10,1200,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(181, 230, 255)); //배경색 설정
        setLayout(null);

        //점수판 패널을 넣는다.
        score_panel.setLocation(50,10);
        add(score_panel);

        //장기가 들어가는 패널을 넣는다.
        board.setLocation(100,130);
        add(board);
        board.addMouseListener(this);

        //메뉴판이 들어가는 패널을 넣는다.
        Menu menu = new Menu();
        add(menu);
        menu.setLocation(930,180);
        menu.restart.addMouseListener(this);
        setVisible(true);
    }
    public Screen(String user1, String user2, int mode, int sec){
        this();
        score_panel.player1_label.setText(user1+" : ");
        score_panel.player2_label.setText(user2+" : ");
        score_panel.repaint();
        board.game_mode=mode;
        board.repaint();
        //타이머를 출력함
        second = sec;
        if(sec!=0) {
            timer = new Timer(sec);
            Thread thread = new Thread(timer);
            thread.start();
            add(timer);
            timer.setBackground(Color.YELLOW);
            timer.setBounds(970, 620, 150, 100);
            turn_check = new Vector<Integer>();
            turn_check.add(4);//처음 돌이 4이기 때문에 넣어둔다.
        }
    }
    public void paint(Graphics g){
        super.paint(g);
        if(board.turn==1)//빨간색 턴이면
            g.setColor(new Color(255, 0, 0, 255));
        if(board.turn==-1)
            g.setColor(new Color(0, 0, 255, 255));//파란색 턴이면
        g.fillOval(1000, 350, 100, 100);
    }//순서의 원을 그려줌
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.toString().contains("JButton")){
            for(int i=0;i<board.draw_array.length;i++){
                for(int k=0;k<board.draw_array[i].length;k++){
                    if((i==3&k==3)||(i==4&k==4))
                        board.draw_array[i][k]=1;//player1의 돌로 초기화
                    else if((i==3&k==4)||(i==4&k==3))
                        board.draw_array[i][k]=-1;//player2의 돌로 초기화
                    else
                        board.draw_array[i][k]=0;
                }
            }//원이 그려질 위치를 초기화해준다.
            if(end_check){
                dispose();
                new GetUser();
            }
            if(second!=0)
                timer.second=second+1;
            board.player1= board.player2=2;
            board.turn=1;
        }//재시작버튼을 누르는 경우
        score_panel.score1_lable.setText(String.valueOf(board.player1));
        score_panel.score2_lable.setText(String.valueOf(board.player2));
        repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.toString().contains("JButton")) return;
        if (board.endCheck){
            Victory victory;//객체를 생성
            if(board.player1> board.player2)//사용자1이 이기는 경우
                victory= new Victory(user1);
            else if(board.player1< board.player2)//사용자2가 이기는 경우
                victory = new Victory(user2);
            else//무승부인 경우
                victory = new Victory("무승부");
            end_check=true;
        }//게임이 종료되는 조건이라면
        sum= board.player1+ board.player2;
        if(second!=0 && turn_check.lastElement()!=sum ){
            turn_check.clear();
            turn_check.add(sum);
            timer.second=second+1;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
