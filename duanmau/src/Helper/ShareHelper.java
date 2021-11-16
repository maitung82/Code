
package Helper;

import Model.Nhanvien;
import java.awt.Image;
import javax.swing.ImageIcon;


public class ShareHelper {
    public static final Image APP_ICON;
    static{ 
        String file = "/icon/fpt.png";
        APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
    } 
    public static Nhanvien USER = null;
    
    public static void logoff(){
        ShareHelper.USER = null;
    }
    
}
