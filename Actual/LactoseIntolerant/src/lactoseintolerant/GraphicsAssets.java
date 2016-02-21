/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author 0001058857
 */
public class GraphicsAssets {
    
    public static ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
    
    
    
    /**
     * 
     */
    public static void importImages(){
        try{
            System.out.println("importing images...");
            //opening images::
            images.add(importFromString("Graphics/WizzardsOpening-01.png"));//0
            images.add(importFromString("Graphics/openingName-01.png"));//1
            
            //Player cars:: 
            images.add(importFromString("Graphics/SudanGame.png"));//2
            images.add(importFromString("Graphics/VanGame.png"));//3
            images.add(importFromString("Graphics/RaceCarGame.png"));//4
            images.add(importFromString("Graphics/ArmoredVanGame.png"));//5
            images.add(importFromString("Graphics/TankGame.png"));//6
            images.add((BufferedImage)(null));//7
            images.add((BufferedImage)(null));//8
            images.add((BufferedImage)(null));//9
            
            
            //maps:: 
            images.add(importFromString("Graphics/straightGrass.png"));//10 - straight
            images.add(importFromString("Graphics/medianGrass.png"));//11 - median
            images.add(importFromString("Graphics/straightGrass.png"));//12 - intersection
            
            images.add(importFromString("Graphics/straightDesert.png"));//13
            images.add(importFromString("Graphics/mediandesert.png"));
            images.add(importFromString("Graphics/straightDesert.png"));//15
            
            images.add(importFromString("Graphics/straightRock.png"));//16
            images.add(importFromString("Graphics/medianstone.png"));
            images.add(importFromString("Graphics/straightRock.png"));//18
            
            images.add(importFromString("Graphics/straightRed.png"));//19
            images.add(importFromString("Graphics/medianred.png"));
            images.add(importFromString("Graphics/straightRed.png"));//21
            
            //Civilian cars::
            images.add(importFromString("Graphics/RaceCarGameCiv.png"));//22
            images.add(importFromString("Graphics/VanGameCiv.png"));//23
            images.add(null);//24
            
            //explosion::
            images.add(importFromString("Graphics/RadialExplosion1.png"));//25
            
            images.add(importFromString("Graphics/IceCreamTruck.png"));//26
            
            //main menu::
            images.add(importFromString("Graphics/MainMenu-01.png"));//27
            
            images.add(importFromString("Graphics/exit0.png"));//28
            images.add(importFromString("Graphics/exit1.png"));//29
            images.add(importFromString("Graphics/new0.png"));//30
            images.add(importFromString("Graphics/new1.png"));//31
            images.add(importFromString("Graphics/options0.png"));//32
            images.add(importFromString("Graphics/options1.png"));//33
            images.add(importFromString("Graphics/start0.png"));//34
            images.add(importFromString("Graphics/start1.png"));//35
            
            //garage menu::
            images.add(importFromString("Graphics/MissionBoard.png"));//36
            
            images.add(importFromString("Graphics/prev1.png"));//37
            images.add(importFromString("Graphics/prevSelected1.png"));//38
            images.add(importFromString("Graphics/prevDisabled1.png"));//39
            images.add(importFromString("Graphics/next1.png"));//40
            images.add(importFromString("Graphics/nextSelected1.png"));//41
            images.add(importFromString("Graphics/nextDisabled1.png"));//42
            
            images.add(importFromString("Graphics/missions0.png"));//43
            images.add(importFromString("Graphics/missions1.png"));//44
            
            images.add(importFromString("Graphics/upgrades0.png"));//45
            images.add(importFromString("Graphics/upgrades1.png"));//46
            
            images.add(importFromString("Graphics/team0.png"));//47
            images.add(importFromString("Graphics/team1.png"));//48
            
            images.add(importFromString("Graphics/menu0.png"));//49
            images.add(importFromString("Graphics/menu1.png"));//50
            
            images.add(importFromString("Graphics/backgroundGarage.png"));//51
            
            images.add(importFromString("Graphics/play1.png"));//52
            images.add(importFromString("Graphics/playSelected1.png"));//53
            images.add(importFromString("Graphics/playDisabled1.png"));//54
            
            images.add(importFromString("Graphics/continue.png"));//55
            images.add(importFromString("Graphics/continueSelected.png"));//56
            
            
            //upgrades menu upgrades bought/unbought::
            images.add(importFromString("Graphics/unbought.png"));//57
            images.add(importFromString("Graphics/bought.png"));//58
            
            //Garage Menu tab::
            images.add(importFromString("Graphics/SaveQuit00.png"));//59
            images.add(importFromString("Graphics/SaveQuit01.png"));//60
            images.add(importFromString("Graphics/QuitWithoutSaving00.png"));//61
            images.add(importFromString("Graphics/QuitWithoutSaving01.png"));//62
            images.add(importFromString("Graphics/Save00.png"));//63
            images.add(importFromString("Graphics/Save01.png"));//64
            
            //ok button::
            images.add(importFromString("Graphics/ok0.png"));//65
            images.add(importFromString("Graphics/ok1.png"));//66
            
            //projectiles::
            images.add(importFromString("Graphics/Bullet.png"));//67
            images.add(importFromString("Graphics/mine.png"));//68
            images.add(importFromString("Graphics/Missile.png"));//69
            images.add(importFromString("Graphics/mine1.png"));//70
            
            //options menu background::
            images.add(importFromString("Graphics/Background.png"));//71
            
            //side views of the player cars::
            images.add(importFromString("Graphics/Sudan.png"));//72
            images.add(importFromString("Graphics/Van.png"));//73
            images.add(importFromString("Graphics/RaceCar.png"));//74
            images.add(importFromString("Graphics/ArmoredVan.png"));//75
            images.add(importFromString("Graphics/Tank.png"));//76
            
            images.add(importFromString("Graphics/Sudan1.png"));//77
            images.add(importFromString("Graphics/Van1.png"));//78
            images.add(importFromString("Graphics/RaceCar1.png"));//79
            images.add(importFromString("Graphics/ArmoredVan1.png"));//80
            images.add(importFromString("Graphics/Tank1.png"));//81
            
            //side view owned::
            images.add(importFromString("Graphics/OwnedSudan.png"));//82
            images.add(importFromString("Graphics/OwnedVan.png"));//83
            images.add(importFromString("Graphics/OwnedRaceCar.png"));//84
            images.add(importFromString("Graphics/OwnedArmoredVan.png"));//85
            images.add(importFromString("Graphics/OwnedTank.png"));//86
            
            images.add(importFromString("Graphics/OwnedSudan1.png"));//87
            images.add(importFromString("Graphics/OwnedVan1.png"));//88
            images.add(importFromString("Graphics/OwnedRaceCar1.png"));//89
            images.add(importFromString("Graphics/OwnedArmoredVan1.png"));//90
            images.add(importFromString("Graphics/OwnedTank1.png"));//91
            
            //options button for in the menu of GarageMenu::
            images.add(importFromString("Graphics/Options00.png"));//92
            images.add(importFromString("Graphics/Options01.png"));//93
            
            //faces::
            images.add(importFromString("Graphics/faceMoneyIncrease1.png"));//94
            images.add(importFromString("Graphics/faceMoneyIncrease2.png"));//95
            images.add(importFromString("Graphics/faceMoneyIncrease3.png"));//96
            images.add(importFromString("Graphics/faceArmorIncrease1.png"));//97
            images.add(importFromString("Graphics/faceArmorIncrease2.png"));//98
            images.add(importFromString("Graphics/faceArmorIncrease3.png"));//99
            
            images.add(importFromString("Graphics/faceMoneyIncrease1_1.png"));//100
            images.add(importFromString("Graphics/faceMoneyIncrease2_1.png"));//101
            images.add(importFromString("Graphics/faceMoneyIncrease3_1.png"));//102
            images.add(importFromString("Graphics/faceArmorIncrease1_1.png"));//103
            images.add(importFromString("Graphics/faceArmorIncrease2_1.png"));//104
            images.add(importFromString("Graphics/faceArmorIncrease3_1.png"));//105
            
        } catch(Exception e){
            ErrorLogger.logError(e,"GraphicsAssets.importImages");
        }
    }
    
    private static BufferedImage importFromString(String loc) throws IOException{
        try{
        InputStream in=GraphicsAssets.class.getResource(loc).openStream();
        if(in!=null)
            return ImageIO.read(in);
        else
            return null;
        } catch(Exception e ){System.out.println("Graphics image failed to be read by the ImageIO::    "+loc);}
        //(BufferedImage)(ImageIO.read(GraphicsAssets.class.getResourceAsStream(loc)));
        return null;
    }
    
    public static BufferedImage getImage(int index){
        return images.get(index);
    }
    
    public static BufferedImage getIcon(){
        BufferedImage i=null;
        try{
            i=importFromString("Graphics/frameIcon.png");
        } catch(IOException e){
            ErrorLogger.logIOError(e,"getIcon() - GraphicsAssets");
        }
        return i;
    }
    
    public static BufferedImage getFirstImage()throws IOException{
        return importFromString("Graphics/WizzardsOpening-01.png");//0 (not added)
    }
    
    public static BufferedImage getSecondImage()throws IOException{
        return importFromString("Graphics/openingName-01.png");//1 (not added)
    }
    
    public static BufferedImage getLogo() throws IOException{
        return importFromString("Graphics/Logo.png");
    }
    
}
