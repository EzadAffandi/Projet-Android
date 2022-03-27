package com.example.but05;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class AllerInfoUe3 implements AdapterView.OnItemClickListener {
    private SecondPage controleur;

    public AllerInfoUe3(SecondPage c) {
        controleur = c;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent action;
        String v;
        Integer id;

        Toast.makeText(view.getContext(), "Item à la position " +
                Integer.toString(i), Toast.LENGTH_SHORT);
        v = ((String) adapterView.getItemAtPosition(i)).toString();
        action = new Intent(controleur, PageRessource.class);
        action.putExtra("titre", v);
        action.putExtra("idUe",String.valueOf(controleur.listeUe3.get(i).getId()));
        controleur.startActivity(action);


    }
}
