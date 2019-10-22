package com.example.bernardd.application_viticulteur.Activites;
import com.example.bernardd.application_viticulteur.Classes_metiers.Viticulteur_concurrent;
import com.example.bernardd.application_viticulteur.Gestionnaires.Gestionnaire_viticulteur;
import com.example.bernardd.application_viticulteur.Classes_metiers.Viticulteur;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.app.Activity;

import com.example.bernardd.application_viticulteur.R;

import java.util.zip.CheckedInputStream;

public class MainActivity extends AppCompatActivity {
    // déclaration des objets permettant de modifier ou récupérer les valeurs des objets dans la fenêtre
    // ou d'effectuer des actions sur les objets dans la fenêtre
    private EditText editTextPrenom,editTextNom;
    private Button boutonvalider, boutonafficher, butonafficherconcu;
    private String prenom,nom,noteSt;
    private TextView textViewEnregistrer;
    private int note;
    private CheckBox checkConcours;
    private EditText editNote;
    private TextView labelNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialisations();
        gestion_evènements();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** fonction permettant d'affecter des valeurs aux objets de la classe depuis l'interface graphique */
    private void initialisations() {
        // récupération des EditText grâce aux ID
        editTextPrenom = (EditText) findViewById(R.id.editPrenom);
        editTextNom = (EditText) findViewById(R.id.editNom);
        textViewEnregistrer = findViewById(R.id.messageBienvenu);
        editNote = (EditText) findViewById(R.id.editNote);
        labelNote = (TextView) findViewById(R.id.labelNote);




        // récupération du Button grâce à son ID
        boutonvalider = (Button) findViewById(R.id.button);
        boutonafficher = (Button) findViewById(R.id.button_Afficher);
        butonafficherconcu = (Button) findViewById(R.id.buttonConcurrent);
        // récupération du checkbox
        checkConcours = (CheckBox) findViewById(R.id.checkBox);
    }

    private void gestion_evènements (){

        final Gestionnaire_viticulteur unGestionnaireviticulteur = (Gestionnaire_viticulteur)  getApplicationContext();

        // association d'un écouteur d’événement à l'événement Clic sur le bouton boutonvalider
        boutonvalider.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String message="";
                // on récupère le texte écrit dans l'objet EditText
                prenom = editTextPrenom.getText().toString();
                nom = editTextNom.getText().toString();
                noteSt = editNote.getText().toString();
                note = Integer.parseInt(noteSt);


                boolean ok=false;
                if (nom.isEmpty() || prenom.isEmpty()){
                    message="Saisie incomplète";
                } else {
                    if (checkConcours.isChecked()) {
                        if (noteSt.isEmpty()) {
                            message = "Noubliez pas de mettre une note";
                        } else {
                            if (Integer.parseInt(noteSt) <= 0) {
                                message = "Mettez une note positive";
                            } else {
                                if (Integer.parseInt(noteSt) > 100) {
                                    message = "Mettez une note sur 100";
                                }else {
                                    message = "Enregistrement de  " + prenom + " " + nom +" avec une note de "+noteSt;
                                    ok=true;
                                }
                            }
                        }
                    }else {
                        message = "Enregistrement de  " + prenom + " " + nom + ", non inscrit au concours";
                        ok=true;
                    }
                }


                // on affiche le message dans l'objet TextView placé au plus haut de l'écran

                // on affiche "enregistrement effectué ! " dans une pop-up qui s'affiche quelques seconde en bas d'écran
                if (ok){
                    //
                    //
                    // fait crash l'appli
                    //si il est inscrit au concourt
                    if (checkConcours.isChecked()) {
                        Viticulteur_concurrent unViticulteurConcurrent = new Viticulteur_concurrent(nom, prenom, note);
                        //verifie ci l'exisatant est un concurrent.
                        if (unGestionnaireviticulteur.getViticulteurs(nom, prenom) !=null){
                            if(unGestionnaireviticulteur.getViticulteurs(nom, prenom).getClass().getName().equals("com.example.bernardd.application_viticulteur.Classes_metiers.Viticulteur_concurrent")){
                                Viticulteur_concurrent leViticulteurConcuDouble = (Viticulteur_concurrent) unGestionnaireviticulteur.getViticulteurs(nom, prenom);
                                if(leViticulteurConcuDouble.getNote()<unViticulteurConcurrent.getNote()){
                                    leViticulteurConcuDouble.setNoteV(unViticulteurConcurrent.getNote());
                                    message = "la note de "+nom+" "+prenom+" à etait modifier";
                                }else{
                                    Toast.makeText(getApplicationContext(), "Note trop basse", Toast.LENGTH_LONG).show();
                                    message = "Aucune modification";
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), "echec enregistrer, ce viticulteur existe deja !", Toast.LENGTH_LONG).show();
                                message = "Aucun enregistrement";
                            }
                        }else {
                            unGestionnaireviticulteur.setViticulteurs(unViticulteurConcurrent);
                            Toast.makeText(getApplicationContext(), "Enreg ok", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Viticulteur unViticulteur=new Viticulteur(nom,prenom);

                        if(unGestionnaireviticulteur.getViticulteurs(nom,prenom) != null){
                            Toast.makeText(getApplicationContext(), "Viticulteur déjà enregistré !!!!", Toast.LENGTH_LONG).show();
                            message = "Aucun enregistrement,deja inscrit";
                        }else{
                            Toast.makeText(getApplicationContext(), "enregistrement effectué !", Toast.LENGTH_LONG).show();
                            unGestionnaireviticulteur.setViticulteurs(unViticulteur);

                        }
                        System.out.println("-----------------------------------------------");

                    }
                }
                textViewEnregistrer.setText(message);
            }
        });

        checkConcours.setOnClickListener(new OnClickListener(){
            public void onClick(View view) {
                if (checkConcours.isChecked()) {
                    editNote.setVisibility(TextView.VISIBLE);
                    labelNote.setVisibility(TextView.VISIBLE);
                } else {
                    editNote.setVisibility(TextView.INVISIBLE);
                    labelNote.setVisibility(TextView.INVISIBLE);
                }
            }
        });
        // bouton afficher qui permet d'afficher la 2° activité
        boutonafficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "clic !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), AffichageActivity.class);
                startActivity(intent);
            }
        });

        butonafficherconcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "clic !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), AffichageConcurentActivity.class);
                startActivity(intent);
            }
        });


    }
    private boolean note_valide(int lanote) {

        return ( lanote >=0 && lanote<=100 );
    }//fin note valide


}

