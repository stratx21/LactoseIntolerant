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
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Josh
 */
public class GaragePanel extends CPanel /*implements MouseListener*/{
    public int[] FRAME_SIZE={StartGameFlow.X_SIZE,StartGameFlow.Y_SIZE};
       
    private JFrame frame=null;
    
    private GamePanel gamePanel=null;
    
    private OptionsMenu optionsMenu=null;
    
    public int currentCar=0,currentCarDisplayed=0;
    
    public int[][] equipped=new int[5][2];
    
    //speed, health, boost, machine gun, missiles
    
    public CButton[][] upgradesButtons=new CButton[3][6];
    public CButton[] upgradesCarTypes=new CButton[5];
    
    
     
    private double currentPriceToDisplay=0.0;
    
    
    
    public CButton prev=null,next=null,play=null;
    public CButton[] bottomButtons=new CButton[4];
    
    public CButton menu0=null,menu1=null,menu2=null,menu3=null;
    
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
        System.out.println("setting up GaragePanel...");
        
        frame=f;
        
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.add(this);
        frame.repaint();
        
        frame.setVisible(true);
        
        FRAME_SIZE=new int[]{f.getWidth(),f.getHeight()};
        
        missionIndex=allowedLevels=level;
        
        addLowerModeButtons();
        
        mode=0;
        
//        this.addMouseListener(this);
        repaint();
        switchBottomButtonComponents();
    }
    
    public int getNewSizeX(double a){
        return (int)(a*FRAME_SIZE[0]);
    }
    
    public int getNewSizeY(double a){
        return (int)(a*1.42857*FRAME_SIZE[1]);
    }
    
    private final double BOTTOM_BUTTONS_Y_RATIO=11.0/14.0;
    
    private void addLowerModeButtons(){
//        double o=.96;
        int y=(int)(BOTTOM_BUTTONS_Y_RATIO*FRAME_SIZE[1]);
        int height=FRAME_SIZE[1]-y;
        //missions button::
        //this.add(bottomButtons[0]=new CButton(0,(int)(FRAME_SIZE[1]*3.1-FRAME_SIZE[1]),(int)(FRAME_SIZE[0]*o),((int)FRAME_SIZE[1]),
//        this.add(bottomButtons[0]=new CButton(0,BOTTOM_BUTTONS_Y,250,BOTTOM_BUTTONS_HEIGHT,
        this.add(bottomButtons[0]=new CButton(0,y,FRAME_SIZE[0]/4,height,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(43)),
                                new ImageIcon(GraphicsAssets.getImage(44))}){
                    
            @Override
            public void released(){
                mode=0;
                switchBottomButtonComponents();
            }
        });
        
        //upgrades button::
        //this.add(bottomButtons[1]=new CButton((int)(FRAME_SIZE[0]*o),(int)(FRAME_SIZE[1]*3.1-FRAME_SIZE[1]),(int)((int)FRAME_SIZE[0]*o),((int)FRAME_SIZE[1]),
//        this.add(bottomButtons[1]=new CButton(250,BOTTOM_BUTTONS_Y,250,BOTTOM_BUTTONS_HEIGHT,
        this.add(bottomButtons[1]=new CButton(FRAME_SIZE[0]/4,y,FRAME_SIZE[0]/4,height,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(45)),
                                new ImageIcon(GraphicsAssets.getImage(46))}){
           @Override
           public void released(){
               mode=1;
               switchBottomButtonComponents();
           }
        });
        
        //team button::
        //this.add(bottomButtons[2]=new CButton((int)(FRAME_SIZE[0]*o)*2,(int)(FRAME_SIZE[1]*3.1-FRAME_SIZE[1]),(int)((int)FRAME_SIZE[0]*o),((int)FRAME_SIZE[1]),
//        this.add(bottomButtons[2]=new CButton(500,BOTTOM_BUTTONS_Y,250,BOTTOM_BUTTONS_HEIGHT,
        this.add(bottomButtons[2]=new CButton(FRAME_SIZE[0]/2,y,FRAME_SIZE[0]/4,height,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(47)),
                                new ImageIcon(GraphicsAssets.getImage(48))}){
           @Override
           public void released(){
               mode=2;
               switchBottomButtonComponents();
           }
        });
        
        
        //menu button::
        //this.add(bottomButtons[3]=new CButton((int)(FRAME_SIZE[0]*o)*3,(int)(FRAME_SIZE[1]*3.1-FRAME_SIZE[1]),(int)((int)FRAME_SIZE[0]*o),((int)FRAME_SIZE[1]),
