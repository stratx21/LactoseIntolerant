/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Josh
 */
public class GaragePanel extends CPanel /*implements MouseListener*/{
    public int[] FRAME_SIZE={StartGameFlow.FRAME_SIZE[0],StartGameFlow.FRAME_SIZE[1]};
       
    private JFrame frame=null;
    
    private GamePanel gamePanel=null;
    
    public boolean missionSuccessCalculated=false;
    
    private OptionsMenu optionsMenu=null;
    
    public int currentCar=0,currentCarDisplayed=0;
    
    public int[][] equipped=new int[5][2];
    
    //speed, health, boost, machine gun, missiles
    
    public CButton[][] upgradesButtons=new CButton[3][6];
    public CButton[] upgradesCarTypes=new CButton[5],
            teamButtons=null;
    
    
     
    private double currentPriceToDisplay=0.0;
    
    
    
    public CButton prev=null,next=null,play=null;
    public CButton[] bottomButtons=new CButton[4];
    
    public CButton menu0=null,menu1=null,menu2=null,menu3=null;
    
    public int missionIndex=1,allowedLevels=1;
    
    private String teamDisplay=null;
    
    private boolean showDeleteConfirmation=false,clickedDeleteOnce=false;
    
    public String toSplitString="::::";
    
    public static final int[] rewards=new int[]{
        22000,32000,50000,85000,98000,125000,160000,210000,305000,420000,
        750000,1250000,1850000,2750000,
    };
    /**
     * Array of type String that contains the mission prompts for each mission
     */
    public String[] missionInfo=new String[]{
        "Mission 1: ::::"
            + "You are running away and taking::::"
            + "some of your belongings with ::::"
            + "you, such as a gun, some money,::::"
            + "and your sudan. If you successfully::::"
            + "escape you can keep the $22,000::::"
            + "that you have stored in your safe.",
        "Mission 2: ::::"
            + "Billy John Doe wants you to transport::::"
            + "his mechanic to the garage for::::"
            + "confidential purposes; he did not::::"
            + "give any details but the location.::::"
            + "Doe will pay you $32,000 to do so::::"
            + "and the mechanic may fix your old::::"
            + "sudan while he’s with Doe. ",
        "Mission 3: ::::"
            + "Billy John Doe wants you to::::"
            + "transport a weapons expert to::::"
            + "the garage since he has an::::"
            + "agreement with him. He will pay::::"
            + "you $50,000 to do so and he will::::"
            + "be able to upgrade your car with::::"
            + "weapon capabilities for a price.",
        "Mission 4: ::::"
            + "The mechanic needs you to transport::::"
            + "his tools from his place. He is::::"
            + "willing to pay you $85,000 for::::"
            + "doing the job.",
        "Mission 5: ::::"
            + "Billy John Doe has seen your great::::"
            + "driving skills and will now hire::::"
            + "you as his getaway driver. He is::::"
            + "robbing Sir WIlliam Sheep, the::::"
            + "Mayor of SheepMania. You will go::::"
            + "to a small town near by where::::"
            + "he keeps his prized sheep to sell::::"
            + "on the black market in Sheepmania.::::"
            + "The cut for the job is $98000 if::::"
            + "you can drive Doe's crew far enough::::"
            + "away by the time anyone notices the::::"
            + "missing sheep.",
        "Mission 6: ::::"
            + "Billy John Doe wants you to bring::::"
            + "back some medical supplies from a::::"
            + "friend at St. Patrick's Hospital.::::"
            + "He will pay you $125,000 for the job.",
        "Mission 7: ::::"
            + "Billy Doe realized that he cannot ::::"
            + "do anything without at first having ::::"
            + "some knowledge about what he is ::::"
            + "doing, so he wants you to transport ::::"
            + "a doctor who knows how to use the ::::"
            + "medical equipment. He is willing to::::"
            + "give $160,000 for this job, but you::::"
            + "must transport him before the time::::"
            + "runs out!",
        "Mission 8: ::::"
            + "An Ultimate Speed derby is being held.::::"
            + "You will be driving for a time along a::::"
            + "straight track. You’ll get paid $210,000::::"
            + "for completing the mission.",
        "Mission 9: ::::"
            + "Billy John Doe needs you to transport::::"
            + "weapons for his next job. Like last ::::"
            + "time, all you need to do is drive::::"
            + "the getaway car, but this time to::::"
            + "earn a whopping $305,000.",
        "MIssion 10: ::::"
            + "The Sheep collection jewelry is ::::"
            + "being delivered to Sheepmania’s ::::"
            + "top jewelry store, Sheep Glamour ::::"
            + "Galore. Like last time, just drive::::"
            + "the getaway car straight back to::::"
            + "the garage to earn $420,000.",
        "Mission 11: ::::"
            + "Billy John Doe wants you to transport::::"
            + "some mysterious material from a::::"
            + "far away town named Little Lamb. ::::"
            + "Doe said the mission would be so ::::"
            + "easy that the payment of $750,000 ::::"
            + "makes it sound too good to be true.",
        "Mission 12: ::::"
            + "Billy John Doe feels bad for having ::::"
            + "mislead you on how easy the job was::::"
            + "going to be, so to make it up to::::"
            + "you he has entered you in a timed::::"
            + "street race. The prize money of::::"
            + "$1,250,000 is all yours if you win. ",
        "MIssion 13: ::::"
            + "The weapons expert was so impressed::::"
            + "by your driving that he is willing::::"
            + "to give you a temporary job of ::::"
            + "transporting weapons for him. He gave::::"
            + "you the map to where the drop off will::::"
            + "be, and tells you that the payment for::::"
            + "the job is $1,850,000.",
        "Mission 14: ::::"
            + "Before leaving from a recent delivery,::::"
            + "Billy John Doe called and asked you to::::"
            + "pick up some packages for him and then::::"
            + "return to the garage to meet him there.::::"
            + "Curiously, you open a box and see that::::"
            + "one is a time bomb counting down. From::::"
            + "the looks of it you have 2 minutes to::::"
            + "return to the garage if you want a chance::::"
            + "of surviving, and once you do your::::"
            + "getting paid $2,750,000.",
        "Mission 15: ::::"
            + "With everything gathered Billy has one::::"
            + "final job for you. HE wants you to::::"
            + "deliver one of the time bombs to the::::"
            + "corrupt mayor of Sheepmania since once::::"
            + "he is out of the way Billy John Doe can::::"
            + "take his place and fix this city. He is::::"
            + "willing to pay you $3,750,000 dollars to::::"
            + "do so.",
        "Mission 16: ::::"
            + "The STC kidnapped Billy John Doe and you::::"
            + "just broke him out. If you are to::::"
            + "successfully save him he will pay you::::"
            + "$5,000,000."
            
            
    }; //\n not working with Graphics.drawString
    
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
    
