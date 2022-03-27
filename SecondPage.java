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

public class SecondPage extends AppCompatActivity {
    private Intent but;
    private TextView nomBut;
    private String idLien;
    private String titre;
    private ListView vl3,vl4; //La liste de BUT
    private ArrayList<String> lf3,lf4; //L'endroit où on stock les noms de BUT
    private ExecutorService exe; //Executor pour récuper des données sur le serveur
    private Future<String> todo; //Action sur le serveur
    private ArrayAdapter<String> aaf3,aaf4; //Controlleur
    private String urlRequest;
    private URL u;
    private String s;
    private JSONArray t;
    private JSONObject o;
    private JSONArray t1;
    private JSONObject o1;
    public ArrayList<Ue> listeUe3=new ArrayList<>();
    public ArrayList<Ue> listeUe4=new ArrayList<>();
    private ArrayList<String> listeComp3 = new ArrayList<>();
    private ArrayList<String> listeComp4 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        but=getIntent();
        nomBut=findViewById(R.id.nomBut);
        titre=but.getStringExtra("titre");
        idLien=but.getStringExtra("id");
        nomBut.setText(titre);
        vl3= findViewById(R.id.listeSem3); //On récupere la vue graphique de la liste
        lf3=new ArrayList<>(); //On crée un modèle pour la liste
        aaf3= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lf3); // On crée l'adapteur
        vl3.setAdapter(aaf3); // On associe cet adapteur à la vue
        vl3.setOnItemClickListener(new AllerInfoUe3(this)); //On défini l'action quand on click sur un item de la liste
        registerForContextMenu(vl3);    // On associe un menu contextuel à la liste
        //pour le semestre 4
        vl4= findViewById(R.id.listeSem4); //On récupere la vue graphique de la liste
        lf4=new ArrayList<>(); //On crée un modèle pour la liste
        aaf4= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lf4); // On crée l'adapteur
        vl4.setAdapter(aaf4); // On associe cet adapteur à la vue
        vl4.setOnItemClickListener(new AllerInfoUe4(this)); //On défini l'action quand on click sur un item de la liste
        registerForContextMenu(vl4);    // On associe un menu contextuel à la liste
        urlRequest = "http://infort.gautero.fr/index2022.php?action=get&obj=ue&idBut="+idLien;
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
                    if(Functions.getSemestre(o)==3) {
                        listeUe3.add(new Ue(Functions.getId(o),Functions.getSemestre(o),Functions.getNumero(o),Functions.getIdCompetence(o),Functions.getParcours(o)));
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
                        listeComp3.add(Functions.getNom(o1));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        urlRequest = "http://infort.gautero.fr/index2022.php?action=get&obj=ue&idBut="+idLien;
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
                    if(Functions.getSemestre(o)==4) {
                        listeUe4.add(new Ue(Functions.getId(o),Functions.getSemestre(o),Functions.getNumero(o),Functions.getIdCompetence(o),Functions.getParcours(o)));
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
                        listeComp4.add(Functions.getNom(o1));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i=0;i<listeUe3.size();i++){
            lf3.add(listeUe3.get(i).getNumero()+") "+listeUe3.get(i).getParcours()+" ("+listeComp3.get(i)+")");
            aaf3.notifyDataSetChanged();
        }
        for (int i=0;i<listeUe4.size();i++){
            lf4.add(listeUe4.get(i).getNumero()+") "+listeUe4.get(i).getParcours()+" ("+listeComp4.get(i)+")");
            aaf4.notifyDataSetChanged();
        }
    }

    public void pagePrecedent(View view){
        Intent myIntent = new Intent(SecondPage.this,SecondActivity.class);
        myIntent.putExtra("titre", titre);
        myIntent.putExtra("id", idLien);
        SecondPage.this.startActivity(myIntent);
    }
    public void returnHome(View view){
        Intent myIntent = new Intent(SecondPage.this,MainActivity.class);
        SecondPage.this.startActivity(myIntent);
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
