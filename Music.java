import javax.sound.sampled.*;

class Music {       //　参考　https://nompor.com/2017/12/14/post-128/
    private DataModel data;
    private Clip clip;
    private FloatControl ctrl;
    private float sound_volume = (float)Math.log10((float)20/100) * 20; ;
    public Music(DataModel d){
        data = d;
        if(data.get_musicdata()==null) return;
        else clip = data.createClip();
        ctrl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        ctrl.setValue(-15.0f);
        this.startMusic();
    }

    public void startMusic(){	
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.flush();
    }

    public void stopMusic(){
        /*
        try{
            while (ctrl.getValue() > ctrl.getMinimum()){
                ctrl.setValue(ctrl.getValue() - 1.0f);
                Thread.sleep(30);
            }
            */clip.close();/*    
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        */
    }

    public void change_music(){
        if(clip!=null) this.stopMusic();
        clip = data.createClip();
        ctrl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        ctrl.setValue(sound_volume);
        this.startMusic();
    }

    public void setvolume(int i){
        sound_volume = (float)Math.log10((float)i/100) * 20; 
        if(data.get_musicdata()!=null) ctrl.setValue(sound_volume);
    }

}