    /**
     * initialize the garage panel and set it up in the Container 
     * javax.swing.JFrame passed in as JFrame f
     * 
     * @param f the javax.swing.JFrame Container that is used for the game
     * @param level the level that the user is currently on.
     */
    public GaragePanel(JFrame f,int level){
        System.out.println("setting up GaragePanel...");
        
        for(int i=0;i<Profile.completedMissions.length;i++)
            if(Profile.completedMissions[i])
                level=i;
        
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
        
        if(missionIndex==missionInfo.length)
            next.disable(true);
        else
            next.disable(false);
        
        if(missionIndex>allowedLevels)
            play.disable(true);
        else
            play.disable(false);
    }
    
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
    
    
    @Override
    public void paintComponent(Graphics p){
        
        if(shouldCheck){
            setBoughtVisibility();
            System.out.println("aaaaaaaaaaaaaaa");
            shouldCheck=false;
        }
        
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
                    p.setFont(Font.createFont(Font.TRUETYPE_FONT,GaragePanel.class.getResource("/Fonts/AA_typewriter.ttf").openStream()).deriveFont((float)(0.025714*FRAME_SIZE[1])));
                    int y=getNewSizeY(0.19),addToY=getNewSizeY(0.025);
//                    y=(int)(FRAME_SIZE[1]*.6);
                    for(int i=0;i<lines.length;i++){
                        p.drawString(lines[i],getNewSizeX(0.235),y+=addToY);
                        
//                        p.drawString(lines[i],(int)(FRAME_SIZE[0]*1.2),y);
//                        y+=(int)(FRAME_SIZE[1]*.1);
                    }
                }catch(IOException | FontFormatException e){
                    ErrorLogger.logError(e,"paintComponent - GaragePanel");
                }
                break;
            case 1: //upgrades
                try{
                    p.setColor(Color.black);
                    p.setFont(Font.createFont(Font.TRUETYPE_FONT,GaragePanel.class.getResource("/Fonts/Square.ttf").openStream()).deriveFont((float)(0.036*FRAME_SIZE[1])));
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
                        p.setFont(Font.createFont(Font.TRUETYPE_FONT,GaragePanel.class.getResource("/Fonts/Square.ttf").openStream()).deriveFont((float)(0.045714*FRAME_SIZE[1])));
                        p.drawString(formatMoney(currentPriceToDisplay),getNewSizeX(0.405),getNewSizeY(0.187));
                    }
                    
