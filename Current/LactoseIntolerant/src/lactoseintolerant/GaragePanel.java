/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Josh
 */
public class GaragePanel extends CPanel /*implements MouseListener*/{
    private JFrame frame=null;
    
    private GamePanel gamePanel=null;
    
    public CButton prev=null,next=null,play=null;
    public CButton[] bottomButtons=new CButton[4];
    
    public int missionIndex=1,allowedLevels=1;
    
    private final int TOTAL_LEVELS=2;////////////////////// *  9090
    
    public String toSplitString="::::";
    
    public String[] missionInfo=new String[]{"Mission #1:  ::::defeat the first timed mission!",
        "Mission #2:   ::::defeat another timed mission!"}; //\n not working with Graphics.drawString
    
    private final int BOTTOM_BUTTONS_Y=550,BOTTOM_BUTTONS_HEIGHT=150;
    
    public ArrayList<Component> innerComponents=new ArrayList<Component>();
    
    public BufferedImage missionBoard=GraphicsAssets.getImage(36),
            background=GraphicsAssets.getImage(51);
    
    /**
     * 0 - Missions
     * 1 - Upgrades
     * 2 - Team
     * 3 - Menu
     * 
     */
    public byte mode=0,oldMode=0;
    
    public GaragePanel(JFrame f,int level){
        frame=f;
        
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.add(this);
        frame.repaint();
        
        frame.setVisible(true);
        
        missionIndex=allowedLevels=level;
        
        System.out.println("reached. made");
        
        
        addLowerModeButtons();
        
//        this.addMouseListener(this);
        repaint();
        switchComponents();
    }
    
