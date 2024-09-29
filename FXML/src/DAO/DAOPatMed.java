/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Classes.patMed;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class DAOPatMed {
    static Connection cn = LaConnexion.seConnecter();
    
    public static ArrayList<patMed> lister(String codePat){
        ArrayList<patMed> pml = new ArrayList<>();
        
        String requete = "select * from patmed where codePat='"+codePat+"'";
        String code, nom;
        Date dAchat;
        patMed pm;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                code = rs.getString(1);
                nom = rs.getString(2);
                dAchat = rs.getDate(3);
                pm = new patMed(code, nom, dAchat);
                pml.add(pm);
            }
            System.out.println("consultation ok");
        } catch (SQLException e) {
            System.out.println("problem de consultation");
        }
        return pml;
    }
    
    public static boolean ajouter(patMed pm){
        
        String req = "insert into patmed values(?,?,?);";
        String req1="select qte from medicament where nom='"+pm.getNomMed()+"'";
        String req2="update Medicament set qte=qte-1 where nom='"+pm.getNomMed()+"'";
        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(req1);
            while(rs.next())
            {
                if(rs.getInt(1)==0)
                    return false;
                    
            }
            
            PreparedStatement pst= cn.prepareStatement(req);
            pst.setString(1,pm.getCodePat());
            pst.setString(2,pm.getNomMed());
            pst.setDate(3,pm.getDateAchat());  
            
            int res=pst.executeUpdate();
            
            PreparedStatement pst2= cn.prepareStatement(req2);
            int res2=pst2.executeUpdate();
            if(res>=1 && res2>=1){
                System.out.println("ajout reussi");
                return true;
            }
            if(res>=1){
                System.out.println("res1");
                return true;}
            if(res2>=1){
                System.out.println("res2");
            return true;}
            
             
        }
        catch(SQLException e){
            System.out.println("problem de requete"+e.getMessage());
        }
        return false;
    }
    
    public static boolean supprimer(String c,String n){
        String req = "delete from patMed where codePat like '"+c+"'and nomMed = '"+n+"'";
        String req2="update Medicament set qte=qte+1 where nom='"+n+"'";
        try{
            Statement st= cn.createStatement();
            int res=st.executeUpdate(req);
            Statement st2= cn.createStatement();
            int res2=st.executeUpdate(req2);
            if(res>=1 && res2>=1){
                System.out.println("supprimer avec reussi");
                return true;}
            if(res>=1 ){
                System.out.println("res");
                return true;}
            if( res2>=1){
                System.out.println("res2");
                return true;}
            
        }
        catch(SQLException e){
            System.out.println("problem de requete"+e.getMessage());
        }
        System.out.println("failed");
        return false;
    }
    
    
    
    
}