                    if(equipped[currentCar][1]>1){
                        p.setColor(gold);
                        p.drawRect(getNewSizeX((395+135*equipped[currentCar][0])/1000.0),getNewSizeY((220+(equipped[currentCar][1])*50)/1000.0),getNewSizeX(0.073),getNewSizeY(0.040));
                    }
                    
                    
                }catch(IOException | FontFormatException e){
                    ErrorLogger.logError(e,"paintComponent, case 1 - GaragePanel");
                }
                break;
            case 2: //team
                //add a display for String teamDisplay, to show info such as please fire one team member before hiring another, prices, etc
                p.setColor(new Color(127,127,127));
                p.fillRect(getNewSizeX(0.390),getNewSizeY(0.155)-5,getNewSizeX(0.175),getNewSizeY(0.035)+10);
                
                try{
                    if(teamDisplay!=null){
                        p.setColor(gold);
                        p.setFont(Font.createFont(Font.TRUETYPE_FONT,GaragePanel.class.getResource("/Fonts/Square.ttf").openStream()).deriveFont((float)(0.045714*FRAME_SIZE[1])));
                        p.drawString(teamDisplay,getNewSizeX(0.405),getNewSizeY(0.187));
                    }
                    
                    if(showDeleteConfirmation){
                        p.setColor(Color.red);
                        p.setFont(Font.createFont(Font.TRUETYPE_FONT,GaragePanel.class.getResource("/Fonts/Square.ttf").openStream()).deriveFont((float)(0.025714*FRAME_SIZE[1])));
                        p.drawString("click again if you wish to delete this member from your team.",getNewSizeX(0.35),getNewSizeY(0.35));
                    }
                } catch(IOException | FontFormatException e){
                    ErrorLogger.logError(e,"GaragePanel.paintComponent(java.awt.Graphics) : case 2, team, Font");
                }
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
                prev=null;
                next=null;
                play=null;
                break;
            case 1:
                removeAllUpgradesButtons();
                break;
            case 2:
                for(int i=0;i<teamButtons.length;i++)
                    if(teamButtons[i]!=null){
                        this.remove(teamButtons[i]);
                        teamButtons[i]=null;
                    }
                break;
            case 3:
                this.remove(menu0);
                this.remove(menu1);
                this.remove(menu2);
                this.remove(menu3);
                
                menu0=null;
                menu1=null;
                menu2=null;
                menu3=null;
                
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
                addTeamComponents();
                setBoughtVisibility();
                break;
            case 3:
                setMenuButtonComponents();
                break;
        }
        repaint();
    }
    
    
    private void removeAllUpgradesButtons(){
        for(int i=0;i<6;i++)
            for(int j=0;j<3;j++)
                if(upgradesButtons[j][i]!=null)
                    this.remove(upgradesButtons[j][i]);
        
        for(int i=0;i<5;i++)
            this.remove(upgradesCarTypes[i]);
    }
    
    boolean b=false,shouldCheck=false;
    private void addTeamComponents(){
        TeamManager.maxTeam=currentCar+1;
        teamButtons=new CButton[12];//owned team plus 6 to choose from to buy
        
        final int sideLength=166,between=15;
        
        //owned
        for(int i=0;i<6;i++)
            this.add(teamButtons[i]=new CButton(FRAME_SIZE[0]/2-(3-i)*(sideLength+between),100,sideLength,sideLength,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(i<TeamManager.ownedTeamType.size()?(94+TeamManager.ownedTeamType.get(i)):94)),
                                new ImageIcon(GraphicsAssets.getImage(i<TeamManager.ownedTeamType.size()?(100+TeamManager.ownedTeamType.get(i)):100))},
                false,i,false){

                @Override
                public void released(){
                    if(clickedDeleteOnce){
                        TeamManager.ownedTeamType.remove((int)ID);
                        setBoughtVisibility();
                        clickedDeleteOnce=false;
                    } else clickedDeleteOnce=true;
                    rpnt();
                }
                
                @Override
                public void entered(){
                    showDeleteConfirmation=true;
                    rpnt();
                }
                
                @Override
                public void exited(){
                    showDeleteConfirmation=false;
                    clickedDeleteOnce=false;
                    rpnt();
                }
            });
        
        //possible to buy
        for(int i=0;i<6;i++)
            this.add(teamButtons[i+6]=new CButton(FRAME_SIZE[0]/2-(3-i)*(sideLength+between),400,sideLength,sideLength,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(94+i)),
                                new ImageIcon(GraphicsAssets.getImage(100+i))},
                false,i,false){

                @Override
                public void released(){
                    int a=0;
                    for(int i=0;i<Profile.boughtCars.length;i++)//determine how many team members the user can hold (based on the top car bought), stored in a
                        if(Profile.boughtCars[i])
                            a=i+1;
                    if(TeamManager.ownedTeamType.size()<a+1){//can buy another
                        System.out.println(TeamManager.ownedTeamType);
                        if(Profile.money>=Profile.teamPrices[ID]){//has enough money to buy
                            Profile.money-=Profile.teamPrices[ID];
                            TeamManager.ownedTeamType.add(ID);
                            CButton t;
                            (t=teamButtons[TeamManager.ownedTeamType.size()-1]).icons=new ImageIcon[]{
                                new ImageIcon(GraphicsAssets.getImage(94+ID)),
                                new ImageIcon(GraphicsAssets.getImage(100+ID))};
                            t.repaint();
                            setBoughtVisibility();
                        }
                    } else{//cannot buy another; they are at the max number of team members for the car they havev attained
                        
                    }  
                    rpnt();
                }
                
                @Override
                public void entered(){
                    teamDisplay=Profile.teamPrices[ID]+"";
                    rpnt();
                }
                
                @Override
                public void exited(){
                    teamDisplay=null;
                    rpnt();
                }
            });
        
        //getNewSizeX(150+i*(sideLength+between)),getNewSizeY(0.350),getNewSizeX(sideLength),getNewSizeY(sideLength),
