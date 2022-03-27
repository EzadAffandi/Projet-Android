package com.example.but05;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class AllerInfoRes implements AdapterView.OnItemClickListener {
    private PageRessource controleur;

    public AllerInfoRes(PageRessource c) {
        controleur = c;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent action;
        String v;

        Toast.makeText(view.getContext(), "Item à la position " +
                Integer.toString(i), Toast.LENGTH_SHORT);
        v = ((String) adapterView.getItemAtPosition(i)).toString();
        action = new Intent(controleur, DescriptionRes.class);
        action.putExtra("titre", v);
        action.putExtra("idRes",String.valueOf(controleur.listeRes.get(i).getId()));
        controleur.startActivity(action);


    }
}
