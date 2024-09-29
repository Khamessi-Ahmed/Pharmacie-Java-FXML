/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Date;

/**
 *
 * @author hp
 */
public class patMed {
    private String codePat;
    private String nomMed;
    private Date dateAchat;

    public patMed(String codePat, String nomMed, Date dateAchat) {
        this.codePat = codePat;
        this.nomMed = nomMed;
        this.dateAchat = dateAchat;
    }

    @Override
    public String toString() {
        return "patMed{" + "codePat=" + codePat + ", nomMed=" + nomMed + ", dateAchat=" + dateAchat + '}';
    }

    public void setCodePat(String codePat) {
        this.codePat = codePat;
    }

    public void setNomMed(String nomMed) {
        this.nomMed = nomMed;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getCodePat() {
        return codePat;
    }

    public String getNomMed() {
        return nomMed;
    }

    public Date getDateAchat() {
        return dateAchat;
    }
    
}
