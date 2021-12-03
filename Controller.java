
import java.awt.event.*;

class Controller implements MouseListener,KeyListener{
    private DataModel data;
    public Controller (DataModel d){
        data = d;

    }

    
    public void mouseClicked(MouseEvent e) {
        if(data.get_check()==0){
            data.Play_Next_data();
        }else if(data.get_check()==2){
            data.p_p.add_Animation("0");
            data.p_p.status_reset();
        }
    }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void keyPressed(KeyEvent e){
        int k = e.getKeyCode();
        if(data.get_check()==0) {
            switch(k){
            case KeyEvent.VK_ENTER:
                data.Play_Next_data();
                break;
            case KeyEvent.VK_SPACE:
                data.Play_Next_data();
                break;
            }
        }
     }
     public void keyTyped(KeyEvent e){
        int k = e.getKeyCode();
        if(data.get_check()==0) {
            switch(k){
            case KeyEvent.VK_ENTER:
                data.Play_Next_data();
                break;
            case KeyEvent.VK_SPACE:
                data.Play_Next_data();
                break;
            }
        }else if(data.get_check()==2){
            switch(k){
                case KeyEvent.VK_ENTER:
                    data.p_p.add_Animation("0");
                    data.p_p.status_reset();
                    break;
                case KeyEvent.VK_SPACE:
                    data.p_p.add_Animation("0");
                    data.p_p.status_reset();
                    break;
                }
        }
     }
     public void keyReleased(KeyEvent e){ }
}