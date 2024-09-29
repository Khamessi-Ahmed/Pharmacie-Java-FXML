/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class LaConnexion {
    private static Connection con;
 
    public static Connection seConnecter(){
        if(con==null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ahmedpharmacy","root","");
                System.out.println("connexion etablie");
            }catch(ClassNotFoundException e){
                System.out.println("driver non trouvé"+e.getMessage());
            }catch(SQLException ex){
                System.out.println("bd non trouver ou problem d'identification "+ex.getMessage());
            }
        }
        return con;
    }

    
    
    
}
