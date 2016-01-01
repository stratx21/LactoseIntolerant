/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import javax.swing.JPanel;

/**
 *
 * @author 0001058857
 */
public class CPanel extends JPanel{
    public int DELAY=40;
    public int[] FRAME_SIZE=new int[]{1000,700};
    public boolean done=false;
    public void setFrameS()
    { 
        FRAME_SIZE[0]=this.getWidth();
        FRAME_SIZE[1]=this.getHeight();
    }
    public CPanel(){
        this.setLayout(null);
    }
    
}
