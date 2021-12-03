import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Message_log extends JPanel implements ActionListener{
    private DataModel data;
    private JPanel panel1;
    private Font font;
    private ArrayList<JLabel> label;
    private BufferedImage bg_image;
    private Image_button back,config;
    private SpringLayout layout;
    int x=-500,y=-500,x2[]={0,180,330,0},y2[]={0,0,800,800},x3[]={0,230,420,0};
    public Message_log(DataModel d){
        try {
            bg_image = ImageIO.read( new File( "./background/message_log.png" ) );
        } catch (IOException e) {
            System.out.println("ファイルが見つかりません。 [" + "./background/config.png" + "]");
        }
        data = d;

        this.setBackground(Color.WHITE);
        this.setLayout(null);

        label = new ArrayList<JLabel>();
        layout = new SpringLayout();

        panel1 = new JPanel();
        panel1.setLayout(layout);
        panel1.setOpaque(false);

        font = new Font(null,Font.BOLD,30);

        back = new Image_button("back1.png", "back2.png");
        back.setBounds(0,675,386,107);
        back.addActionListener(this);
        back.setActionCommand("back");
        config = new Image_button("ml_config1.png", "ml_config2.png");
        config.setBounds(1,568,322,89);
        config.addActionListener(this);
        config.setActionCommand("config");

        JScrollPane scrollpane = new JScrollPane(panel1);
        scrollpane.setBounds(370, 205, 887, 554);
        scrollpane.setOpaque(false);                //背景透明処理
        scrollpane.getViewport().setOpaque(false);  //背景透明処理
        scrollpane.setBorder(null);                 //枠線消去
        scrollpane.getVerticalScrollBar().setUnitIncrement(20);//スクロール量変更

        this.add(scrollpane);
        this.add(back);
        this.add(config);
    }

    public void set_log(String name, String text){
        JLabel l = new JLabel();
        l.setFont(font);
        l.setText(name+":「"+text+"」");
        
        label.add(l);
        if(label.size()>1) 
            layout.putConstraint(SpringLayout.NORTH, label.get(label.size()-1), 0, SpringLayout.SOUTH, label.get(label.size()-2));
        panel1.add(label.get(label.size()-1));
        panel1.setPreferredSize(new Dimension(800,(int)(l.getPreferredSize().getHeight())*(label.size())));
        if(l.getPreferredSize().getWidth()>800) 
            panel1.setPreferredSize( new Dimension( (int)(l.getPreferredSize().getWidth()) ,(int)(l.getPreferredSize().getHeight())*(label.size())));
        if(label.size()>30){        //30個以上で消去
            panel1.remove(label.get(0));
            label.remove(0);
        }
    }

    public void actionPerformed(ActionEvent ev){
        String cmd = ev.getActionCommand();
        if(cmd.equals("back")) data.change_to_playview();
        else if(cmd.equals("config")) {
            data.change_to_config();
        }
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
        g.drawImage(bg_image,0,0,this);
        try{
            Thread.sleep(50);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        x+=1;y-=1;
        if(x==-140){
            x=-500;y=-500;
        }
        this.repaint();
    }
}