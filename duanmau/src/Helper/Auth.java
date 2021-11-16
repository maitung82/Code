/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Model.Nhanvien;

/**
 *
 * @author Admin
 */
public class Auth {
    public static Nhanvien user=null;
    public static void clear(){
        Auth.user=null;
    }
    public static Boolean isLogin(){
        return Auth.user !=null;
    }
    public  static Boolean isManager(){
        return Auth.isLogin() && user.isVaiTro();
    }
    
}
