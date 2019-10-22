package com.example.bernardd.application_viticulteur.Gestionnaires;
import android.app.Application;
import com.example.bernardd.application_viticulteur.Classes_metiers.Viticulteur;
import java.util.ArrayList;
/**
 * Created by Bernardd on 18/09/2019.
 */

public class Gestionnaire_viticulteur extends Application{
    // collection de viticulteurs
    private  ArrayList<Viticulteur> lesViticulteurs = new ArrayList<Viticulteur>();


    // méthode qui recupère le viticulteur dans la collection à partir de sa position dans la collection
    public Viticulteur getViticulteurs(int pPosition) {
        return lesViticulteurs.get(pPosition);
    }


    // méthode qui recupère le viticulteur dans la collection à partir de son Id
    public Viticulteur getViticulteurs(Long id) {
        Viticulteur leViticulteur=null;
        int i=0;
        boolean trouve= false;
        while (i<lesViticulteurs.size() && ! trouve){
            if (lesViticulteurs.get(i).getIdV()==id){
                trouve=true;
            }
            else {
                i++;
            }
        }
        if (trouve){
            leViticulteur=lesViticulteurs.get(i);
        }

        return leViticulteur;
    }

    // créer une méthode surchargée qui recupère le viticulteur dans la collection à partir de son nom et son prénom

    public Viticulteur getViticulteurs(String pNom, String pPrenom) {
        Viticulteur leViticulteur=null;
        int i=0;
        boolean trouve = false;
        while (i<lesViticulteurs.size() && ! trouve){
            if (lesViticulteurs.get(i).getNomV().equals(pNom) && lesViticulteurs.get(i).getPrenomV().equals(pPrenom)){
                trouve=true;
            }
            else {
                i++;
            }
        }
        if (trouve){
            leViticulteur=lesViticulteurs.get(i);
        }

        return leViticulteur;
    }


    // méthode qui affecte un viticulteur à la collection
    public void setViticulteurs(Viticulteur unViticulteur) {
        lesViticulteurs.add(unViticulteur);
    }

    //public boolean tousEstOk(Viticulteur unViticulteur){
    //    boolean trouve = false;
    //    int i = 0;
    //    while (i < this.getViticulteursArraylistSize() && trouve != true) {
    //        if (unViticulteur.getNomV().equals(lesViticulteurs.get(i).getNomV()) && unViticulteur.getPrenomV().equals(lesViticulteurs.get(i).getPrenomV())) {
    //            trouve=true;
    //       }else {
    //            i++;
    //        }
    //    }
    //    return trouve;
    //}



    // méthode qui fournit la collection de viticulteurs
    public ArrayList<Viticulteur> getLesViticulteurs() {
        return lesViticulteurs;
    }

    // méthode qui fournit la taille de la collection
    public int getViticulteursArraylistSize() {
        return lesViticulteurs.size();
    }

}