//        this.add(bottomButtons[3]=new CButton(750,BOTTOM_BUTTONS_Y,250,BOTTOM_BUTTONS_HEIGHT,
        this.add(bottomButtons[3]=new CButton((int)(FRAME_SIZE[0]*0.75),y,FRAME_SIZE[0]/4,height,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(49)),
                                new ImageIcon(GraphicsAssets.getImage(50))}){
           @Override
           public void released(){
               mode=3;
               switchBottomButtonComponents();
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
        
        p.drawImage(background,0,0,FRAME_SIZE[0],FRAME_SIZE[1],null);
        drawMoneyDisplay(p);
        
        switch(mode){
            case 0: //missions
                updateNextPrevPlay();
                p.drawImage(missionBoard,(int)(0.15*FRAME_SIZE[0]),(int)(0.142*FRAME_SIZE[1]),(int)(0.7*FRAME_SIZE[0]),(int)(0.714*FRAME_SIZE[1]),null);
//                p.drawImage(missionBoard,FRAME_SIZE[0],(int)(FRAME_SIZE[1]*.2),FRAME_SIZE[0]*2,(int)(FRAME_SIZE[1]*3),null);
                
                try{
                    String[] lines=missionInfo[missionIndex-1].split(toSplitString);
                    p.setColor(Color.black);
                    p.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/Fonts/AA_typewriter.ttf")).deriveFont((float)(0.025714*FRAME_SIZE[1])));
                    int y=170,addToY=getNewSizeY(0.05);
//                    y=(int)(FRAME_SIZE[1]*.6);
                    for(int i=0;i<lines.length;i++){
                        p.drawString(lines[i],getNewSizeX(0.235),y+=addToY);
                        
//                        p.drawString(lines[i],(int)(FRAME_SIZE[0]*1.2),y);
//                        y+=(int)(FRAME_SIZE[1]*.1);
                    }
                }catch(Exception e){
                    ErrorLogger.logError(e,"paintComponent - GaragePanel");
                }
                break;
            case 1: //upgrades
                try{
                    p.setColor(Color.black);
                    p.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/Fonts/Square.ttf")).deriveFont((float)(0.036*FRAME_SIZE[1])));
                    int a=FRAME_SIZE[0]/4-50;
                    p.drawString("Speed",a,getNewSizeY(0.240));
                    p.drawString("Armor",a,getNewSizeY(0.290));
                    p.drawString("Boost",a,getNewSizeY(0.340));
                    p.drawString("Auto Gun",a,getNewSizeY(0.390));
                    p.drawString("Mines",a,getNewSizeY(0.440));
                    p.drawString("Missiles",a,getNewSizeY(0.490));
                    
                    
                    p.setColor(new Color(127,127,127));
                    p.fillRect(getNewSizeX(0.390),getNewSizeY(0.155)-5,getNewSizeX(0.175),getNewSizeY(0.035)+10);
                    //price text::
                    if(currentPriceToDisplay>0){
                        p.setColor(gold);
                        p.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/Fonts/Square.ttf")).deriveFont((float)(0.045714*FRAME_SIZE[1])));
                        p.drawString(formatMoney(currentPriceToDisplay),getNewSizeX(0.405),getNewSizeY(0.187));
                    }
                    
                    if(equipped[currentCar][1]>1){
                        p.setColor(gold);
                        p.drawRect(getNewSizeX((395+135*equipped[currentCar][0])/1000.0),getNewSizeY((220+(equipped[currentCar][1])*50)/1000.0),getNewSizeX(0.073),getNewSizeY(0.040));
                    }
                    
                    
                }catch(Exception e){
                    ErrorLogger.logError(e,"paintComponent, case 1 - GaragePanel");
                }
                break;
            case 2: //team
                break;
            case 3: //menu
                p.setColor(new Color(0,0,0,110));
                p.fillRect(0,0,FRAME_SIZE[0]*4,(int)(FRAME_SIZE[1]*2.2-(int)(FRAME_SIZE[1]*.0)));//all the space except where the lower buttons are
                
                break;
        }
    }
    
    private String formatMoney(double money){
        if(money>999999999)
            return "$"+new DecimalFormat("000.00").format(money/1000000000)+" B";
        else if(money>999999)
            return "$"+new DecimalFormat("000.00").format(money/1000000)+" M";
        else if(money>999)
            return "$"+new DecimalFormat("000.00").format(money/1000)+" K";
        else 
            return "$"+new DecimalFormat("000.00").format(money);
    }
    
    private void switchBottomButtonComponents(){
        bottomButtons[oldMode].setPernamantSelect(false);
        bottomButtons[mode].setPernamantSelect(true);
            
        switch(oldMode){
            case 0: 
                if(prev!=null){
                    this.remove(prev);
                    this.remove(next);
                    this.remove(play);
                }
                break;
            case 1:
                removeAllUpgradesButtons();
                break;
            case 2:
                break;
            case 3:
                this.remove(menu0);
                this.remove(menu1);
                this.remove(menu2);
                this.remove(menu3);
                
//                this.revalidate();
                this.repaint();
                break;
        }
        
        switch(oldMode=mode){
            case 0:
                addNextPrevPlayButtons();
                updateNextPrevPlay();
                break;
            case 1:
                addUpgradesComponents();
                break;
            case 2:
                break;
            case 3:
                setMenuButtonComponents();
                break;
        }
        repaint();
    }
    
    //note :: to switch cars, try running removeAllBuyUpgradesButtons, then change currentCarDispalyed to whatever id should be displayed, then run addUpgradesComponents - but will also need to look for the stuff on top deciding which car is chosen, this is not in at all yet. 
    
    
    private void removeAllUpgradesButtons(){
        for(int i=0;i<6;i++)
            for(int j=0;j<3;j++)
                this.remove(upgradesButtons[j][i]);
        
        for(int i=0;i<5;i++)
            this.remove(upgradesCarTypes[i]);
    }
    
    ///upgradesCarTypes
    private void addUpgradesComponents(){
        //sudan
        this.add(upgradesCarTypes[0]=new CButton(getNewSizeX(0.142),getNewSizeY(0.015),getNewSizeX(0.144),getNewSizeY(0.072),
            new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(72)),
                            new ImageIcon(GraphicsAssets.getImage(77))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                currentCar=0;
                removeAllUpgradesButtons();
                addUpgradesComponents();
                repaint();
            }
        });
        
        //van
        this.add(upgradesCarTypes[1]=new CButton(getNewSizeX(0.286),getNewSizeY(0.015),getNewSizeX(0.144),getNewSizeY(0.072),
            new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(73)),
                            new ImageIcon(GraphicsAssets.getImage(78))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                currentCar=1;
                removeAllUpgradesButtons();
                addUpgradesComponents();
                repaint();
            }
        });
        
        
        //racecar
        this.add(upgradesCarTypes[2]=new CButton(getNewSizeX(0.430),getNewSizeY(0.015),getNewSizeX(0.144),getNewSizeY(0.072),
            new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(74)),
                            new ImageIcon(GraphicsAssets.getImage(79))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                currentCar=2;
                removeAllUpgradesButtons();
                addUpgradesComponents();
                repaint();
            }
        });
        
        //armored van
        this.add(upgradesCarTypes[3]=new CButton(getNewSizeX(0.574),getNewSizeY(0.015),getNewSizeX(0.144),getNewSizeY(0.072),
            new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(75)),
                            new ImageIcon(GraphicsAssets.getImage(80))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                currentCar=3;
                removeAllUpgradesButtons();
                addUpgradesComponents();
                repaint();
            }
        });
        
        //tank
        this.add(upgradesCarTypes[4]=new CButton(getNewSizeX(0.718),getNewSizeY(0.015),getNewSizeX(0.144),getNewSizeY(0.072),
            new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(76)),
                            new ImageIcon(GraphicsAssets.getImage(81))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                System.out.println("aaaaaaaaaaaaa");
                currentCar=4;
                removeAllUpgradesButtons();
                addUpgradesComponents();
                repaint();
            }
        });
        
        System.out.println("currentCar=="+currentCar);
        
        upgradesCarTypes[currentCar].setPernamantSelect(true);
        
        
        //buy upgrades for the car::
        
        //400+j*135,225+i*50,63,20,
        int upWidth=(int)(0.063*FRAME_SIZE[0]),
            upHeight=(int)(0.04*FRAME_SIZE[1]);
        CButton temp;
        for(int i=0;i<6;i++)
            for(int j=0;j<3;j++){
                this.add(temp=new CButton(getNewSizeX((400+j*135)/1000.0),getNewSizeY((222+i*50)/1000.0),upWidth,upHeight,
                    new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(57)),
                            new ImageIcon(GraphicsAssets.getImage(57))},false){
                                @Override
                                public void released(){
                                    if(!Profile.upgrades[currentCar][xIndex][yIndex]){
                                        double t;
                                        if(Profile.money>(t=Profile.prices[currentCar][xIndex][yIndex])
                                                &&(xIndex==0||Profile.upgrades[currentCar][0][yIndex])
                                                &&(xIndex<2||Profile.upgrades[currentCar][1][yIndex])
                                                &&(xIndex<3||Profile.upgrades[currentCar][2][yIndex])){//can buy it
                                            Profile.money-=t;
                                            Profile.upgrades[currentCar][xIndex][yIndex]=true;
                                            
                                            icons=new ImageIcon[]{
                                                new ImageIcon(GraphicsAssets.getImage(58)),
                                                new ImageIcon(GraphicsAssets.getImage(58))
                                                };
                                            
                                            changeIconSizes();
                                            
                                            setIcon(icons[0]);
                                            
                                            if(yIndex>1)
                                                equipped[currentCar]=new int[]{xIndex,yIndex};
                                        }
                                    } else if(yIndex>1){//is a weapon type and is already owned
                                        equipped[currentCar]=equipped[currentCar][0]!=xIndex||equipped[currentCar][1]!=yIndex ? new int[]{xIndex,yIndex} : new int[2];
                                        
                                        repaint();
                                    }
                                    rpnt();
                                }
                                
                                @Override
                                public void entered(){
                                    currentPriceToDisplay=Profile.prices[currentCar][xIndex][yIndex];
                                    rpnt();
                                }
                                
                                @Override
                                public void exited(){
                                    currentPriceToDisplay=0;
                                    rpnt();
                                }
                            }
                            );
                
                temp.xIndex=j;
                temp.yIndex=i;
                
                if(Profile.upgrades[currentCar][j][i]){
                    temp.icons=new ImageIcon[]{
                        new ImageIcon(GraphicsAssets.getImage(58)),
                        new ImageIcon(GraphicsAssets.getImage(58))};
                    temp.changeIconSizes();
                    temp.setIcon(temp.icons[0]);
                }
                
                upgradesButtons[j][i]=temp;
            }
        
        repaint();
    }
    
    
    private void setMenuButtonComponents(){
        //save
        this.add(menu0=new CButton(getNewSizeX(0.375),getNewSizeY(0.03),getNewSizeX(0.250),getNewSizeY(0.1),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(63)),
                            new ImageIcon(GraphicsAssets.getImage(64))}
                ){
                    @Override
                    public void released(){
                        Profile.save();
                    }
            });
        
        //save and quit
        this.add(menu1=new CButton(getNewSizeX(0.375),getNewSizeY(0.16),getNewSizeX(0.250),getNewSizeY(0.100),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(59)),
                            new ImageIcon(GraphicsAssets.getImage(60))}
                ){
                    @Override
                    public void released(){
                        Profile.save();
                        backToMainMenu();
                    }
            });
        
        
        //quit without saving
        this.add(menu2=new CButton(getNewSizeX(0.375),getNewSizeY(0.29),getNewSizeX(0.250),getNewSizeY(0.100),
            new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(61)),
                            new ImageIcon(GraphicsAssets.getImage(62))}
                ){
                    @Override
                    public void released(){
                        backToMainMenu();
                    }
            });
        
        //options
        this.add(menu3=new CButton(getNewSizeX(0.375),getNewSizeY(0.42),getNewSizeX(0.250),getNewSizeY(0.100),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(37)),
                            new ImageIcon(GraphicsAssets.getImage(38))}
                ){
                    @Override
                    public void released(){
                        frame.getContentPane().removeAll();
                        frame.getContentPane().repaint();
                        frame.add(optionsMenu=new OptionsMenu(new CListener(){
                                @Override
                                public void actionPerformed(){
                                    frame.getContentPane().removeAll();
                                    frame.getContentPane().repaint();
                                    addThis();
                                    frame.repaint();
                                    
                                    addLowerModeButtons();
        
                                    mode=oldMode=3;
                                    repaint();
                                    switchBottomButtonComponents();
                                    
                                }
                            },frame));
                        frame.repaint();
                        prev=null;
                        next=null;
                        play=null;
                        for(int i=0;i<bottomButtons.length;i++){
                            rmv(bottomButtons[i]);
                            bottomButtons[i]=null;
                        }
                    }
            });
    }
    
    public void backToMainMenu(){}
    
    public void rmv(Component c){
        this.remove(c);
    }
    
    public void addThis(){
        frame.add(this);
    }
    
    private void addNextPrevPlayButtons(){  // x 63, y  24
        //prev::
        //this.add(prev=new CButton((int)(FRAME_SIZE[0]*.5),(int)(FRAME_SIZE[1]*1.2),(int)(FRAME_SIZE[0]*.4),(int)(FRAME_SIZE[1]*.3),
        this.add(prev=new CButton(getNewSizeX(0.025),getNewSizeY(0.400),getNewSizeX(0.063),getNewSizeY(0.024),
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
        //this.add(next=new CButton((int)(FRAME_SIZE[0]*3.1),(int)(FRAME_SIZE[1]*1.2),(int)(FRAME_SIZE[0]*.4),(int)(FRAME_SIZE[1]*.3),
        this.add(next=new CButton(getNewSizeX(0.912),getNewSizeY(0.400),getNewSizeX(0.063),getNewSizeY(0.024),
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
        //this.add(play=new CButton((int)(FRAME_SIZE[0]*3*.6),(int)(FRAME_SIZE[1]*1.5),(int)(FRAME_SIZE[0]*.4),(int)(FRAME_SIZE[1]*.3),
        this.add(play=new CButton(getNewSizeX(0.600),getNewSizeY(0.350),getNewSizeX(0.125),getNewSizeY(0.059),
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
    
    private Color gold =new Color(255,215,0);
    
    private void drawMoneyDisplay(Graphics p){
        try{
            p.setColor(gold);
            p.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/Fonts/AA_typewriter.ttf")).deriveFont((float)(0.025714*FRAME_SIZE[1])));
            p.drawString(formatMoney(Profile.money),getNewSizeX(0.005),getNewSizeY(0.018)+10);
        } catch(Exception e){
            ErrorLogger.logError(e,"drawMoneyDisplay(Graphics) - GaragePanel");
        }
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
         
        Weapon w=null;
        
        if(equipped[currentCar][1]>1)
            w=new Weapon(equipped[currentCar][1]-2,equipped[currentCar][0]);
        
        frame.add(gamePanel=new GamePanel(new int[]{frame.getWidth(),frame.getHeight()},missionIndex,new CListener(){
                @Override
                public void actionPerformed(){
                    if((yourTime=gamePanel.time-3000)<(lastNeededTime=gamePanel.objectiveTime))
                        missionSuccess=true;
                    else
                        missionSuccess=false;
                    
                    if(missionSuccess&&(allowedLevels<gamePanel.level+1))
                        allowedLevels=gamePanel.level+1;
                    
                    roundDone=true;
                }
                },w){
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
                    p.fillRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);
                    
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
                   if(player.health<0)
                   {
                       missionSuccess=false;
                       gamePanel.time=100000000;
                        p.setColor(new Color(0,0,0,215));
                    p.fillRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);
                    changeBackToGarage();
                          }
//                System.out.println(frameRate+", "+gamePanel.player.speed);
                
                 try{Thread.sleep(delay);}
                 catch(Exception e){ErrorLogger.logError(e,"GameFlow.paintComponent");}
                 
                 if(!roundDone)
                    repaint();
                 else{
                    p.setColor(new Color(0,0,0,215));
                    p.fillRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);
                    
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
