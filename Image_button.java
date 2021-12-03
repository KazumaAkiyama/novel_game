import javax.swing.ImageIcon;
import javax.swing.JButton;

class Image_button extends JButton{
    private ImageIcon icon1,icon2;

    public Image_button(String icon1,String icon2){
        this.icon1= new ImageIcon("./iconfolder/"+icon1);
        this.icon2= new ImageIcon("./iconfolder/"+icon2);
        this.setIcon(this.icon1);
        this.setRolloverIcon(this.icon2);
        this.setContentAreaFilled(false); //ボタンの背景透明化
        this.setBorderPainted(false);     //ボタンの枠透明化
        this.setFocusPainted(false);   //ボタン選択後の枠線消去
    }
}