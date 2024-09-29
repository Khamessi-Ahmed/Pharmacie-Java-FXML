/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Classes.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author hp
 */
public class DAOPatient {
   static Connection cn = LaConnexion.seConnecter();
   
   
    public static ArrayList<Patient> lister(){
        ArrayList<Patient> cl = new ArrayList<>();
        
        String requete = "select * from Patient;";
        String code, nom;
        int tel;
        Patient c;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                code = rs.getString(1);
                nom = rs.getString(2);
                
                tel = rs.getInt(3);
                c = new Patient(code, nom, tel);
                cl.add(c);
            }
            System.out.println("consultation ok");
        } catch (SQLException e) {
            System.out.println("problem de consultation");
        }
        return cl;
    }
    /*public static TreeMap<String,Patient> trouver(String val){
        TreeMap <String,Patient> res= new TreeMap();
        
        String requete = "select * from client where nomcli like '"+val+"'";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);;
            String code,nom,adresse,email;
            Client c;
            if ( rs!=null){
                while (rs.next()){
                    code=rs.getString("codeCli");
                    nom=rs.getString("nomCli");
                    adresse=rs.getString("adresseCli");
                    email=rs.getString("emailCli");
                    c= new Client(code, nom, adresse, email);
                    res.put(code, c);
                    
                }
            }
            
        }catch(SQLException e){
                    System.out.println("probleme de req select "+e.getMessage());;
                    }
        return res;
    }*/
    
    /*
    public static TreeSet<Client> listerParVille(String a){
        TreeSet<Client> list= new TreeSet<>();
        
        String req = "select * from client where adresseCli like ?";
        try {
            PreparedStatement ps = cn.prepareStatement(req);
            ps.setString(1, "%"+a+"%");
            ResultSet rs1= ps.executeQuery();
            String code,nom,adresse,email;
            Client c;
            if(rs1!=null){
                while(rs1.next()){
                    code=rs1.getString("codeCli");
                    nom=rs1.getString("nomCli");
                    adresse=rs1.getString("adresseCli");
                    email=rs1.getString("emailCli");
                    c= new Client(code, nom, adresse, email);
                    list.add( c);
                }
            }
        } catch (SQLException e) {
            System.out.println("probleme de req select "+e.getMessage());
        }
        return list;
    }*/
    
    public static Patient chercher(String code){
        String req = "select * from patient where code like '"+code+"';";
        try {
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery(req); 
            String codee,nom;
            int tel;
            Patient p;
            if(rs.next()){
                
                    codee=rs.getString(1);
                    nom=rs.getString(2);
                    tel=rs.getInt(3);
                    p= new Patient(codee, nom,tel);
                return p;
            }
            
        } catch (SQLException e) {
            System.out.println("problem de req select "+e.getMessage());
        }
        return null;
    }
    
    public static boolean ajouter(Patient p){
        
        String req = "insert into Patient values(?,?,?);";
        try{
            PreparedStatement pst= cn.prepareStatement(req);
            pst.setString(1,p.getCode());
            pst.setString(2,p.getNom());
            pst.setInt(3,p.getTel());                                                                                                                                                          
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
    
    public static boolean supprimer(String code){
        String req = "delete from Patient where code like '"+code+"';";
        try{
            Statement st= cn.createStatement();
            int res=st.executeUpdate(req);
            if(res>=1){
                System.out.println("supprimer avec reussi");
                return true;}
        }
        catch(SQLException e){
            System.out.println("problem de requete"+e.getMessage());
        }
        return false;
    }
    
/*    public static boolean changerNom (Patient c,String nom){
String req= "update Patient set adresseCli ='"+nom+"' where codeCli like '"+c.getcode()+"';";

try{
Statement st = cn.createStatement();
int res=st.executeUpdate(req);
if(res>=1){
    System.out.println("update reussi");
    return true;
}
}catch(SQLException e){
    System.out.println("problem de requete"+e.getMessage());
}
return false;
}*/
   
    public static boolean changer (String code,Patient p2){
String req= "update patient set code ='"+p2.getCode()+"',nom='"+p2.getNom()+"' ,tel="+p2.getTel()+" where code like '"+code+"';";

try{
Statement st = cn.createStatement();
int res=st.executeUpdate(req);
if(res>=1){
    System.out.println("update reussi");
    return true;
}
}catch(SQLException e){
    System.out.println("problem de requete"+e.getMessage());
}
return false;
}
}

