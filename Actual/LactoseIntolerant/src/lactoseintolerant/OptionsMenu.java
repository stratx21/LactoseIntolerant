/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author 0001058857
 */
public class OptionsMenu extends Menu{
    
    public CButton[] buttons=new CButton[3];
    
    public JFrame frame=null;
    
    public CListener done=null;
    
    /**
     * take the original measurements used and translate them to the new sizes
     * that should be used instead, depending on the frame's size, which
     * depends on the screen since the game is full screen. 
     * 
     * @param a the original size divided by 1000 since that is the original x
     *      size that was used
     * @return the new x dimension
     */
    @Override
    public int getNewSizeX(double a){
        return (int)(a*FRAME_SIZE[0]);
    }
    
    /**
     * take the original measurements used and translate them to the new sizes
     * that should be used instead, depending on the frame's size, which
     * depends on the screen since the game is full screen. 
     * 
     * @param a the original size divided by 1000 to make it simpler than dividing
     *      by 700, the original y size; this function formats the number to as
     *      if it was divided by 1000. 
     * @return the new y dimension
     */
    @Override
    public int getNewSizeY(double a){
        return (int)(a*1.42857*FRAME_SIZE[1]);
    }
    
    public OptionsMenu(CListener done,JFrame fr){
        FRAME_SIZE=StartGameFlow.FRAME_SIZE;
        System.out.println("setting up options menu...");
        this.done=done;
        (frame=fr).add(this);
        
        this.setLayout(null);
        
        addComponents();
        
        this.repaint();
        this.revalidate();
        
        
        
        repaint();
        
    }
    
    public void addComponents(){
        
        System.out.println(getNewSizeX(0.2)+"  "+getNewSizeX(2));
        //exit::
        this.add(buttons[0]=new CButton(getNewSizeX(0.2),getNewSizeY(0.15),getNewSizeX(0.2),getNewSizeY(0.1),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(112)),
                                new ImageIcon(GraphicsAssets.getImage(112))}){
                    
            @Override
            public void released(){
                done.actionPerformed();
            }
        });
        //sound
        this.add(buttons[1]=new CButton(getNewSizeX(0.2),getNewSizeY(0.3),getNewSizeX(0.2),getNewSizeY(0.1),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(Profile.soundEffectsOn?115:116)),
                                new ImageIcon(GraphicsAssets.getImage(Profile.soundEffectsOn?115:116))}){
                    
            @Override
            public void released(){
                if(Profile.soundEffectsOn){
                    icons=new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(116)),new ImageIcon(GraphicsAssets.getImage(116))};
                } else{
                    icons=new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(115)),new ImageIcon(GraphicsAssets.getImage(115))};
                }
                changeIconSizes();
                Profile.soundEffectsOn=!Profile.soundEffectsOn;
            }
        });
        //music
        this.add(buttons[2]=new CButton(getNewSizeX(0.2),getNewSizeY(0.45),getNewSizeX(0.2),getNewSizeY(0.1),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(Profile.musicOn?113:114)),
                                new ImageIcon(GraphicsAssets.getImage(Profile.musicOn?113:114))}){
                    
            @Override
            public void released(){
                if(Profile.soundEffectsOn){
                    Profile.soundEffectsOn=false;
                    AudioAssets.music.stop();
                    icons=new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(113)),new ImageIcon(GraphicsAssets.getImage(113))};
                } else{
                    Profile.soundEffectsOn=true;
                    AudioAssets.play("music");
                    icons=new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(114)),new ImageIcon(GraphicsAssets.getImage(114))};
                }
                changeIconSizes();
            }
        });
    }
    boolean a=true;
    @Override
    public void paintComponent(Graphics p){
        //draw background::
        p.clearRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);
        p.drawImage(GraphicsAssets.getImage(71),0,0,FRAME_SIZE[0],FRAME_SIZE[1],null);
    }
    
}
