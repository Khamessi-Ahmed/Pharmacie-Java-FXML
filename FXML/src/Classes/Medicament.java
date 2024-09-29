/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author hp
 */
public class Medicament  implements Comparable<Medicament>{
    private String nom;
    private Float prix;
    private Integer qte;
    private TYPE type;
    
    public enum TYPE{
        special,normal
    }

    @Override
    public String toString() {
        return "Medicament{" + "nom=" + nom + ", prix=" + prix + ", qte=" + qte + ", type=" + type + '}';
    }

    @Override
    public int compareTo(Medicament o) {
        return nom.compareTo(o.nom);
    }

    public Medicament(String nom, Float prix, Integer qte, TYPE type) {
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.type = type;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public Float getPrix() {
        return prix;
    }

    public Integer getQte() {
        return qte;
    }

    public String getType() {
        if(type==TYPE.normal)
            return "normal";
        return "special";
    }
    
    
    
    
    
    
    
    
}
