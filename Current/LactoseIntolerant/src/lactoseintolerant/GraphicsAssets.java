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
     * improvement to import images idea::
     * 
     * private BufferedImage import(String index){
     *      return (BufferedImage)(ImageIO.read(new File(index)));
     * }
     */
    
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
            
            
            
            
            
            
        } catch(Exception e){
            ErrorLogger.logError(e,"importImages");
        }
    }
    
    public static BufferedImage getImage(int index){
        return images.get(index);
    }
    
    public static BufferedImage getFirstImage()throws IOException{
        return (BufferedImage)(ImageIO.read(new File("src/Graphics/OpeningImages/WizzardsOpening-01.png")));//0 (not added)
    }
    
    public static BufferedImage getSecondImage()throws IOException{
        return (BufferedImage)(ImageIO.read(new File("src/Graphics/OpeningImages/openingName-01.png")));//1 (not added)
    }
    
}
