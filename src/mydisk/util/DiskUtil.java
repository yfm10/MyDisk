/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisk.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Administrator
 */
public class DiskUtil {
    public static ImageView getImageView(String type) {
        if("baidu".equalsIgnoreCase(type)){
            return new ImageView(new Image("mydisk/images/baidu-logo.png"));
        }else if("vdisk".equalsIgnoreCase(type)){
            return new ImageView(new Image("mydisk/images/vdisk-logo.png"));
        }
        return new ImageView();
    }
    
    public static boolean isBlank(String str){
       return null==str||"".equals(str);
    }
}
