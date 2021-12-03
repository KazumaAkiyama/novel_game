import java.awt.*;
import java.io.*;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.*;

class DataModel {
    String Name_data,BG_data,Character1_data,Character2_data,Music_data,text_data;
    private BufferedImage bg_image,Chara1_image ,Chara2_image,mw_image;
    private Clip clip;
    private int c_1, c_2;
    private int Controller_check;  //描画中に入力できないようにするための変数　0じゃないとクリックできない
    int i,j;

    Character character = new Character();
    Save save = new Save();
    String filename = "savedata/savetext.txt";
    String text = "",imageFlag = "",musicFlag= "";
    BufferedReader in;

    ////　内部データ↑
    private PlayFrame frame;
    protected PlayPanel p_p;
    protected Music music;
    private Config config;
    private Message_log ML;
    ////　内部データに基づいて呼び出すやつ　↑

    public DataModel() {
	Oohara();
        Controller_check=0;
        i = 0;
        j=0;
        BG_data = "b001.jpg";		//背景の名前を入れる変数 背景のデータは同じフォルダ内に入れてください
        Character1_data = null;		//キャラクター１の画像の名前を入れる変数　データはcharacterの中に入れてください
        Character2_data=null;		//キャラクター２用の変数　上とほとんど同じ
        Music_data = null;	        //音楽のファイル名を入れる変数
        text_data = " ";			//会話内容を入れる変数
        Name_data = "名前";         //名前を入れる変数
        c_1=0;                      //キャラ１のx座標　-300だと2キャラ時にいい感じになる
        c_2=0;                      //キャラ２のx座標　300だと2キャラ時にいい感じになる
        music = new Music(this);
        p_p = new PlayPanel(this);
        config = new Config(this);
        ML = new Message_log(this);
        set_image();
        frame = new PlayFrame();
        frame.change_panel(new Title(this));
        character.setChara(this);
    }

    public void data_reset(){//ステータスリセットしたい
    }

    public void change_to_playview(){///プレイ画面移動
        frame.change_panel(p_p);
        this.Controller(0);
    }

    public void change_to_title(){///タイトル画面移動
        music.stopMusic();
        this.data_reset();
        frame.change_panel(new Title(this));
    }

    public void change_to_config(){////設定画面移動
        frame.change_panel(config);
    }

    public void change_to_message_log(){////メッセージログ画面移動
        frame.change_panel(ML);
    }

    public void display_choice(){
        frame.setLayout(null);
        frame.change_panel(new Choice(this));
        frame.setLayout(new BorderLayout());
        frame.add(p_p);
        this.Controller(0);
    }

    public String[] makeString_Array(String str){ //テキスト分割
        String[] strArray = new String[str.length()];
        for(int i=1; i<=str.length(); i++){
            if(i<=45) strArray[i-1] = new String(str.substring(0, i));
	    else strArray[i-1] = new String(str.substring(45, i));
        }
        return strArray;
    }

    public void set_image(){   //画像取得
        try {
            if(BG_data==null) bg_image=null;
            else bg_image = ImageIO.read( new File( "./background/"+BG_data ) );
        } catch (IOException e) {
            System.out.println("ファイルが見つかりません。 [" + BG_data + "]");
        }

        try {
            if(Character1_data==null) Chara1_image=null;
            else Chara1_image = ImageIO.read( new File( "./character/"+Character1_data ) );
        } catch (IOException e) {
            System.out.println("ファイルが見つかりません。 [" + Character1_data + "]");
        }

        try {
            if(Character2_data==null) Chara2_image=null;
            else Chara2_image = ImageIO.read( new File( "./character/"+Character2_data ) );
        } catch (IOException e) {
            System.out.println("ファイルが見つかりません。 [" + Character2_data + "]");
        }

        try {
            mw_image = ImageIO.read( new File( "./background/mw03.png" ) );
        } catch (IOException e) {
            System.out.println("ファイルが見つかりません。 [" + "./mw03.png" + "]");
        }

        p_p.Chara1_image = Chara1_image;
        p_p.Chara2_image = Chara2_image;
        p_p.bg_image = bg_image;
        p_p.mw_image = mw_image;
        p_p.name = Name_data;
        p_p.str = makeString_Array(text_data);

    }

    public Clip createClip() {  ///音楽取得
		try (AudioInputStream ais = AudioSystem.getAudioInputStream(new File( Music_data ))){

			AudioFormat af = ais.getFormat();
			DataLine.Info dataLine = new DataLine.Info(Clip.class,af);
			Clip c = (Clip)AudioSystem.getLine(dataLine);
			
			c.open(ais);
			
			return c;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return null;
    }

    public String get_musicdata(){
        return Music_data;
    }

    public int Character1_point(){
        return c_1;
    }

    public int Character2_point(){
        return c_2;
    }

    public int get_check(){
        return Controller_check;
    }

    public void Controller (int i){
        Controller_check = i;
    }

 //////////////////        プレイ中のクリックしたときの処理↓↓↓↓↓↓↓↓↓↓↓↓

    public void Play_Next_data(){

    p_p.add_Animation(PlayPanel.feed); ///////アニメを付けたいときはp_p.add_Animationを入れてください
                                        //////引数ははString型で　ワイプ→1　テキスト表示→2　キャラフェード→3　です　それ以外だとアニメなしになります
                                        //////一応引数は　PlayPanel.wipe、PlayPanel.text、PlayPanel.feed　でも同じことができます。 
                                        //////repaintする前までにこのメソッド入れとけばアニメ入るはずです。             
    this.Akiyama();                     //////データをテキストファイルから引っ張ってくるやつ
    ML.set_log(Name_data, text_data);   //////メッセージログ記入
    p_p.repaint();                      //////表示

    }

////////////////////////////////////////↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    public void change_music(){
        music.change_music();
    }

    public void Oohara(){
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
        } catch (FileNotFoundException |UnsupportedEncodingException e){ 
            e.printStackTrace();
            System.exit(-1); // 0 以外は異常終了
     	}
    }
    public void Akiyama(){
	    try{
            String line;
            if(in.ready() == false) {
                p_p.add_Animation("1");
                Oohara();
            }
            line = in.readLine();
	        String[] lineSplit = line.split(",",0);
            for(int i = 0; i < lineSplit.length; i++){
                switch(i){
                case 0:
                    text = lineSplit[i];
                    text_data = text;
                    break;
                case 1:
                    imageFlag = lineSplit[i];
                    Character1_data = imageFlag;
                    break;
                case 2:
                    musicFlag = lineSplit[i];
                    Music_data = musicFlag;
                    music.change_music();
                    break;
                }
		    }
	    } catch (FileNotFoundException e){ 
            e.printStackTrace();
            System.exit(-1); // 0 以外は異常終了
        } catch (IOException e){ 
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public static void main(String argv[]) {
        new DataModel();
     }

} 
