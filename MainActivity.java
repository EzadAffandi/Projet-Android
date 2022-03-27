package com.example.but05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    private Boolean hasClicked=false;
    private int i;
    private String inputURL; //URL du serveur à utiliser
    private ListView vl; //La liste de BUT
    private ArrayList<String> lf; //L'endroit où on stock les noms de BUT
    private ExecutorService exe; //Executor pour récuper des données sur le serveur
    private Future<String> todo; //Action sur le serveur
    private Button buttonBut; //Le button pour fait apparaitre tous le BUTs
    private ArrayAdapter<String> aaf; //Controlleur
    private JSONArray t;
    private JSONObject o;
    public ArrayList<But> listeBut=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputURL = "http://infort.gautero.fr/index2022.php?action=get&obj=but";  //L'URL du serveur
        buttonBut = findViewById(R.id.buttonBut); //On récupere le button LISTE BUT
        vl = findViewById(R.id.lesButs); //On récupere la vue graphique de la liste
        lf= new ArrayList<>(); //On crée un modèle pour la liste
        aaf= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lf); // On crée l'adapteur
        vl.setAdapter(aaf); // On associe cet adapteur à la vue
        vl.setOnItemClickListener(new ActionItemList(this)); //On défini l'action quand on click sur un item de la liste
        registerForContextMenu(vl);    // On associe un menu contextuel à la liste
    }

    public void listeBut(View v) throws JSONException {
        String s;
        URL u;


        s = inputURL;

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
        // On ajout à la liste
        t= new JSONArray(s);
        if (hasClicked!=true) {
            for (int i=0;i<t.length();i++){
            o=t.getJSONObject(i);
            listeBut.add(new But(Functions.getId(o),Functions.getSpecialite(o)));
            }
            for(int i=0;i<listeBut.size();i++){
                lf.add(listeBut.get(i).getSpecialite());
            }
            aaf.notifyDataSetChanged();
            hasClicked=true;
        } else {
            Toast.makeText(getApplicationContext(),
                    "Vous avez listé tous les BUTs", Toast.LENGTH_SHORT).show();
        }


    }

    public JSONArray getJsonArrayFromMain() throws JSONException {
        return this.t;
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