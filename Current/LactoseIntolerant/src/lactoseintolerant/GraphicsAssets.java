/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author 0001058857
 */
public class GraphicsAssets { //EVERYTING shall be static here ;)
    
    public static ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
    
    
    
    /**
     * 
     */
    public static void importImages(){
        try{
            System.out.println("importing images...");
            //opening images::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/OpeningImages/WizzardsOpening-01.png"))));//0
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/OpeningImages/openingName-01.png"))));//1
            
            //Player cars:: 
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/Sudan.png"))));//2
            images.add((BufferedImage)(null));//3
            images.add((BufferedImage)(null));//4
            images.add((BufferedImage)(null));//5
            images.add((BufferedImage)(null));//6
            images.add((BufferedImage)(null));//7
            images.add((BufferedImage)(null));//8
            images.add((BufferedImage)(null));//9
            
            
            //maps:: 
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Maps/ground/straight.png"))));//10 - straight
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Maps/ground/median.png"))));//11 - median
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Maps/ground/straight.png"))));//12 - intersection
            
            images.add((BufferedImage)(null));//13
            images.add((BufferedImage)(null));
            images.add((BufferedImage)(null));//15
            
            images.add((BufferedImage)(null));//16
            images.add((BufferedImage)(null));
            images.add((BufferedImage)(null));//18
            
            images.add((BufferedImage)(null));//19
            images.add((BufferedImage)(null));
            images.add((BufferedImage)(null));//21
            
            //Civilian cars::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Civilian/RaceCar.png"))));//22
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Civilian/Van.png"))));//23
            images.add(null);//24
            
            //explosion::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Effects/RadialExplosion1.png"))));//25
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Enemy/IceCreamTruck.png"))));//26
            
            //main menu::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/MainMenu-01.png"))));//27
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/buttons/exit0.png"))));//28
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/buttons/exit1.png"))));//29
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/buttons/new0.png"))));//30
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/buttons/new1.png"))));//31
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/buttons/options0.png"))));//32
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/buttons/options1.png"))));//33
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/buttons/start0.png"))));//34
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/buttons/start1.png"))));//35
            
            //garage menu::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/MissionBoard.png"))));//36
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/prev1.png"))));//37
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/prevSelected1.png"))));//38
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/prevDisabled1.png"))));//39
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/next1.png"))));//40
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/nextSelected1.png"))));//41
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/nextDisabled1.png"))));//42
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/missions0.png"))));//43
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/missions1.png"))));//44
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/upgrades0.png"))));//45
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/upgrades1.png"))));//46
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/team0.png"))));//47
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/team1.png"))));//48
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/menu0.png"))));//49
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/menu1.png"))));//50
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/background.png"))));//51
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/play1.png"))));//52
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/playSelected1.png"))));//53
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/playDisabled1.png"))));//54
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/EndGame/continue.png"))));//55
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/EndGame/continueSelected.png"))));//56
            
            
            //upgrades menu upgrades bought/unbought::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/upgradesMenu/unbought.png"))));//57
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/upgradesMenu/bought.png"))));//58
            
            //Garage Menu tab::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/Menu/SaveQuit00.png"))));//59
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/Menu/SaveQuit01.png"))));//60
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/Menu/QuitWithoutSaving00.png"))));//61
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/Menu/QuitWithoutSaving01.png"))));//62
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/Menu/Save00.png"))));//63
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/Menu/Save01.png"))));//64
            
            //ok button::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/ok0.png"))));//65
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/MainMenu/ok1.png"))));//66
            
            //projectiles::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Projectiles/Bullet.png"))));//67
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Projectiles/Mine.png"))));//68
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Projectiles/Missile.png"))));//69
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Projectiles/Mine1.png"))));//70
            
            //options menu background::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/OptionsMenu/Background.png"))));//71
            
            //side views of the player cars::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/Sudan.png"))));//72
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/Van.png"))));//73
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/RaceCar.png"))));//74
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/ArmoredVan.png"))));//75
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/Tank.png"))));//76
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/Sudan1.png"))));//77
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/Van1.png"))));//78
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/RaceCar1.png"))));//79
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/ArmoredVan1.png"))));//80
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/Tank1.png"))));//81
            
            //side view owned::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedSudan.png"))));//82
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedVan.png"))));//83
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedRaceCar.png"))));//84
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedArmoredVan.png"))));//85
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedTank.png"))));//86
            
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedSudan1.png"))));//87
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedVan1.png"))));//88
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedRaceCar1.png"))));//89
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedArmoredVan1.png"))));//90
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Cars/Player/SideViews/OwnedTank1.png"))));//91
            
            //options button for in the menu of GarageMenu::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/Menu/Options00.png"))));//92
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/Menus/Garage/Menu/Options01.png"))));//93
            
            
            
            
            
            
        } catch(Exception e){
            ErrorLogger.logError(e,"GraphicsAssets.importImages");
        }
    }
    
    public static BufferedImage getImage(int index){
        return images.get(index);
    }
    
    public static BufferedImage getIcon(){
        BufferedImage i=null;
        try{
            i=(BufferedImage)(ImageIO.read(new File("src/Graphics/frameIcon.png")));
        } catch(IOException e){
            ErrorLogger.logIOError(e,"getIcon() - GraphicsAssets");
        }
        return i;
    }
    
    public static BufferedImage getFirstImage()throws IOException{
        return (BufferedImage)(ImageIO.read(new File("src/Graphics/OpeningImages/WizzardsOpening-01.png")));//0 (not added)
    }
    
    public static BufferedImage getSecondImage()throws IOException{
        return (BufferedImage)(ImageIO.read(new File("src/Graphics/OpeningImages/openingName-01.png")));//1 (not added)
    }
    
    public static BufferedImage getLogo() throws IOException{
        return (BufferedImage)(ImageIO.read(new File("src/Graphics/OpeningImages/Logo.png")));
    }
    
}