//        System.out.println("TeamManager.ownedTeamType.size()="+TeamManager.ownedTeamType.size());
        setBoughtVisibility();
//        System.out.println("after check");
        shouldCheck=true;
        
        this.repaint();
    }
    
    private void setBoughtVisibility(){
        CButton a;
        for(int i=0;i<TeamManager.ownedTeamType.size();i++){
            (a=teamButtons[i]).setVisible(true);
            a.repaint();
        }
        
        if(TeamManager.ownedTeamType.size()>0)
            for(int i=TeamManager.ownedTeamType.size()-1;i<6;i++){ //if the owned member does not exist then the button should be invisible
                (a=teamButtons[i]).setVisible(false);
                a.repaint();
            }
        
        else if(shouldCheck){
            for(int i=TeamManager.ownedTeamType.size();i<6;i++){ //if the owned member does not exist then the button should be invisible
                (a=teamButtons[i]).setVisible(false);
                a.repaint();
            }
        }
    }
    
    ///upgradesCarTypes
    private void addUpgradesComponents(){
        //sudan
        this.add(upgradesCarTypes[0]=new CButton(getNewSizeX(0.142),getNewSizeY(0.015),getNewSizeX(0.144),getNewSizeY(0.072),
            Profile.boughtCars[0] ? new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(82)),new ImageIcon(GraphicsAssets.getImage(87))}
                    :new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(72)),new ImageIcon(GraphicsAssets.getImage(77))},
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
            Profile.boughtCars[1] ? new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(83)),new ImageIcon(GraphicsAssets.getImage(88))}
                    :new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(73)),new ImageIcon(GraphicsAssets.getImage(78))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                if(Profile.boughtCars[1]){
                    currentCar=1;
                    removeAllUpgradesButtons();
                    addUpgradesComponents();
                } else if(Profile.carPrices[1]<=Profile.money){//has enough to buy it
                    Profile.money-=Profile.carPrices[1];
                    Profile.boughtCars[1]=true;
                    currentCar=1;
                    removeAllUpgradesButtons();
                    addUpgradesComponents();
                }
                repaint();
            }
            
            @Override
            public void entered(){
                if(!Profile.boughtCars[1]){
                    currentPriceToDisplay=Profile.carPrices[1];
                    rpnt();
                }
            }

            @Override
            public void exited(){
                currentPriceToDisplay=0;
                rpnt();
            }
        });
        
        //racecar
        this.add(upgradesCarTypes[2]=new CButton(getNewSizeX(0.430),getNewSizeY(0.015),getNewSizeX(0.144),getNewSizeY(0.072),
            Profile.boughtCars[2] ? new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(84)),new ImageIcon(GraphicsAssets.getImage(89))}
                    :new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(74)),new ImageIcon(GraphicsAssets.getImage(79))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                if(Profile.boughtCars[2]){
                    currentCar=2;
                    removeAllUpgradesButtons();
                    addUpgradesComponents();
                } else if(Profile.carPrices[2]<=Profile.money){//has enough to buy it
                    Profile.money-=Profile.carPrices[2];
                    Profile.boughtCars[2]=true;
                    currentCar=2;
                    removeAllUpgradesButtons();
                    addUpgradesComponents();
                }
                repaint();
            }
            
            @Override
            public void entered(){
                if(!Profile.boughtCars[2]){
                    currentPriceToDisplay=Profile.carPrices[2];
                    rpnt();
                }
            }

            @Override
            public void exited(){
                currentPriceToDisplay=0;
                rpnt();
            }
        });
        
        
        //armored van
        this.add(upgradesCarTypes[3]=new CButton(getNewSizeX(0.574),getNewSizeY(0.015),getNewSizeX(0.144),getNewSizeY(0.072),
            Profile.boughtCars[3] ? new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(85)),new ImageIcon(GraphicsAssets.getImage(90))}
                    :new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(75)),new ImageIcon(GraphicsAssets.getImage(80))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                if(Profile.boughtCars[3]){
                    currentCar=3;
                    removeAllUpgradesButtons();
                    addUpgradesComponents();
                } else if(Profile.carPrices[3]<=Profile.money){//has enough to buy it
                    Profile.money-=Profile.carPrices[3];
                    Profile.boughtCars[3]=true;
                    currentCar=3;
                    removeAllUpgradesButtons();
                    addUpgradesComponents();
                }
                repaint();
            }
            
            @Override
            public void entered(){
                if(!Profile.boughtCars[3]){
                    currentPriceToDisplay=Profile.carPrices[3];
                    rpnt();
                }
            }

            @Override
            public void exited(){
                currentPriceToDisplay=0;
                rpnt();
            }
        });
        
        
        //tank
        this.add(upgradesCarTypes[4]=new CButton(getNewSizeX(0.718),getNewSizeY(0.015),getNewSizeX(0.144),getNewSizeY(0.072),
            Profile.boughtCars[4] ? new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(86)),new ImageIcon(GraphicsAssets.getImage(91))}
                    :new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(76)),new ImageIcon(GraphicsAssets.getImage(81))},
            new ImageIcon(GraphicsAssets.getImage(39)),false){
                
            @Override
            public void released(){
                if(Profile.boughtCars[4]){
                    currentCar=4;
                    removeAllUpgradesButtons();
                    addUpgradesComponents();
                } else if(Profile.carPrices[4]<=Profile.money){//has enough to buy it
                    Profile.money-=Profile.carPrices[4];
                    Profile.boughtCars[4]=true;
                    currentCar=4;
                    removeAllUpgradesButtons();
                    addUpgradesComponents();
                }
                repaint();
            }
            
            @Override
            public void entered(){
                if(!Profile.boughtCars[4]){
                    currentPriceToDisplay=Profile.carPrices[4];
                    rpnt();
                }
            }

            @Override
            public void exited(){
                currentPriceToDisplay=0;
                rpnt();
            }
        });
        
        upgradesCarTypes[currentCar].setPernamantSelect(true);
        
        
        //buy upgrades for the car::
        
        //400+j*135,225+i*50,63,20,
        int upWidth=(int)(0.063*FRAME_SIZE[0]),
            upHeight=(int)(0.04*FRAME_SIZE[1]);
        CButton temp;
        for(int i=0;i<6;i++)
            for(int j=0;j<3;j++){
                if(Profile.prices[currentCar][j][i]!=0){
                    this.add(temp=new CButton(getNewSizeX((400+j*135)/1000.0),getNewSizeY((222+i*50)/1000.0),upWidth,upHeight,
                        new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(57)),
                                new ImageIcon(GraphicsAssets.getImage(57))},false){
                                    @Override
                                    public void released(){
                                        if(!Profile.upgrades[currentCar][xIndex][yIndex]){
                                            double t;
                                            if(Profile.money>=(t=Profile.prices[currentCar][xIndex][yIndex])
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
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(92)),
                            new ImageIcon(GraphicsAssets.getImage(93))}
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
    
    //overriden to go back to the main menu
    public void backToMainMenu(){}
    
    public void rmv(Component c){
        this.remove(c);
    }
    
    public void addThis(){
        frame.add(this);
    }
    
    private void addNextPrevPlayButtons(){  // x 63, y  24
        
        System.out.println(super.getNewSizeX(0.025));
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
    
    private final Color gold =new Color(255,215,0);
    
    private void drawMoneyDisplay(Graphics p){
        try{
            p.setColor(gold);
            p.setFont(Font.createFont(Font.TRUETYPE_FONT,GaragePanel.class.getResource("/Fonts/AA_typewriter.ttf").openStream()).deriveFont((float)(0.025714*FRAME_SIZE[1])));
            p.drawString(formatMoney(Profile.money),getNewSizeX(0.005),getNewSizeY(0.018)+10);
        } catch(IOException | FontFormatException e){
            ErrorLogger.logError(e,"drawMoneyDisplay(Graphics) - GaragePanel");
        }
    }
    
    public boolean missionSuccess=false;
    
    private boolean roundDone=false,died=false,justStarting=true;
    private final int frameRateMillisecondsInGame=20;
    public long time=0,lastTime=0,thisTime=0,
            lastNeededTime=0,yourTime=0;
    public int frameRate=0;
    public boolean gameDisabled=false;
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
                public void actionPerformed(boolean a){
                    if(!missionSuccessCalculated){
                        if(missionSuccess=!(died=a)){//if player did not die, then the success depends on the time.
                            missionSuccess=(yourTime=gamePanel.time-3000)<=(lastNeededTime=gamePanel.map.currentLevel.objectiveTime);
                        }
                        
                        if(missionSuccess&&(allowedLevels<gamePanel.level+1))
                            allowedLevels=gamePanel.level+1;
                        roundDone=true;
                        missionSuccessCalculated=true;
                        
                        if(missionSuccess){
                            if(missionIndex<15){
                                missionIndex++;
                                AudioAssets.play("Beat A Level");
                            } else{
                                AudioAssets.music.stop();
                                AudioAssets.play("ylvis_the_fox");
                            }
                        }
                    }
                }
                    },
                w,currentCar){
                     @Override
                     public void paintComponent(Graphics p){
                        long t;
                        time+=(t=System.currentTimeMillis())-lastTime;

                        lastTime=t;

                        gamePanel.paintC(p);

                        if(!gamePanel.paused)
                            gamePanel.calcFlow();
                        else{
                            if(justStarting&&!gameDisabled){
                                
                                
                            if((175-(time)/20)>0)
                            p.setColor(new Color(0,0,0,(int)(175-(time)/20)));
                            p.fillRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);

                            try{
                                p.setColor(Color.black);
                                p.setFont(Font.createFont(Font.TRUETYPE_FONT,GaragePanel.class.getResource("/Fonts/straight.ttf").openStream()).deriveFont(48f));
                                p.drawString((3-(int)(time/1000))+"",450,300);
                            } catch(FontFormatException | IOException e){
                                ErrorLogger.logError(e,"GamePanel overriden in GaragePanel; error using font");
                            }

                            if(time>3000){
                                gamePanel.paused=false;
                                justStarting=false;
                            }
                            } else{ //pause menu
                                drawPauseMenu(p);
                            }
                        }
                        
                        thisTime=System.currentTimeMillis()-lastTime;

                        int delay=0;
                        if(thisTime<frameRateMillisecondsInGame){
                            delay=frameRateMillisecondsInGame-(int)thisTime;
                        }

                        if(delay>frameRateMillisecondsInGame){
                            delay=20;
                        }

                        //frameRate=1000/delay;
                        try{Thread.sleep(delay);}
                        catch(Exception e){ErrorLogger.logError(e,"GameFlow.paintComponent");}

                         if(!roundDone&&!gameDisabled)
                            repaint();
                         else{
                            p.setColor(new Color(0,0,0,215));
                            p.fillRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);

                            try{
                                String a;
                                double s=0.0;
                                if(!died){
                                    if(missionSuccess){
                                        Profile.money+=s=(rewards[missionIndex]/2+rewards[missionIndex]*(player.health/player.startingHealth));
                                        Profile.completedMissions[missionIndex]=true;
                                    }
                                    a=missionSuccess ? "You won!":"You lost!";
                                }else
                                    a="You died!";

                                p.setColor(Color.white);
                                p.setFont(Font.createFont(Font.TRUETYPE_FONT,GaragePanel.class.getResource("/Fonts/straight.ttf").openStream()).deriveFont(24f));
                                p.drawString(a,400,300);
                                if(!died){
                                    p.drawString("Needed time::     "+lastNeededTime/1000+" s",400,350);
                                    p.drawString("Your time::       "+yourTime/1000+" s",400,400);
                                    p.drawString("Earned money::   $"+formatMoney(s),400,450);
                                }

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
         
         gamePanel.FRAME_SIZE=FRAME_SIZE;
    }
    
//    private void removeAllFromFrame(){
//        Component[] c=frame.getComponents();
//        for(int i=0;i<c.length;i++)
//    }
    
    public void drawPauseMenu(Graphics p){
        System.out.println("setting up pause menu 1");
        PauseMenu t=new PauseMenu(new CListener(){
                @Override
                public void actionPerformed(boolean a){
                    System.out.println("reached action performed...");
                    if(a)
                        backToGamePanel();
                    else
                        changeBackToGarage();
                }
            },
                frame);
        gamePanel.shouldDisable=false;
    }
    
    private void backToGamePanel(){
        System.out.println("back to game panel!!!");
        gameDisabled=false;
        gamePanel.paused=false;
        
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.add(gamePanel);
        frame.repaint();
        
        frame.addKeyListener(gamePanel);
        
        frame.setVisible(true);
        
        gamePanel.repaint();
    }
    
    /**
     * change the <code>JPanel</code> object displayed by the game's
     * <code>JFrame</code> object from gamePanel back to GaragePanel
     */
    public void changeBackToGarage(){
        missionSuccessCalculated=false;
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.add(this);
        frame.repaint();
        
        frame.setVisible(true);
        
        gamePanel=null;
    }
}
