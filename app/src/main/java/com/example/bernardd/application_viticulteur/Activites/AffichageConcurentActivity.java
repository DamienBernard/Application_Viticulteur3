package com.example.bernardd.application_viticulteur.Activites;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.example.bernardd.application_viticulteur.Classes_metiers.Viticulteur_concurrent;
import com.example.bernardd.application_viticulteur.Gestionnaires.Gestionnaire_viticulteur;
import com.example.bernardd.application_viticulteur.R;
import java.util.ArrayList;
import java.util.HashMap;

public class AffichageConcurentActivity extends AppCompatActivity {

    private ListView listviewEntete;
    private ListView listviewConcurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_concurent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Initialiser();
        remplirListViewEntete();
        remplirListViewConcurrent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void Initialiser(){
        listviewEntete = (ListView) findViewById(R.id.listViewEntete);
        listviewConcurrent = (ListView) findViewById(R.id.listViewConcurrent);
    }

    public void remplirListViewEntete(){
        // Hashmap pour le stockage de plusieurs (clé/valeur)
        HashMap<String, String> map_entete;

        map_entete = new HashMap<String, String>();
        map_entete.put("nom", "Nom");
        map_entete.put("prenom", " Prenom");
        map_entete.put("note", " Note");

        ArrayList<HashMap<String, String>>  maliste_entete;
        maliste_entete = new ArrayList<HashMap<String, String>>();
        maliste_entete.add(map_entete);

        ListAdapter adapter_entete;
        try {
            adapter_entete = new SimpleAdapter(this, maliste_entete, R.layout.colonnes, new String[] { "nom", "prenom", "note" }, new int[] {
                    R.id.nom, R.id.prenom, R.id.note });
            // MAJ de la listview à utilisant l'adapter
            AlertDialog.Builder listeView_entete;
            listviewEntete.setAdapter(adapter_entete);
        } catch (Exception e) {
            
        }
    }

    public void remplirListViewConcurrent() {
        final Gestionnaire_viticulteur unGestionnaireviticulteur = (Gestionnaire_viticulteur) getApplicationContext();

        ArrayList<String> listenoms = new ArrayList();

        // Hashmap pour le stockage de plusieurs (clé/valeur)
        ArrayList<HashMap<String, String>> maliste_concurrent;
        maliste_concurrent = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < unGestionnaireviticulteur.getViticulteursArraylistSize(); i++) {
            String lenomdeClasse = unGestionnaireviticulteur.getLesViticulteurs().get(i).getClass().getName();
            // nouvel hasmap pour chaque nouveau viticulteur
            HashMap<String, String> map_concurrent = new HashMap<String, String>();
            if (lenomdeClasse.equals("com.example.bernardd.application_viticulteur.Classes_metiers.Viticulteur_concurrent")) {

                Viticulteur_concurrent leViticulteurConcurrent = (Viticulteur_concurrent) unGestionnaireviticulteur.getLesViticulteurs().get(i);

                String leNom = leViticulteurConcurrent.getNomV();
                String lePrenom = leViticulteurConcurrent.getPrenomV();
                String laNote = Integer.toString(leViticulteurConcurrent.getNote());

                map_concurrent.put("nom", leNom);
                map_concurrent.put("prenom", lePrenom);
                map_concurrent.put("note", laNote);

                maliste_concurrent.add(map_concurrent);
            }
        }
        ListAdapter adapter_concurrent;
        try {
            adapter_concurrent = new SimpleAdapter(this, maliste_concurrent, R.layout.colonnes, new String[] { "nom", "prenom", "note" }, new int[] {
                    R.id.nom, R.id.prenom, R.id.note });
            // MAJ de la listview à utilisant l'adapter
            listviewConcurrent.setAdapter(adapter_concurrent);
        }catch (Exception e) {
        }
    }

}
