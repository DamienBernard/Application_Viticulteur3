package com.example.bernardd.application_viticulteur.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.bernardd.application_viticulteur.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView textViewNote, textViewPrenom, textViewNom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initialisations();
        gerer_evenement();
    }

    private void initialisations() {
        // récupération des EditText grâce aux ID
        textViewNom = (TextView) findViewById(R.id.textViewNom);
        textViewPrenom = (TextView) findViewById(R.id.textViewPrenom);
    }

    public void gerer_evenement(){
        Intent intent = getIntent();
        if (intent != null){
            String NomRecup, PrenomRecup = "";
            if (intent.hasExtra("NomViticulteur")){
                NomRecup = intent.getStringExtra("NomViticulteur");
                PrenomRecup = intent.getStringExtra("PrenomViticulteur");
                textViewNom.setText(NomRecup);
                textViewPrenom.setText(PrenomRecup);
            }
        }
    }

}
