import java.io.*;
import java.util.Scanner;

public class Character{
    private DataModel data;
    int aMylove; // 茶髪の主人公への好感度
    int aElove; // 茶髪の地球への好感度
    int bMylove; // 黒髪の主人公への好感度
    int bElove; // 黒髪の地球への好感度
    int count;


    public void setCharacter(DataModel d,int Amylove, int Aelove, int Bmylove, int Belove) {
        data = d;
        d.character.aMylove = Amylove;
        d.character.aElove = Aelove;
        d.character.bMylove = Bmylove;
        d.character.bElove = Belove;
    }

    public void setChara(DataModel d){
        data = d;
        File f = new File("savedata/data.txt");
        try(Scanner sc = new Scanner(f)){
            sc.useDelimiter(",");
            d.character.aMylove = sc.nextInt();
            d.character.aElove = sc.nextInt();
            d.character.bMylove = sc.nextInt();
            d.character.bElove = sc.nextInt();
            d.character.count = sc.nextInt();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public int getcount(DataModel d){
        data = d;
        return d.character.count;
    }

    public int getaMylove(DataModel d){
        data = d;
        return d.character.aMylove;
    }

    public int getbMylove(DataModel d){
        data = d;
        return d.character.bMylove;
    }

    public int getaElove(DataModel d){
        data = d;
        return d.character.aElove;
    }

    public int getbElove(DataModel d){
        data = d;
        return d.character.bElove;
    }

    public void change(DataModel d, String s, int change) {
        data = d;
        switch(s){
            case "am":
                d.character.aMylove += change;
                if(d.character.aMylove <= 0){
                    d.character.aMylove = 0;
                }else if(d.character.aMylove >= 100){
                    d.character.aMylove = 100;
                }
                break;
            case "ae":
                d.character.aElove += change;
                if(d.character.aElove <= 0){
                    d.character.aElove = 0;
                }else if(d.character.aElove >= 100){
                    d.character.aElove = 100;
                }
                break;
            case "bm":
                d.character.bMylove += change;
                if(d.character.bMylove <= 0){
                    d.character.bMylove = 0;
                }else if(d.character.bMylove >= 100){
                    d.character.bMylove = 100;
                }
                break;
            case "be":
                d.character.bElove += change;
                if(d.character.bElove <= 0){
                    d.character.bElove = 0;
                }else if(d.character.bElove >= 100){
                    d.character.bElove = 100;
                }
                break;

        }
    }
}