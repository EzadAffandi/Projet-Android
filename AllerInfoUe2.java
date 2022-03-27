package com.example.but05;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AllerInfoUe2 implements AdapterView.OnItemClickListener {
    private SecondActivity controleur;
    public AllerInfoUe2 (SecondActivity c) { controleur= c; }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent action;
        String v;
        Integer id;

        Toast.makeText(view.getContext(), "Item à la position " +
                Integer.toString(i), Toast.LENGTH_SHORT);
        v= ((String)adapterView.getItemAtPosition(i)).toString();
        action= new Intent(controleur, PageRessource.class);
        action.putExtra("titre",v);
        action.putExtra("idUe",String.valueOf(controleur.listeUe2.get(i).getId()));
        controleur.startActivity(action);




    }
}
