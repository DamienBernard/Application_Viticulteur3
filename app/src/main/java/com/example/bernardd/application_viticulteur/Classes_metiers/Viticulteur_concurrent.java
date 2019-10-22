package com.example.bernardd.application_viticulteur.Classes_metiers;

/**
 * Created by Bernardd on 25/09/2019.
 */

public class Viticulteur_concurrent extends Viticulteur{
    private int noteV;

    public Viticulteur_concurrent(String pNom, String pPrenom, int pNote){
        super(pNom, pPrenom);
        noteV = pNote;
    }

    public int getNoteV(){
        int noteSelect;
        noteSelect=this.noteV;
        return noteSelect;
    }

    public void setNoteV(int noteModif){
        this.noteV=noteModif;
    }

    public int getNote(){
        return noteV;
    }

    public String toString(){
        String message;
        message = "La note de "+this.getNomV()+"est de "+noteV;
        return message;
    }


}


