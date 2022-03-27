package com.example.but05;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActionItemList implements AdapterView.OnItemClickListener {
    private MainActivity controleur;
    public ActionItemList (MainActivity c) {
        controleur= c;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent action;
        String v;
        Integer id;

        Toast.makeText(view.getContext(), "Item à la position " +
                Integer.toString(i), Toast.LENGTH_SHORT);
        v= ((String)adapterView.getItemAtPosition(i)).toString();

            id=controleur.listeBut.get(i).getId();
            action= new Intent(controleur, SecondActivity.class);
            action.putExtra("titre",v);
            action.putExtra("id",id.toString());
            controleur.startActivity(action);




    }
}
