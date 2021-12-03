import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


class PlayPanel extends JPanel implements ActionListener{
    private DataModel data;
    private Controller cont;
    protected BufferedImage bg_image,Chara1_image ,Chara2_image,mw_image;
    protected String str[],name;
    private Image_button config,ml,save;
    public static final String wipe="1",text="2",feed="3";


    private BufferedImage bg_pre_image,Chara1_pre_image ,Chara2_pre_image;
    private int i=0,check1=0,check2=0,speed=6;
    private String Animation_check="0";
    private float f=0;
    private Font font,name_font;
    private String pre_name;

    public PlayPanel(DataModel d){
        this.setPreferredSize(new Dimension(1280,800));
        this.setLayout(null); 
        data = d;

        config = new Image_button("play_config1.png", "play_config2.png");
        config.setBounds(803,700,247,66);
        config.setActionCommand("c");
        config.addActionListener(this);
        ml = new Image_button("play_ml1.png", "play_ml2.png");
        ml.setBounds(1050,700,239,66);
        ml.setActionCommand("m");             
        ml.addActionListener(this);
        save = new Image_button("play_ml1.png", "play_ml2.png");
        save.setBounds(0,700,239,66);
        save.setActionCommand("s");             
        save.addActionListener(this);

        font = new Font("Label.font",Font.PLAIN,25);
        name_font = new Font("Label.font",Font.PLAIN,40);

        this.add(config);
        this.add(ml);
        this.add(save);
        
        cont = new Controller(data);
        this.addMouseListener(cont);
        this.setFocusable(true);
        this.addKeyListener(cont);
    }

    public void paintComponent(Graphics g){
        g.setClip(0, 0, 1280, 800);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        super.paintComponent(g);
        if (Animation_check == "1"){
            wipe_animation(g2);
        }else if (Animation_check == "2"){
            text_flow(g2);
        }else if(Animation_check == "3"){
            feed_in_character(g2);
        }else{
            data.set_image();
            no_animation(g2);
        }

    }

    public void set_button(){
        this.add(config);
        this.add(ml);
        this.add(save);
    }

    public void actionPerformed(ActionEvent ev){
        String cmd = ev.getActionCommand();
        if(cmd.equals("c")) data.change_to_config();
        else if(cmd.equals("m")) data.change_to_message_log();
        else if(cmd.equals("s")) {
            data.character.count = data.character.count + 1;
            data.save.save(data);
            //System.out.println(data.character.aMylove);
        }
    }


///////////////////////以下アニメ用のやつ

    

    public void wipe_animation(Graphics2D g ){
        this.removeAll();
        if(check1==0){
            this.no_animation(g);
            g.setColor(Color.BLACK);
            g.fillOval(640-i/2, 400-i/2, i, i);

            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            i+=30;
            if(i<=2000) {
                data.Controller(1);
                this.repaint();
                return;
            }else{
                check1=1;
            }
        }
////////////////　　　↑

        if(check2==0){  /////
            data.set_image();
            check2=1;   /////
        }               /////

        this.no_animation(g);

////////////////////////　　　↓
        if(check1 == 1){
            g.setColor(Color.BLACK);
            g.fillOval(640-i/2, 400-i/2, i, i);

            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            i-=30;
            if(i>=0) {
                this.repaint();
                return;
            }else{data.Controller(0);}
        }
        this.set_button();
        i=0;
        check1=0;
        check2=0;
        Animation_check="0";
/////////////////////////////　　　↑
    }

