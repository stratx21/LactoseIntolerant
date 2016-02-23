/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author 0001058857
 */
public class OptionsMenu extends Menu{
    
    public JFrame frame=null;
    
    public CListener done=null;
    
    public OptionsMenu(CListener done,JFrame fr){
        System.out.println("setting up options menu...");
        this.done=done;
        (frame=fr).add(this);
        
        this.setLayout(null);
        
        addComponents();
        
        this.repaint();
        this.revalidate();
        
        FRAME_SIZE=new int[]{fr.getWidth(),fr.getHeight()};
        
        repaint();
        
    }
    
    private void addComponents(){
        //exit::
        this.add(new CButton(100,100,300,100,
            new javax.swing.ImageIcon[]{null,null}){
                @Override
                public void released(){
                    done.actionPerformed();
                }
            });
    }
    
    @Override
    public void paintComponent(Graphics p){
        //draw background::
        p.clearRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);
        p.drawImage(GraphicsAssets.getImage(71),0,0,FRAME_SIZE[0],FRAME_SIZE[1],null);
    }
    
}
