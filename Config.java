import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Config extends JPanel implements ChangeListener,ActionListener {
    private DataModel data;
    private JSlider slider,slider2;
    private BufferedImage bg_image;
    private Image_button back,ML;
    int x=-500,y=-500,x2[]={0,180,330,0},y2[]={0,0,800,800},x3[]={0,230,420,0};
    public Config(DataModel d){
        //背景画像読み込み
        try {
            bg_image = ImageIO.read( new File( "./background/config.png" ) );
        } catch (IOException e) {
            System.out.println("ファイルが見つかりません。 [" + "./background/config.png" + "]");
        }

        data = d;
        slider = new JSlider(0,100,20);
        slider.setBounds(700,300,400,100);
        slider.setOpaque(false);
        slider.addChangeListener(this);
        slider2 = new JSlider(0,10,6);
        slider2.setBounds(700,550,400,100);
        slider2.setOpaque(false);
        slider2.addChangeListener(this);
        back = new Image_button("back1.png", "back2.png");
        back.setBounds(0,675,386,107);
        back.addActionListener(this);
        back.setActionCommand("back");
        ML = new Image_button("config_messagelog1.png", "config_messagelog2.png");
        ML.addActionListener(this);
        ML.setActionCommand("ML");
        ML.setBounds(1,454,322,89);
        this.setLayout(null);
        this.add(slider);
        this.add(slider2);
        this.add(back);
        this.add(ML);
        this.setBackground(Color.white);
    }
    
    public void stateChanged(ChangeEvent e){
        data.music.setvolume(slider.getValue());
        data.p_p.change_speed(slider2.getValue());
    }

    public void actionPerformed(ActionEvent ev){
        String cmd = ev.getActionCommand();
        if(cmd.equals("back")) data.change_to_playview();
        else if(cmd.equals("ML")) data.change_to_message_log();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setClip(0, 0, 1280, 800);
        g.setColor(new Color(202,255,255));
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(j%2==0) g.fillOval(x+i*180, y+j*180, 100, 100);
                else g.fillOval(x-100+i*180, y+j*180, 100, 100);
            }
        }
        g.setColor(new Color(180,255,255));
        g.fillPolygon(x3, y2, 4);
        g.setColor(Color.CYAN);
        g.fillPolygon(x2, y2, 4);
        g.setFont(new Font(null,Font.BOLD,40));
        g.setColor(Color.BLACK);
        g.drawString(":"+slider.getValue(), 1110, 358);
        g.drawString(":"+slider2.getValue(), 1110, 608);
        g.drawImage(bg_image,0,0,this);
        try{
            Thread.sleep(50);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        x+=1;y+=1;
        if(x==-140){
            x=-500;y=-500;
        }
        this.repaint();
    }
}
