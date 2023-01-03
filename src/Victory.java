import com.sun.xml.internal.ws.util.UtilException;

import javax.swing.*;
import java.awt.*;

public class Victory extends JFrame {
    public Victory(){
        setTitle("결과창");
        setLayout(null);
        getContentPane().setBackground(new Color(245, 255, 128));
        setBounds(500,100,730,850);
        setVisible(true);
    }//승리창을 띄워주는 생성자
    public Victory(String s){
        this();
        setLayout(null);
        JLabel title= new JLabel(s);
        title.setFont(new Font("맑은 고딕", Font.BOLD,40));
        title.setForeground(Color.BLACK);
        add(title);
        title.setBounds(250,105,200,150);
        if(!s.equals("무승부")){
            title.setText(s+"님");
            JLabel content= new JLabel("승리하셨습니다.");
            JLabel congratution= new JLabel("축하드립니다.");
            content.setFont(new Font("맑은 고딕", Font.BOLD,50));
            congratution.setFont(new Font("맑은 고딕", Font.BOLD,60));
            content.setForeground(Color.BLACK);
            congratution.setForeground(Color.BLACK);
            content.setBounds(170,170,1000,200);
            congratution.setBounds(160,250,500,200);
            add(content);
            add(congratution);
            //축하 사진을 가져옴
            ImageIcon image = new ImageIcon(Victory.class.getResource("축하.PNG"));
            Image change_img= image.getImage();
            change_img = change_img.getScaledInstance(400,100,Image.SCALE_DEFAULT);//이미지 사이즈 조절
            image = new ImageIcon(change_img);
            JLabel img = new JLabel(image);
            add(img);
            img.setBounds(160,20,400,100);
            ImageIcon image2 = new ImageIcon(Victory.class.getResource("축하2.PNG"));
            Image change_img2= image2.getImage();
            change_img2 = change_img2.getScaledInstance(300,300,Image.SCALE_DEFAULT);//이미지 사이즈 조절
            image2 = new ImageIcon(change_img2);
            JLabel img2 = new JLabel(image2);
            add(img2);
            img2.setBounds(200,420,300,300);
        }//승리하였습니다. 축하드립니다. 메시지 띄우기
        else{
            title.setBounds(270,105,200,150);
            JLabel content= new JLabel("비겼네요");
            JLabel congratution= new JLabel("한판더 하는 것은 어떤가요?");
            content.setFont(new Font("맑은 고딕", Font.BOLD,50));
            congratution.setFont(new Font("맑은 고딕", Font.BOLD,40));
            content.setForeground(Color.BLACK);
            congratution.setForeground(Color.BLACK);
            content.setBounds(240,150,1000,200);
            congratution.setBounds(100,220,900,200);
            add(content);
            add(congratution);
            ImageIcon image = new ImageIcon(Victory.class.getResource("무승부.PNG"));
            Image change_img= image.getImage();
            change_img = change_img.getScaledInstance(400,100,Image.SCALE_DEFAULT);//이미지 사이즈 조절
            image = new ImageIcon(change_img);
            JLabel img = new JLabel(image);
            add(img);
            img.setBounds(140,20,400,100);
            ImageIcon image2 = new ImageIcon(Victory.class.getResource("한판.PNG"));
            Image change_img2= image2.getImage();
            change_img2 = change_img2.getScaledInstance(300,400,Image.SCALE_DEFAULT);//이미지 사이즈 조절
            image2 = new ImageIcon(change_img2);
            JLabel img2 = new JLabel(image2);
            add(img2);
            img2.setBounds(200,370,300,400);
        }//비겼네요..한판 더 하실래요?
    }
}
