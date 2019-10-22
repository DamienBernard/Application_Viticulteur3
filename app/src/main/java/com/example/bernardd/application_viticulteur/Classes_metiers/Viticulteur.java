package com.example.bernardd.application_viticulteur.Classes_metiers;

/**
 * Created by Bernardd on 18/09/2019.
 */

public class Viticulteur {

    private long idV;
    private String nomV;
    private String prenomV;
    static private long numero_viticulteur=0; // Static => attribut à porter de classe, même valeur pour toutes instances.


    public Viticulteur() {                  //
        this.idV = numero_viticulteur+1;    //
        this.nomV = "";                     //  = serial en sql
        this.prenomV = "";                  //
        numero_viticulteur++;               //
    }


    // créer le constructeur surchargé en fournissant un nom et un prénom

    public Viticulteur(String pNom, String pPrenom) {
        this.idV = numero_viticulteur+1;
        this.nomV = pNom;
        this.prenomV = pPrenom;
        numero_viticulteur++;
    }

    public long getIdV() {
        return idV;
    }

    public void setIdV(long idV) {
        this.idV = idV;
    }

    public String getNomV() {
        return nomV;
    }

    public void setNomV(String nomV) {
        this.nomV = nomV;
    }
    public String getPrenomV() {
        return prenomV;
    }
    public void setPrenomV(String prenomV) {
        this.prenomV =prenomV ;
    }
    public String toString(){
        return "idV="+idV+",nomV="+nomV+",prenomV="+prenomV;
    }
}