    private void addLowerModeButtons(){
        //missions button::
        this.add(bottomButtons[0]=new CButton(0,BOTTOM_BUTTONS_Y,250,BOTTOM_BUTTONS_HEIGHT,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(43)),
                                new ImageIcon(GraphicsAssets.getImage(44))},
                false){
                    
            @Override
            public void released(){
                
            }
        });
        
        //upgrades button::
        this.add(bottomButtons[1]=new CButton(250,BOTTOM_BUTTONS_Y,250,BOTTOM_BUTTONS_HEIGHT,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(45)),
                                new ImageIcon(GraphicsAssets.getImage(46))},
                false){
           @Override
           public void released(){
               
           }
        });
        
        //team button::
        this.add(bottomButtons[2]=new CButton(500,BOTTOM_BUTTONS_Y,250,BOTTOM_BUTTONS_HEIGHT,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(47)),
                                new ImageIcon(GraphicsAssets.getImage(48))},
                false){
           @Override
           public void released(){
               
           }
        });
        
        
        //menu button::
        this.add(bottomButtons[3]=new CButton(750,BOTTOM_BUTTONS_Y,250,BOTTOM_BUTTONS_HEIGHT,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(49)),
                                new ImageIcon(GraphicsAssets.getImage(50))},
                false){
           @Override
           public void released(){
               
           }
        });
    }
    
    private void updateNextPrevPlay(){
        if(missionIndex==1)
            prev.disable(true);
        else 
            prev.disable(false);
        
        if(missionIndex==TOTAL_LEVELS)
            next.disable(true);
        else
            next.disable(false);
        
        if(missionIndex>allowedLevels)
            play.disable(true);
        else
            play.disable(false);
    }
    
    
    @Override
    public void paintComponent(Graphics p){
        p.drawImage(background,0,0,null);
        drawMoneyDisplay(p);
        switch(mode){
            case 0: //missions
                updateNextPrevPlay();
                p.drawImage(missionBoard,150,100,700,500,null);
                
                try{
                    String[] lines=missionInfo[missionIndex-1].split(toSplitString);
                p.setColor(Color.black);
                p.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/Fonts/AA_typewriter.ttf")).deriveFont(18f));
                int y=170;
                for(int i=0;i<lines.length;i++){
                    p.drawString(lines[i],235,y+=25);
                }
                }catch(Exception e){
                    ErrorLogger.logError(e,"paintComponent - GaragePanel");
                }
                break;
            case 1: //upgrades
                break;
            case 2: //team
                break;
            case 3: //menu
                break;
        }
    }
    
    private void switchComponents(){
        bottomButtons[oldMode].setPernamantSelect(false);
        bottomButtons[mode].setPernamantSelect(true);
        switch(oldMode=mode){
            case 0:
                addNextPrevPlayButtons();
                updateNextPrevPlay();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
    
    private void addNextPrevPlayButtons(){  // x 63, y  24
        //prev::
        this.add(prev=new CButton(25,400,63,24,
            new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(37)),
                            new ImageIcon(GraphicsAssets.getImage(38))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                if(!disabled){
                    missionIndex--;
                    rpnt();
                }
            }
        });
        
        //next::
        this.add(next=new CButton(912,400,63,24,
            new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(40)),
                            new ImageIcon(GraphicsAssets.getImage(41))},
            new ImageIcon(GraphicsAssets.getImage(42)),false){
                
            @Override
            public void released(){
                if(!disabled){
                    missionIndex++;
                    rpnt();
                }
            }
        });
        
        //play:: 
        this.add(play=new CButton(600,350,125,59,
            new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(52)),
                            new ImageIcon(GraphicsAssets.getImage(53))},
            new ImageIcon(GraphicsAssets.getImage(54)),false){
                
            @Override
            public void released(){
                if(!disabled)
                    setUpGamePanel();
            }
        });
        
    }
    
    /**
     * extra class reference to the repaint function
     */
    public void rpnt(){
        this.repaint();
    }
    
    private void drawMoneyDisplay(Graphics p){
        
    }
    
    public boolean missionSuccess=false;
    
    private boolean roundDone=false,justStarting=true;
    private int frameRateMillisecondsInGame=20;
    public long time=0,lastTime=0,thisTime=0,
            lastNeededTime=0,yourTime=0;
    public int frameRate=0;
    private void setUpGamePanel(){
        frame.remove(this);
        
        roundDone=false;
        
         frame.getContentPane().removeAll();
         frame.getContentPane().repaint();
         
         frame.setVisible(false);
         
         justStarting=true;
         
         lastTime=System.currentTimeMillis();
         
         frame.add(gamePanel=new GamePanel(new int[]{frame.getWidth(),frame.getHeight()},missionIndex,new CListener(){
                @Override
                public void actionPerformed(){
                    if((yourTime=gamePanel.time-3000)<(lastNeededTime=gamePanel.objectiveTime))
                        missionSuccess=true;
                    else
                        missionSuccess=false;
                    
                    if(missionSuccess&&(allowedLevels<gamePanel.level+1))
                        allowedLevels=gamePanel.level;
                    
                    
                    roundDone=true;
                }
                }){
             @Override
             public void paintComponent(Graphics p){
                time+=System.currentTimeMillis()-lastTime;
                
                lastTime=System.currentTimeMillis();
                
                gamePanel.paintC(p);
                
                int t=0;
                
                if(!gamePanel.paused)
                    gamePanel.calcFlow();
                else{
                    if(justStarting){
                    p.setColor(new Color(0,0,0,(t=(int)(175-(time)/20))));
                    p.fillRect(0,0,1100,800);
                    
                    try{
                        p.setColor(Color.black);
                        p.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/Fonts/straight.ttf")).deriveFont(48f));
                        p.drawString((3-(int)(time/1000))+"",450,300);
                    } catch(Exception e){
                        ErrorLogger.logError(e,"GamePanel overriden in GaragePanel; error using font");
                    }
                    
                    if(time>3000)
                        gamePanel.paused=false;
                    } else{ //pause menu
                        drawPauseMenu(p);
                    }
                }
                
                thisTime=System.currentTimeMillis()-lastTime;
                
//                System.out.println(t+", "+time);
                
                int delay=0;
                if(thisTime<frameRateMillisecondsInGame)
                    delay=frameRateMillisecondsInGame-(int)thisTime;
                
                if(delay<frameRateMillisecondsInGame)
                    delay=20;
                
                
                frameRate=1000/delay;
                    
//                System.out.println(frameRate+", "+gamePanel.player.speed);
                
                 try{Thread.sleep(delay);}
                 catch(Exception e){ErrorLogger.logError(e,"GameFlow.paintComponent");}
                 
                 if(!roundDone)
                    repaint();
                 else{
                    p.setColor(new Color(0,0,0,215));
                    p.fillRect(0,0,1100,800);
                    
                    try{
                        String a;
                        if(missionSuccess)
                            a="You won!";
                        else 
                            a="You lost";
                        
                        p.setColor(Color.white);
                        p.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/Fonts/straight.ttf")).deriveFont(24f));
                        p.drawString(a,400,300);
                        p.drawString("Needed time:: "+lastNeededTime/1000+" s",400,350);
                        p.drawString("Your time::   "+yourTime/1000+" s",400,400);
                        
                        add(new CButton(400,500,150,50,new ImageIcon[]{
                                                    new ImageIcon(GraphicsAssets.getImage(55)),
                                                    new ImageIcon(GraphicsAssets.getImage(56))}){
                            @Override
                            public void released(){
                                changeBackToGarage();
                            }
                        });
                        
                    } catch(Exception e){
                        ErrorLogger.logError(e,"GamePanel overriden in GaragePanel; error using font");
                    }
                 }
             }
         });
         frame.setVisible(true);
         
         frame.addKeyListener(gamePanel);
         
         frame.requestFocus();
    }
    
//    private void removeAllFromFrame(){
//        Component[] c=frame.getComponents();
//        for(int i=0;i<c.length;i++)
//            
//    }
    
    public void drawPauseMenu(Graphics p){
        
    }
    
    public void changeBackToGarage(){
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.add(this);
        frame.repaint();
        
        frame.setVisible(true);
        
        gamePanel=null;
    }
}
