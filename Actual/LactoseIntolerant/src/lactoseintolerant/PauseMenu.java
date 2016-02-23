/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import javax.swing.ImageIcon;

/**
 *
 * @author 0001058857
 */
public class PauseMenu extends Menu{
    public CButton[] buttons=new CButton[3];
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
    
    
    public PauseMenu(CListener c,javax.swing.JFrame f){
        done=c;
        setUpPauseMenu(f);
    }
    
    private void setUpPauseMenu(javax.swing.JFrame f){
        FRAME_SIZE=StartGameFlow.FRAME_SIZE;
        
        f.getContentPane().removeAll();
        f.getContentPane().repaint();
        f.add(this);
        f.repaint();
        
        f.setVisible(true);
        
        this.setLayout(null);
        
        f.add(this);
        
        //resume
        this.add(buttons[0]=new CButton(getNewSizeX(0.2),getNewSizeY(0.15),getNewSizeX(0.2),getNewSizeY(0.1),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(106)),
                                new ImageIcon(GraphicsAssets.getImage(107))}){
                    
            @Override
            public void released(){
                done.actionPerformed(true);
            }
        });
        
        //options
        this.add(buttons[1]=new CButton(getNewSizeX(0.2),getNewSizeY(0.3),getNewSizeX(0.2),getNewSizeY(0.1),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(108)),
                                new ImageIcon(GraphicsAssets.getImage(109))}){
                    
            @Override
            public void released(){
                f.getContentPane().removeAll();
                f.getContentPane().repaint();
                f.add(new OptionsMenu(new CListener(){
                @Override
                public void actionPerformed(){
                    backToPauseMenu(f);
                }
                },f));
                f.repaint();
            }
        });
        
        //exit
        this.add(buttons[2]=new CButton(getNewSizeX(0.2),getNewSizeY(0.45),getNewSizeX(0.2),getNewSizeY(0.1),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(110)),
                                new ImageIcon(GraphicsAssets.getImage(111))}){
                    
            @Override
            public void released(){
                done.actionPerformed(false);
            }
        });
        
        System.out.println("doneeeeeee");
        
        this.repaint();
        //f.repaint();
    }
    
    private void backToPauseMenu(javax.swing.JFrame f){
        setUpPauseMenu(f);
    }
    
    @Override
    public void paintComponent(java.awt.Graphics p){
        p.clearRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);
        p.drawImage(GraphicsAssets.getImage(71),0,0,FRAME_SIZE[0],FRAME_SIZE[1],null);
        System.out.println("painted......");
    }
    
    
}
