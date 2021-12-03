
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Choice extends JPanel implements ActionListener{
    private DataModel data;
    public Choice(DataModel d){
        this.setBounds(440,300,400,300);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        data = d;
        Image_button b1 = new Image_button("wipe1.png","wipe2.png");  
        //　↑　引数1に常に表示される画像、引数2にはカーソルが合った時に表示される画像の名前を入れる。　画像はiconfolderに入れておく。
        Image_button b2 = new Image_button("music1.png","music2.png");  
        b1.setBounds(33,200,150,50);
        b2.setBounds(216,200,150,50);
        b1.setActionCommand("b1"); //ボタン判別用の文字列
        b2.setActionCommand("b2");
        JLabel l = new JLabel("Choose");
        l.setFont(new Font("Arial",Font.BOLD,40));
        l.setBounds(130,50,150,150);
        this.add(b1);
        this.add(b2);
        this.add(l);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev){
        String cmd = ev.getActionCommand();
        if(cmd.equals("b1")){
            //data.animation();
        }else if(cmd.equals("b2")){
            //data.change_music();
        }
        data.change_to_playview();
    }

}