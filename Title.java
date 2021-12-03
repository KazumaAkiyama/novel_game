
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Title extends JPanel implements ActionListener {
    private DataModel data;
    //JButton b1,b2;
    int cnt;

    public Title( DataModel d) {
        data = d;
        this.setSize(1280, 800);
        this.setLayout(null);
        ImageIcon icon1 = new ImageIcon("タイトル/new1.png");
        JButton b1 = new JButton(icon1);
        b1.setContentAreaFilled(false); // ボタンの背景透明化
        b1.setBorderPainted(false); // ボタンの枠透明か
        b1.setFocusPainted(false); // ボタン選択後の枠線消去
        ImageIcon icon2 = new ImageIcon("タイトル/con.png");
        JButton b2 = new JButton(icon2);
        b2.setContentAreaFilled(false); // ボタンの背景透明化
        b2.setBorderPainted(false); // ボタンの枠透明か
        b2.setFocusPainted(false); // ボタン選択後の枠線消去
        d.character.setChara(d);
        if(d.character.count > 0){
          b1.setBounds(300, 650, 200, 100);
          b2.setBounds(800, 650, 200, 100);
          this.add(b1, BorderLayout.NORTH);
          this.add(b2, BorderLayout.NORTH);
        }else{
          b1.setBounds(550, 650, 200, 100);
          this.add(b1, BorderLayout.NORTH);
        }
          JLabel l1 = new JLabel(new ImageIcon("タイトル/タ.png"));
        l1.setBounds(300, 300, 200, 200);
          JLabel l2 = new JLabel(new ImageIcon("タイトル/イ.png"));
        l2.setBounds(450, 300, 200, 200);
          JLabel l3 = new JLabel(new ImageIcon("タイトル/ト.png"));
        l3.setBounds(600, 300, 200, 200);
          JLabel l4 = new JLabel(new ImageIcon("タイトル/ル.png"));
        l4.setBounds(750, 300, 200, 200);
          JLabel backgraund = new JLabel(new ImageIcon("タイトル/back1.jpg"));
        backgraund.setBounds(0, 0, 1280, 800);
        this.add(l1, BorderLayout.CENTER);
        this.add(l2, BorderLayout.CENTER);
        this.add(l3, BorderLayout.CENTER);
        this.add(l4, BorderLayout.CENTER);
        this.add(backgraund, BorderLayout.NORTH);
        b1.addActionListener(this);
        b1.setActionCommand("b1");
        b2.addActionListener(this);
        b2.setActionCommand("b2");
    }

    public void actionPerformed( ActionEvent e) {        
      String cmd = e.getActionCommand();
      if (cmd.equals("b1")) {
          data.save.newgame(data);
          data.change_to_playview();
      } else if (cmd.equals("b2")) {
          data.save.load(data);
          data.change_to_playview();
      } 
    }
}