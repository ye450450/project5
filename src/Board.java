import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
    int [][] draw_array= new int[8][8];
    //어디를 그려야할지 정해준다. (0은 빈공간, 1은 player의 돌, -1는 player2의 돌, 2는 player1가 마우스를 올리면 잠시 들어가는 곳,
    // -2는 player2가 마우스를 올리면 잠시 들어가는 곳, 3은 player1의 돌이 들어갈 수 있는 곳, -3은 player2의 돌이 들어갈 수 있는 곳)
    int turn = 1;//1은 player1의 차례, -1는 player2의 차례
    int turn_mouse= 2;//1은 player1의 차례, -1는 player2의 차례(마우스 커서를 올려놓은 위치)
    int player1=2;//player1의 돌의 수
    int player2=2;//player2의 돌의 수
    int count=0;//그냥 넘기는 턴의 수를 세는 방법이다.(만약 이 수 가 2가 되면 종료된다.)
    boolean endCheck=false;//게임이 종료되는지를 확인하는 변수 (true시 종료)
    int game_mode=0;//1이 쉬운모드, 2가 보통모드이다.
    public Board(){
        setSize(800,800); //800x800으로 사이즈를 정함
        setBorder(new LineBorder(Color.black));//테두리를 검은색으로 해줌
        setBackground(new Color(252, 228, 101));
        for(int i=0;i<draw_array.length;i++){
            for(int k=0;k<draw_array[i].length;k++){
                if((i==3&k==3)||(i==4&k==4))
                    draw_array[i][k]=1;//player1의 돌로 초기화
                else if((i==3&k==4)||(i==4&k==3))
                    draw_array[i][k]=-1;//player2의 돌로 초기화
                else
                    draw_array[i][k]=0;
            }
        }//원이 그려질 위치를 초기화해준다.
        repaint();
        addMouseListener(this);
        addMouseMotionListener(this);
    }//장기가 들어가는 판을 만듬
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //줄 그리기
        for(int i =0;i<8;i++){
            g2.setStroke(new BasicStroke(5));
            g2.drawLine(100+i*100,0,100+i*100,800);
        }
        for(int i =0;i<8;i++){
            g2.drawLine(0,100+i*100,800,100+i*100);
        }
        //원그리기
        for(int i=0;i<draw_array.length;i++){
            for(int k=0;k<draw_array[i].length;k++){
               if(draw_array[i][k]==1) {
                   g2.setColor(new Color(255, 0, 0, 255));
                   g2.fillOval(k*100+5,i*100+5,90,90);
               }
                else if(draw_array[i][k]==2) {
                    g2.setColor(new Color(255, 0, 0, 123));
                    g2.fillOval(k*100+5,i*100+5,90,90);
                   draw_array[i][k]=0;
                }
               else if(draw_array[i][k]==-1) {
                    g2.setColor(new Color(0xFF426DF6, true));
                    g2.fillOval(k*100+5,i*100+5,90,90);
                }
               else if(draw_array[i][k]==-2) {
                    g2.setColor(new Color(0x6B0342EA, true));
                    g2.fillOval(k*100+5,i*100+5,90,90);
                   draw_array[i][k]=0;
                }
               if(game_mode==1) {
               if (draw_array[i][k] == 3) {
                       g2.setColor(new Color(255, 0, 0, 123));
                       g2.fillOval(k * 100 + 5, i * 100 + 5, 90, 90);
                       draw_array[i][k] = 0;
                   } else if (draw_array[i][k] == -3) {
                       g2.setColor(new Color(0x6B0342EA, true));
                       g2.fillOval(k * 100 + 5, i * 100 + 5, 90, 90);
                       draw_array[i][k] = 0;
                   }
               }
            }
        }//원이 그려질 위치를 초기화해준다.

        for(int i=0;i<draw_array.length;i++){
            for(int k=0;k<draw_array[i].length;k++) {
                if(draw_array[i][k]%3==0) draw_array[i][k]=0;
            }
        }//돌이 들어갈 수 있는 곳을 초기화한다.
    }
    public void changeDoll(int y, int x){
        for(int i=-1;i<2;i++){
            for(int k=-1;k<2;k++){
                if(isLineEnd(y+i,x+k)) continue;
                if(draw_array[y+i][x+k]==(turn*-1))
                    change2Doll(y,x,i,k);
            }
        }//주변의 다른 돌이 있는 방향을 살펴본다.
    }//돌주변을 살펴보고 바꾸어주는 함수
    public void change2Doll(int y, int x, int directy, int directx){
        int changex = x+directx;//다른 돌이 있는 x좌표
        int changey = y+directy;//다른 돌이 있는 y좌표
        boolean end_check = false;//흰색배경이나 벽을 만났는지를 확인
        while(true){
            if(isLineEnd(changey,changex)||draw_array[changey][changex]==0) {
                end_check=true;
                break;
            }//흰색배경이나 벽을 만나면 그냥 종료
            else if(draw_array[changey][changex]==turn) break;//같은 돌을 만나도 멈춘다.
            else if(draw_array[changey][changex]==turn*-1){
                changex +=directx;
                changey +=directy;
            }//다른 돌을 만나면 계속된다.
        }
        if(!end_check){
            while(changex!=x ||changey!=y){
                draw_array[changey][changex]=turn;
                changex-=directx;
                changey-=directy;
            }
        }//같은 돌을 만나는 경우
    }//돌이 나아갈 방향을 받으면 간다.
    public boolean isLineEnd(int y, int x){
        return (y<0 || y>draw_array.length-1|| x<0 || x>draw_array.length-1);
    }//라인의 끝인지 확인
    public void count(int [][] dollarray){
        int player1_num=0;
        int player2_num=0;
        for(int i =0; i< dollarray.length;i++){
            for(int k =0; k<dollarray[i].length;k++) {
                if (dollarray[i][k] == 1) player1_num++;
                else if (dollarray[i][k] == -1) player2_num++;
            }
        }
        player1=player1_num;
        player2=player2_num;
    }//돌의 수를 세어주는 함수
    public boolean put_check(int y, int x,int type) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (isLineEnd(y + i, x + j)) continue;
                else if (draw_array[y + i][x + j] == turn * -1) {
                    if(type==2) blank_check(y,x,i,j); //빈공간이 있는지를 확인하는 함수
                    if(type ==1 && same_check(y,x,i,j)) return true;//같은 돌이있는지 확인하는 함수 호출
                }//여기서 다른 돌을 찾으면 방향으로 가서 같은 돌이 있는지 검사
            }//주변의 다른 돌이 있는지 확인
        }//이곳에 돌을 둘 수 있는지를 확인하는 함수
        return false;
    }// type 1은 돌을 넣을 사용하는 부부, type2는 사용자에 둘 수 있는 곳을 확인 [초보모드, 일반 모드로 나누어서 한다. -> start에서 선택한다.]
    public void blank_check(int y,int x,int directy,int directx){
        int startx=x+directx;
        int starty=y+directy;
        while(true){
            if(isLineEnd(starty,startx)||draw_array[starty][startx]==turn||draw_array[starty][startx]==turn*3) return;//벽에 만나거나 본인의 돌을 만나면
            if(draw_array[starty][startx]==0) {
                draw_array[starty][startx]=3*turn;//들어갈 수 있는 곳을 3*turn으로 넣어준다. (player1은 3, player2는 -3)
                return;
            }
            startx+=directx;
            starty+=directy;
        }
    }//다른 돌이 있는 방향에 빈공간이 있는지를 확인하는 함수
    public boolean same_check(int y,int x, int directy, int directx){
        int changex = x+directx;
        int changey = y+directy;
        while(true){
            if(isLineEnd(changey,changex) || draw_array[changey][changex]==0 ){
                return false;
            }//벽에 도달하거나 흰색을 만나면 false를 반환.
            else if(draw_array[changey][changex]==turn) return true;//같은 돌이라면 true를 반환
            else if(draw_array[changey][changex]==turn*-1){
                changex+=directx;
                changey+=directy;
            }
        }
    }//기준돌을 기준으로 같은 돌이 있는지 확인
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        Point mousePoint=e.getPoint();//마우스가 클릭된 포인트를 설정
        int y = (int) mousePoint.getY()/100;
        int x = (int) mousePoint.getX()/100;
        if(draw_array[y][x]!=0)
            return;//이미 돌이 존재하면 작동하지 않는다.
        if(!put_check(y,x,1)) return;//둘 수 없는 곳이면 작동하지 않는다.
        draw_array[y][x]=turn;
        changeDoll(y,x);
        repaint();
        turn *=-1;//턴을 바꾸어준다.
        count(draw_array);//돌의 수를 세어서 넣어준다.//돌의 수를 세어준다.
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        for(int i = 0 ;i<draw_array.length;i++){
            for(int j =0; j<draw_array[i].length;j++){
                if(draw_array[i][j]==turn){
                    put_check(i,j,2);//사용자의 8방향에 다른 돌들이 있는지를 확인한다 -> 흰 공간이 있는지를 확인
                }//사용자와 같은 색의 돌이 있는 경우
            }
        }//클릭되고 나서의 사용자의 턴의 돌을 확인한다.
        if(end_check()) {
            endCheck=end_check();
            return;//경기가 끝나면 종료
        }
        if(!turn_check()){
            turn*=-1;
            count++;
        }//둘 곳이 없다면 사용자 순서를 바꾸고, count를 하나 올려준다,
        else count=0;
        endCheck=end_check();
        repaint();
        //게임이 끝이나는지를 확인한다.
    }
    public boolean end_check(){
        if(player1+player2==64 || player2==0 || player1==0) {
            return true;
        }// 두 사용자의 돌의 개수가 64개인경우 ,하나의 사용자의 돌이 0개인 경우
        else if(count==2) return true;//사용자의 턴이 연속으로 2번 지난 경우
        else return false;
    }//게임이 끝나는지를 확인한다.
    public boolean turn_check(){
        for(int i = 0 ;i<draw_array.length;i++){
            for(int j =0; j<draw_array[i].length;j++){
                if(draw_array[i][j]==turn*3||draw_array[i][j]==turn*-3) return true; //하나라도 둘 곳이 있다면 true
            }
        }//둘 수 있는 곳이 없다면 turn을 넘긴다.
        if(turn==1)
            JOptionPane.showMessageDialog(null,"빨강색 돌이 둘 곳이 없어 pass하겠습니다.");
        if(turn==2)
            JOptionPane.showMessageDialog(null,"파랑색 돌이 둘 곳이 없어 pass하겠습니다.");
        return false;
    }//턴을 이어서 할 수 있는지를 확인하는 함수
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        if(game_mode==1) return;//쉬운모드에서는 사용하지 않는다.
        Point mouse_position = e.getPoint();//마우스커서 위치를 가져옴
        turn_mouse=turn*2;//마우스가 올라가는 위치의 투명한 돌을 올림
        boolean check =false;
        int y = (int) mouse_position.getY()/100;
        int x = (int) mouse_position.getX()/100;
        if(draw_array[y][x]!=0) return;
        check = put_check(y,x,1);//둘 수 있는 곳이 있는지 확인하는 함수(같은 곳에 있는지를 확인하는 함수)
        if(check) {
            if (draw_array[y][x] == 0) {
                draw_array[y][x] = turn_mouse;
                repaint();
            }
        }
    }
}
