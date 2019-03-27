package com.example.api_project;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Classe <b>Detailed_ItemActivity</b> de la deuxième activité, hérite de AppCompatActivity
 *
 * @see AppCompatActivity
 */
public class Detailed_ItemActivity extends AppCompatActivity {

    /**
     * Surcharge de la méthode <b>onCreate</b>
     *
     * @param savedInstanceState - un <b>Bundle</b>
     * @see Override
     * @see Bundle
     * @see Connection
     * @see StringBuilder
     * @see WebView
     * @see NotFoundId
     * @see InterruptedException
     * @see ExecutionException
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_item);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        int extrasValue = extras.getInt("id");

        try {
            int idByExtra = getIdByExtra(extrasValue);

            Connection task = new Connection();
            StringBuilder htmlDocument = task.execute(idByExtra).get();

            WebView wbView = findViewById(R.id.webview);
            wbView.loadData(htmlDocument.toString(), "text/html; charset=utf-8", "utf-8");
        } catch (NotFoundId notFoundId) {
            notFoundId.printStackTrace();

        }  catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Associe un ID à la ville en fonction de la valeur retournée par le Bundle de l'activité précédente
     *
     * @param extrasValue - un <b>Integer</b>
     * @return int
     * @throws NotFoundId - L'ID n'existe pas
     * @see NotFoundId
     * @see Map
     * @see HashMap
     */
    private int getIdByExtra(int extrasValue) throws NotFoundId {
        @SuppressLint("UseSparseArrays") Map<Integer, Integer> cityId = new HashMap<>();
        cityId.put(R.id.tel_aviv, 293397);
        cityId.put(R.id.paris, 6455259);
        cityId.put(R.id.miami, 4164138);
        cityId.put(R.id.berlin, 2950158);
        cityId.put(R.id.madrid, 3128832);

        for(Map.Entry<Integer, Integer> entry : cityId.entrySet()) {
            int key = entry.getKey();
            if(extrasValue == key) {
                return entry.getValue();
            }
        }
        throw new NotFoundId("Not found Id !");
    }

    /**
     * (Surcharge) Animation quand le <b>bouton Back pressé</b>
     *
     * @see Override
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}

/**
 * Classe <b>Connection</b> qui hérite de la classe <b>AsyncTask</b> avec la progession <b>"<Integer, Void, StringBuilder>"</b>
 *
 * @see AsyncTask
 */
class Connection extends AsyncTask<Integer, Void, StringBuilder> {

    /**
     * (Surcharge) Effectue en tâche de fond la connection à l'API pour récupérer la réponse HTML
     *
     * @param extrasValue - un <b>tableau d'entier</b> (ici uniquement 1 entier est demandé)
     * @return StringBuilder
     * @see Override
     * @see StringBuilder
     * @see Integer
     * @see String
     * @see BufferedReader
     * @see URL
     * @see IOException
     */
    @Override
    protected StringBuilder doInBackground(Integer... extrasValue) {
            String appid = "8b1ddd7acba492abb0821cbfbbb061bb";
            String urlRequest = "http://api.openweathermap.org/data/2.5/weather?";

            BufferedReader br;
            int getExtrasValue = extrasValue[0];

            try {
                URL url = new URL(urlRequest + "id=" + getExtrasValue + "&APPID=" + appid + "&mode=html");
                br = new BufferedReader(new InputStreamReader(url.openStream()));

                String line;
                StringBuilder sb = new StringBuilder();
                while((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append('\n');
                }
                br.close();

                return sb;
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
}
