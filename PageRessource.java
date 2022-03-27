package com.example.but05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
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

public class PageRessource extends AppCompatActivity {
    private Intent res;
    private TextView nomUe;
    private String titre;
    private String urlRequest;
    private ListView vl; //La liste de BUT
    private ArrayList<String> lf; //L'endroit où on stock les noms de BUT
    private ExecutorService exe; //Executor pour récuper des données sur le serveur
    private Future<String> todo; //Action sur le serveur
    private ArrayAdapter<String> aaf; //Controlleur
    private URL u;
    private String s;
    private JSONArray t;
    private JSONObject o;
    public ArrayList<Ressource> listeRes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_ressource);
        nomUe=findViewById(R.id.nomUe);
        res=getIntent();
        titre=res.getStringExtra("titre");
        nomUe.setText(titre);
        vl= findViewById(R.id.listeRes); //On récupere la vue graphique de la liste
        lf=new ArrayList<>(); //On crée un modèle pour la liste
        aaf= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lf); // On crée l'adapteur
        vl.setAdapter(aaf); // On associe cet adapteur à la vue
        vl.setOnItemClickListener(new AllerInfoRes(this)); //On défini l'action quand on click sur un item de la liste
        registerForContextMenu(vl);    // On associe un menu contextuel à la liste
        urlRequest="http://infort.gautero.fr/index2022.php?action=get&obj=res&idUe="+res.getStringExtra("idUe");
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
        // On ajout à la liste
        try {
            t= new JSONArray(s);
            for (int i=0;i<t.length();i++){
                try {
                    o=t.getJSONObject(i);
                    listeRes.add(new Ressource(Functions.getId(o),Functions.getNom(o),Functions.getCours(o),Functions.getTd(o),Functions.getTp(o)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<listeRes.size();i++){
            lf.add(listeRes.get(i).getNom());
        }

        }


    public void returnHome(View view){
        Intent myIntent = new Intent(PageRessource.this,MainActivity.class);
        PageRessource.this.startActivity(myIntent);
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