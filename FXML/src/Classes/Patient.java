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
public class Patient implements Comparable<Patient>{
    private String code;
    private String nom;
    private int tel;

    public Patient(String code, String nom, int tel) {
        this.code = code;
        this.nom = nom;
        this.tel = tel;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public int getTel() {
        return tel;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Patient{" + "code=" + code + ", nom=" + nom + ", tel=" + tel + '}';
    }

    @Override
    public int compareTo(Patient o) {
        return code.compareTo(o.code);
    }

   
}
