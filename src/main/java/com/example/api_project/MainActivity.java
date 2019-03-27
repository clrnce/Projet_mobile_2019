package com.example.api_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Classe <b>MainActivity</b> de l'activité principale, hérite de <b>AppCompatActivity</b>
 *
 * @see AppCompatActivity
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Surcharge de la méthode <b>onCreate</b>
     *
     * @param savedInstanceState - un <b>Bundle</b>
     * @see Bundle
     * @see Override
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tlv_btn = findViewById(R.id.tel_aviv);
        tlv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passToActivity(R.id.tel_aviv);
            }
        });

        Button paris_btn = findViewById(R.id.paris);
        paris_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passToActivity(R.id.paris);
            }
        });

        Button miami_btn = findViewById(R.id.miami);
        miami_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passToActivity(R.id.miami);
            }
        });

        Button berlin_btn = findViewById(R.id.berlin);
        berlin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passToActivity(R.id.berlin);
            }
        });

        Button madrid_btn = findViewById(R.id.madrid);
        berlin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passToActivity(R.id.madrid);
            }
        });
    }

    /**
     * Permet d'envoyer l'ID du bouton qui a été pressé à la deuxième activité.
     * <b>Transition de slide entre les deux écrans.</b>
     *
     * @param id - un <b>entier</b>
     * @see Integer
     * @see Intent
     */
    public void passToActivity(int id) {
        Intent intent = new Intent(this, Detailed_ItemActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);

        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    /**
     * Transition de slide quand <b>le bouton Home est pressé</b> (Surcharge)
     *
     * @param item - un <b>MenuItem</b>
     * @return <b>boolean</b>
     * @see Override
     * @see MenuItem
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
