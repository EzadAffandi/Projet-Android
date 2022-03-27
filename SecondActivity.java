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

public class SecondActivity extends AppCompatActivity {
    private Intent but;
    private TextView nomBut;
    private String titre;
    private ListView vl1,vl2; //La liste de BUT
    private ArrayList<String> lf1,lf2; //L'endroit où on stock les noms de BUT
    private ExecutorService exe; //Executor pour récuper des données sur le serveur
    private Future<String> todo; //Action sur le serveur
    private ArrayAdapter<String> aaf1,aaf2; //Controlleur
    private String urlRequest;
    private URL u;
    private String s;
    private String id;
    public ArrayList<Ue> listeUe1= new ArrayList<>();
    public ArrayList<Ue> listeUe2= new ArrayList<>();
    public ArrayList<String> listeComp1 = new ArrayList<>();
    public ArrayList<String> listeComp2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nomBut=findViewById(R.id.nomBut);
        but=getIntent();
        titre=but.getStringExtra("titre");
        nomBut.setText(titre);
        vl1= findViewById(R.id.listeSem3); //On récupere la vue graphique de la liste
        lf1=new ArrayList<>(); //On crée un modèle pour la liste
        aaf1= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lf1); // On crée l'adapteur
        vl1.setAdapter(aaf1); // On associe cet adapteur à la vue
        vl1.setOnItemClickListener(new AllerInfoUe(this)); //On défini l'action quand on click sur un item de la liste
        registerForContextMenu(vl1);    // On associe un menu contextuel à la liste
        //pour le semestre 2
        vl2= findViewById(R.id.listeSem4); //On récupere la vue graphique de la liste
        lf2=new ArrayList<>(); //On crée un modèle pour la liste
        aaf2= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lf2); // On crée l'adapteur
        vl2.setAdapter(aaf2); // On associe cet adapteur à la vue
        vl2.setOnItemClickListener(new AllerInfoUe2(this)); //On défini l'action quand on click sur un item de la liste
        registerForContextMenu(vl2);    // On associe un menu contextuel à la liste
        id=but.getStringExtra("id");
        urlRequest = "http://infort.gautero.fr/index2022.php?action=get&obj=ue&idBut="+id;
        s = urlRequest;
        JSONArray t;
        JSONObject o;
        JSONArray t1;
        JSONObject o1;

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
                    if(Functions.getSemestre(o)==1) {
                        listeUe1.add(new Ue(Functions.getId(o),Functions.getSemestre(o),Functions.getNumero(o),Functions.getIdCompetence(o),Functions.getParcours(o)));
                        urlRequest = "http://infort.gautero.fr/index2022.php?action=get&obj=competence&idComp="+Functions.getIdCompetence(o);
                        s = urlRequest;
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
                        t1= new JSONArray(s);
                        o1= t1.getJSONObject(0);
                        listeComp1.add(Functions.getNom(o1));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        urlRequest = "http://infort.gautero.fr/index2022.php?action=get&obj=ue&idBut="+id;
        s = urlRequest;
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
            for (int i=0;i<t.length();i++){
                try {
                    o=t.getJSONObject(i);
                    if(Functions.getSemestre(o)==2) {
                        listeUe2.add(new Ue(Functions.getId(o),Functions.getSemestre(o),Functions.getNumero(o),Functions.getIdCompetence(o),Functions.getParcours(o)));
                        urlRequest = "http://infort.gautero.fr/index2022.php?action=get&obj=competence&idComp="+Functions.getIdCompetence(o);
                        s = urlRequest;
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
                        t1= new JSONArray(s);
                        o1= t1.getJSONObject(0);
                        listeComp2.add(Functions.getNom(o1));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<listeUe1.size();i++){
            lf1.add(listeUe1.get(i).getNumero()+") "+listeUe1.get(i).getParcours()+" ("+listeComp1.get(i)+")");
            aaf1.notifyDataSetChanged();
        }
        for(int i=0;i<listeUe2.size();i++){
            lf2.add(listeUe2.get(i).getNumero()+") "+listeUe2.get(i).getParcours()+" ("+listeComp2.get(i)+")");
            aaf2.notifyDataSetChanged();
        }


    }

    public void pageSuivant(View view){
        Intent myIntent = new Intent(SecondActivity.this,SecondPage.class);
        myIntent.putExtra("titre", titre);
        myIntent.putExtra("id",id);
        SecondActivity.this.startActivity(myIntent);
    }
    public void returnHome(View view){
        Intent myIntent = new Intent(SecondActivity.this,MainActivity.class);
        SecondActivity.this.startActivity(myIntent);
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
