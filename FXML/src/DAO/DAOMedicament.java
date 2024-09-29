/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Medicament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class DAOMedicament {
    static Connection cn = LaConnexion.seConnecter();
   
   
    public static ArrayList<Medicament> lister(){
        ArrayList<Medicament> cl = new ArrayList<>();
        
        String requete = "select * from Medicament;";
        String nom;
        Float prix;
        int qte;
        Medicament.TYPE type;
        Medicament m;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                nom = rs.getString(1);
                prix = rs.getFloat(2);
                qte = rs.getInt(3);
                if(rs.getString(4).compareTo("normal")==0) type=Medicament.TYPE.normal;
                else  type=Medicament.TYPE.special;
                m = new Medicament(nom,prix,qte,type);
                cl.add(m);
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
    
    public static Medicament chercher(String nomMed){
        
        String req = "select * from medicament where nom like '"+nomMed+"';";
        try {
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery(req); 
            String nom;
            float prix;
            int qte;
            Medicament.TYPE type;
            Medicament m;
            if(rs != null){
                
                    
                    nom=rs.getString(1);
                    prix=rs.getFloat(2);
                    qte=rs.getInt(3);
                    if(rs.getString(4).compareTo("normal")==0)
                        type=Medicament.TYPE.normal;
                     else type=Medicament.TYPE.special;
                     m = new Medicament(nom,prix,qte,type);
                
                return m;
            }
            
        } catch (SQLException e) {
            System.out.println("problem de req select "+e.getMessage());
        }
        return null;
    }
    
    public static boolean ajouter(Medicament m){
        
        String req = "insert into Medicament values(?,?,?,?);";
        try{
            PreparedStatement pst= cn.prepareStatement(req);
            pst.setString(1,m.getNom());
            pst.setFloat(2,m.getPrix());
            pst.setInt(3,m.getQte());    
            pst.setString(4,m.getType()); 
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
    
    public static boolean supprimer(String nom){
        String req = "delete from Medicament where nom like '"+nom+"';";
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
    
/*    public static boolean changerNom (Medicament c,String nom){
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
   
    public static boolean changer (String nom,Medicament p2){
String req= "update Medicament set nom ='"+p2.getNom()+"',prix="+p2.getPrix()+" ,qte="+p2.getQte()+",type='"+p2.getType()+"' where nom like '"+nom+"';";

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
