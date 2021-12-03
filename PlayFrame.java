
import javax.swing.*;

class PlayFrame extends JFrame  {
    public PlayFrame(){
        this.setTitle("sample box");
        this.setSize(1280,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null); //画面中央表示
        this.setResizable(false);  //サイズ固定
    }
    public void change_panel(JPanel panel) {    //PlayFrameに加えるJpanelを切り替える
        getContentPane().removeAll();           //参考　https://nompor.com/2017/12/20/post-1782/
		super.add(panel);
		validate();
		repaint();
    }
    
}