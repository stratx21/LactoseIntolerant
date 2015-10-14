/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author 0001058857
 */
public class GraphicsAssets { //EVERYTING shall be static here ;)
    
    public static ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
    
    public GraphicsAssets(){
        
    }
    
    public static void importImages(){
        try{
            //opening images::
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/OpeningImages/WizzardsOpening-01.png"))));//0
            images.add((BufferedImage)(ImageIO.read(new File("src/Graphics/OpeningImages/openingName-01.png"))));//1
        } catch(Exception e){
            ErrorLogger.logError(e,"importImages");
        }
    }
    
    public static BufferedImage get(int index){
        return images.get(index);
    }
    
}