    public void text_flow(Graphics2D g ){
        if(check2==0){  /////
            data.set_image();            
            data.Controller(2);
            check2=1;   /////
        }               /////
        if(i<str.length){
            i++;

            g.drawImage(bg_image, 0, 0, this);
             this.draw_character(g, Chara1_image, Chara2_image);
            g.drawImage(mw_image, 0, 0, this);
            g.setColor(Color.WHITE);
            g.setFont(name_font);
            g.drawString(name, 100, 540);
            g.setFont(font);
            if(speed!=10){
                     if(i<=45) g.drawString(str[i-1], 100, 620);
		     else{
			g.drawString(str[i-1], 100, 650);
			g.drawString(str[44], 100, 620);
		     }
            }
            try{
                Thread.sleep(100-speed*10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            this.repaint();
            return;
        }else{
            i=0;
            check2=0;
            Animation_check = "0";
            this.no_animation(g);
            data.Controller(0);
        }

    }

    public void feed_in_character(Graphics2D g){
        if(check2==0){  /////
            this.set_pre_data(bg_image, Chara1_image, Chara2_image,name);
            data.set_image();
            check2=1;   /////
        }               /////
        if(f<1.0f){
            f+=0.1f;
            if(f>1) f=1.0f;
            data.Controller(1);

            g.drawImage(bg_image, 0, 0, this);
            this.draw_character(g, Chara1_pre_image, Chara2_pre_image);

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f));
            //g.drawImage(bg_image, 0, 0, this);
            this.draw_character(g, Chara1_image, Chara2_image);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

            g.drawImage(mw_image, 0, 0, this);
            g.setColor(Color.WHITE);
            g.setFont(name_font);
            g.drawString(pre_name, 100, 540);

            try{
                Thread.sleep(50);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            this.repaint();
            return;
        }else{
            f=0;
            check2=0;
            Animation_check = "2";
            this.text_flow(g);
            //data.Controller(1);
        }
    }

    public void feed_out_character(Graphics2D g ){
        if(check2==0){  /////
            this.set_pre_data(bg_image, Chara1_image, Chara2_image,name);
            data.set_image();
            check2=1;   /////
        }               /////
        if(f<1.0f){
            f+=0.1f;
            if(f>1) f=1.0f;
            data.Controller(1);

            g.drawImage(bg_image, 0, 0, this);
             this.draw_character(g, Chara1_pre_image, Chara2_pre_image);

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f));
            g.drawImage(bg_image, 0, 0, this);
             this.draw_character(g, Chara1_image, Chara2_image);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

            g.drawImage(mw_image, 0, 0, this);
            g.setColor(Color.WHITE);
            g.setFont(name_font);
            g.drawString(pre_name, 100, 540);

            try{
                Thread.sleep(50);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            this.repaint();
            return;
        }else{
            f=0;
            check2=0;
            Animation_check = "2";
            this.text_flow(g);
            //data.Controller(1);
        }
    }
//上のアニメーション使う時は、表示したいキャラを必ずnullにしてください。

    public void no_animation(Graphics2D g ){
        g.drawImage(bg_image, 0, 0, this);
         this.draw_character(g, Chara1_image, Chara2_image);
        g.drawImage(mw_image, 0, 0, this);

        g.setColor(Color.WHITE);
        g.setFont(name_font);
        g.drawString(name, 100, 540);
        g.setFont(font);
        if(str.length<=45) g.drawString(str[str.length-1],100,620);
	else {
	    g.drawString(str[44],100,620);
	    g.drawString(str[str.length-1],100,650);
	}
    }

    public void draw_character(Graphics2D g, BufferedImage Chara1_image, BufferedImage Chara2_image){
        g.drawImage(Chara1_image, data.Character1_point(), 0, this);
        g.drawImage(Chara2_image, data.Character2_point(), 0, this);
    }

    public void set_pre_data(BufferedImage bg_image, BufferedImage Chara1_image, BufferedImage Chara2_image,String name){
        bg_pre_image = bg_image;
        pre_name = name;
        Chara1_pre_image = Chara1_image;
        Chara2_pre_image = Chara2_image;
    }

    public void add_Animation(String i){
        Animation_check  = i ;
    }

    public void change_speed(int i){
        speed = i;
    }

    public void status_reset(){
        i=0;check1=0;check2=0;Animation_check="0";
        data.Controller(0);
    }


    public String return_check(){
        return Animation_check;
    }
    
}
