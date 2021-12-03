//import java.awt.*;
import java.io.*;

public class Save{
    private DataModel data;

    public void newgame(DataModel d){
        data = d;
        text();
        savedata(50,50,50,50,0);
        d.character.setChara(d);
    }

    public void save(DataModel d){
        data = d;
        savedata(d.character.aMylove,d.character.aElove,d.character.bMylove,d.character.bElove,d.character.count);
        txtwrite(d);
    }

    public void load(DataModel d){
        data = d;
        d.character.setChara(d);
    }

    public void savedata(int a,  int b,  int c,  int d, int count) {
        try {
            File file = new File("savedata/data.txt");
            FileWriter filewriter = new FileWriter(file);
            filewriter.write(a + "," + b + "," + c + "," + d + "," + count);
            filewriter.close();
        } catch ( IOException e) {
            System.out.println(e);
        }
    }

    public void txtwrite(DataModel d){
        data = d;
        try {
            File file1 = new File("savedata/savetext.txt");
            FileWriter file1writer = new FileWriter(file1);
            BufferedWriter bw = new BufferedWriter(file1writer);
            String line;
            line = d.text;
	        String[] lineSplit = line.split(",",0);
            for(int i = 0; i < lineSplit.length; i++){
                switch(i){
                case 0:
                    bw.write(d.text+","+d.Character1_data+","+d.Music_data);
                    break;
                case 1:
                    bw.write(d.text+","+d.Music_data);
                    break;
                case 2:
                    bw.write(d.text);
                    break;
                }
            }
            bw.newLine();
            while((line = d.in.readLine())!=null){
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void text(){
        try {
            File file1 = new File("savedata/savetext.txt");
            FileWriter file1writer = new FileWriter(file1);
            BufferedWriter bw = new BufferedWriter(file1writer);
            BufferedReader in = new BufferedReader(new FileReader("test.txt"));
            String line;
            while((line = in.readLine())!=null){
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}