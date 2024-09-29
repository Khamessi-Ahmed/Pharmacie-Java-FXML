
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Pharmacien;
import static DAO.DAOPatient.cn;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hp
 */
public class DAOPharmacien {
    public static boolean SignUp(Pharmacien p){
        
        String req = "insert into Pharmacien values(?,?);";
        try{
            PreparedStatement pst= cn.prepareStatement(req);
            pst.setString(1,p.getEmail());
            pst.setString(2,p.getMdp());
                                                                                                                                                                     
            int res=pst.executeUpdate();
            if(res >=1){
                System.out.println("ajout reussi");
            return true;}
             
        }
        catch(SQLException e){
            System.out.println("problem de requete"+e.getMessage());
            
        }
        return false;
    }
    
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    public static boolean Login(Pharmacien p){
        
        String req = "select * from Pharmacien where email='"+p.getEmail()+"' and mdp='"+p.getMdp()+"';";
        try{
            Statement st = cn.createStatement();
            ResultSet res=st.executeQuery(req);
                                                                                                                                                                     
            
            if(res.next()){
                System.out.println("Login reussi");
            return true;}
             
        }
        catch(SQLException e){
            System.out.println("problem de requete"+e.getMessage());
            
        }
        return false;
    }
    
}
