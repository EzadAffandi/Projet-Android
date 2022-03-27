package com.example.but05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DescriptionRes extends AppCompatActivity {
    private Intent res;
    private TextView nomUe;
    private TextView description;
    private String urlRequest;
    private ExecutorService exe; //Executor pour récuper des données sur le serveur
    private Future<String> todo; //Action sur le serveur
    private URL u;
    private String s;
    private JSONArray t;
    private JSONObject o;
    public Ressource laRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_res);
        nomUe = findViewById(R.id.nomRessource);
        description = findViewById(R.id.description);
        res = getIntent();
        nomUe.setText(res.getStringExtra("titre"));
        urlRequest="http://infort.gautero.fr/index2022.php?action=get&obj=res&idRes="+res.getStringExtra("idRes");
        s=urlRequest;
        try {
            u = new URL(s);
        } catch (MalformedURLException e) {
            // Ce n'est qu'un exemple, pas de traitement propre de l'exception
            e.printStackTrace();
            u = null;
        }

        // On crée l'objet qui va gérer la thread
        exe = Executors.newSingleThreadExecutor();
        // On lance la thread
        todo = lireURL(u);
        // On attend le résultat
        try {
            s = todo.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t= new JSONArray(s);

                try {
                    o=t.getJSONObject(0);
                    laRes=(new Ressource(Functions.getId(o),Functions.getNom(o),Functions.getCours(o),Functions.getTd(o),Functions.getTp(o)));
                    laRes.setDescription(Functions.getDescription(o));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


        } catch (JSONException e) {
            e.printStackTrace();
        }

            description.setText(laRes.getDescription());

    }
    public void plusInfo(View view) {
        Intent myIntent = new Intent(DescriptionRes.this, PlusInfo.class);
        myIntent.putExtra("nom",laRes.getNom());
        myIntent.putExtra("cours",laRes.getCours());
        myIntent.putExtra("td",laRes.getTd());
        myIntent.putExtra("tp",laRes.getTp());
        DescriptionRes.this.startActivity(myIntent);
    }
    public void returnHome(View view) {
        Intent myIntent = new Intent(DescriptionRes.this, MainActivity.class);
        DescriptionRes.this.startActivity(myIntent);
    }

    public Future<String> lireURL(URL u) {
        return exe.submit(() -> {
            URLConnection c;
            String inputline;
            StringBuilder codeHTML = new StringBuilder("");

            try {
                c = u.openConnection();
                //temps maximun alloué pour se connecter
                c.setConnectTimeout(60000);
                //temps maximun alloué pour lire
                c.setReadTimeout(60000);
                //flux de lecture avec l'encodage des caractères UTF-8
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(c.getInputStream(), "UTF-8"));
                while ((inputline = in.readLine()) != null) {
                    //concaténation+retour à la ligne avec \n
                    codeHTML.append(inputline + "\n");
                }
                //il faut bien fermer le flux de lecture
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return codeHTML.toString();
        });
    }
}