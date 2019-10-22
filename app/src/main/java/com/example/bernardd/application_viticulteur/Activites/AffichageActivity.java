package com.example.bernardd.application_viticulteur.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bernardd.application_viticulteur.Classes_metiers.Viticulteur;
import com.example.bernardd.application_viticulteur.Gestionnaires.Gestionnaire_viticulteur;
import com.example.bernardd.application_viticulteur.R;

import java.util.ArrayList;

public class AffichageActivity extends AppCompatActivity {

    private ListView listeViticulteurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        Initialiser();
        remplirListView();
        gestion_evenements();
    }

    private void Initialiser() {
        listeViticulteurs = (ListView) findViewById(R.id.ListView_viticulteurs);
    }

    private void remplirListView () {
        final Gestionnaire_viticulteur unGestionnaireviticulteur = (Gestionnaire_viticulteur)  getApplicationContext();

        ArrayList<String> lesNoms = new ArrayList();

        for(int i = 0; i<unGestionnaireviticulteur.getViticulteursArraylistSize();i++){
            String leNom = unGestionnaireviticulteur.getLesViticulteurs().get(i).getNomV();
            String lePrenom = unGestionnaireviticulteur.getLesViticulteurs().get(i).getPrenomV();
            if (unGestionnaireviticulteur.getViticulteurs(leNom,lePrenom).getClass().getName().equals("com.example.bernardd.application_viticulteur.Classes_metiers.Viticulteur")){
                lesNoms.add(leNom);
            }
        }
        ArrayAdapter<String> monadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesNoms);
        listeViticulteurs.setAdapter(monadapter);
    }

    private void gestion_evenements(){
        final Gestionnaire_viticulteur unGestionnaireviticulteur = (Gestionnaire_viticulteur)  getApplicationContext();
            listeViticulteurs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String NomVi = unGestionnaireviticulteur.getLesViticulteurs().get(i).getNomV();
                    String PrenomVi = unGestionnaireviticulteur.getLesViticulteurs().get(i).getPrenomV();
                    Toast.makeText(getApplicationContext(), "le prenom de "+NomVi+" est "+PrenomVi, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
                    intent.putExtra("NomViticulteur",NomVi);
                    intent.putExtra("PrenomViticulteur",PrenomVi);
                    startActivity(intent);
                }
            });

    }



}
